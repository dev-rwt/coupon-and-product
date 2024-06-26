package com.devansh.cnp.model;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.devansh.cnp.dao.CouponDAO;


public class CouponController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CouponDAO dao  = new CouponDAO();   

    public CouponController() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("create")) {
			createCoupon(request, response);
		}
		else if(action.equals("find")) {
			findCouponCode(request,response);
		}
		else {
			findAll(request,response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action!=null && action.equals("find")) {
			findCouponCode(request,response);
		}
		findAll(request,response);
	}


	private void createCoupon(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String couponCode = request.getParameter("couponCode");
		String discount = request.getParameter("discount");
		String expiryDate = request.getParameter("expiryDate");
		
		Coupon coupon = new Coupon();
		coupon.setCode(couponCode);
		coupon.setDiscount(new BigDecimal(discount));
		coupon.setExpDate(expiryDate);
		
		dao.save(coupon);
		System.out.println("Reached");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Coupon Created!!</b>");
		out.print("<br/><a href = '/candpapp'>Home</a>");
	}

	public void findCouponCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String couponCode = request.getParameter("couponCode");
		Coupon coupon = dao.findByCode(couponCode);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<b>Coupon Details:</b>");
		out.println(coupon);
		out.print("<br/><a href = '/candpapp'>Home</a>");
		out.flush();
	}
	
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		ArrayList<Coupon> coupons = dao.displayCoupons();
		System.out.println(coupons.size());
		request.setAttribute("list", coupons);
		for(Coupon coup : coupons) {
			System.out.println(coup.getCode());
			}
		
		RequestDispatcher view = request.getRequestDispatcher("displayCoupons.jsp");
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
