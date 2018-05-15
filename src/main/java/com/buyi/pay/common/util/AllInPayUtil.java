package com.buyi.pay.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.buyi.common.log.Log;
import com.buyi.pay.vo.channel.allinpay.AllInPayReqXML;

import lombok.extern.slf4j.Slf4j;

/**
 * 通联支付工具类
 *
 * @author buyi
 * @date 2018年5月14日下午10:02:01
 * @since 1.0.0
 */
@Slf4j
public class AllInPayUtil {

	/** 私钥本地缓存 */
	private static final Map<String, PrivateKey> privateKeyMap = new HashMap<>();

	/** 公钥本地缓存 */
	private static final Map<String, PublicKey> publicKeyMap = new HashMap<>();

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * 获取私钥
	 * 
	 * @author buyi
	 * @date 2017-12-26 15:31:17
	 * @since v1.0.0
	 * @param merchantCode
	 * @param path
	 * @return
	 * @throws KeyStoreException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws UnrecoverableKeyException
	 */
	public static PrivateKey getPrivateKey(String merchantCode, String path, String password)
			throws KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableKeyException {
		if (privateKeyMap.containsKey(merchantCode)) {
			return privateKeyMap.get(merchantCode);
		}

		return initPrivateKey(merchantCode, path, password);
	}

	/**
	 * 初始化
	 * 
	 * @author buyi
	 * @date 2017-12-26 15:31:41
	 * @since v1.0.0
	 * @param merchantCode
	 * @param path
	 * @return
	 * @throws KeyStoreException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws UnrecoverableKeyException
	 */
	private synchronized static PrivateKey initPrivateKey(String merchantCode, String path, String password)
			throws KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableKeyException {
		final String op = "AllInPayUtil.initPrivateKey";
		if (privateKeyMap.containsKey(merchantCode)) {
			return privateKeyMap.get(merchantCode);
		}

		// 设置安全提供者
		BouncyCastleProvider provider = new BouncyCastleProvider();
		Security.addProvider(provider);

		KeyStore ks = KeyStore.getInstance("PKCS12", provider);
		File file = new File(path);
		if (!file.exists()) {
			// 如果文件不存在
			log.error(Log.newInstance(op,"初始化通联私钥，私钥文件不存在").kv("path", path).toString());
			throw new FileNotFoundException("文件不存在" + path);
		}

		try (FileInputStream fileInputStream = new FileInputStream(path)) {
			ks.load(fileInputStream, password.toCharArray());
		}

		Enumeration<String> myEnum = ks.aliases();

		String keyAlias = null;
		PrivateKey prikey = null;
		/* IBM JDK必须使用While循环取最后一个别名，才能得到个人私钥别名 */
		while (myEnum.hasMoreElements()) {
			keyAlias = (String) myEnum.nextElement();
			if (ks.isKeyEntry(keyAlias)) {
				prikey = (PrivateKey) ks.getKey(keyAlias, password.toCharArray());
				break;
			}
		}

		if (prikey != null) {
			privateKeyMap.put(merchantCode, prikey);
		}

		return prikey;
	}

	/**
	 * 签名
	 * 
	 * @author buyi
	 * @date 2017-12-26 16:35:38
	 * @since v1.0.0
	 * @param merchantCode
	 * @param allInPayReqXML
	 * @param path
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String sign(String merchantCode, AllInPayReqXML allInPayReqXML, String path, String password)
			throws Exception {
		final String SIGN_STR = "<SIGNED_MSG></SIGNED_MSG>";
		String xml = allInPayReqXML.buildXML();

		String strMsg = xml.replaceAll(SIGN_STR, "");

		String sign = sign(merchantCode, strMsg, path, password);

		String result = xml.replaceAll(SIGN_STR, "<SIGNED_MSG>" + sign + "</SIGNED_MSG>");

		return result;
	}

	/**
	 * 签名
	 * 
	 * @author buyi
	 * @date 2017-12-26 16:35:47
	 * @since v1.0.0
	 * @param merchantCode
	 * @param xml
	 * @param path
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String sign(String merchantCode, String xml, String path, String password) throws Exception {
		PrivateKey privateKey = getPrivateKey(merchantCode, path, password);

		if (privateKey == null) {
			throw new IllegalArgumentException("privateKey为空");
		}

		Signature sign = Signature.getInstance("SHA1withRSA", "BC");

		sign.initSign(privateKey);
		sign.update(xml.getBytes("GBK"));
		byte signed[] = sign.sign();
		byte sign_asc[] = new byte[signed.length * 2];
		Hex2Ascii(signed.length, signed, sign_asc);

		return new String(sign_asc);
	}

	public static boolean verfiySign(String merchantCode, String xml, String path) throws Exception {
		final String op = "AllInPayUtil.verfiySign";

		int iStart = xml.indexOf("<SIGNED_MSG>");
		if (iStart == -1) {
			log.error(Log.newInstance(op,"XML报文中不存在<SIGNED_MSG>").kv("merchantCode", merchantCode).kv("通联响应报文", xml)
					.toString());
			return false;
		}
		int end = xml.indexOf("</SIGNED_MSG>");
		if (end == -1) {
			log.error(Log.newInstance(op,"XML报文中不存在</SIGNED_MSG>").kv("merchantCode", merchantCode).kv("通联响应报文", xml)
					.toString());
			return false;
		}

		// 截取签名串
		String signedMsg = xml.substring(iStart + 12, end);

		// 取到没有signedMsg的xml报文
		String strMsg = xml.substring(0, iStart) + xml.substring(end + 13);

		PublicKey publicKey = getPublicKey(merchantCode, path);
		if (publicKey == null) {
			throw new IllegalArgumentException("publicKey为空");
		}

		Signature sign = Signature.getInstance("SHA1withRSA", "BC");
		sign.initVerify(publicKey);
		byte signeddata[] = new byte[signedMsg.length() / 2];
		Ascii2Hex(signedMsg.length(), signedMsg.getBytes("GBK"), signeddata);
		sign.update(strMsg.getBytes("GBK"));

		return sign.verify(signeddata);
	}

	/**
	 * 获取公钥
	 * 
	 * @author buyi
	 * @date 2017-12-26 16:50:23
	 * @since v1.0.0
	 * @param merchantCode
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws CertificateException
	 */
	private static PublicKey getPublicKey(String merchantCode, String path) throws CertificateException, IOException {
		if (publicKeyMap.containsKey(merchantCode)) {
			return publicKeyMap.get(merchantCode);
		}

		return initPublicKey(merchantCode, path);
	}

	/**
	 * 初始化公钥
	 * 
	 * @author buyi
	 * @date 2017-12-26 16:50:11
	 * @since v1.0.0
	 * @param merchantCode
	 * @param path
	 * @return
	 * @throws CertificateException
	 * @throws IOException
	 */
	private synchronized static PublicKey initPublicKey(String merchantCode, String path)
			throws CertificateException, IOException {
		final String op = "AllInPayUtil.initPublicKey";
		if (publicKeyMap.containsKey(merchantCode)) {
			return publicKeyMap.get(merchantCode);
		}

		BouncyCastleProvider provider = new BouncyCastleProvider();
		CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", provider);

		File file = new File(path);
		if (!file.exists()) {
			// 如果文件不存在
			log.error(Log.newInstance(op,"初始化通联私钥，私钥文件不存在").kv("path", path).toString());
			throw new FileNotFoundException("文件不存在" + path);
		}

		PublicKey publicKey = null;
		try (FileInputStream fileInputStream = new FileInputStream(path)) {
			Certificate certificate = certificateFactory.generateCertificate(fileInputStream);
			publicKey = certificate.getPublicKey();
		}

		if (publicKey != null) {
			publicKeyMap.put(merchantCode, publicKey);
		}

		return publicKey;
	}

	/**
	 * 将十六进制数据转换成ASCII字符串
	 * 
	 * @param len
	 *            十六进制数据长度
	 * @param data_in
	 *            待转换的十六进制数据
	 * @param data_out
	 *            已转换的ASCII字符串
	 */
	private static void Hex2Ascii(int len, byte data_in[], byte data_out[]) {
		byte temp1[] = new byte[1];
		byte temp2[] = new byte[1];
		for (int i = 0, j = 0; i < len; i++) {
			temp1[0] = data_in[i];
			temp1[0] = (byte) (temp1[0] >>> 4);
			temp1[0] = (byte) (temp1[0] & 0x0f);
			temp2[0] = data_in[i];
			temp2[0] = (byte) (temp2[0] & 0x0f);
			if (temp1[0] >= 0x00 && temp1[0] <= 0x09) {
				(data_out[j]) = (byte) (temp1[0] + '0');
			} else if (temp1[0] >= 0x0a && temp1[0] <= 0x0f) {
				(data_out[j]) = (byte) (temp1[0] + 0x57);
			}

			if (temp2[0] >= 0x00 && temp2[0] <= 0x09) {
				(data_out[j + 1]) = (byte) (temp2[0] + '0');
			} else if (temp2[0] >= 0x0a && temp2[0] <= 0x0f) {
				(data_out[j + 1]) = (byte) (temp2[0] + 0x57);
			}
			j += 2;
		}
	}

	/**
	 * 将ASCII字符串转换成十六进制数据
	 * 
	 * @param len
	 *            ASCII字符串长度
	 * @param data_in
	 *            待转换的ASCII字符串
	 * @param data_out
	 *            已转换的十六进制数据
	 */
	private static void Ascii2Hex(int len, byte data_in[], byte data_out[]) {
		byte temp1[] = new byte[1];
		byte temp2[] = new byte[1];
		for (int i = 0, j = 0; i < len; j++) {
			temp1[0] = data_in[i];
			temp2[0] = data_in[i + 1];
			if (temp1[0] >= '0' && temp1[0] <= '9') {
				temp1[0] -= '0';
				temp1[0] = (byte) (temp1[0] << 4);

				temp1[0] = (byte) (temp1[0] & 0xf0);

			} else if (temp1[0] >= 'a' && temp1[0] <= 'f') {
				temp1[0] -= 0x57;
				temp1[0] = (byte) (temp1[0] << 4);
				temp1[0] = (byte) (temp1[0] & 0xf0);
			}

			if (temp2[0] >= '0' && temp2[0] <= '9') {
				temp2[0] -= '0';

				temp2[0] = (byte) (temp2[0] & 0x0f);

			} else if (temp2[0] >= 'a' && temp2[0] <= 'f') {
				temp2[0] -= 0x57;

				temp2[0] = (byte) (temp2[0] & 0x0f);
			}
			data_out[j] = (byte) (temp1[0] | temp2[0]);

			i += 2;
		}

	}
}
