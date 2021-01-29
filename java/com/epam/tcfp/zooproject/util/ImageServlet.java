package com.epam.tcfp.zooproject.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger();
	public static final String EXCEPTION_MSG = "Exception on Image Servlet!";
	public static final String ERROR_TYPE = "Error Type: ";
	public static final String ERROR_MESSAGE = "Error Message: ";
	public static final String IMAGE_LOCATION = "ImageLocation";
	public static final String IMAGE_NAME = "imageName";	

	public ImageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileLocation = getServletContext().getInitParameter(IMAGE_LOCATION);
		String fileName = request.getParameter(IMAGE_NAME);

		byte[] result = FileConvertUtil.fileToByteArray(fileLocation + fileName);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(result);
		int arrayLength = result.length;

		response.setContentType("image/gif");
		response.setContentLength(arrayLength);

		try {
			ServletOutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, length);
			}
			inputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(ERROR_TYPE + e.getClass().getName());
			logger.info(ERROR_MESSAGE + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
