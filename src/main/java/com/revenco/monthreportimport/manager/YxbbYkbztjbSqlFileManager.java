package com.revenco.monthreportimport.manager;

import java.io.FileOutputStream;
import java.io.IOException;

import com.revenco.monthreportimport.domain.YxbbYkbztjbSqlFile;

public class YxbbYkbztjbSqlFileManager {

	public void storeYxbbYkbztjbAsSqlFile(YxbbYkbztjbSqlFile yxbbYkbztjbSqlFile)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(
				yxbbYkbztjbSqlFile.getFile());
		byte[] b = yxbbYkbztjbSqlFile.getContent().getBytes();
		fos.write(b);
		fos.flush();
		fos.close();
	}

	public void storeYxbbYkbztjbAsSqlFile(String path, String content)
			throws IOException {
		YxbbYkbztjbSqlFile yxbbYkbztjbSqlFile = new YxbbYkbztjbSqlFile(path,
				content);
		storeYxbbYkbztjbAsSqlFile(yxbbYkbztjbSqlFile);
	}
}
