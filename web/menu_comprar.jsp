<%-- 
    Document   : menu_comprar
    Created on : 04-11-2021, 23:16:33
    Author     : diego
--%>
<%@page import="Modelo.Carro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Menu"%>
<%@page import="Modelo.MenuDAO"%>
<%
    MenuDAO dao = new MenuDAO();
    List<Menu> Menus = dao.listarMenu();
    
        ArrayList<Carro> carro_list = (ArrayList<Carro>) session.getAttribute("carro-list");
    if(carro_list != null){
        request.setAttribute("carro_list", carro_list);
    }
    %>
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
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú Restaurant Siglo XII</title>
        <link rel="icon" type="image/png" sizes="32x32" href="icono/favicon-32x32.png">
        <%@include file="includes/head.jsp" %>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>
        
        <div>
            <%     
                            if (request.getAttribute("ErrorSalir") != (null) && (Boolean)request.getAttribute("ErrorSalir") == true ){
                                out.print("<div style='text-align: center;'>"
                                        + "</br>"
                                        + "<p style='color: red'> Tiene una boleta/orden pendiente de pago, por favor pagar antés de salir. </p>"
                                        + "</div>"
                                );
                            }
                        %>
                    <div class="row">
			<%
			if (!Menus.isEmpty()) {
				for (Menu p : Menus) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="menu-image/<%=p.getImagen()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getNombre_menu()%></h5>
						<h6 class="Descripcion">Descripción: <%=p.getDescripcion_menu()%></h6>
						<h6 class="Precio">Precio: $<%=p.getValor_menu()%></h6>
						<div class="mt-3 d-flex justify-content-between">
                                                    <a href="add-al-carro?id_menu=<%=p.getId_menu() %>" class="btn btn-primary btn-block btn-flat" onclick="alert('Menú añadido al carro')">Añadir al carro</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("No hay menús");
			}
			%>
                </div>
            </div>
    </body>
</html>
