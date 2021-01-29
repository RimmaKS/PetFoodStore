package com.epam.tcfp.zooproject.dao;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.tcfp.zooproject.entity.Product;
import com.epam.tcfp.zooproject.database.connection.*;

public class ProductDAO {
	static Logger logger = LogManager.getLogger();
	public static final String SQL_ERROR = "SQL Error Type: ";
	public static final String SQL_ERROR_MESSAGE = "Error Message: ";
	public static final String SQL_ERROR_CODE = "Error Code: ";
	public static final String SQL_STATE = "SQL State: ";
	public static final String EXCEPTION_MSG = "SQL Exception!!!";
	public static final String ID = "id";
	public static final String BRAND = "brand";
	public static final String FORM = "form";
	public static final String BREED = "breed";
	public static final String AGE_RATE = "age_rate";
	public static final String TITLE = "title";
	public static final String PRICE = "price";
	public static final String QUANTITY = "quantity";
	public static final String WEIGHT = "weight";
	public static final String MEASURE = "measure";
	public static final String TYPE = "type";
	public static final String SUMMARY = "summary";
	public static final String IMAGE = "image";

	private static final String QUERY_SELECT_BY_ID = "SELECT product.id, " + "product.title, brand.brand, form.form, "
			+ "breed.breed, age.age_rate, weight.weight, " + "weight.measure, type.type, product.summary, "
			+ "product.price, product.quantity, product.image\r\n" + "FROM product product\r\n"
			+ "LEFT JOIN brand brand\r\n" + "ON product.brand = brand.id\r\n" + "LEFT JOIN form form\r\n"
			+ "ON product.form = form.id  \r\n" + "LEFT JOIN breed breed\r\n" + "ON product.breed = breed.id\r\n"
			+ "LEFT JOIN age_rate age\r\n" + "ON product.age = age.id\r\n" + "LEFT JOIN weight weight\r\n"
			+ "ON product.weight = weight.id\r\n" + "LEFT JOIN type_of_animal type\r\n"
			+ "ON product.type_of_animal = type.id\r\n" + "WHERE product.id = ?;";
	private static final String QUERY_SELECT_ALL = "SELECT product.id, "
			+ "product.title, brand.brand, form.form, breed.breed, "
			+ "age.age_rate, weight.weight, weight.measure, type.type, "
			+ "product.summary, product.price, product.quantity, product.image\r\n" + "FROM product product\r\n"
			+ "LEFT JOIN brand brand\r\n" + "ON product.brand = brand.id\r\n" + "LEFT JOIN form form\r\n"
			+ "ON product.form = form.id  \r\n" + "LEFT JOIN breed breed\r\n" + "ON product.breed = breed.id\r\n"
			+ "LEFT JOIN age_rate age\r\n" + "ON product.age = age.id\r\n" + "LEFT JOIN weight weight\r\n"
			+ "ON product.weight = weight.id\r\n" + "LEFT JOIN type_of_animal type\r\n"
			+ "ON product.type_of_animal = type.id\r\n" + "WHERE 1=1;";
	private static final String QUERY_GET_BRAND_ID = "SELECT id FROM brand WHERE brand = ?;";
	private static final String QUERY_GET_FORM_ID = "SELECT id FROM form WHERE form = ?;";
	private static final String QUERY_GET_BREED_ID = "SELECT id FROM breed WHERE breed = ?;";
	private static final String QUERY_GET_AGE_RATE_ID = "SELECT id FROM age_rate WHERE age_rate = ?;";
	private static final String QUERY_GET_ANIMAL_TYPE_ID = "SELECT id FROM type_of_animal WHERE type = ?;";
	private static final String QUERY_GET_WEIGHT_ID = "SELECT id FROM weight WHERE weight = ?;";
	private static final String QUERY_UPDATE = "UPDATE product SET title = ?, brand = ?, form = ?, breed = ?, age = ?, weight = ?, type_of_animal = ?, summary = ?, price = ?, quantity = ? WHERE id = ?;";
	private static final String QUERY_INSERT = "INSERT INTO product (title, brand, form, breed, age, weight, type_of_animal, summary, price, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String QUERY_SUBTRACT_AMOUNT = "UPDATE product SET quantity = quantity - ? WHERE id = ?;";

	public Product selectProduct(long id) {
		Product product = null;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String weigth = resultSet.getString(WEIGHT);
				String measure = resultSet.getString(MEASURE);
				
				product = new Product.Builder(resultSet.getString(TITLE))
						.id(resultSet.getLong(ID))
						.brand(resultSet.getString(BRAND))
						.form(resultSet.getString(FORM))
						.breed(resultSet.getString(BREED))
						.ageRate(resultSet.getString(AGE_RATE))
						.weight(weigth + " " + measure)
						.animalType(resultSet.getString(TYPE))
						.summary(resultSet.getString(SUMMARY))
						.price(resultSet.getBigDecimal(PRICE))
						.quantity(resultSet.getLong(QUANTITY))
						.image(resultSet.getString(IMAGE))
						.build();
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

	public List<Product> getProducts() {
		Product product = null;
		List<Product> products = new ArrayList<>();
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL)) {
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String weigth = resultSet.getString(WEIGHT);
				String measure = resultSet.getString(MEASURE);				
				product = new Product.Builder(resultSet.getString(TITLE))
						.id(resultSet.getLong(ID))
						.brand(resultSet.getString(BRAND))
						.form(resultSet.getString(FORM))
						.breed(resultSet.getString(BREED))
						.ageRate(resultSet.getString(AGE_RATE))
						.weight(weigth + " " + measure)
						.animalType(resultSet.getString(TYPE))
						.summary(resultSet.getString(SUMMARY))
						.price(resultSet.getBigDecimal(PRICE))
						.quantity(resultSet.getLong(QUANTITY))
						.image(resultSet.getString(IMAGE))
						.build();
				products.add(product);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return products;
	}

	public boolean subtractAmount(long id, long quantity) {
		boolean isUpdated = false;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SUBTRACT_AMOUNT)) {
			statement.setLong(1, quantity);
			statement.setLong(2, id);
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

	public boolean updateProduct(Product product) {
		boolean isUpdated = false;
		int brandId = getBandId(product.getBrand());
		int formId = getFormId(product.getForm());
		int breedId = getBreedId(product.getBreed());
		int ageId = getAgeRateId(product.getAgeRate());
		int weightId = getWeightId(product.getWeight());
		int animalTypeId = getAnimalTypeId(product.getAnimalType());

		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE)) {
			statement.setString(1, product.getTitle());
			statement.setInt(2, brandId);
			statement.setInt(3, formId);
			statement.setInt(4, breedId);
			statement.setInt(5, ageId);
			statement.setInt(6, weightId);
			statement.setInt(7, animalTypeId);
			statement.setString(8, product.getSummary());
			statement.setFloat(9, product.getPrice().setScale(2, RoundingMode.DOWN).floatValue());
			statement.setLong(10, product.getQuantity());
			statement.setLong(11, product.getId());
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

	public boolean insertProduct(Product product) {
		boolean isUpdated = false;
		int brandId = getBandId(product.getBrand());
		int formId = getFormId(product.getForm());
		int breedId = getBreedId(product.getBreed());
		int ageId = getAgeRateId(product.getAgeRate());
		int weightId = getWeightId(product.getWeight());
		int animalTypeId = getAnimalTypeId(product.getAnimalType());

		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_INSERT)) {
			statement.setString(1, product.getTitle());
			statement.setInt(2, brandId);
			statement.setInt(3, formId);
			statement.setInt(4, breedId);
			statement.setInt(5, ageId);
			statement.setInt(6, weightId);
			statement.setInt(7, animalTypeId);
			statement.setString(8, product.getSummary());
			statement.setFloat(9, product.getPrice().setScale(2, RoundingMode.DOWN).floatValue());
			statement.setLong(10, product.getQuantity());
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

	public int getBandId(String columnValue) {
		int id = 0;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_BRAND_ID)) {
			statement.setString(1, columnValue);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				id = resultSet.getInt(ID);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return id;
	}

	public int getFormId(String columnValue) {
		int id = 0;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_FORM_ID)) {
			statement.setString(1, columnValue);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				id = resultSet.getInt(ID);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return id;
	}

	public int getBreedId(String columnValue) {
		int id = 0;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_BREED_ID)) {
			statement.setString(1, columnValue);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(ID);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return id;
	}

	public int getAgeRateId(String columnValue) {
		int id = 0;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_AGE_RATE_ID)) {
			statement.setString(1, columnValue);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(ID);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return id;
	}

	public int getAnimalTypeId(String columnValue) {
		int id = 0;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_ANIMAL_TYPE_ID)) {
			statement.setString(1, columnValue);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(ID);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return id;
	}

	public int getWeightId(String columnValue) {
		int id = 0;
		try (Connection connection = Pool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_GET_WEIGHT_ID)) {
			statement.setString(1, columnValue);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(ID);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, EXCEPTION_MSG);
			logger.info(SQL_ERROR + e.getClass().getName());
			logger.info(SQL_ERROR_MESSAGE + e.getMessage());
			logger.info(SQL_ERROR_CODE + e.getErrorCode());
			logger.info(SQL_STATE + e.getSQLState());
		}
		return id;
	}			
}