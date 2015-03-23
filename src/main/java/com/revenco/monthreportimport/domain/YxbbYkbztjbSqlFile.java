package com.revenco.monthreportimport.domain;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;

public class YxbbYkbztjbSqlFile {
	private String path;
	private String content;
	private File file;

	public YxbbYkbztjbSqlFile(String path, String content)
			throws FileAlreadyExistsException {
		super();
		this.path = path;
		this.content = content;
		createFile(path);
	}

	private void createFile(String filePath) throws FileAlreadyExistsException {
		File file = new File(filePath);
		if (file.exists()) {
			throw new FileAlreadyExistsException(filePath);
		} else {
			this.file = file;
		}
	}

	public String getPath() {
		return path;
	}

	public String getContent() {
		return content;
	}

	public File getFile() {
		return file;
	}

}
