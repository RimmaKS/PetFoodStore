package com.epam.tcfp.zooproject.util;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileConvertUtil {
	static Logger logger = LogManager.getLogger();
	public static final String EXCEPTION_MSG = "Exception on File Convert Util!!";
	public static final String ERROR_TYPE = "Error Type: ";
	public static final String ERROR_MESSAGE = "Error Message: ";
	
	public FileConvertUtil() {		
	}

	public static byte[] fileToByteArray(String url) {
		byte[] result = null;
		File file = new File(url);
		try {
			InputStream input = new FileInputStream(file);
			result = IOUtils.toByteArray(input);			
		} catch (IOException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(ERROR_TYPE + e.getClass().getName());
			logger.info(ERROR_MESSAGE + e.getMessage());
		}
		return result;
	}
}