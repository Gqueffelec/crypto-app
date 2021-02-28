package app.sql.service.imp;

import java.util.List;
import java.util.Optional;

import app.model.CryptoCurrency;
import app.sql.dao.imp.CryptoDaoImpl;
import app.sql.service.IService;

public class CryptoCurrencyService implements IService<CryptoCurrency> {

	private CryptoDaoImpl dao;

	public CryptoCurrencyService() {
		this.dao = new CryptoDaoImpl();
	}

	@Override
	public Optional<CryptoCurrency> create(CryptoCurrency o) {
		if (dao.getAll().stream().anyMatch(e -> e.getName().equals(o.getName()) || e.getLabel().equals(o.getLabel()))) {
			return null;
		}
		return Optional.ofNullable(dao.create(o));
	}

	@Override
	public Optional<CryptoCurrency> getById(int id) {
		return Optional.ofNullable(dao.getById(id));
	}

	@Override
	public List<CryptoCurrency> getAll() {
		return dao.getAll();
	}

	@Override
	public boolean delete(int id) {
		return dao.deleteById(id);
	}

	@Override
	public boolean update(CryptoCurrency o) {
		return dao.update(o);
	}

	public List<CryptoCurrency> getCryptoWithoutAsset(){
		return dao.getCryptoWithoutAssets();
	}
	
}
