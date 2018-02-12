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

@WebServlet(urlPatterns = {"/admin/manage/products"})
public class ManageProductsController extends HttpServlet {
    
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
                String currentPageString = request.getParameter("currentPage");
                int currentPage = (currentPageString == null) ? 1 : Integer.parseInt(currentPageString);
                long total = productDAO.countAll();
                int offset = (currentPage - 1) * perPage;
                int totalPages = (int) Math.ceil((double) total / perPage);
                ArrayList<ProductBean> limitedProducts = productDAO.readLimited(perPage, offset);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("limitedProducts", limitedProducts);
                getServletContext().getRequestDispatcher("/WEB-INF/admin/manageProducts.jsp").forward(request, response);
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