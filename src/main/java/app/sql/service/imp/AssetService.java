package app.sql.service.imp;

import java.util.List;
import java.util.Optional;

import app.model.Asset;
import app.sql.dao.imp.AssetDaoImpl;
import app.sql.dao.imp.CryptoDaoImpl;
import app.sql.service.IService;

public class AssetService implements IService<Asset> {

	private AssetDaoImpl daoAsset = new AssetDaoImpl();
	private CryptoDaoImpl daoCrypto = new CryptoDaoImpl();

	@Override
	public Optional<Asset> create(Asset o) {
		if (daoCrypto.getById(o.getId()) == null || daoAsset.getAll().stream().anyMatch(e -> e.getId() == o.getId())) {
			return null;
		}
		return Optional.ofNullable(daoAsset.create(o));
	}

	@Override
	public Optional<Asset> getById(int id) {
		return Optional.ofNullable(daoAsset.getById(id));
	}

	@Override
	public List<Asset> getAll() {
		return daoAsset.getAll();
	}

	@Override
	public boolean delete(int id) {
		return daoAsset.deleteById(id);
	}

	@Override
	public boolean update(Asset o) {
		return daoAsset.update(o);
	}

	
}
