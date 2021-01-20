package com.epam.tcfp.zooproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.tcfp.zooproject.entity.User;
import com.epam.tcfp.zooproject.database.connection.*;

public class UserDAO {
	static Logger logger = LogManager.getLogger();
	public static final String SQL_ERROR = "SQL Error Type: ";
	public static final String SQL_ERROR_MESSAGE = "Error Message: ";
	public static final String SQL_ERROR_CODE = "Error Code: ";
	public static final String SQL_STATE = "SQL State: ";
	public static final String EXCEPTION_MSG = "SQL Exception!!!";	

	public static final String PASSWORD_HASH = "passwordHash";
	public static final String ACTIVE_USER = "isActive";
	public static final String ID = "id";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile";
	public static final String ADMIN_USER = "isAdmin";
	
	private static final String QUERY_INSERT = "INSERT INTO user (`firstName`, `lastName`, `mobile`, `email`, `passwordHash`, `isAdmin`) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String QUERY_UPDATE = "UPDATE user SET firstName = ?, lastName = ?, mobile = ?, email = ?, passwordHash = ? WHERE email = ?;";
	private static final String QUERY_DEACTIVATE = "UPDATE user SET isActive = ? WHERE email = ?;";
	private static final String QUERY_VALIDATE_USER = "SELECT * from `user` WHERE email = ? AND passwordHash = ?";
	private static final String QUERY_SELECT_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
	private static final String QUERY_SELECT_ALL_USERS = "SELECT * FROM user;";


	public int insertUser(User user) {
		int result = 0;
		boolean isDuplicateEntryError = false;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_INSERT)) {
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getMobile());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.setBoolean(6, user.isAdmin());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
			if (e instanceof SQLIntegrityConstraintViolationException) {
				if (e.getSQLState().equals("23000")) {
					if (e.getMessage().contains("Duplicate")) {
						isDuplicateEntryError = true;
					}
				}
			}
		}
		if (result != 0) {
			return 1;
		} else if (isDuplicateEntryError) {
			return 2;
		} else {
			return 0;
		}
	}

	public boolean updatetUser(User user) {
		boolean isUpdated = false;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE)) {
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getMobile());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.setString(6, user.getEmail());
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

	public boolean validateUser(String userEmail, String password) {
		boolean loginSuccess = false;
		String md5Hex = DigestUtils.md5Hex(password);
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(QUERY_VALIDATE_USER)) {

			ps.setString(1, userEmail);
			ps.setString(2, md5Hex);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				String emaildb = resultSet.getString(EMAIL);
				String passworddb = resultSet.getString(PASSWORD_HASH);
				String isActive = resultSet.getString(ACTIVE_USER);

				if (emaildb.equals(userEmail.trim()) && md5Hex.equals(passworddb) && isActive.equals("1")) {
					loginSuccess = true;
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return loginSuccess;
	}

	public User selectUser(String userEmail) {
		User user = null;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_BY_EMAIL)) {
			statement.setString(1, userEmail);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong(ID));
				user.setFirstName(resultSet.getString(FIRST_NAME));
				user.setLastName(resultSet.getString(LAST_NAME));
				user.setMobile(resultSet.getString(MOBILE));
				user.setEmail(resultSet.getString(EMAIL));
				user.setPassword(resultSet.getString(PASSWORD_HASH));
				// поменять на тернарный
				String isAdmin = resultSet.getString(ADMIN_USER);
				if (isAdmin.equalsIgnoreCase("0")) {
					user.setAdmin(false);
				} else {
					user.setAdmin(true);
				}

				String isActive = resultSet.getString(ACTIVE_USER);
				if (isActive.equalsIgnoreCase("0")) {
					user.setActive(false);
				} else {
					user.setActive(true);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return user;
	}

	public List<User> selectAllUsers() {
		User user = null;
		List<User> users = new ArrayList<>();
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL_USERS)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(ID));
				user.setFirstName(resultSet.getString(FIRST_NAME));
				user.setLastName(resultSet.getString(LAST_NAME));
				user.setMobile(resultSet.getString(MOBILE));
				user.setEmail(resultSet.getString(EMAIL));
				user.setPassword(resultSet.getString(PASSWORD_HASH));
				String isAdmin = resultSet.getString(ADMIN_USER);
				if (isAdmin.equalsIgnoreCase("0")) {
					user.setAdmin(false);
				} else {
					user.setAdmin(true);
				}
				String isActive = resultSet.getString(ACTIVE_USER);
				if (isActive.equalsIgnoreCase("0")) {
					user.setActive(false);
				} else {
					user.setActive(true);
				}
				users.add(user); 
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return users;
	}

	public boolean deactivateUser(User user) {
		boolean isDeactivated = false;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_DEACTIVATE)) {
			statement.setBoolean(1, false);
			statement.setString(2, user.getEmail());
			statement.executeUpdate();
			isDeactivated = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return isDeactivated;
	}
}