package com.daraz.controller.admin;

import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.bean.ProductBean;
import com.daraz.dao.CategoryDAO;
import com.daraz.dao.ManufacturerDAO;
import com.daraz.dao.ProductDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/admin/products/category"})
public class CategoryProductsController extends HttpServlet {
    
    private final int perPage = 4;
    
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
                ProductDAO productDAO = new ProductDAO();
                long categoryId = Long.parseLong(request.getParameter("id"));
                String query = request.getParameter("query");
                String currentPageString = request.getParameter("currentPage");
                int currentPage = (currentPageString == null) ? 1 : Integer.parseInt(currentPageString);
                long total = 0;
                if (query == null || query.isEmpty()) {
                    total = productDAO.countByCategory(categoryId);
                } else {
                    total = productDAO.countByCategoryAndQuery(categoryId, query);
                }
                int offset = (currentPage - 1) * perPage;
                int totalPages = (int) Math.ceil((double) total / perPage);
                ArrayList<ProductBean> products = null;
                if (query == null || query.isEmpty()) {
                    products = productDAO.readLimitedByCategory(categoryId, perPage, offset);
                } else {
                    products = productDAO.readLimitedByCategoryAndQuery(categoryId, query, perPage, offset);
                }
                request.setAttribute("id", categoryId);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("products", products);
                request.setAttribute("query", query);
                getServletContext().getRequestDispatcher("/WEB-INF/admin/categoryProducts.jsp").forward(request, response);
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