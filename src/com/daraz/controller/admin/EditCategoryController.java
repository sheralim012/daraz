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

@WebServlet(urlPatterns = {"/admin/edit/category"})
public class EditCategoryController extends HttpServlet {
    
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
                long categoryId = Integer.parseInt(request.getParameter("id"));
                CategoryBean category = (new CategoryDAO()).readById(categoryId);
                request.setAttribute("category", category);
                getServletContext().getRequestDispatcher("/WEB-INF/admin/editCategory.jsp").forward(request, response);
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
                String categoryName = request.getParameter("categoryName").trim();
                String visibility = request.getParameter("visibility").trim();
                long adminId = (long) session.getAttribute("roleId");
                CategoryBean category = new CategoryBean();
                category.setId(id);
                category.setCategoryName(categoryName);
                category.setVisibility((byte) (visibility.equals("yes") ? 1 : 0));
                category.setModifiedDate(dateTime);
                category.setAdminId(adminId);
                boolean editCategoryStatus = false;
                CategoryDAO categoryDAO = new CategoryDAO();
                GenericDAO.setAutoCommit(false);
                if (!categoryDAO.duplicateCategoryName(categoryName, id)) {
                    if (categoryDAO.update(category)) {
                        editCategoryStatus = true;
                    }
                } else {
                    request.setAttribute("duplicateCategoryName", "yes");
                }
                if (editCategoryStatus) {
                    request.setAttribute("editCategoryStatus", "success");
                    GenericDAO.commit();
                } else {
                    request.setAttribute("editCategoryStatus", "failure");
                    GenericDAO.rollback();
                }
                GenericDAO.setAutoCommit(true);
                request.setAttribute("category", category);
                doGet(request, response);
            } else {
                getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
        }
    }
    
}