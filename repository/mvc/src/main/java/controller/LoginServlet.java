/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import DB.*;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {


   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Standard Controller / servlet construction
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager"); 
        // _____________________________________________
        
        //Get page Parameters//
        String emailUser = request.getParameter("emailuser");
        String password = request.getParameter("password");
        
        session.setAttribute("customer", null);
        session.setAttribute("staff", null);
        session.setAttribute("admin", null);
        //_____________________________________________//
        
        //Standard out//
        validator.clear(session);
        //Page logic 
        
            try{
                Customer customer = manager.findCustomer(emailUser, password);
                Staff staff = manager.findStaff(emailUser, password);
                Admin admin = manager.findAdmin(emailUser, password);
                if(customer != null){
                    // Generate a new access log
                    String logID = manager.addAccessLog(customer.getCustomerId());
                    session.setAttribute("customer", customer); //Primary Session Attribs
                    session.setAttribute("SessionLogId", logID); //Primary
                    request.getRequestDispatcher("mainpage.jsp").include(request, response);
                } else if (staff != null){
                    //generate access log
                     String logID = manager.addAccessLog(staff.getStaffID());
                     session.setAttribute("SessionLogId", logID); //Primary Session Attrib
                    session.setAttribute("staff", staff);      //Primary Session Attribs
                    request.getRequestDispatcher("mainpage.jsp").include(request, response);
                }else if(admin != null){
                    String logID = manager.addAccessLog(admin.getAdminID());
                    session.setAttribute("SessionLogId", logID); //Primary Session Attrib
                    session.setAttribute("admin", admin);      //Primary Session Attribs
                    request.getRequestDispatcher("mainpage.jsp").include(request, response);
                }
                else {
                    session.setAttribute("existErr", "Credentials are incorrect or the account does not exist");
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage() == null ? "Staff or Customer does not exist" : "Successful login");
                
            } 
        }
    }


