package app.sql.dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

import app.model.Asset;
import app.sql.SQLConnection;
import app.sql.dao.AssetDao;

public class AssetDaoImpl implements AssetDao {

	Connection con;

	public AssetDaoImpl() {
		if (SQLConnection.con == null) {
			SQLConnection.connect();
		}
		con = SQLConnection.con;
	}

	@Override
	public Asset create(Asset o) {
		String request = "insert into asset(id,numberofaction,buyprice,delta,buydate) values (?,?,?,?,?)";
		ResultSet results;
		try {
			PreparedStatement stmt = this.con.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, o.getId());
			stmt.setInt(2, o.getNumberOfAction());
			stmt.setDouble(3, o.getBuyPrice());
			stmt.setDouble(4, o.getDeltaPrice());
			stmt.setDate(5, Date.valueOf(o.getBuyDate()));
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
	public boolean update(Asset o) {
		String request = "update asset set numberofaction = ? ,buyprice = ?,delta = ?,buydate  = ? where id = ?";
		int results = 0;
		try {
			PreparedStatement stmt = this.con.prepareStatement(request);
			stmt.setInt(1, o.getNumberOfAction());
			stmt.setDouble(2, o.getBuyPrice());
			stmt.setDouble(3, o.getDeltaPrice());
			stmt.setDate(4, Date.valueOf(o.getBuyDate()));
			stmt.setInt(5, o.getId());
			System.out.println(stmt);
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
		String request = "delete from asset where id = ?";
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
	public Asset getById(Integer id) {
		String request = "select * from asset where id = ?";
		ResultSet results = null;
		Asset asset = null;
		try {
			PreparedStatement stmt = this.con.prepareStatement(request);
			stmt.setInt(1, id);
			results = stmt.executeQuery();
			if (results.next()) {
				asset = Asset.builder().id(results.getInt("id")).numberOfAction(results.getInt("numberofaction"))
						.buyPrice(results.getDouble("buyprice")).deltaPrice(results.getDouble("delta"))
						.buyDate(results.getDate("buydate").toLocalDate()).build();
			}
		} catch (SQLException e) {
//			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(), e);
		}
		if (results != null) {
			return asset;
		}
		return null;
	}

	@Override
	public List<Asset> getAll() {
		String request = "select * from asset order by id";
		ResultSet results = null;
		List<Asset> list = new LinkedList<Asset>();
		try {
			PreparedStatement stmt = this.con.prepareStatement(request);
			results = stmt.executeQuery();
			while (results.next()) {
				Asset asset = Asset.builder().id(results.getInt("id")).numberOfAction(results.getInt("numberofaction"))
						.buyPrice(results.getDouble("buyprice")).deltaPrice(results.getDouble("delta"))
						.buyDate(results.getDate("buydate").toLocalDate()).build();
				list.add(asset);
			}
		} catch (SQLException e) {
//			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(), e);
		}
		if (results != null) {
			return list;
		}
		return null;
	}

}
