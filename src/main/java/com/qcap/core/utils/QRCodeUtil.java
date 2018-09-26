package com.qcap.core.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class QRCodeUtil {

	/***
	 * 
	 * @Title: zxingCodeCreate
	 * @Description: TODO
	 * @param text
	 *            内容
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param outPutPath
	 *            图片路径
	 * @return: void
	 * @throws Exception
	 */
	public static String createQRCode(String content, int width, int height, String basePath,String outPutPath) throws Exception {
		Map hints = new HashMap();
		try {
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			File path = new File(basePath);
			del(path);
			
			File qrCodeFile = new File(outPutPath);
			if (FileToolsUtil.isExist(outPutPath)) {
				MatrixToImageWriter.writeToFile(bitMatrix, "jpg", qrCodeFile);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "500";
		}
		return "200";
	}
	
	/**
	 * 删除jpg文件
	 * @param file
	 */
	public static void del(File file) {
        if (file.exists() && file.isDirectory()) {
            File[] f = file.listFiles();
            for (File fd : f) {
                if (fd.isFile() && fd.getName().toLowerCase().endsWith(".jpg")) {
                    fd.delete();
                } else {
                    del(fd);
                }
            }
        }
    }

	public static Object zxingCodeAnalyze(String analyzePath) throws Exception {
		MultiFormatReader formatReader = new MultiFormatReader();
		File file = new File(analyzePath);
		if (!file.exists()) {
			throw new RuntimeException("文件路径不存在：" + analyzePath);
		}
		BufferedImage image = ImageIO.read(file);

		LuminanceSource source = new BufferedImageLuminanceSource(image);
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		Result result = formatReader.decode(binaryBitmap, hints);

		System.out.println("result = " + result.toString());
		System.out.println("resultFormat = " + result.getBarcodeFormat());
		System.out.println("resultText = " + result.getText());
		return result;
	}

	/**
	 * 最终调用该方法生成二维码图片
	 * 
	 * @param url
	 *            要生成二维码的url
	 * @param imgPath
	 *            二维码生成的绝对路径
	 * @param logoPath
	 *            二维码中间logo绝对地址
	 * @throws Exception
	 */
	public static void zxingCodeCreate(String content, int width, int height, String outPutPath, String logoPath)
			throws Exception {
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		File qrCodeFile = new File(outPutPath);

		if (FileToolsUtil.isExist(outPutPath)) {
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", qrCodeFile, logoPath);
		}
	}

	public static void main(String[] args) {
		try {
			String content = "https://www.baidu.com";
			String path = "C:/Users/ds/Desktop/";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
