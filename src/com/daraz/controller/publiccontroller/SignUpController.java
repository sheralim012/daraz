package com.daraz.controller.publiccontroller;

import com.daraz.bean.AddressBean;
import com.daraz.bean.BuyerBean;
import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.bean.UserBean;
import com.daraz.dao.AddressDAO;
import com.daraz.dao.BuyerDAO;
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

@WebServlet(urlPatterns = {"/public/signup"})
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ManufacturerBean> manufacturers = (new ManufacturerDAO()).readByVisibility((byte) 1);
        ArrayList<CategoryBean> categories = (new CategoryDAO()).readByVisibility((byte) 1);
        request.setAttribute("manufacturers", manufacturers);
        request.setAttribute("categories", categories);
        getServletContext().getRequestDispatcher("/WEB-INF/public/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        long buyerId = 0;
        long addressId = 0;
        
        UserBean user = new UserBean();
        BuyerBean buyer = new BuyerBean();
        AddressBean address = new AddressBean();
        
        user.setUsername(username);
        user.setPwd(password);
        user.setCreatedDate(dateTime);
        user.setModifiedDate(dateTime);
        user.setRole((byte) 2);
        
        buyer.setFirstName(firstName);
        buyer.setLastName(lastName);
        buyer.setEmail(email);
        buyer.setContactNo(contactNo);
        buyer.setCreatedDate(dateTime);
        buyer.setModifiedDate(dateTime);
        
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setZip(zip);
        address.setCreatedDate(dateTime);
        address.setModifiedDate(dateTime);
        
        UserDAO userDAO = new UserDAO();
        BuyerDAO buyerDAO = new BuyerDAO();
        AddressDAO addressDAO = new AddressDAO();
        boolean signUpStatus = false;
        GenericDAO.setAutoCommit(false);
        if (!userDAO.duplicateUsername(username)) {
            buyerId = buyerDAO.create(buyer);
            if (buyerId > 0) {
                addressId = addressDAO.create(address);
                if (addressId > 0) {
                    user.setRoleId(buyerId);
                    user.setAddressId(addressId);
                    userId = userDAO.create(user);
                    if (userId > 0) {
                        signUpStatus = true;
                    }
                }
            }
        } else {
            request.setAttribute("duplicateUsername", "yes");
        }
        if (signUpStatus) {
            request.setAttribute("signUpStatus", "success");
            GenericDAO.commit();
        } else {
            request.setAttribute("signUpStatus", "failure");
            GenericDAO.rollback();
        }
        GenericDAO.setAutoCommit(true);
        request.setAttribute("user", user);
        request.setAttribute("admin", buyer);
        request.setAttribute("address", address);
        doGet(request, response);
    }
    
}