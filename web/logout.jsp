<%-- 
    Document   : logout
    Created on : 13-10-2021, 17:08:08
    Author     : Felipe Fuentes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0); 
        %>
    <div onload="CerrarSesion" method="POST">
        
    </div>
        <script type="text/javascript">
            if (window.performance && window.performance.navigation.type === window.performance.navigation.TYPE_BACK_FORWARD) {
                location.reload();
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Siglo XXI</title>
    <form ></form>
    </head>
    
    <body>
    </body>
</html>
