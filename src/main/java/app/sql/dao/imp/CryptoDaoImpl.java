package app.sql.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

import app.model.CryptoCurrency;
import app.sql.SQLConnection;
import app.sql.dao.CryptoDao;

public class CryptoDaoImpl implements CryptoDao {
	
Connection con;
	
	public CryptoDaoImpl() {
		con = SQLConnection.con;
	}

	@Override
	public CryptoCurrency create(CryptoCurrency o) {
		String request = "insert into cryptocurrency(name,label,actualprice) values (?,?,?)";
		ResultSet results;
		try {
			PreparedStatement stmt = this.con.prepareStatement(request,
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, o.getName());
			stmt.setString(2, o.getLabel());
			stmt.setDouble(3, o.getActualPrice());
			stmt.executeUpdate();
			results = stmt.getGeneratedKeys();
			if (results.next()) {
				o.setId(results.getInt(1));
				return o;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
//			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		} catch (SQLException e) {
//			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		return null;
	}

	@Override
	public boolean update(CryptoCurrency o) {
		String request = "update cryptocurrency set name = ?, label = ?, actualprice = ? where id = ?";
		int results = 0;
		try {
			PreparedStatement stmt = this.con.prepareStatement(request);
			stmt.setString(1, o.getName());
			stmt.setString(2, o.getLabel());
			stmt.setDouble(3, o.getActualPrice());
			stmt.setInt(4, o.getId());
			results = stmt.executeUpdate();
//			logger.info("données entrés libelle  " + champ +" = "+ value +", " + " log id " + System.currentTimeMillis());
		} catch (SQLException e) {
//			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		if (results == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean deleteById(Integer id) {
		String request = "delete from cryptocurrency where id = ?";
		int results = 0;
		try {
			PreparedStatement stmt = this.con.prepareStatement(request);
			stmt.setInt(1, id);
			results = stmt.executeUpdate();
//			logger.warn("Vehicule supprimé, log id " + System.currentTimeMillis());
		} catch (SQLException e) {
//			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(), e);
		}
		if (results == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public CryptoCurrency getById(Integer id) {
		String request = "select * from cryptocurrency where id = ?";
		ResultSet results = null;
		CryptoCurrency cryptoCurrency = null;
		try {
			PreparedStatement stmt = this.con.prepareStatement(request);
			stmt.setInt(1, id);
			results = stmt.executeQuery();
			if (results.next()) {
				cryptoCurrency = CryptoCurrency.builder().id(results.getInt("id")).name(results.getString("name"))
						.label(results.getString("label")).actualPrice(results.getDouble("actualprice")).build();
			}
		} catch (SQLException e) {
//			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(), e);
		}
		if (results != null) {
			return cryptoCurrency;
		}
		return null;
	}

	@Override
	public List<CryptoCurrency> getAll() {
		String request = "select * from Vehicule order by id";
		ResultSet results = null;
		List<CryptoCurrency> listVehicule = new LinkedList<CryptoCurrency>();
		try {
			PreparedStatement stmt = this.con.prepareStatement(request);
			results = stmt.executeQuery();
			while (results.next()) {
				CryptoCurrency vehicule = CryptoCurrency.builder().id(results.getInt("id")).name(results.getString("name"))
						.label(results.getString("label")).actualPrice(results.getDouble("actualprice")).build();
				listVehicule.add(vehicule);
			}
		} catch (SQLException e) {
//			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(), e);
		}
		if (results != null) {
			return listVehicule;
		}
		return null;
	}

}
