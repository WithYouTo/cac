package com.qcap.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Readxls {
	private Workbook readBook;
	private int currIndex;
	private ArrayList<String> alLineContent;
	private Cell cell;
	private int firstDataCount;
	private boolean isFirst;
	private Sheet sheet;
	private WritableWorkbook writeBook;
	private WritableSheet writeSheet;
	private Label label;

	/**
	 * 初始化xls文件的读取参数
	 * 
	 * @param filePath
	 *            xls文件路径
	 * 
	 */
	public void read(String filePath) throws Exception {
		try {
			// 创建xls工作表对象
			readBook = Workbook.getWorkbook(new File(filePath));
			// 只读取第一个工作表中的内容
			sheet = readBook.getSheet(0);
			currIndex = 0;
			isFirst = true;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 
	 * @param is
	 *            初始化xls文件的读取参数
	 * @throws Exception
	 */

	public void read(InputStream is) throws Exception {
		try {
			// 创建xls工作表对象
			readBook = Workbook.getWorkbook(is);
			// 只读取第一个工作表中的内容
			sheet = readBook.getSheet(0);
			currIndex = 0;
			isFirst = true;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 读取一行xls文件中的数据
	 * 
	 * @return 包含数据的String列表
	 */
	public ArrayList<String> readLine() {
		alLineContent = new ArrayList<String>();
		int i = 0;
		String content = null;
		while (true) {
			if (!isFirst && i >= firstDataCount) {
				break;
			}
			try {
				// 读取一个单元格的数据
				cell = sheet.getCell(i, currIndex);
				i++;
			} catch (Exception e) {
				// 没有数据可读取
				if (i == 0)
					return null;
				// 读取首行
				if (isFirst) {
					firstDataCount = i;
					isFirst = false;
					break;
				} else {
					content = "";
				}
			}
			content = cell.getContents();
			// 首行存在空值时认为提取数据完毕
			if (isFirst && "".equals(content)) {
				firstDataCount = i - 1;
				isFirst = false;
				break;
			}
			alLineContent.add(content);
		}
		currIndex++;
		return alLineContent;
	}
	

	/**
	 * 读取xls文件中的所有可读取数据
	 */
	public ArrayList<ArrayList<String>> readAll() {
		ArrayList<ArrayList<String>> alAllData = new ArrayList<ArrayList<String>>();
		ArrayList<String> data = null;
		while (true) {
			data = this.readLine();
			if (data == null) {
				break;
			}
			alAllData.add(data);
		}
		return alAllData;
	}

	public void closeRead() {
		readBook.close();
	}

	// 出去excel文件中常有的空格
	public static String returnNoSpaceStr(String str) {
		String[] string = str.split(" ");// 去掉可能的空格
		String ss = null;
		if (string[0].equals("") || string[0] == null) {
			ss = string[1];
		} else {
			ss = string[0];
		}
		return ss;
	}
	
	public ArrayList<ArrayList<String>> ReadComplexXls(InputStream ins,int dataStartRow) throws Exception{
		ArrayList<ArrayList<String>>allList=new ArrayList<ArrayList<String>>();
		try {
			//读取的excel数据装入List中，第一条数据对应List中index为0的元素，以此类推。
			dataStartRow=dataStartRow-1;
			readBook=Workbook.getWorkbook(ins);
			sheet=readBook.getSheet(0);
			Range[]ran=sheet.getMergedCells();
			for(int i=0;i<sheet.getRows();i++) {
				ArrayList<String>lineList=new ArrayList<String>();
				for(int j=0;j<sheet.getColumns();j++) {
					String str=null;
					str=sheet.getCell(j, i).getContents();
					for(Range r:ran) {
						if(i>r.getTopLeft().getRow() && i<r.getBottomRight().getRow()
								&& j>r.getTopLeft().getColumn() && j<r.getBottomRight().getColumn()) {
							str=sheet.getCell(r.getTopLeft().getColumn(), r.getBottomRight().getColumn()).getContents();
						}
					}
					//装数据行
					if(i>=dataStartRow) {
						lineList.add(str);
					}
					//开始数据行之后，首个单元格为空时，则不再读取单元格，并返回已经读取的数据
					if(i>dataStartRow && j==0 && (str==null || "".equals(str))) {
						System.out.println("总共有："+allList.size()+"条数据");
						return allList;
					}
				}
				
				//装载数据
				if(!lineList.isEmpty()) {
					allList.add(lineList);
				}
				
			}
			System.out.println("总共有："+allList.size()+"条数据");
		} catch (Exception e) {
			throw new Exception(e);
			
		}
		return allList;

	}
	
	public ArrayList<ArrayList<String>> ReadComplexXls(String filePath,int dataStartRow) throws Exception{
		ArrayList<ArrayList<String>>allList=new ArrayList<ArrayList<String>>();
		try {
			if(StringUtils.isBlank(filePath)) {
				return allList;
			}
			InputStream in=new FileInputStream(new File(filePath));
			allList=this.ReadComplexXls(in,dataStartRow);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return allList;
	}
}
