package com.daraz.controller.buyer;

import com.daraz.bean.AddressBean;
import com.daraz.bean.BuyerBean;
import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.bean.ProductBean;
import com.daraz.bean.UserBean;
import com.daraz.dao.AddressDAO;
import com.daraz.dao.BuyerDAO;
import com.daraz.dao.CategoryDAO;
import com.daraz.dao.GenericDAO;
import com.daraz.dao.ManufacturerDAO;
import com.daraz.dao.ProductDAO;
import com.daraz.dao.UserDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/buyer/edit/profile"})
public class EditProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            byte role = (byte) session.getAttribute("role");
            if (role == (byte) 2) {
                long userId = (long) session.getAttribute("id");
                long buyerId = (long) session.getAttribute("roleId");
                long addressId = (long) session.getAttribute("addressId");
                UserBean user = (new UserDAO()).readById(userId);
                BuyerBean buyer = (new BuyerDAO()).readById(buyerId);
                AddressBean address = (new AddressDAO()).readById(addressId);
                request.setAttribute("user", user);
                request.setAttribute("buyer", buyer);
                request.setAttribute("address", address);
                getServletContext().getRequestDispatcher("/WEB-INF/buyer/editProfile.jsp").forward(request, response);
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
            if (role == (byte) 2) {
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
                long userId = (long) session.getAttribute("id");
                long buyerId = (long) session.getAttribute("roleId");
                long addressId = (long) session.getAttribute("addressId");
                UserBean user = new UserBean();
                BuyerBean buyer = new BuyerBean();
                AddressBean address = new AddressBean();
                user.setId(userId);
                user.setUsername(username);
                user.setPwd(password);
                user.setModifiedDate(dateTime);
                buyer.setId(buyerId);
                buyer.setFirstName(firstName);
                buyer.setLastName(lastName);
                buyer.setEmail(email);
                buyer.setContactNo(contactNo);
                buyer.setModifiedDate(dateTime);
                address.setId(addressId);
                address.setStreet(street);
                address.setCity(city);
                address.setState(state);
                address.setCountry(country);
                address.setZip(zip);
                address.setModifiedDate(dateTime);
                UserDAO userDAO = new UserDAO();
                BuyerDAO buyerDAO = new BuyerDAO();
                AddressDAO addressDAO = new AddressDAO();
                boolean editProfileStatus = false;
                GenericDAO.setAutoCommit(false);
                if (!userDAO.duplicateUsername(username, userId)) {
                    if (userDAO.update(user)) {
                        if (buyerDAO.update(buyer)) {
                            if (addressDAO.update(address)) {
                                session.setAttribute("username", username);
                                editProfileStatus = true;
                            }
                        }
                    }
                } else {
                    request.setAttribute("duplicateUsername", "yes");
                }
                if (editProfileStatus) {
                    request.setAttribute("editProfileStatus", "success");
                    GenericDAO.commit();
                } else {
                    request.setAttribute("editProfileStatus", "failure");
                    GenericDAO.rollback();
                }
                GenericDAO.setAutoCommit(true);
                request.setAttribute("user", user);
                request.setAttribute("buyer", buyer);
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