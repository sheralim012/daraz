package com.daraz.controller.buyer;

import com.daraz.bean.CategoryBean;
import com.daraz.bean.ManufacturerBean;
import com.daraz.bean.ProductBean;
import com.daraz.bean.ReviewBean;
import com.daraz.bean.ReviewerBean;
import com.daraz.dao.CategoryDAO;
import com.daraz.dao.GenericDAO;
import com.daraz.dao.ManufacturerDAO;
import com.daraz.dao.ProductDAO;
import com.daraz.dao.ReviewDAO;
import com.daraz.dao.ReviewerDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/buyer/product"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            byte role = (byte) session.getAttribute("role");
            if (role == (byte) 2) {
                ArrayList<ManufacturerBean> manufacturers = (new ManufacturerDAO()).readByVisibility((byte) 1);
                ArrayList<CategoryBean> categories = (new CategoryDAO()).readByVisibility((byte) 1);
                request.setAttribute("manufacturers", manufacturers);
                request.setAttribute("categories", categories);
                long id = Long.parseLong(request.getParameter("id"));
                ProductBean product = (new ProductDAO()).readByIdWithVisibility((byte) 1, id);
                request.setAttribute("product", product);
                ReviewerDAO reviewerDAO = new ReviewerDAO();
                ArrayList<ReviewerBean> reviewers = reviewerDAO.readByProductId(id);
                double rating = reviewerDAO.readRatingByProductId(id);
                long buyerId = (long) session.getAttribute("roleId");
                if ((new ReviewDAO()).canReview(id, buyerId)) {
                    request.setAttribute("canReview", "yes");
                } else {
                    request.setAttribute("canReview", "no");
                }
                request.setAttribute("reviewers", reviewers);
                request.setAttribute("rating", rating);
                getServletContext().getRequestDispatcher("/WEB-INF/buyer/product.jsp").forward(request, response);
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
                double rating = Double.parseDouble(request.getParameter("rating").trim());
                String message = request.getParameter("message").trim();
                long buyerId = (long) session.getAttribute("roleId");
                long productId = Long.parseLong(request.getParameter("id"));
                ReviewBean review = new ReviewBean();
                review.setMessage(message);
                review.setRating(rating);
                review.setCreatedDate(dateTime);
                review.setModifiedDate(dateTime);
                review.setBuyerId(buyerId);
                review.setProductId(productId);
                String postReviewStatus = "failure";
                ReviewDAO reviewDAO = new ReviewDAO();
                if (reviewDAO.create(review) > 0) {
                    postReviewStatus = "success";
                }
                request.setAttribute("postReviewStatus", postReviewStatus);
                doGet(request, response);
            } else {
                getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/public/logout").forward(request, response);
        }
    }

}
