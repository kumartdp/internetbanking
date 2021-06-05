/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import java.sql.*;
/**
 *
 * @author Sai Kumar
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String name=request.getParameter("name");
        String gmail=request.getParameter("gmail");
        String card=request.getParameter("card");
        String mobile=request.getParameter("mobile");
        String password=request.getParameter("password");
        String amount=request.getParameter("amount");
        
        
          try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/internet?zeroDateTimeBehavior=convertToNull","root","");  
out.println("connected");
 String sql = "insert into account(id,name,gmail,debit,mobile,pin,amount) values (null,?,?,?,?,?,?)"; 
 PreparedStatement prep = con.prepareStatement(sql); 
 prep.setString(1, name); 
 prep.setString(2, gmail);
 prep.setString(3, card);
 prep.setString(4, mobile);
 prep.setString(5, password);
 prep.setString(6, amount);
 int i=prep.executeUpdate(); 
 if (i>0){
            out.println("inserted");
            
 }
 String sql1 = "insert into login(id,gmail,pin) values (null,?,?)"; 
 PreparedStatement prep1 = con.prepareStatement(sql1); 
 prep1.setString(1, gmail); 
 prep1.setString(2, password);

 int j=prep1.executeUpdate(); 
 
 
 
 
 
 
 
 prep.close();
 con.close();
 


}
          catch(Exception e)

          {
              out.println(e+"error");
              out.println(amount);
              
          }
      
        
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
