package com.daraz.controller.buyer;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/buyer/removefromcart"})
public class RemoveFromCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String id = request.getParameter("id");
            ArrayList<String> productIds = (ArrayList<String>) session.getAttribute("productIds");
            if (productIds == null) {
                productIds = new ArrayList<>();
            } 
            productIds.remove(id);
            session.setAttribute("productIds", productIds);
            response.getWriter().print("true");
        } else {
            getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
}