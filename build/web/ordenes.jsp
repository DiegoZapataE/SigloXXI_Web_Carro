<%-- 
    Document   : ordenes
    Created on : 07-11-2021, 0:18:48
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .logo{
                cursor: pointer;
                width: 100px;
                height: 100px;
                
            }
            .btn:hover{
            background-color: darkcyan;
            color: white;
            }
            h3{
                margin-left: 25%;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordenes</title>
        <%@include file="includes/head.jsp" %>
    </head>
    <body>
        <%@include file="includes/navbarorden.jsp" %>
        <h3>La orden ha sido generada con exito...</h3>
        <h3>Gracias por su paciencia...</h3>
    </body>
</html>
