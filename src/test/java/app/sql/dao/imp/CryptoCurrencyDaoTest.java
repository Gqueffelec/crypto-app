package app.sql.dao.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import app.model.CryptoCurrency;

@ExtendWith(MockitoExtension.class)
class CryptoCurrencyDaoTest {

	CryptoDaoImpl dao = new CryptoDaoImpl();

	@Mock
	CryptoCurrency crypto;
	@Mock
	PreparedStatement stmt;
	@Mock
	ResultSet rs;
	@Mock
	Connection con;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dao.con = con;
		crypto = createFakeCrypto();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_insertion_to_database() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getInt(1)).thenReturn(0);
		Mockito.when(dao.con.prepareStatement(Mockito.anyString(), Mockito.anyInt())).thenReturn(stmt);
		Mockito.when(stmt.getGeneratedKeys()).thenReturn(rs);
		CryptoCurrency test = dao.create(crypto);
		Mockito.verify(dao.con).prepareStatement(Mockito.anyString(), Mockito.anyInt());
		Mockito.verify(stmt,Mockito.times(1)).executeUpdate();
		Mockito.verify(stmt).getGeneratedKeys();
		Mockito.verify(rs, Mockito.times(1)).next();
		Mockito.verify(rs).getInt(1);
		assertEquals(0, test.getId());
		assertEquals("bitcoin", test.getName());
		assertEquals("Bitcoin, most famous crypto currency", test.getLabel());
		assertEquals(300, test.getActualPrice());
	}

	@Test
	void test_update_to_database() throws SQLException {
		Mockito.when(dao.con.prepareStatement(Mockito.anyString())).thenReturn(stmt);
		Mockito.when(stmt.executeUpdate()).thenReturn(1);
		boolean test = dao.update(crypto);
		Mockito.verify(dao.con).prepareStatement(Mockito.anyString());
		Mockito.verify(stmt,Mockito.times(2)).setString(Mockito.anyInt(),Mockito.anyString());;
		Mockito.verify(stmt,Mockito.times(1)).setDouble(Mockito.anyInt(),Mockito.anyDouble());;
		Mockito.verify(stmt,Mockito.times(1)).setInt(Mockito.anyInt(),Mockito.anyInt());
		Mockito.verify(stmt,Mockito.times(1)).executeUpdate();
		assertTrue(test);
	}
	
	@Test
	void test_delete_from_database_with_id() throws SQLException{
		Mockito.when(dao.con.prepareStatement(Mockito.anyString())).thenReturn(stmt);
		Mockito.when(stmt.executeUpdate()).thenReturn(1);
		boolean test = dao.deleteById(0);
		Mockito.verify(dao.con).prepareStatement(Mockito.anyString());
		Mockito.verify(stmt,Mockito.times(1)).setInt(Mockito.anyInt(),Mockito.anyInt());
		Mockito.verify(stmt,Mockito.times(1)).executeUpdate();
		assertTrue(test);
	}
	
	@Test
	void test_get_from_database_with_id() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getInt("id")).thenReturn(crypto.getId());
		Mockito.when(rs.getString("name")).thenReturn(crypto.getName());
		Mockito.when(rs.getString("label")).thenReturn(crypto.getLabel());
		Mockito.when(rs.getDouble("actualprice")).thenReturn(crypto.getActualPrice());
		Mockito.when(dao.con.prepareStatement(Mockito.anyString())).thenReturn(stmt);
		Mockito.when(stmt.executeQuery()).thenReturn(rs);
		CryptoCurrency test = dao.getById(0);
		Mockito.verify(dao.con).prepareStatement(Mockito.anyString());
		Mockito.verify(stmt,Mockito.times(1)).setInt(Mockito.anyInt(),Mockito.anyInt());
		Mockito.verify(stmt,Mockito.times(1)).executeQuery();
		Mockito.verify(rs, Mockito.times(1)).next();
		Mockito.verify(rs).getInt(Mockito.anyString());
		Mockito.verify(rs, Mockito.times(2)).getString(Mockito.anyString());
		Mockito.verify(rs).getDouble(Mockito.anyString());
		assertEquals(0, test.getId());
		assertEquals("bitcoin", test.getName());
		assertEquals("Bitcoin, most famous crypto currency", test.getLabel());
		assertEquals(300, test.getActualPrice());
	}

	@Test
	void test_get_all_from_database() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
		Mockito.when(rs.getInt("id")).thenReturn(crypto.getId());
		Mockito.when(rs.getString("name")).thenReturn(crypto.getName());
		Mockito.when(rs.getString("label")).thenReturn(crypto.getLabel());
		Mockito.when(rs.getDouble("actualprice")).thenReturn(crypto.getActualPrice());
		Mockito.when(dao.con.prepareStatement(Mockito.anyString())).thenReturn(stmt);
		Mockito.when(stmt.executeQuery()).thenReturn(rs);
		List<CryptoCurrency> test = dao.getAll();
		Mockito.verify(dao.con).prepareStatement(Mockito.anyString());
		Mockito.verify(stmt,Mockito.times(1)).executeQuery();
		Mockito.verify(rs, Mockito.times(2)).next();
		Mockito.verify(rs).getInt(Mockito.anyString());
		Mockito.verify(rs, Mockito.times(2)).getString(Mockito.anyString());
		Mockito.verify(rs).getDouble(Mockito.anyString());
		assertEquals(0, test.get(0).getId());
		assertEquals("bitcoin", test.get(0).getName());
		assertEquals("Bitcoin, most famous crypto currency", test.get(0).getLabel());
		assertEquals(300, test.get(0).getActualPrice());
	}
	
	private CryptoCurrency createFakeCrypto() {
		crypto = CryptoCurrency.builder().id(0).name("bitcoin").label("Bitcoin, most famous crypto currency")
				.actualPrice(300).build();
		return crypto;
	}

}
