package app.sql.dao;

import java.util.List;

import app.model.CryptoCurrency;

public interface CryptoDao extends IDao<CryptoCurrency, Integer> {
	public List<CryptoCurrency> getCryptoWithoutAssets();
}
