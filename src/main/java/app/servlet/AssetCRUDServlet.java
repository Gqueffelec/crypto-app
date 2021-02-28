package app.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.model.Asset;
import app.sql.service.imp.AssetService;
import app.sql.service.imp.CryptoCurrencyService;

@WebServlet("/Assets")
public class AssetCRUDServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		serv = new AssetService();
	}
	private AssetService serv;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action =  req.getParameter("asset");
		System.out.println("asset :"+action);
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		int numberOfAction = 0;
		int id = 0;
		switch (action) {
		case "add":
			if (req.getParameter("numberofaction").equals("")) {
				resp.getWriter().write("Please fill all the field");
				break;
			}
			try {
				numberOfAction = Integer.parseInt(req.getParameter("numberofaction"));
				id = Integer.parseInt(req.getParameter("id"));
			} catch (NumberFormatException e) {
				resp.getWriter().write("Wrong number format, please insert a number");
				break;
			}
			Asset asset = Asset.builder().id(id).numberOfAction(numberOfAction).buyDate(LocalDate.now())
					.buyPrice(new CryptoCurrencyService().getById(id).get().getActualPrice()).deltaPrice(0).build();
			System.out.println(asset);
			if (serv.create(asset) == null) {
				resp.getWriter().write("Failed Insertion");
			} else {
				resp.getWriter().write("New Asset Added !");
			}
			break;
		case "refresh":
			break;
		case "update":
			break;
		case "delete":

			break;
		default:
			resp.sendRedirect("index.html");
			break;
		}	
	}
}
