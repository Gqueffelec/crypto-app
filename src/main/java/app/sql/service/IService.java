package app.sql.service;

import java.util.List;
import java.util.Optional;

import app.model.CryptoCurrency;

public interface IService<T> {
	
	public Optional<T> create(T o);
	
	public Optional<T> getById(int id);
	
	public List<T> getAll();
	
	public boolean delete(int id);
	
	public boolean update(T o);

}
