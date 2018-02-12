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

@WebServlet(urlPatterns = {"/admin/add/new/manufacturer"})
public class AddNewManufacturerController extends HttpServlet {

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
                getServletContext().getRequestDispatcher("/WEB-INF/admin/addNewManufacturer.jsp").forward(request, response);
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
                String manufacturerName = request.getParameter("manufacturerName").trim();
                String visibility = request.getParameter("visibility").trim();
                long adminId = (long) session.getAttribute("roleId");
                ManufacturerBean manufacturer = new ManufacturerBean();
                manufacturer.setManufacturerName(manufacturerName);
                manufacturer.setVisibility((byte) (visibility.equals("yes") ? 1 : 0));
                manufacturer.setCreatedDate(dateTime);
                manufacturer.setModifiedDate(dateTime);
                manufacturer.setAdminId(adminId);
                boolean addNewManufacturerStatus = false;
                ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
                if (!manufacturerDAO.duplicateManufacturerName(manufacturerName)) {
                    if (manufacturerDAO.create(manufacturer) > 0) {
                        addNewManufacturerStatus = true;
                    }
                } else {
                    request.setAttribute("duplicateManufacturerName", "yes");
                }
                if (addNewManufacturerStatus) {
                    request.setAttribute("addNewManufacturerStatus", "success");
                } else {
                    request.setAttribute("addNewManufacturerStatus", "failure");
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