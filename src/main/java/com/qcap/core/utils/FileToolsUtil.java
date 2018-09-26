package com.qcap.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileToolsUtil {
	public static boolean isExist(String path) throws InterruptedException {
		String[] paths = path.split("/");
		StringBuffer fullPath = new StringBuffer();
		for (int i = 0; i < paths.length; i++) {
			fullPath.append(paths[i]).append("/");
			File file = new File(fullPath.toString());
			if (paths.length - 1 != i) {// 判断path到文件名时，无须继续创建文件夹！
				if (!file.exists()) {
					file.mkdir();
					System.out.println("创建目录为：" + fullPath.toString());
					Thread.sleep(500);
				}
			}
		}
		File file = new File(fullPath.toString());// 目录全路径
		if (!file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public static String readTxtFile(String filePath) {
		StringBuffer content = new StringBuffer();
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), CommConst.utf8);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					content.append(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的秘钥");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return content.toString();
	}
}
