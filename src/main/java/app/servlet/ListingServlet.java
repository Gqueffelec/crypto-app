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

@WebServlet("/list")
public class ListingServlet extends HttpServlet {

	/**
	 * 
	 */
		
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("list");
		if (action == null) {
			action = "";
		}
		System.out.println("list :"+action);
		List<Asset> listAsset = new AssetService().getAll();
		List<CryptoCurrency> listCrypto = new CryptoCurrencyService().getAll();
		listCrypto.removeAll(new CryptoCurrencyService().getCryptoWithoutAsset());
		String[][] prepareJsonData = new String[listAsset.size()][6];
		for (int i = 0; i < listAsset.size(); i++) {
			prepareJsonData[i][0] = listCrypto.get(i).getName();
			prepareJsonData[i][1] = String.valueOf(listAsset.get(i).getNumberOfAction());
			prepareJsonData[i][2] = String.valueOf(listAsset.get(i).getBuyPrice());
			prepareJsonData[i][3] = String.valueOf(listCrypto.get(i).getActualPrice());
			prepareJsonData[i][4] = String.valueOf(listAsset.get(i).getDeltaPrice());
			prepareJsonData[i][5] = String.valueOf(listCrypto.get(i).getId());
		}
		String json = new Gson().toJson(prepareJsonData);
		switch (action) {
		case "refresh":
			System.out.println("test");
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
			break;
		default:
			this.getServletContext().getRequestDispatcher("/WEB-INF/Liste.jsp").forward(req, resp);
			break;
		}
	}

}
