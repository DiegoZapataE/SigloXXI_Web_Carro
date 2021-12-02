<%-- 
    Document   : menu_carro
    Created on : 05-11-2021, 1:09:35
    Author     : diego
--%>
<%@page import="Modelo.MenuDAO"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Carro"%>
<%@page import="java.util.*"%>
<%
    ArrayList<Carro> carro_list = (ArrayList<Carro>) session.getAttribute("carro-list");
    List<Carro> carroMenus = null;
    if(carro_list != null){
        MenuDAO dao = new MenuDAO();
        carroMenus = dao.getCarroMenu(carro_list);
        int total = dao.getTotalValorMenu(carro_list);
        request.setAttribute("carro_list", carro_list);
        request.setAttribute("total", total);
    }
    %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            .logo{
                cursor: pointer;
                width: 100px;
                height: 100px;
            }
            .table tbody td{
                vertical-align: middle;
            }
            .btn-incre,.btn-decre{
                box-shadow: none;
                font_size: 25px;
            }
            .btn:hover{
            background-color: darkcyan;
            color: white;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carro Restaurant Siglo XXI</title>
        <link rel="icon" type="image/png" sizes="32x32" href="icono/favicon-32x32.png">
        <%@include file="includes/head.jsp" %>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>
        
        <div class="container">
            <%     
                            if (request.getAttribute("ErrorSalir") != (null) && (Boolean)request.getAttribute("ErrorSalir") == true ){
                                out.print("<div style='text-align: center;'>"
                                        + "</br>"
                                        + "<p style='color: red'> Tiene una boleta/orden pendiente de pago, por favor pagar antés de salir. </p>"
                                        + "</div>"
                                );
                            }
                        %>
            <div class="d-flex py-3">
                <h3>Precio total: $ ${ (total>0)?total:0}</h3>
                <a class="mx-3 btn btn-primary" href="carro-comprar" onclick="alert('La orden será generada...')">Generar orden</a>
                <!---<a class="mx-3 btn btn-primary" href="carro-comprar-webpay">Pago digital</a>--->
            </div>
            <table class="table table-loght">
                <thead>
                    <tr>
                        <th scope="col">Nombre de menú </th>
                        <th scope="col">Descripción </th>
                        <th scope="col">Precio </th>
                        <th scope="col">Cantidad </th>
                        <th scope="col">Cancelar </th>
                    </tr>
                </thead>
                <tbody>
                    <% if(carro_list != null){
                        for(Carro c:carroMenus){
                    %>
                        <tr>
                        <td><%= c.getNombre_menu() %></td>
                        <td><%= c.getDescripcion_menu() %></td>
                        <td>$<%= c.getValor_menu() %></td>
                        <td>
                            <form action="comprar-ahora" method="post" class="form-inline">
                                <input type="hidden" name="id_menu" value="<%= c.getId_menu() %>" class="form-input">
                                <div class="form-group d-flex justify-content-between">
                                <a class="btn btn-sm btn-decre" href="cantidad-inc-dec?action=dec&id_menu=<%= c.getId_menu() %>"><i class="fas fa-minus-square"></i></a>
                                <input type="text" name="cantidad" class="form-control_w-" value="<%= c.getCantidad() %>" readonly>
                                <a class="btn btn-sm btn-incre" href="cantidad-inc-dec?action=inc&id_menu=<%= c.getId_menu() %>"><i class="fas fa-plus-square"></i></a>
                                </div>
                            </form>
                        </td>
                        <td><a class="btn btn-sm btn-danger" href="remover-del-carro?id_menu=<%= c.getId_menu() %>">Eliminar</a></td>
                    </tr>
                        <%}
                    }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
