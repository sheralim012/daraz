package com.daraz.controller.publiccontroller;

import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.bean.UserBean;
import com.daraz.dao.CategoryDAO;
import com.daraz.dao.ManufacturerDAO;
import com.daraz.dao.UserDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/public/login"})
public class LogInController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ArrayList<ManufacturerBean> manufacturers = (new ManufacturerDAO()).readByVisibility((byte) 1);
        ArrayList<CategoryBean> categories = (new CategoryDAO()).readByVisibility((byte) 1);
        request.setAttribute("manufacturers", manufacturers);
        request.setAttribute("categories", categories);
        getServletContext().getRequestDispatcher("/WEB-INF/public/logIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        UserDAO userDAO = new UserDAO();
        UserBean user = userDAO.readByUsername(username);
        if (user != null && user.getUsername().equals(username) && user.getPwd().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("id", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            session.setAttribute("roleId", user.getRoleId());
            session.setAttribute("addressId", user.getAddressId());
            byte role = user.getRole();
            switch (role) {
                case 1:
                    getServletContext().getRequestDispatcher("/admin/index").forward(request, response);
                    return;
                case 2:
                    getServletContext().getRequestDispatcher("/buyer/index").forward(request, response);
                    return;
                default:
                    getServletContext().getRequestDispatcher("/public/index").forward(request, response);
                    return;
            }
        } else {
            request.setAttribute("logInStatus", "failure");
        }
        doGet(request, response);
    }

}