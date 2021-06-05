<%-- 
    Document   : newjsp
    Created on : 23 Aug, 2020, 7:59:20 PM
    Author     : Sai Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <style>
            
            body
            {
                background-color:coral;
            }
            .main
            
            {
                margin-top: 300px;
                margin-bottom: 200px;
                display:inline-block;
                margin-left: 600px;
                
                
            }
           

        </style>
         <%@page import="java.sql.*;"%>
    </head>
    <body>
        <div class="main">
        <% int u=Integer.parseInt(request.getParameter("upi")); %>
        <%
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/internet?zeroDateTimeBehavior=convertToNull","root","");
        Statement st=con.createStatement();
        
       
         
        ResultSet rs=st.executeQuery("select * from transaction where upi='"+u+"'");
       
        
    %><table border=1 align=center style="text-align:center">
      <thead>
          <tr>
             <th>transaction id</th>
             <th>upi</th>
             <th>rupi</th>
             <th>money</th>
             <th>Date</th>
          </tr>
      </thead>
      <tbody>
        <%while(rs.next())
        {
            %>
            <tr>
                <td><%=rs.getInt("tid") %></td>
                 <td><%=rs.getInt("upi") %></td>
                  <td><%=rs.getInt("rupi") %></td>
                   <td><%=rs.getInt("amount") %></td>
               
                <td><%=rs.getString("date") %></td>
            </tr>
            <%}%>
           </tbody>
        </table><br>
    <%}
    catch(Exception e){
        out.print(e.getMessage());%><br><%
    }
    
    %>
        </div>
        
    </body>
</html>
