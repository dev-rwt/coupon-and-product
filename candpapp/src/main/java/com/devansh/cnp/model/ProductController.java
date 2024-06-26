package com.devansh.cnp.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import com.devansh.cnp.dao.CouponDAO;
import com.devansh.cnp.dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CouponDAO couponDAO = new CouponDAO();
	ProductDAO productDAO = new ProductDAO();

    public ProductController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String couponCode = request.getParameter("couponCode");
		Coupon coupon = couponDAO.findByCode(couponCode);
		
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(new BigDecimal(price).subtract(coupon.getDiscount()));
		
		productDAO.save(product);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Product Created!!</b>");
		out.print("<br/><a href = '/candpapp'>Home</a>");
	}

}
