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

@WebServlet(urlPatterns = {"/admin/edit/manufacturer"})
public class EditManufacturerController extends HttpServlet {
    
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
                ManufacturerBean manufacturer = (new ManufacturerDAO()).readById(id);
                request.setAttribute("manufacturer", manufacturer);
                getServletContext().getRequestDispatcher("/WEB-INF/admin/editManufacturer.jsp").forward(request, response);
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
            String dateTime = GenericDAO.getDateTime();
            long id = Long.parseLong(request.getParameter("id").trim());
            String manufacturerName = request.getParameter("manufacturerName").trim();
            String visibility = request.getParameter("visibility").trim();
            long adminId = (long) session.getAttribute("roleId");
            ManufacturerBean manufacturer = new ManufacturerBean();
            manufacturer.setId(id);
            manufacturer.setManufacturerName(manufacturerName);
            manufacturer.setVisibility((byte) (visibility.equals("yes") ? 1 : 0));
            manufacturer.setModifiedDate(dateTime);
            manufacturer.setAdminId(adminId);
            boolean editManufacturerStatus = false;
            ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
            GenericDAO.setAutoCommit(false);
            if (!manufacturerDAO.duplicateManufacturerName(manufacturerName, id)) {
                if (manufacturerDAO.update(manufacturer)) {
                    editManufacturerStatus = true;
                }
            } else {
                request.setAttribute("duplicateManufacturerName", "yes");
            }
            if (editManufacturerStatus) {
                request.setAttribute("editManufacturerStatus", "success");
                GenericDAO.commit();
            } else {
                request.setAttribute("editManufacturerStatus", "failure");
                GenericDAO.rollback();
            }
            GenericDAO.setAutoCommit(true);
            request.setAttribute("manufacturer", manufacturer);
            doGet(request, response);
        } else {
            getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
    }
    
}