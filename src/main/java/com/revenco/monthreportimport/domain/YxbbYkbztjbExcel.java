package com.revenco.monthreportimport.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import jxl.Workbook;

import com.revenco.monthreportimport.util.ExcelUtil;

public class YxbbYkbztjbExcel {
	private String path;
	private long month;
	private String bbxh;
	private Workbook workbook;
	private int sheetNum;

	public YxbbYkbztjbExcel(String path) throws Exception {
		super();
		this.path = path;
		this.month = parseMonth();
		this.bbxh = parseBbxh();
		this.workbook = ExcelUtil.buildWorkbook(path);
		this.sheetNum = this.workbook.getNumberOfSheets();
	}

	private String getFileName() {
		int beginIndex = path.lastIndexOf("\\");
		String fileName = path.substring(beginIndex + 1);
		return fileName;
	}

	private String parseBbxh() throws Exception {
		String fileName = getFileName();
		if (null != fileName) {
			int index = fileName.indexOf("母公司");
			if (index != -1) {
				return "01";
			}

			index = fileName.indexOf("全资资产");
			if (index != -1) {
				return "02";
			}

			index = fileName.replaceAll("贵州电网公司", "").indexOf("电网");
			if (index != -1) {
				return "03";
			}
		}
		// System.err.println("bbxh not found.");
		throw new Exception("文件:" + fileName
				+ "\n中的文件名,不包含：母公司、全资资产、电网关键字，无法解析。");
	}

	private long parseMonth() throws Exception {
		String fileName = getFileName();
		long month = Long.valueOf(fileName.replaceAll("\\D", ""));
		String monstr = String.valueOf(month);
		int len = monstr.length();
		if (len != 6 && len != 5)
			throw new Exception("文件:" + fileName + "\n中的年月分析出的结果为" + monstr
					+ "，不符合yyyyMM的格式要求。");
		if (len == 5)
			monstr = monstr.substring(0, 4) + "0" + monstr.substring(4);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		sdf.setLenient(false);
		try {
			sdf.parse(monstr);
		} catch (ParseException e) {
			throw new Exception("文件:" + fileName + "\n中的年月分析出的结果为" + monstr
					+ "，不是正确的年月。");
		}

		month = Long.valueOf(monstr);
		return month;
	}

	public String getPath() {
		return path;
	}

	public long getMonth() {
		return month;
	}

	public String getBbxh() {
		return bbxh;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public int getSheetNum() {
		return sheetNum;
	}

}
