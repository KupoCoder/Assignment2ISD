/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DB.*;
import model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class editCustomer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Standard Controller / servlet construction
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager"); 
        // _____________________________________________
                //Standard out//
           validator.clear(session);   
            try {
                String customerID = request.getParameter("custId");
                String staffID = request.getParameter("staffId");
                Customer editCus = (Customer) manager.findCustomerID(customerID);
                Staff editStaff = (Staff) manager.findStaffID(staffID);
                session.setAttribute("editCus", editCus);
                session.setAttribute("editStaff", editStaff);
                System.out.println(customerID);
                 request.getRequestDispatcher("CustomerManagerEdit.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(editCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
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
