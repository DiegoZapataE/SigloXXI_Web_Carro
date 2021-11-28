<%-- 
    Document   : index
    Created on : 05-09-2021, 21:52:25
    Author     : Felipe Fuentes
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!--- Redireccionar directo si la sesión está activa --->
<%
    if (session.getAttribute("email")!= null) {
        response.sendRedirect("menu_comprar.jsp");
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <script type="text/javascript">
            history.pushState(null, null, document.URL);
            window.addEventListener('popstate', function () {
                history.pushState(null, null, document.URL);
            });
        </script>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Siglo XXI</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link href="https://unpkg.com/ionicons@4.5.5/dist/css/ionicons.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.10/css/AdminLTE.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/skins/flat/blue.css  ">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        <!--- Configuraciones específicas para este JSP --->
        <style>
            body{
                background-image: url('././images/fondo_restaurante.jpg');
                background-repeat: no-repeat,no-repeat;
                background-position: center center,center center;
                background-size: cover,cover;

            }
            .principal{
                width: 100%;
                height: 100%;
                background: rgba(0,0,0,0.55);
                position: absolute;

            }
            .login-logo a{
                color: white!important;

            }
            .login-box{
                margin-top: 14%;
            }
            .btn{
            background-color: palegreen;
            color: black;
            border-color: black;
            }
            .btn{
                transition-duration: 0.4;
            }
            .btn:hover{
                background-color: darkcyan;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="principal">
            <div class="login-box " >
                <div class="login-logo">
                    <a href="#"><b>Restaurant</b>  Siglo XXI</a>
                </div>
                <div class="login-box-body">
                    <p class="login-box-msg">Iniciar Sesión</p>
                    <form role="form" action="Ingresar" method="POST" id="formulario">
                        <div class="form-group has-feedback">
                            <input class="form-control" type="email" id="email" name="email" placeholder="Correo electrónico" required>
                        </div>
                        <div class="form-group has-feedback">
                            <input class="form-control" type="password" id="password" name="password" placeholder="Contraseña" required>
                        </div>
                        <div class="row">
                            <div class="col-xs-8">
                                <a href="/SigloXXI_Web/reset_password.jsp">¿Olvidaste tu contraseña?</a>
                            </div>
                            <div class="col-xs-4">
                                <button type="submit" class="btn btn-primary btn-block btn-flat">Entrar </button>
                            </div>
                        </div>
                    </form>
                    <div class="col-xs-8">
                            </div>
                    <div class="row">
                            <div class="col-xs-8">
                            </div>
                            <div class="col-xs-4">
                                <form action="ClienteServlet" method="POST">
                                    <button class="btn btn-primary btn-block btn-flat" type="submit" name="accion" value="IngresarRegistrar">Registrarse</button>
                                </form>
                            </div>
                        </div>
                <!--- Si no supera la validación, se indicará a través de un mensaje --->
                <%     
                    if (request.getAttribute("Error") != (null) && (Boolean)request.getAttribute("Error") == true ){
                        out.print("<div style='text-align: center;'>"
                                + "</br>"
                                + "<p style='color: red'> Correo o contraseña incorrectos. </p>"
                                + "</div>"
                        );
                    }
                %>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <!-- Bootstrap 3.4.1 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.15/js/adminlte.min.js"></script>
        <!--- Validación de los campos ingresados --->
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
        <script>
            $('#formulario').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    email: {
                        validators: {
                            stringLength: {
                                min: 3,
                                message: 'El correo puede tener un máximo de 100 caracteres.',
                                max: 100
                            },
                            notEmpty: {
                                message: 'Ingresar correo electrónico.'
                            },
                            emailAddress: {
                                message: 'Ingresar correo electrónico con el formato correcto. Recuerda que este debe llevar un símbolo "@".'
                            }
                        }
                    },
                    password: {
                        validators: {
                            stringLength: {
                                min: 8,
                                message: 'La cantidad de caracteres debe ser superior o igual a 8 y no sobrepasar los 30.',
                                max: 30
                            },
                            notEmpty: {
                                message: 'Por favor ingresar contraseña actual.'
                            }
                        }
                    }
                }
            });
        </script>
    </body>
</html>


