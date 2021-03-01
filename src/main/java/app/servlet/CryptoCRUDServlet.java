package app.servlet;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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

@WebServlet("/CRUDCurrency")
public class CryptoCRUDServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		cryptoServ = new CryptoCurrencyService();
	}
	private CryptoCurrencyService cryptoServ;
	private CryptoCurrency crypto;
	private List<CryptoCurrency> list;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("crypto");
		System.out.println("crypto :"+action);
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		if (action == null) {
			action = "";
		}
		int id = 0;
		double temp = 0;
		switch (action) {
		case "create":
			if (req.getParameter("actualprice").equals("") || req.getParameter("name").equals("")
					|| req.getParameter("label").equals("")) {
				resp.getWriter().write("Please fill all the field");
				break;
			}
			try {
				temp = Double.parseDouble(req.getParameter("actualprice"));
			} catch (NumberFormatException e) {
				resp.getWriter().write("Wrong actual price format, please insert a number");
				break;
			}
			if (temp<0) {
				resp.getWriter().write("Price can't be negative");
				break;
			}
			crypto = CryptoCurrency.builder().id(0).name(req.getParameter("name")).label(req.getParameter("label"))
					.actualPrice(temp).build();
			if (cryptoServ.create(crypto) == null) {
				resp.getWriter().write("Failed Insertion, Currency already exist");
			} else {
				resp.getWriter().write("New Crypto Currency Added !");
			}
			break;
		case "remove":
			if (req.getParameter("id").equals("")) {
				resp.getWriter().write("Please fill all the field");
				break;
			}
			try {
				id = Integer.parseInt(req.getParameter("id"));
			} catch (NumberFormatException e) {
				resp.getWriter().write("Wrong id format, please insert a number");
				break;
			}
			if (new AssetService().getById(id)!=null) {
				resp.getWriter().write("Failed Deletion, you still have Assets on this currency");
				break;
			}
			if (!cryptoServ.delete(id)) {
				resp.getWriter().write("Failed Deletion, check if Id exists or if you still have Assets");
			} else {
				resp.getWriter().write("Crypto Currency removed !");
			}
			break;
		case "update":
			if (req.getParameter("actualprice").equals("") || req.getParameter("name").equals("")
					|| req.getParameter("label").equals("")) {
				resp.getWriter().write("Please fill all the field");
				break;
			}
			temp = 0;
			try {
				temp = Double.parseDouble(req.getParameter("actualprice"));
				id = Integer.parseInt(req.getParameter("id"));
			} catch (NumberFormatException e) {
				resp.getWriter().write("Wrong actual price format, please insert a number");
				break;
			}
			if (temp<0) {
				resp.getWriter().write("Price can't be negative");
				break;
			}
			crypto = CryptoCurrency.builder().id(id).name(req.getParameter("name")).label(req.getParameter("label"))
					.actualPrice(temp).build();
			if (!cryptoServ.update(crypto)) {
				resp.getWriter().write("Failed Update");
			} else {
				resp.getWriter().write(req.getParameter("name") + " updated !");
			}
			break;
		case "list":
			list = cryptoServ.getAll();
			String json = new Gson().toJson(list);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
			break;
		case "getdata":
			if (req.getParameter("id").equals("")) {
				resp.getWriter().write("Please fill all the field");
				break;
			}
			try {
				id = Integer.parseInt(req.getParameter("id"));
				crypto = cryptoServ.getById(id).get();
			} catch (NumberFormatException nfe) {
				resp.getWriter().write("Wrong id format, please insert a number");
				break;
			} catch (NoSuchElementException nsee) {
				resp.getWriter().write("This id doesn't exist");
				break;
			}
			json = new Gson().toJson(crypto);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
			break;
		default:
			list = cryptoServ.getAll();
			req.setAttribute("list", list);
			System.out.println(new AssetService().getAll().stream().mapToDouble(e -> e.getDeltaPrice()*e.getNumberOfAction()).sum());
			req.setAttribute("total",new AssetService().getAll().stream().mapToDouble(Asset::getDeltaPrice).sum());
			this.getServletContext().getRequestDispatcher("/WEB-INF/CRUDCurrency.jsp").forward(req, resp);
			break;
		}

	}

}
