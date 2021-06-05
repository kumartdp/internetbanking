/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import java.util.Date;
/**
 *
 * @author Sai Kumar
 */
@WebServlet(urlPatterns = {"/Pay"})
public class Pay extends HttpServlet {

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
         
        String upi=request.getParameter("upi");
        String rupi=request.getParameter("rupi");
        String pin=request.getParameter("pass");
        String money=request.getParameter("money");
          try{
            
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/internet?zeroDateTimeBehavior=convertToNull","root","");  
out.println("connected");
  Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM account");
            int flag1=0;
            int flag2=0;
            int cr=0;
            int bal=0;
            int a=Integer.parseInt(upi);
             int b=Integer.parseInt(rupi);
             int a1=Integer.parseInt(money);
         while (rs.next()) {
            
            int i = rs.getInt("id");
            int balance = rs.getInt("amount");
             int pintest = rs.getInt("pin");
             out.println(i);
              out.println(balance);
               out.println(pintest);
                out.println(Integer.parseInt(upi));
            if(i==Integer.parseInt(upi))
            {
                
                flag1=1;
                if(balance>=Integer.parseInt(money))
                {
                    out.println("balance");
                    bal=1;
                    if(pintest==Integer.parseInt(pin))
                    {
                        out.println("pin ok");
                        cr=1;
                    }
                    
                }
            }
            if(i==Integer.parseInt(rupi))
            {
                flag2=1;
            }
            
            
            
            
            
         }
         if(flag1==0 & flag2==1 & bal==1 & cr==1)
         {
             out.println("invalid upi");
             
             
             
         }
         else if(flag1==1& flag2==0 & bal==1 & cr==1)
         {
          out.println("invalid reciever upi"); 
         }
         else if(flag1==1 & flag2==1 & bal==0 & cr==1)
         {
             out.println("insufficient funds"); 
         }
         else if(flag1==1 & flag2==1 & bal==1 & cr==0)
         {
               out.println("invalid pin");   
         }
         else if(flag1==1 & flag2==1 & bal==1 & cr==1)
         {
         
               
             String sql1 = "update account set amount=amount - ? where id=?";
               out.println("okkkkkk");  
 PreparedStatement prep1 = con.prepareStatement(sql1);
int k=Integer.parseInt(money);
int m=Integer.parseInt(upi);
 prep1.setInt(1, k); 
 prep1.setInt(2,m);
prep1.executeUpdate();
out.println("1");

    String sql2= "update account set amount=amount + ? where id=?";
                  
 PreparedStatement prep2 = con.prepareStatement(sql2);
int k1=Integer.parseInt(money);
int m1=Integer.parseInt(rupi);
 prep2.setInt(1, k1); 
 prep2.setInt(2,m1); 
 prep2.executeUpdate();
 Date d=new Date();
 String date=d.toString();
 out.println("2");
 String sql3="insert into transaction (tid,upi,rupi,amount,date) values(null,?,?,?,?)";
 PreparedStatement prep3 = con.prepareStatement(sql3);

 prep3.setInt(1, a); 
 prep3.setInt(2,b); 
 prep3.setInt(3,a1); 
 prep3.setString(4,date); 
 prep3.executeUpdate();
 out.println("3");
  response.sendRedirect("index.html");
         }
             
             
             
             
             
           
         else
         {
              response.sendRedirect("invalid try again!!!");
         }
         
                
                

           
    }
          catch(Exception e)
          {
              out.println(e);
              
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

