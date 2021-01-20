package com.epam.tcfp.zooproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.tcfp.zooproject.entity.Order;
import com.epam.tcfp.zooproject.database.connection.*;

public class OrderDAO {
	static Logger logger = LogManager.getLogger();
	public static final String SQL_ERROR = "SQL Error Type: ";
	public static final String SQL_ERROR_MESSAGE = "Error Message: ";
	public static final String SQL_ERROR_CODE = "Error Code: ";
	public static final String SQL_STATE = "SQL State: ";
	public static final String EXCEPTION_MSG = "SQL Exception!!!";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String STATUS = "status";
	public static final String TOTAL = "total";

	private static final String QUERY_INSERT = "INSERT INTO `order` (`user`, `status`, `total`, `delivery_address`) VALUES (?, ?, ?, ?);";
	private static final String QUERY_SELECT_ALL = "SELECT * FROM `order`;";
	private static final String QUERY_UPDATE_STATUS_BY_ID = "UPDATE `order` SET status = ? WHERE id = ?;";

	public String createOrder(Order order) {
		String result = "";
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_INSERT,
						Statement.RETURN_GENERATED_KEYS);) {
			statement.setLong(1, order.getUserId());
			statement.setLong(2, order.getStatusId());
			statement.setBigDecimal(3, order.getTotalCost());
			statement.setLong(4, order.getDeliveryAddressId());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				result = resultSet.getString(1);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return result;
	}

	public List<Order> getOrders() {
		Order order = null;
		List<Order> orders = new ArrayList<>();
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL)) {
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				order = new Order();
				order.setId(resultSet.getLong(ID));
				order.setUserId(resultSet.getLong(USER));
				order.setStatusId(resultSet.getLong(STATUS));
				order.setTotalCost(resultSet.getBigDecimal(TOTAL));
				orders.add(order);
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

	public boolean updateStatus(String orderId, String statusId) {
		boolean isUpdated = false;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_STATUS_BY_ID)) {
			statement.setString(1, statusId);
			statement.setString(2, orderId);
			isUpdated = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return isUpdated;
	}
}