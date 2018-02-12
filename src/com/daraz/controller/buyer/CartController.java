package com.daraz.controller.buyer;

import com.daraz.bean.BuyerOrderBean;
import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.bean.OrderDetailBean;
import com.daraz.bean.ProductBean;
import com.daraz.dao.BuyerOrderDAO;
import com.daraz.dao.CategoryDAO;
import com.daraz.dao.GenericDAO;
import com.daraz.dao.ManufacturerDAO;
import com.daraz.dao.OrderDetailDAO;
import com.daraz.dao.ProductDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/buyer/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            byte role = (byte) session.getAttribute("role");
            if (role == (byte) 2) {
                ArrayList<ManufacturerBean> manufacturers = (new ManufacturerDAO()).readByVisibility((byte) 1);
                ArrayList<CategoryBean> categories = (new CategoryDAO()).readByVisibility((byte) 1);
                ArrayList<ProductBean> products = (new ProductDAO()).readAllWithVisibility((byte) 1);
                request.setAttribute("manufacturers", manufacturers);
                request.setAttribute("categories", categories);
                request.setAttribute("products", products);
                ArrayList<String> productIds = (ArrayList<String>) session.getAttribute("productIds");
                request.setAttribute("productIds", productIds);
                getServletContext().getRequestDispatcher("/WEB-INF/buyer/cart.jsp").forward(request, response);
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
                long buyerId = (long) session.getAttribute("roleId");
                ArrayList<ProductBean> products = (new ProductDAO()).readAllWithVisibility((byte) 1);
                ArrayList<String> productIds = (ArrayList<String>) session.getAttribute("productIds");
                GenericDAO.setAutoCommit(false);
                BuyerOrderBean buyerOrder = new BuyerOrderBean();
                buyerOrder.setCreatedDate(dateTime);
                buyerOrder.setBuyerId(buyerId);
                BuyerOrderDAO buyerOrderDAO = new BuyerOrderDAO();
                long buyerOrderId = buyerOrderDAO.create(buyerOrder);
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                boolean buyerOrderStatus = false;
                if (buyerOrderId > 0) {
                    if (products != null && productIds != null) {
                        for (String productId : productIds) {
                            buyerOrderStatus = false;
                            for (ProductBean product : products) {
                                if (Long.parseLong(productId) == product.getId()) {
                                    OrderDetailBean orderDetail = new OrderDetailBean();
                                    int quantity = Integer.parseInt(request.getParameter(product.getId() + "").trim());
                                    orderDetail.setQuantity(quantity);
                                    orderDetail.setStatus((byte) 1);
                                    orderDetail.setProductId(product.getId());
                                    orderDetail.setBuyerOrderId(buyerOrderId);
                                    if (orderDetailDAO.create(orderDetail) > 0) {
                                        buyerOrderStatus = true;
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
                    }
                }
                if (buyerOrderStatus) {
                    session.setAttribute("productIds", null);
                    request.setAttribute("buyerOrderStatus", "success");
                    GenericDAO.commit();
                } else {
                    request.setAttribute("buyerOrderStatus", "failure");
                    GenericDAO.rollback();
                }
                GenericDAO.setAutoCommit(true);
                doGet(request, response);
            } else {
                getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
        }
    }

}