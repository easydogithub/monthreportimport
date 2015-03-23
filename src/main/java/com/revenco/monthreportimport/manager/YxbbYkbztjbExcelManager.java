package com.revenco.monthreportimport.manager;

import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.revenco.monthreportimport.domain.YxbbYkbztjb;
import com.revenco.monthreportimport.domain.YxbbYkbztjbExcel;

public class YxbbYkbztjbExcelManager {

	public List<YxbbYkbztjb> getOneWorkbookYxbbYkbztjb(String path)
			throws Exception {
		YxbbYkbztjbExcel yxbbYkbztjbExcel = new YxbbYkbztjbExcel(path);
		return getOneWorkbookYxbbYkbztjb(yxbbYkbztjbExcel);
	}

	public List<YxbbYkbztjb> getOneWorkbookYxbbYkbztjb(
			YxbbYkbztjbExcel yxbbYkbztjbExcel) throws Exception {
		// final int beginSheet = 0;// 起始Sheet页，从0开始
		// final int endSheet = 9;// 结束Sheet页
		final int sheetNum = yxbbYkbztjbExcel.getSheetNum();
		List<YxbbYkbztjb> yxbbYkbztjbs = new ArrayList<YxbbYkbztjb>();
		// for (int i = beginSheet; i <= endSheet; i++) {
		for (int i = 0; i < sheetNum; i++) {
			try {
				yxbbYkbztjbs
						.addAll(getOneSheetYxbbYkbztjb(yxbbYkbztjbExcel, i));
			} catch (Exception e) {
				throw new Exception(yxbbYkbztjbExcel.getPath() + "文件\n第" + ++i
						+ "个sheet页，" + e.getMessage());
			}
		}
		return yxbbYkbztjbs;
	}

	public List<YxbbYkbztjb> getOneSheetYxbbYkbztjb(
			YxbbYkbztjbExcel yxbbYkbztjbExcel, int index) throws Exception {
		Workbook workbook = yxbbYkbztjbExcel.getWorkbook();
		Sheet sheet = workbook.getSheet(index);
		String gsName = convertGsdm(sheet.getName());
		final int beginRow = 11;// 起始行，从0开始
		int endRow = 52;// 结束行 //201411月全部增加110kV居民客户（大工业改类）结束行由50->51 ;201502月全部增加110kV非居民，结束行由51-52

		int lastRow = endRow;
		/* 201411月全部增加110kV居民客户（大工业改类）
		if ("060000".equals(gsName) || "060100".equals(gsName)) {// 由于贵阳新增了1户110kV居民客户（大工业改类）
			lastRow = lastRow + 1;
		}*/
		// System.out.println(gsName + ":" + lastRow);
		List<YxbbYkbztjb> yxbbYkbztjbs = new ArrayList<YxbbYkbztjb>();
		for (int i = beginRow; i <= lastRow; i++) {
			try {
				yxbbYkbztjbs.add(getYxbbYkbztjbFromExcel(yxbbYkbztjbExcel,
						index, i));
			} catch (Exception e) {
				throw new Exception("第" + ++i + "行," + e.getMessage());
			}
		}
		return yxbbYkbztjbs;
	}

	public YxbbYkbztjb getYxbbYkbztjbFromExcel(
			YxbbYkbztjbExcel yxbbYkbztjbExcel, int index, int row)
			throws Exception {
		int column = 0;
		YxbbYkbztjb yxbbYkbztjb = new YxbbYkbztjb();
		try {
			Workbook workbook = yxbbYkbztjbExcel.getWorkbook();
			Sheet sheet = workbook.getSheet(index);

			yxbbYkbztjb.setGsdm(convertGsdm(sheet.getName()));// 公司代码

			yxbbYkbztjb.setNy(yxbbYkbztjbExcel.getMonth());// 年月

			yxbbYkbztjb.setBbxh(yxbbYkbztjbExcel.getBbxh());// 报表序号

			String xhmc = sheet.getCell(0, row).getContents();
			
			String dydj = sheet.getCell(1, row).getContents();
			String ydlb_id = convertYdlb(xhmc);
			xhmc = convertXhmc(xhmc) + "(" + dydj + ")";// 序号名称拼接
			yxbbYkbztjb.setXhmc(xhmc);// 序号名称
			yxbbYkbztjb.setYdlb_id(ydlb_id);// 用电类别
			
			column++;
			yxbbYkbztjb.setDydj(convertDydj(dydj));// 电压等级
			
			column++;
			yxbbYkbztjb.setZzjrl(Double.valueOf(sheet.getCell(2, row)
					.getContents()));// 总装见容量

			column++;
			yxbbYkbztjb.setZzjhs(Long.valueOf(sheet.getCell(3, row)
					.getContents()));// 总装见户数

			column++;
			yxbbYkbztjb.setYjxzrl(Double.valueOf(sheet.getCell(4, row)
					.getContents()));// 永久新装申请报装容量

			column++;
			yxbbYkbztjb.setYjxzhs(Long.valueOf(sheet.getCell(5, row)
					.getContents()));// 永久新装申请报装户数

			column++;
			yxbbYkbztjb.setYjxzjdrl(Double.valueOf(sheet.getCell(6, row)
					.getContents()));// 永久新装接电容量

			column++;
			yxbbYkbztjb.setYjxzjdhs(Long.valueOf(sheet.getCell(7, row)
					.getContents()));// 永久新装接电户数

			column++;
			yxbbYkbztjb.setYjzrrl(Double.valueOf(sheet.getCell(8, row)
					.getContents()));// 永久增容申请报装容量

			column++;
			yxbbYkbztjb.setYjzrhs(Long.valueOf(sheet.getCell(9, row)
					.getContents()));// 永久增容申请报装户数

			column++;
			yxbbYkbztjb.setYjzrjdrl(Double.valueOf(sheet.getCell(10, row)
					.getContents()));// 永久增容申请接电容量

			column++;
			yxbbYkbztjb.setYjzrjdhs(Long.valueOf(sheet.getCell(11, row)
					.getContents()));// 永久增容申请接电户数

			column++;
			yxbbYkbztjb.setLsjdrl(Double.valueOf(sheet.getCell(12, row)
					.getContents()));// 临时接电接入容量

			column++;
			yxbbYkbztjb.setLsjdhs(Long.valueOf(sheet.getCell(13, row)
					.getContents()));// 临时接电接入户数

			column++;
			yxbbYkbztjb.setLsjdtcrl(Double.valueOf(sheet.getCell(14, row)
					.getContents()));// 临时接电退出容量

			column++;
			yxbbYkbztjb.setLsjdtchs(Long.valueOf(sheet.getCell(15, row)
					.getContents()));// 临时接电退出户数

			column++;
			yxbbYkbztjb.setXhrl(Double.valueOf(sheet.getCell(16, row)
					.getContents()));// 销户容量

			column++;
			yxbbYkbztjb.setXhhs(Long.valueOf(sheet.getCell(17, row)
					.getContents()));// 销户户数

			column++;
			yxbbYkbztjb.setJrrl(Double.valueOf(sheet.getCell(18, row)
					.getContents()));// 减容及恢复减容容量

			column++;
			yxbbYkbztjb.setJrhs(Long.valueOf(sheet.getCell(19, row)
					.getContents()));// 减容及恢复减容户数

			column++;
			yxbbYkbztjb.setJrfrrl(Double.valueOf(sheet.getCell(20, row)
					.getContents()));// 减容及恢复复容容量

			column++;
			yxbbYkbztjb.setJrfrhs(Long.valueOf(sheet.getCell(21, row)
					.getContents()));// 减容及恢复复容户数

			column++;
			yxbbYkbztjb.setZttrl(Double.valueOf(sheet.getCell(22, row)
					.getContents()));// 暂停及恢复停容容量
			
			column++;
			yxbbYkbztjb.setZtths(Long.valueOf(sheet.getCell(23, row)
					.getContents()));// 暂停及恢复停容户数
			
			column++;
			yxbbYkbztjb.setZtfrl(Double.valueOf(sheet.getCell(24, row)
					.getContents()));// 暂停及恢复复容容量
			
			column++;
			yxbbYkbztjb.setZtfhs(Long.valueOf(sheet.getCell(25, row)
					.getContents()));// 暂停及恢复复容户数

			column++;
			yxbbYkbztjb.setFbqrl(Double.valueOf(sheet.getCell(26, row)
					.getContents()));// 分并前容量
			
			column++;
			yxbbYkbztjb.setFbqhs(Long.valueOf(sheet.getCell(27, row)
					.getContents()));// 分并前户数
			
			column++;
			yxbbYkbztjb.setFbhrl(Double.valueOf(sheet.getCell(28, row)
					.getContents()));// 分并后容量
			
			column++;
			yxbbYkbztjb.setFbhhs(Long.valueOf(sheet.getCell(29, row)
					.getContents()));// 分并后户数

			column++;
			yxbbYkbztjb.setGyrl(Double.valueOf(sheet.getCell(30, row)
					.getContents()));// 改压或改类容量
			
			column++;
			yxbbYkbztjb.setGyhs(Long.valueOf(sheet.getCell(31, row)
					.getContents()));// 改压或改类户数

			column++;
			yxbbYkbztjb.setByzzrl(Double.valueOf(sheet.getCell(32, row)
					.getContents()));// 本月实增容量
			
			column++;
			yxbbYkbztjb.setByzzhs(Long.valueOf(sheet.getCell(33, row)
					.getContents()));// 本月实增户数

			column++;
			yxbbYkbztjb.setNljzzrl(Double.valueOf(sheet.getCell(34, row)
					.getContents()));// 年累计实增容量
			
			column++;
			yxbbYkbztjb.setNljzzhs(Long.valueOf(sheet.getCell(35, row)
					.getContents()));// 年累计实增户数
			
			column++;
			yxbbYkbztjb.setNljzzrl_tq(Double.valueOf(sheet.getCell(36, row)
					.getContents()));// 同期年累计实增容量
			
			column++;
			yxbbYkbztjb.setNljzzhs_tq(Long.valueOf(sheet.getCell(37, row)
					.getContents()));// 同期年累计实增户数

			yxbbYkbztjb.setJlzt_id("1");// 记录状态
		} catch (Exception e) {
			throw new Exception(this.getColumnName(column) + "列解析出错。" + e.getMessage());
		}
		return yxbbYkbztjb;
	}

	private String convertXhmc(String sourceXhmc) throws Exception {// xhmc-序号名称转换
		String xhmc = "";
		switch (sourceXhmc) {
		case "一、按电价类别":
			xhmc = "一、按电价类别";
			break;
		case "大工业":
			xhmc = "1、大工业";
			break;
		case "非普工业":
			xhmc = "2、非普工业";
			break;
		case "商业":
			xhmc = "3、商业";
			break;
		case "居民":
			xhmc = "4、居民";
			break;
		case "非居民":
			xhmc = "5、非居民";
			break;
		case "农业生产":
			xhmc = "6、农业生产";
			break;
		case "农业排灌":
			xhmc = "7、农业排灌";
			break;
		case "趸售":
			xhmc = "8、趸售";
			break;
		default:
			xhmc = sourceXhmc;
			throw new Exception("序号名称无法解析：" + xhmc);
		}

		return xhmc;
	}

	private String convertDydj(String sourceDydj) throws Exception {
		String dydj = "";
		switch (sourceDydj) {
		case "10kV":
			dydj = "2";
			break;
		case "20kV":
			dydj = "3";
			break;
		case "35kV":
			dydj = "4";
			break;
		case "110kV":
			dydj = "5";
			break;
		case "220kV":
			dydj = "6";
			break;
		case "220/380V":
			dydj = "1";
			break;
		case "小计":
		case "合计":
			dydj = "000";
			break;
		default:
			dydj = sourceDydj;
			throw new Exception("电压等级无法解析：" + dydj);
		}
		return dydj;
	}

	private String convertYdlb(String sourceYdlb) throws Exception {
		String ydlb = "";
		switch (sourceYdlb) {
		case "一、按电价类别":
			ydlb = "000";
			break;
		case "大工业":
			ydlb = "100";
			break;
		case "非普工业":
			ydlb = "200";
			break;
		case "商业":
			ydlb = "300";
			break;
		case "居民":
			ydlb = "500";
			break;
		case "非居民":
			ydlb = "600";
			break;
		case "农业生产":
			ydlb = "700";
			break;
		case "农业排灌":
			ydlb = "800";
			break;
		case "趸售":
			ydlb = "400";
			break;
		default:
			ydlb = sourceYdlb;
			throw new Exception("用电类别无法解析：" + ydlb);
		}

		return ydlb;
	}

	private String convertGsdm(String sourceGsdm) throws Exception {
		String gsdm = "";
		switch (sourceGsdm) {
		case "公司":
			gsdm = "060000";
			break;
		case "贵阳":
			gsdm = "060100";
			break;
		case "遵义":
			gsdm = "060300";
			break;
		case "六盘水":
			gsdm = "060200";
			break;
		case "安顺":
			gsdm = "060400";
			break;
		case "凯里":
			gsdm = "060800";
			break;
		case "都匀":
			gsdm = "060900";
			break;
		case "兴义":
			gsdm = "060600";
			break;
		case "毕节":
			gsdm = "060700";
			break;
		case "铜仁":
			gsdm = "060500";
			break;
		default:
			gsdm = sourceGsdm;
			throw new Exception("公司代码无法解析：" + gsdm);
		}
		return gsdm;
	}

	private String getColumnName(int col) {
		int mod = 0;
		int div = 0;
		char c;
		String colName = "";
		if (col >= 26) {
			div = col / 26 - 1;
			mod = col % 26;
			c = (char) (mod + 65);
			colName = String.valueOf(c) + colName;
			colName = this.getColumnName(div) + colName;

		} else {
			c = (char) (col + 65);
			colName = String.valueOf(c) + colName;
		}
		return colName;
	}

}
