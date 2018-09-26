package com.qcap.core.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Doc2pdf {

	   public  static  String makeWord(Map dataMap,String basePath) throws Exception{		
		   try {
		        /** 初始化配置文件 **/
		        Configuration configuration = new Configuration();
		        String fileDirectory = basePath;
		        /** 加载文件 **/
		        configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
		        /** 加载模板 **/
		        Template template = configuration.getTemplate("document.xml");
	
		        /** 指定输出word文件的路径 **/
		        String outFilePath = basePath+"data.xml";
		        File docFile = new File(outFilePath);
		        FileOutputStream fos = new FileOutputStream(docFile);
		        Writer out = new BufferedWriter(new OutputStreamWriter(fos),10240);
		        template.process(dataMap,out);
		        if(out != null){
		            out.close();
		        }
	        
	            ZipInputStream zipInputStream = ZipUtils.wrapZipInputStream(new FileInputStream(new File(basePath+"esp_contract.zip")));
	            ZipOutputStream zipOutputStream = ZipUtils.wrapZipOutputStream(new FileOutputStream(new File(basePath+"esp_contract.docx")));
	            String itemname = "word/document.xml";
	            ZipUtils.replaceItem(zipInputStream, zipOutputStream, itemname, new FileInputStream(new File(basePath+"data.xml")));

	            XWPFDocument document=new XWPFDocument(new FileInputStream(new File(basePath+"esp_contract.docx")));
	            File outFile=new File(basePath+"esp_contract.pdf");
	            outFile.getParentFile().mkdirs();
	            OutputStream out1 = new FileOutputStream(outFile);
	            PdfOptions options= PdfOptions.create();  //gb2312
	            PdfConverter.getInstance().convert(document,out1,options);

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "500";
	        }
		   return "200";
	    }
	   
	   public  static  Map<String, Object> makeWordNew(Map dataMap,String basePath,String fileName) throws Exception{		
		   Map<String, Object>result=new HashMap<>();
		   try {
		        /** 初始化配置文件 **/
		        Configuration configuration = new Configuration();
		        String fileDirectory = basePath;
		        /** 加载文件 **/
		        configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
		        /** 加载模板 **/
		        Template template = configuration.getTemplate("document.xml");
	
		        /** 指定输出word文件的路径 **/
		        String outFilePath = basePath+"data.xml";
		        File docFile = new File(outFilePath);
		        FileOutputStream fos = new FileOutputStream(docFile);
		        Writer out = new BufferedWriter(new OutputStreamWriter(fos),10240);
		        template.process(dataMap,out);
		        if(out != null){
		            out.close();
		        }
	        
	            ZipInputStream zipInputStream = ZipUtils.wrapZipInputStream(new FileInputStream(new File(basePath+fileName+".zip")));
	            ZipOutputStream zipOutputStream = ZipUtils.wrapZipOutputStream(new FileOutputStream(new File(basePath+fileName+".docx")));
	            String itemname = "word/document.xml";
	            ZipUtils.replaceItem(zipInputStream, zipOutputStream, itemname, new FileInputStream(new File(basePath+"data.xml")));

	            XWPFDocument document=new XWPFDocument(new FileInputStream(new File(basePath+fileName+".docx")));
	            File outFile=new File(basePath+fileName+".pdf");
	            outFile.getParentFile().mkdirs();
	            OutputStream out1 = new FileOutputStream(outFile);
	            PdfOptions options= PdfOptions.create();  //gb2312
	            PdfConverter.getInstance().convert(document,out1,options);
	            result.put("code", "200");
	            result.put("fliePath", basePath+fileName+".pdf");
	        } catch (Exception e) {
	            e.printStackTrace();
	            result.put("code", "500");
	        }
		   return result;
	    }

}
