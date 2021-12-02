<%-- 
    Document   : metodoDePago
    Created on : 29-11-2021, 1:29:59
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
            .mx-3{
                margin-left: 25%
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordenes</title>
        <%@include file="includes/head.jsp" %>
    </head>
    <body>
        <%@include file="includes/navbarorden.jsp" %>
        <h3>Metodos de pago disponibles</h3>
        <div class="login-box">
            <a class="mx-3 btn btn-primary" href="" onclick="alert('El mesero se acercará a su mesa...')">Pagar en efectivo</a>
            <a class="mx-3 btn btn-primary" href="carro-comprar-webpay">Pago webpay</a>
        </div>
        
    </body>
</html>
