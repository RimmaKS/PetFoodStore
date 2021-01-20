package com.epam.tcfp.zooproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.tcfp.zooproject.entity.OrderInfo;
import com.epam.tcfp.zooproject.database.connection.*;

public class OrderInfoDAO {
	static Logger logger = LogManager.getLogger();
	public static final String SQL_ERROR = "SQL Error Type: ";
	public static final String SQL_ERROR_MESSAGE = "Error Message: ";
	public static final String SQL_ERROR_CODE = "Error Code: ";
	public static final String SQL_STATE = "SQL State: ";
	public static final String EXCEPTION_MSG = "SQL Exception!!!";
	public static final String USER_ID = "userid";
	public static final String USER = "user";
	public static final String STATUS = "status";
	public static final String TOTAL = "total";	
	public static final String ORDER_ID = "orderid";
	public static final String TITLE = "title";
	public static final String PRICE = "price";
	public static final String QUANTITY = "quantity";	
	
	private static final String QUERY_GET_ORDERS_INFO_BY_USER_ID = "SELECT orders.id as orderid, products.title, order_items.price, order_items.quantity, order_status.status, orders.user as userid, orders.total\r\n"
			+ "FROM `order` orders\r\n" + "LEFT JOIN order_item order_items\r\n"
			+ "ON orders.id = order_items.order\r\n" + "LEFT JOIN product products \r\n"
			+ "ON order_items.product = products.id\r\n" + "LEFT JOIN order_status order_status\r\n"
			+ "ON orders.status = order_status.id\r\n" + "WHERE orders.user = ?;";

	public List<OrderInfo> getUserOrders(long userId) {
		OrderInfo orderInfo = null;
		List<OrderInfo> orders = new ArrayList<>();
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_ORDERS_INFO_BY_USER_ID);) {
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				orderInfo = new OrderInfo();
				orderInfo.setOrderid(resultSet.getLong(ORDER_ID));
				orderInfo.setOrderStatus(resultSet.getString(STATUS));
				orderInfo.setUserid(resultSet.getLong(USER_ID));
				orderInfo.setTotalCost(resultSet.getBigDecimal(TOTAL));

				orderInfo.setProductTitle(resultSet.getString(TITLE));
				orderInfo.setProductPrice(resultSet.getBigDecimal(PRICE));
				orderInfo.setProductQuantity(resultSet.getLong(QUANTITY));
				orders.add(orderInfo);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG); 
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return orders;
	}
}