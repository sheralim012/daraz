package com.daraz.controller.admin;

import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.dao.CategoryDAO;
import com.daraz.dao.GenericDAO;
import com.daraz.dao.ManufacturerDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/admin/add/new/category"})
public class AddNewCategoryController extends HttpServlet {

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
                getServletContext().getRequestDispatcher("/WEB-INF/admin/addNewCategory.jsp").forward(request, response);
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
                ArrayList<ManufacturerBean> manufacturers = (new ManufacturerDAO()).readAll();
                ArrayList<CategoryBean> categories = (new CategoryDAO()).readAll();
                request.setAttribute("manufacturers", manufacturers);
                request.setAttribute("categories", categories);
                String dateTime = GenericDAO.getDateTime();
                String categoryName = request.getParameter("categoryName").trim();
                String visibility = request.getParameter("visibility").trim();
                long adminId = (long) session.getAttribute("roleId");
                CategoryBean category = new CategoryBean();
                category.setCategoryName(categoryName);
                category.setVisibility((byte) (visibility.equals("yes") ? 1 : 0));
                category.setCreatedDate(dateTime);
                category.setModifiedDate(dateTime);
                category.setAdminId(adminId);
                boolean addNewCategoryStatus = false;
                CategoryDAO categoryDAO = new CategoryDAO();
                if (!categoryDAO.duplicateCategoryName(categoryName)) {
                    if (categoryDAO.create(category) > 0) {
                        addNewCategoryStatus = true;
                    }
                } else {
                    request.setAttribute("duplicateCategoryName", "yes");
                }
                if (addNewCategoryStatus) {
                    request.setAttribute("addNewCategoryStatus", "success");
                } else {
                    request.setAttribute("addNewCategoryStatus", "failure");
                }
                doGet(request, response);
            } else {
                getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
        }
    }
    
}