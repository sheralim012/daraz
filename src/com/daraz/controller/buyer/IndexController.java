package com.daraz.controller.buyer;

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

@WebServlet(urlPatterns = {"/buyer/index"})
public class IndexController extends HttpServlet {
    
    private final int perPage = 4;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            byte role = (byte) session.getAttribute("role");
            if (role == (byte) 2) {
                ArrayList<ManufacturerBean> manufacturers = (new ManufacturerDAO()).readByVisibility((byte) 1);
                ArrayList<CategoryBean> categories = (new CategoryDAO()).readByVisibility((byte) 1);
                request.setAttribute("manufacturers", manufacturers);
                request.setAttribute("categories", categories);
                ProductDAO productDAO = new ProductDAO();
                String query = request.getParameter("query");
                String currentPageString = request.getParameter("currentPage");
                int currentPage = (currentPageString == null) ? 1 : Integer.parseInt(currentPageString);
                long total = 0;
                if (query == null || query.isEmpty()) {
                    total = productDAO.countWithVisibility((byte) 1);
                } else {
                    total = productDAO.countByQueryWithVisibility((byte) 1, query);
                }
                int offset = (currentPage - 1) * perPage;
                int totalPages = (int) Math.ceil((double) total / perPage);
                ArrayList<ProductBean> products = null;
                if (query == null || query.isEmpty()) {
                    products = productDAO.readLimitedWithVisibility((byte) 1, perPage, offset);
                } else {
                    products = productDAO.readLimitedByQueryWithVisibility((byte) 1, query, perPage, offset);
                }
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("products", products);
                request.setAttribute("query", query);
                getServletContext().getRequestDispatcher("/WEB-INF/buyer/index.jsp").forward(request, response);
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