package app.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

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
			if (numberOfAction<0) {
				resp.getWriter().write("You can't have a negative number of action");
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
		case "update":
			if (req.getParameter("numberofaction") == null && req.getParameter("numberofaction").equals("")) {
				resp.getWriter().write("Please fill all the field");
				break;
			}
			try {
				System.out.println(req.getParameter("numberofaction"));
				System.out.println(req.getParameter("id"));
				numberOfAction = Integer.parseInt(req.getParameter("numberofaction"));
				id = Integer.parseInt(req.getParameter("id"));
			} catch (NumberFormatException e) {
				resp.getWriter().write("Wrong number format, please insert a number");
				break;
			}
			if (numberOfAction<0) {
				resp.getWriter().write("You can't have a negative number of action");
				break;
			}
			try {
				asset = serv.getById(id).get();
			} catch (NoSuchElementException e) {
				resp.getWriter().write("Wrong id");
				break;
			}
			asset.setNumberOfAction(numberOfAction);
			System.out.println(asset);
			if (serv.update(asset)) {
				resp.getWriter().write("Asset Updated !");
			} else {
				resp.getWriter().write("Failed Update");
			}
			break;
		case "remove":
			if (req.getParameter("id").equals("")) {
				resp.getWriter().write("You need an ID, did you lost it ?");
				break;
			}
			try {
				id = Integer.parseInt(req.getParameter("id"));
			} catch (NumberFormatException e) {
				resp.getWriter().write("Id need to be a number");
				break;
			}
			if (id<1) {
				resp.getWriter().write("Id need to be positive ! Smile and be positive");
				break;
			}
			if (!serv.delete(id)) {
				resp.getWriter().write("Failed Deletion");
			} else {
				resp.getWriter().write("Asset removed !");
			}
			break;
		default:
			resp.sendRedirect("index.html");
			break;
		}	
	}
}
