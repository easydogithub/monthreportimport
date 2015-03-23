package com.revenco.monthreportimport;

import java.io.IOException;

import javax.swing.SwingUtilities;

import com.revenco.monthreportimport.ui.swing.MonthReportImportSwing;

public class App {
	public static void main(String[] args) throws IOException {

		/*if (args.length < 1) {
			System.err.println("please input excel file full path.");
			System.exit(0);
		}
		YxbbYkbztjbManager mgr = new YxbbYkbztjbManager();
		YxbbYkbztjbExcelManager excelmgr = new YxbbYkbztjbExcelManager();
		YxbbYkbztjbSqlFileManager sqlmgr = new YxbbYkbztjbSqlFileManager();
		String content = "";
		for (String path : args) { //
			content += mgr.getYxbbYkbztjbAsSqls(excelmgr
					.getOneWorkbookYxbbYkbztjb(path));
		}
		sqlmgr.storeYxbbYkbztjbAsSqlFile("F:\\result.sql", content);
		System.out.println("Success!");
		System.exit(0);*/

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MonthReportImportSwing().createAndShowGUI();
			}
		});
	}
}
