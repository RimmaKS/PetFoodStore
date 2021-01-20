package com.epam.tcfp.zooproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.tcfp.zooproject.entity.OrderItem;
import com.epam.tcfp.zooproject.database.connection.*;

public class OrderItemDAO {
	static Logger logger = LogManager.getLogger();
	public static final String SQL_ERROR = "SQL Error Type: ";
	public static final String SQL_ERROR_MESSAGE = "Error Message: ";
	public static final String SQL_ERROR_CODE = "Error Code: ";
	public static final String SQL_STATE = "SQL State: ";
	public static final String EXCEPTION_MSG = "SQL Exception!!!";	
	
	private static final String QUERY_INSERT = "INSERT INTO `order_item` (`order`, `product`, `price`, `quantity`) VALUES (?, ?, ?, ?);";		

	public int insertOrderItem(OrderItem orderItem) {
		int result = 0;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_INSERT)) {
			statement.setLong(1, orderItem.getOrderId());
			statement.setLong(2, orderItem.getProductId());
			statement.setBigDecimal(3, orderItem.getPrice());
			statement.setLong(4, orderItem.getQuantity());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG); 
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return result; 
	}
}