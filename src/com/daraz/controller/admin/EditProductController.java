package com.daraz.controller.admin;

import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.bean.ProductBean;
import com.daraz.dao.CategoryDAO;
import com.daraz.dao.GenericDAO;
import com.daraz.dao.ManufacturerDAO;
import com.daraz.dao.ProductDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/admin/edit/product"})
public class EditProductController extends HttpServlet {
    
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
                long id = Integer.parseInt(request.getParameter("id"));
                ProductBean product = (new ProductDAO()).readById(id);
                request.setAttribute("product", product);
                getServletContext().getRequestDispatcher("/WEB-INF/admin/editProduct.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            byte role = (byte) session.getAttribute("role");
            if (role == (byte) 1) {
                String dateTime = GenericDAO.getDateTime();
                long id = Long.parseLong(request.getParameter("id").trim());
                String productName = request.getParameter("productName").trim();
                String imageURL = request.getParameter("imageURL").trim();
                BigDecimal price = new BigDecimal(request.getParameter("price").trim());
                int quantity = Integer.parseInt(request.getParameter("quantity").trim());
                long categoryId = Long.parseLong(request.getParameter("categoryId").trim());
                long manufacturerId = Long.parseLong(request.getParameter("manufacturerId").trim());
                String visibility = request.getParameter("visibility").trim();
                String description = request.getParameter("description").trim();
                long adminId = (long) session.getAttribute("roleId");
                ProductBean product = new ProductBean();
                product.setId(id);
                product.setProductName(productName);
                product.setImageURL(imageURL);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setCategoryId(categoryId);
                product.setManufacturerId(manufacturerId);
                product.setVisibility((byte) (visibility.equals("yes") ? 1 : 0));
                product.setDescription(description);
                product.setModifiedDate(dateTime);
                product.setAdminId(adminId);
                GenericDAO.setAutoCommit(false);
                ProductDAO productDAO = new ProductDAO();
                if (productDAO.update(product)) {
                    GenericDAO.commit();
                    request.setAttribute("editProductStatus", "success");
                } else {
                    GenericDAO.rollback();
                    request.setAttribute("editProductStatus", "failure");
                }
                GenericDAO.setAutoCommit(true);
                request.setAttribute("product", product);
                doGet(request, response);
            } else {
                getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
        }
    }
    
}