/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;
import DB.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class unRegisterController extends HttpServlet {

    
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
        
        //Standard Controller / servlet construction
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager"); 
        // _____________________________________________
        
                //Standard out//
        validator.clear(session);
        
         try {
            Customer customer = (Customer) session.getAttribute("customer");
            Staff staff = (Staff) session.getAttribute("staff");
            
            if(customer != null && staff == null){
                //logic to remove their access logs first. 
                ArrayList<AccessLog> Logs = (ArrayList<AccessLog>) manager.findAccessLogs(customer.getCustomerId(), null, null);
                //ArrayList<Payment> Cards = (ArrayList<Payment>) manager.findPaymentDetails(customer.getCustomerId());
                for(AccessLog log : Logs){
                    manager.deleteAccessLog(log.getLogID()); //delete their access logs
                }
                //for(Payment card : Cards){
                //    manager.deletePaymentDetails(card.getPaymentID());
                //}
                session.setAttribute("customer", null);
                manager.deleteCustomer(customer.getCustomerId()); //delete the customer

                // I also need functions to delete payment information 

                request.getRequestDispatcher("index.jsp").include(request, response); //send em back to index
            } else {
                //logic to remove their access logs first. 
                ArrayList<AccessLog> Logs = (ArrayList<AccessLog>) manager.findAccessLogs(staff.getStaffID(), null, null);
                for(AccessLog log : Logs){
                    manager.deleteAccessLog(log.getLogID()); //delete their access logs
                }
                session.setAttribute("staff", null);
                manager.deleteStaff(staff.getStaffID()); //delete the customer

                // I also need functions to delete payment information 

                request.getRequestDispatcher("index.jsp").include(request, response); //send em back to index
                
            }
            //session.invalidate(); //kill the session
        } catch (SQLException ex) {
            Logger.getLogger(DeleteCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
