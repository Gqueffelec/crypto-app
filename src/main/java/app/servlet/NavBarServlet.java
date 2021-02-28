package app.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import app.model.Asset;
import app.model.CryptoCurrency;
import app.sql.service.imp.AssetService;
import app.sql.service.imp.CryptoCurrencyService;

@WebServlet("/navbar")
public class NavBarServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		assetServ = new AssetService();
		cryptoServ = new CryptoCurrencyService();
	}
	
	private AssetService assetServ;
	private CryptoCurrencyService cryptoServ;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action =  req.getParameter("navbar");
		System.out.println("navbar :" +action);
		if (action ==null) {
			action="";
		}
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		switch (action) {
		case "list":
			List<CryptoCurrency>list = cryptoServ.getCryptoWithoutAsset();
			String json = new Gson().toJson(list);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
			break;
		case "refresh":
			resp.getWriter().write(String.valueOf(new AssetService().getAll().stream().mapToDouble(e -> e.getDeltaPrice()*e.getNumberOfAction()).sum()));
			break;
		default:
			resp.sendRedirect("index.html");
			break;
		}
	}
}
