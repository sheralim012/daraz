package com.daraz.controller.publiccontroller;

import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.bean.ProductBean;
import com.daraz.bean.ReviewerBean;
import com.daraz.dao.CategoryDAO;
import com.daraz.dao.ManufacturerDAO;
import com.daraz.dao.ProductDAO;
import com.daraz.dao.ReviewDAO;
import com.daraz.dao.ReviewerDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/public/product"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ManufacturerBean> manufacturers = (new ManufacturerDAO()).readByVisibility((byte) 1);
        ArrayList<CategoryBean> categories = (new CategoryDAO()).readByVisibility((byte) 1);
        request.setAttribute("manufacturers", manufacturers);
        request.setAttribute("categories", categories);
        long id = Long.parseLong(request.getParameter("id"));
        ProductBean product = (new ProductDAO()).readByIdWithVisibility((byte) 1, id);
        request.setAttribute("product", product);
        ReviewerDAO reviewerDAO = new ReviewerDAO();
        ArrayList<ReviewerBean> reviewers = reviewerDAO.readByProductId(id);
        double rating = reviewerDAO.readRatingByProductId(id);
        request.setAttribute("reviewers", reviewers);
        request.setAttribute("rating", rating);
        getServletContext().getRequestDispatcher("/WEB-INF/public/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}