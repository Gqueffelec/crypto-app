package app.sql.dao;

import java.util.List;

public interface IDao<T, U> {
	T create(T o);

	boolean update(T o);

	boolean deleteById(U id);

	T getById(U id);

	List<T> getAll();
}
