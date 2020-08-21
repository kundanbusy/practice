package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;	
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	
	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		List<KitDetail>list=this.kitDAO.getKitDetails(request.getParameter("cid"));
		request.setAttribute("list",list);
		return "showkit.jsp";
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		String pid="";
		String qty="";
		int amt=0;
		Enumeration<String> plist=request.getParameterNames();
		while(plist.hasMoreElements())
		{
			pid=plist.nextElement();
			if(pid.contains("btn")) {
				pid=pid.substring(4);
				qty=request.getParameter("qty_"+pid);
				break;			  
			}
		}
		String cid=request.getParameter("cid");
		ProductMaster product=this.productMasterDao.getARecord(pid);
		double value=Double.parseDouble(product.getCost());
		amt=(int) (value*Integer.parseInt(qty));
		this.kitDAO.insertKit(request.getParameter("cid"), pid, qty, amt);
		List<KitDetail>list=this.kitDAO.getKitDetails(request.getParameter("cid"));
		request.setAttribute("list",list);
		//request.setAttribute("product", product);
		return "showkit.jsp";
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		List<ProductMaster> productlist=this.productMasterDao.getProducts();
		CoronaKit coronaKit=null;
		String pname =request.getParameter("pname");
		String pmail =request.getParameter("pmail");
		String pnum =request.getParameter("pnum");
		String cid=request.getParameter("cid");
		if(request.getParameter("callFromUser")!=null) {
		this.kitDAO.insertUser(pname, pmail, pnum);
		coronaKit=this.kitDAO.getUser(pname, pnum);
		}
		else
		{
		coronaKit=this.kitDAO.getUser(cid);
		}
		request.setAttribute("user", coronaKit);
		request.setAttribute("availableProdList", productlist);
		return "showproductstoadd.jsp";
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * String pname =request.getParameter("pname"); String pmail
		 * =request.getParameter("pmail"); String pnum =request.getParameter("pnum");
		 * this.kitDAO.insertUser(pname, pmail, pnum); return "showproductstoadd.jsp";
		 */
		return "";
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "newuser.jsp";
	}
}