package com.epam.tcfp.zooproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.tcfp.zooproject.entity.UserAddress;
import com.epam.tcfp.zooproject.database.connection.*;

public class UserAddressDAO {
	static Logger logger = LogManager.getLogger();
	public static final String SQL_ERROR = "SQL Error Type: ";
	public static final String SQL_ERROR_MESSAGE = "Error Message: ";
	public static final String SQL_ERROR_CODE = "Error Code: ";
	public static final String SQL_STATE = "SQL State: ";
	public static final String EXCEPTION_MSG = "SQL Exception!!!";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String COUNTRY = "country";
	public static final String REGION = "region";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String BUILDING = "building";
	public static final String APARTMENT = "apartment";
	public static final String ZIP_CODE = "zip_code";

	private static final String QUERY_INSERT = "INSERT INTO `user_address` (`user`, `country`, `region`, `city`, `street`, `building`, `apartment`, `zip_code`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String QUERY_GET_ADDRESS_ID = "SELECT id FROM `user_address` WHERE user = ?";
	private static final String QUERY_GET_ADDRESS_BY_ID = "SELECT * FROM `user_address` WHERE user = ?";
	private static final String QUERY_UPDATE = "UPDATE `user_address` SET country = ?, region = ?, city = ?, street = ?, building = ?, apartment = ?, zip_code = ? WHERE user = ?;";

	public String insertAddress(UserAddress userAddress) {
		String result = "";
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_INSERT,
						Statement.RETURN_GENERATED_KEYS);) {
			statement.setLong(1, userAddress.getUserId());
			statement.setString(2, userAddress.getCountry());
			statement.setString(3, userAddress.getRegion());
			statement.setString(4, userAddress.getCity());
			statement.setString(5, userAddress.getStreet());
			statement.setString(6, userAddress.getBuilding());
			statement.setString(7, userAddress.getApartment());
			statement.setString(8, userAddress.getZipCode());
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

	public String getAddressId(long userId) {
		String result = "";
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_ADDRESS_ID);) {
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getString("id");
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

	public boolean checkIfUserAddressExists(long userId) {
		boolean userAddressExists = false;
		String addressID = getAddressId(userId);
		if (!addressID.isEmpty()) {
			userAddressExists = true;
		}
		return userAddressExists;
	}

	public UserAddress getUserAddress(long userId) {
		UserAddress userAddress = null;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_ADDRESS_BY_ID);) {
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				userAddress = new UserAddress();
				userAddress.setId(resultSet.getLong(ID));
				userAddress.setUserId(resultSet.getLong(USER));
				userAddress.setCountry(resultSet.getString(COUNTRY));
				userAddress.setRegion(resultSet.getString(REGION));
				userAddress.setCity(resultSet.getString(CITY));
				userAddress.setStreet(resultSet.getString(STREET));
				userAddress.setBuilding(resultSet.getString(BUILDING));
				userAddress.setApartment(resultSet.getString(APARTMENT));
				userAddress.setZipCode(resultSet.getString(ZIP_CODE));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return userAddress;
	}

	public boolean updateAddress(UserAddress userAddress) {
		boolean isUpdated = false;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);) {
			statement.setString(1, userAddress.getCountry());
			statement.setString(2, userAddress.getRegion());
			statement.setString(3, userAddress.getCity());
			statement.setString(4, userAddress.getStreet());
			statement.setString(5, userAddress.getBuilding());
			statement.setString(6, userAddress.getApartment());
			statement.setString(7, userAddress.getZipCode());
			statement.setLong(8, userAddress.getUserId());
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