<%-- 
    Document   : balance
    Created on : 23 Aug, 2020, 9:57:32 PM
    Author     : Sai Kumar
--%>









<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
          
            body {
  background-color: #e74c3c;
}

a {
  background-color: #c0392b;
  color: #fff;
  text-align: center;
  width: 200px;
  height: 50px;
  line-height: 50px;
  text-decoration: none;
  border-radius: 2px;
}
a:hover {
  background-color: #a5281b;
}
body > a {
  display: block;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.md-modal {
	position: fixed;
	top: 50%;
	left: 50%;
	width: 50%;
	max-width: 630px;
	min-width: 320px;
	height: auto;
	z-index: 2000;
	visibility: hidden;
	transform: translateX(-50%) translateY(-50%);
}

.md-modal:target {
	visibility: visible;
}

.md-overlay {
	position: fixed;
	width: 100%;
	height: 100%;
	visibility: hidden;
	top: 0;
	left: 0;
	z-index: 1000;
	opacity: 0;
	background: rgba(143,27,15,0.8);
	transition: all 0.3s;
}

.md-modal:target ~ .md-overlay {
	opacity: 1;
	visibility: visible;
}

/* Content styles */
.md-content {
	color: #fff;
	background: #e74c3c;
	position: relative;
	border-radius: 3px;
	margin: 0 auto;
}

.md-content h3 {
	margin: 0;
	padding: 0.4em;
	text-align: center;
	font-size: 2.4em;
	font-weight: 300;
	opacity: 0.8;
	background: rgba(0,0,0,0.1);
	border-radius: 3px 3px 0 0;
}

.md-content > div {
	padding: 15px 40px 30px;
	margin: 0;
	font-weight: 300;
	font-size: 1.15em;
}

.md-content > div p {
	margin: 0;
	padding: 10px 0;
}

.md-content > div ul {
	margin: 0;
	padding: 0 0 30px 20px;
}

.md-content > div ul li {
	padding: 5px 0;
}

.md-content a {
	display: block;
	margin: 0 auto;
	font-size: 0.8em;
}

/* Effect */

.md-modal .md-content {
	-webkit-transform: scale(0.7);
	-moz-transform: scale(0.7);
	-ms-transform: scale(0.7);
	transform: scale(0.7);
	opacity: 0;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
}

.md-modal:target .md-content {
	-webkit-transform: scale(1);
	-moz-transform: scale(1);
	-ms-transform: scale(1);
	transform: scale(1);
	opacity: 1;
}
            
            
            
            </style>
     <%@page import="java.sql.*;"%>
    </head>
    <body>
        
       
        
         <% int u=Integer.parseInt(request.getParameter("upi")); %>
         <%
             try
             {
    
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/internet?zeroDateTimeBehavior=convertToNull","root","");
        Statement st=con.createStatement();
      
       ResultSet rs=st.executeQuery("select * from account where id='"+u+"'");
       int a=0;
       while(rs.next())
       {
         a=rs.getInt("amount");
       }
          
       
       
       %>
     
    
    <a href="#">Balance in your account $<%=a%></a>

<div class="md-modal" id="modal">
	<div class="md-content">
		<h3>$$Balance$$</h3>
		<div>
                   
                        
			<p>okk</p>

			<a class="md-close" href="#">Close</a>
		</div>
	</div>
</div>
<div class="md-overlay"></div>
<%}

    catch(Exception e)
    {
    out.println(e);


}
%>

}
            
            
    </body>
</html>
