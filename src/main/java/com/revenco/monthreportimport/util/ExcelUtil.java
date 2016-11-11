package com.revenco.monthreportimport.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {

	public static Workbook buildWorkbook(String path) throws Exception {
		try {
			File file = new File(path);
			String extName = getExtensionName(file.getName());
			Workbook workbook;
			FileInputStream fis = new FileInputStream(file);
			
			if("xlsx".equalsIgnoreCase(extName)){
				workbook = new XSSFWorkbook(file);
			}else{
				workbook = new HSSFWorkbook(fis);
			}
			//Workbook workbook = Workbook.getWorkbook(new File(path));
			return workbook;
		} catch (Throwable ex) {
			System.err.println("Initial Workbook creation failed." + ex);
			throw new Exception("初始化工作表失败,请确定文件路径是否正确：\n" + path);
		}
	}

	
	public static String getExtensionName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return filename.substring(dot + 1);   
            }   
        }   
        return filename;   
    }   
}
