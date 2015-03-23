package com.revenco.monthreportimport.util;

import java.util.ResourceBundle;

public class ResourceBundleUtil {
	
	private static final String baseName = "MessagesBundle";
	
	public static ResourceBundle getDefaultBundle(){
		return ResourceBundle.getBundle(ResourceBundleUtil.baseName);
	}

}
