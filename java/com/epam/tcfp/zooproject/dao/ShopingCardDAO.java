package com.epam.tcfp.zooproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.tcfp.zooproject.entity.Product;
import com.epam.tcfp.zooproject.database.connection.*;

public class ShopingCardDAO {
	static Logger logger = LogManager.getLogger();
	public static final String SQL_ERROR = "SQL Error Type: ";
	public static final String SQL_ERROR_MESSAGE = "Error Message: ";
	public static final String SQL_ERROR_CODE = "Error Code: ";
	public static final String SQL_STATE = "SQL State: ";
	public static final String EXCEPTION_MSG = "SQL Exception!!!";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String BRAND = "brand";
	public static final String FORM = "form";
	public static final String BREED = "breed";
	public static final String AGE_RATE = "age_rate";
	public static final String WEIGHT = "weight";
	public static final String MEASURE = "measure";
	public static final String TYPE = "type";
	public static final String SUMMARY = "summary";
	public static final String PRICE = "price";
	public static final String DISCOUNT = "discount";
	
	private static final String QUERY_SELECT_BY_ID = "SELECT product.id, product.title, brand.brand, form.form, breed.breed, age.age_rate, weight.weight, weight.measure, type.type, product.summary, product.price, product.quantity\r\n"
			+ "FROM product product\r\n" + "LEFT JOIN brand brand\r\n" + "ON product.brand = brand.id\r\n"
			+ "LEFT JOIN form form\r\n" + "ON product.form = form.id  \r\n" + "LEFT JOIN breed breed\r\n"
			+ "ON product.breed = breed.id\r\n" + "LEFT JOIN age_rate age\r\n" + "ON product.age = age.id\r\n"
			+ "LEFT JOIN weight weight\r\n" + "ON product.weight = weight.id\r\n" + "LEFT JOIN type_of_animal type\r\n"
			+ "ON product.type_of_animal = type.id\r\n" + "WHERE product.id = ?;";

	public Product selectProduct(long id) {
		Product product = null;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getLong(ID));
				product.setTitle(resultSet.getString(TITLE));
				product.setBrand(resultSet.getString(BRAND));
				product.setForm(resultSet.getString(FORM));
				product.setBreed(resultSet.getString(BREED));
				product.setAgeRate(resultSet.getString(AGE_RATE));

				String weigth = resultSet.getString(WEIGHT);
				String measure = resultSet.getString(MEASURE);
				product.setWeight(weigth + " " + measure);

				product.setAnimalType(resultSet.getString(TYPE));
				product.setSummary(resultSet.getString(SUMMARY));
				product.setPrice(resultSet.getBigDecimal(PRICE));
				product.setDiscount(resultSet.getFloat(DISCOUNT));
				product.setQuantity(0);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return product;
	}
}