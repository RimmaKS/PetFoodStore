package com.epam.tcfp.zooproject.database.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Pool {
	static Logger logger = LogManager.getLogger();
	private static final int GET_CONNECTION_MILLIS = 1000;
	private static final String PROPERTIES_PATH = "/dbconnection.properties";
	private static Pool INSTANCE;
	public static final String EXCEPTION_MSG = "Exception in connection pool.";
	public static final String EXCEPTION_MSG_CONNECTION = "Retrying to get connection.";
	public static final String ERROR = "SQL Error Type: ";
	public static final String ERROR_MESSAGE = "Error Message: ";
	private Map<Connection, Boolean> connections;
	private String URL;
	private String USER;
	private String PASSWORD;

	public static synchronized Pool getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Pool();
		}			
		return INSTANCE;
	}	

	private Pool() {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream(PROPERTIES_PATH));
			Class.forName(properties.getProperty("db.driver"));
			URL = properties.getProperty("db.url");
			USER = properties.getProperty("db.user");
			PASSWORD = properties.getProperty("db.password");

			int capacity = Integer.parseInt(properties.getProperty("db.poolsize"));
			connections = new HashMap<>(capacity);
			for (int i = 0; i < capacity; i++) {
				connections.put(createConnection(), true);
			}

		} catch (IOException | ClassNotFoundException | SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(ERROR + e.getClass().getName());
			logger.info(ERROR_MESSAGE + e.getMessage());
		}
	}

	private PoolConnection createConnection() throws SQLException {
		return new PoolConnection(DriverManager.getConnection(URL, USER, PASSWORD), this);
	}

	@SuppressWarnings("finally")
	public Connection getConnection() {
		Connection result = null;
		for (Map.Entry<Connection, Boolean> entry : connections.entrySet()) {
			if (entry.getValue()) {
				synchronized (this) {
					if (entry.getValue()) {
						Connection key = entry.getKey();
						connections.put(key, false);
						result = key;
					}
				}
			}
		}

		if (result == null) {
			try {
				Thread.sleep(GET_CONNECTION_MILLIS);
			} catch (InterruptedException e) {
				logger.log(Level.ERROR, EXCEPTION_MSG);
				logger.info(ERROR_MESSAGE + e.getMessage());
				logger.info(EXCEPTION_MSG_CONNECTION);
				Thread.currentThread().interrupt();
			} finally {
				return getConnection();
			}
		} else {
			return result;
		}
	}

	public void free(PoolConnection poolConnection) {
		connections.put(poolConnection, true);
	}
}
