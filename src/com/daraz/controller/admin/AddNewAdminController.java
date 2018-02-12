package com.daraz.controller.admin;

import com.daraz.bean.AddressBean;
import com.daraz.bean.AdminBean;
import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.bean.UserBean;
import com.daraz.dao.AddressDAO;
import com.daraz.dao.AdminDAO;
import com.daraz.dao.CategoryDAO;
import com.daraz.dao.GenericDAO;
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

@WebServlet(urlPatterns = {"/admin/add/new/admin"})
public class AddNewAdminController extends HttpServlet {

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
                getServletContext().getRequestDispatcher("/WEB-INF/admin/addNewAdmin.jsp").forward(request, response);
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
        
                String username = request.getParameter("username").trim();
                String password = request.getParameter("password").trim();
                String firstName = request.getParameter("firstName").trim();
                String lastName = request.getParameter("lastName").trim();
                String email = request.getParameter("email").trim();
                String contactNo = request.getParameter("contactNo").trim();
                String street = request.getParameter("street").trim();
                String city = request.getParameter("city").trim();
                String state = request.getParameter("state").trim();
                String country = request.getParameter("country").trim();
                String zip = request.getParameter("zip").trim();

                long userId = 0;
                long adminId = 0;
                long addressId = 0;

                UserBean user = new UserBean();
                AdminBean admin = new AdminBean();
                AddressBean address = new AddressBean();

                user.setUsername(username);
                user.setPwd(password);
                user.setCreatedDate(dateTime);
                user.setModifiedDate(dateTime);
                user.setRole((byte) 1);

                admin.setFirstName(firstName);
                admin.setLastName(lastName);
                admin.setEmail(email);
                admin.setContactNo(contactNo);
                admin.setCreatedDate(dateTime);
                admin.setModifiedDate(dateTime);

                address.setStreet(street);
                address.setCity(city);
                address.setState(state);
                address.setCountry(country);
                address.setZip(zip);
                address.setCreatedDate(dateTime);
                address.setModifiedDate(dateTime);

                UserDAO userDAO = new UserDAO();
                AdminDAO adminDAO = new AdminDAO();
                AddressDAO addressDAO = new AddressDAO();
                boolean addNewAdminStatus = false;
                GenericDAO.setAutoCommit(false);
                if (!userDAO.duplicateUsername(username)) {
                    adminId = adminDAO.create(admin);
                    if (adminId > 0) {
                        addressId = addressDAO.create(address);
                        if (addressId > 0) {
                            user.setRoleId(adminId);
                            user.setAddressId(addressId);
                            userId = userDAO.create(user);
                            if (userId > 0) {
                                addNewAdminStatus = true;
                            }
                        }
                    }
                } else {
                    request.setAttribute("duplicateUsername", "yes");
                }
                if (addNewAdminStatus) {
                    request.setAttribute("addNewAdminStatus", "success");
                    GenericDAO.commit();
                } else {
                    request.setAttribute("addNewAdminStatus", "failure");
                    GenericDAO.rollback();
                }
                GenericDAO.setAutoCommit(true);
                request.setAttribute("user", user);
                request.setAttribute("admin", admin);
                request.setAttribute("address", address);
                doGet(request, response);
            } else {
                getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
        }
    }

}