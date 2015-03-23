package com.revenco.monthreportimport.util;

import java.io.File;

import jxl.Workbook;

public class ExcelUtil {

	public static Workbook buildWorkbook(String path) throws Exception {
		try {
			Workbook workbook = Workbook.getWorkbook(new File(path));
			return workbook;
		} catch (Throwable ex) {
			System.err.println("Initial Workbook creation failed." + ex);
			throw new Exception("初始化工作表失败,请确定文件路径是否正确：\n" + path);
		}
	}

}
