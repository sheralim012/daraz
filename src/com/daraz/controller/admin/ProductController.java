package com.daraz.controller.admin;

import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.bean.ProductBean;
import com.daraz.bean.ReviewerBean;
import com.daraz.dao.CategoryDAO;
import com.daraz.dao.ManufacturerDAO;
import com.daraz.dao.ProductDAO;
import com.daraz.dao.ReviewerDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/admin/product"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            byte role = (byte) session.getAttribute("role");
            if (role == (byte) 1) {
                ArrayList<ManufacturerBean> manufacturers = (new ManufacturerDAO()).readAll();
                ArrayList<CategoryBean> categories = (new CategoryDAO()).readAll();
                request.setAttribute("manufacturers", manufacturers);
                request.setAttribute("categories", categories);
                long id = Long.parseLong(request.getParameter("id"));
                ProductBean product = (new ProductDAO()).readById(id);
                request.setAttribute("product", product);
                ReviewerDAO reviewerDAO = new ReviewerDAO();
                ArrayList<ReviewerBean> reviewers = reviewerDAO.readByProductId(id);
                double rating = reviewerDAO.readRatingByProductId(id);
                request.setAttribute("reviewers", reviewers);
                request.setAttribute("rating", rating);
                getServletContext().getRequestDispatcher("/WEB-INF/admin/product.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
        }   
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}