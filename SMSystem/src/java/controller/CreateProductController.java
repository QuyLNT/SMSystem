/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product.ProductDAO;
import model.product.ProductDTO;
import model.product.ProductImageDAO;

/**
 *
 * @author dell
 */
@WebServlet(name = "CreateProductController", urlPatterns = {"/CreateProductController"})
public class CreateProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int brandId = Integer.parseInt(request.getParameter("brandID"));
        int userOjectId = Integer.parseInt(request.getParameter("userObjectID"));
        String detail = request.getParameter("detail");
        boolean hot = request.getParameter("hot") != null;
        String name = request.getParameter("Name");
        String color = request.getParameter("color");
        float price = Float.parseFloat(request.getParameter("price"));
        float sale = Float.parseFloat(request.getParameter("sale"));
        int warrantyPeriod = Integer.parseInt(request.getParameter("warrantyPeriod"));
        boolean productStaus = Boolean.parseBoolean(request.getParameter("productStatus"));

        ProductDTO product = new ProductDTO(0, brandId, userOjectId, detail, hot, name, color, price, sale, warrantyPeriod, productStaus);
        ProductDAO dao = new ProductDAO();
        ProductImageDAO imageDao = new ProductImageDAO();
        
        try {
            int createProduct = dao.addProduct(product);

            request.setAttribute("ms", "Product created successfully");

            String[] imageUrls = new String[4];
            imageUrls[0] = request.getParameter("productImage1");
            imageUrls[1] = request.getParameter("productImage2");
            imageUrls[2] = request.getParameter("productImage3");
            imageUrls[3] = request.getParameter("productImage4");

            for (String imageUrl : imageUrls) {
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    imageDao.addProductImage(createProduct, imageUrl);
                }
            }

        } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("err", "Failed to create product: " + e.getMessage());
        }

        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
