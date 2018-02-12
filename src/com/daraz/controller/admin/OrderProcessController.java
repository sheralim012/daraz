package com.daraz.controller.admin;

import com.daraz.bean.OrderDetailBean;
import com.daraz.dao.OrderDetailDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/admin/order/process"})
public class OrderProcessController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            byte role = (byte) session.getAttribute("role");
            if (role == (byte) 1) {
                long id = Long.parseLong(request.getParameter("id"));
                byte status = Byte.parseByte(request.getParameter("status"));
                OrderDetailBean orderDetail = new OrderDetailBean();
                orderDetail.setId(id);
                orderDetail.setStatus(status);
                String orderProcessStatus = "failure";
                if ((new OrderDetailDAO()).update(orderDetail)) {
                    orderProcessStatus = "success";
                }
                request.setAttribute("orderProcessStatus", orderProcessStatus);
                getServletContext().getRequestDispatcher("/admin/orders").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}