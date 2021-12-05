<%-- 
    Document   : registrarU
    Created on : 05-09-2021, 21:52:25
    Author     : Felipe Fuentes
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Siglo XXI</title>
        <link rel="icon" type="image/png" sizes="32x32" href="icono/favicon-32x32.png">
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
            #outer{                   
                width:100%;
                text-align: center;
            }
            .inner{
            display: inline-block;
            }
            .btn:hover{
            background-color: darkcyan;
            color: white;
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
                    <p class="login-box-msg">Crear cuenta</p>
                    
                    <form role="form" id="form" action="ClienteServlet"  method="POST">
                        <div class="form-group has-feedback">
                            <input class="form-control" type="email" name="email" placeholder="Email">
                        </div>
                        <div class="form-group has-feedback">
                            <input class="form-control" type="text" name="rutCliente" placeholder="Rut" oninput="checkRut(this)">
                        </div>
                        <div class="form-group has-feedback">
                            <input class="form-control" type="password" name="password" placeholder="Contraseña">
                        </div>
                        <div class="form-group has-feedback">
                            <input class="form-control" type="text" name="nombre" placeholder="Nombre">
                        </div>
                        <div class="form-group has-feedback">
                            <input class="form-control" type="text" name="primerApellido" placeholder="Apellido paterno">
                        </div>
                        <div class="form-group has-feedback">
                            <input class="form-control" type="text" name="segundoApellido" placeholder="Apellido materno">
                        </div>
                        <div class="form-group has-feedback">
                            <input class="form-control" type="text" name="telefono" placeholder="Teléfono">
                        </div>
                        <div class="form-group has-feedback">
                            <input class="form-control" type="text" name="direccion" placeholder="Dirección">
                        </div>
                        <div id="outer">
                            <div class="inner">
                                <button class="btn btn-primary btn-block btn-flat" type="submit" name="accion" onclick="confirmarCambios(),alert('Sus datos han sido registrados.');" value="RegistrarC">Registrar</button>
                            </div>
                            <div class="inner">
                            <button class="btn btn-primary btn-block btn-flat" type="submit" name="accion" onclick="atras();">Volver</button>
                        </div>
                        </div>
                    </form>
                <!--- Si no supera la validación, se indicará a través de un mensaje --->
                <%     
                    if (request.getAttribute("ErrorRegistrar") != (null) && (Boolean)request.getAttribute("ErrorRegistrar") == true ){
                        out.print("<div style='text-align: center;'>"
                                + "</br>"
                                + "<p style='color: red'> Por favor ingrese un email y rut que no estén registrados. </p>"
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
            $('#form').bootstrapValidator({
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
                    rutCliente: {
                        validators: {
                            stringLength: {
                                min: 9,
                                message: 'La cantidad de caracteres debe ser superior o igual a 8 y no sobrepasar los 30.',
                                max: 10
                            },
                            notEmpty: {
                                message: 'Por favor ingresar rut.'
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
                            regexp: {
                                regexp: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}$/,
                                message: 'El formato debe llevar tanto mayúsculas como minúsculas, además de al menos un número.'
                            },
                            notEmpty: {
                                message: 'Por favor ingresar contraseña.'
                            }
                        }
                    },
                    nombre: {
                        validators: {
                            stringLength: {
                                min: 1,
                                message: 'El nombre supera la cantidad de caracteres.',
                                max: 30
                            },
                            notEmpty: {
                                message: 'Por favor ingresar nombre.'
                            }
                        }
                    },
                    primerApellido: {
                        validators: {
                            stringLength: {
                                min: 1,
                                message: 'El apellido supera la cantidad de caracteres.',
                                max: 30
                            },
                            notEmpty: {
                                message: 'Por favor ingresar apellido.'
                            }
                        }
                    },
                    segundoApellido: {
                        validators: {
                            stringLength: {
                                min: 1,
                                message: 'El apellido supera la cantidad de caracteres.',
                                max: 30
                            },
                            notEmpty: {
                                message: 'Por favor ingresar apellido.'
                            }
                        }
                    },
                    telefono: {
                        validators: {
                            regexp: {
                                regexp: /^(9)(\s?)[987654321]\d{7}$/,
                                message: 'El número debe ser antepuesto por un 9. Ejemplo: 912345678'
                            },
                            notEmpty: {
                                message: 'Por favor ingresar número telefónico'
                            }
                        }
                    },
                    direccion: {
                        validators: {
                            stringLength: {
                                min: 1,
                                message: 'La dirección del domicilio supera el límite de caracteres.',
                                max: 100
                            },
                            notEmpty: {
                                message: 'Por favor ingresar dirección de su domicilio.'
                            }
                        }
                    }
                }
            });
        </script>
        <!--- Funciones del archivo JSP --->
        <script>
            function atras(){history.back();}
            
            function confirmarCambios() {
                var respuesta=confirm("¿Estás seguro que deseas crear una nueva cuenta?");
                if (respuesta==true)
                  {
                    if (form.checkValidity()) {
                    }
                    else{
                        alert("La cuenta no ha sido creada. Si cambias de opinión, vuele a intentarlo.");
                    }
                    return true;
                  }
                else
                {
                    alert("Si cambias de opinión, vuele a ingresar los datos.");
                    return false;
                }
            }
        </script>
        <!--- Función para validar el Rut --->
        <script>
            function checkRut(rut) {
                // Despejar Puntos
                var valor = rut.value.replace('.', '');
                // Despejar Guión
                valor = valor.replace('-', '');

                // Aislar Cuerpo y Dígito Verificador
                cuerpo = valor.slice(0, -1);
                dv = valor.slice(-1).toUpperCase();

                // Formatear RUN
                rut.value = cuerpo + '-' + dv

                // Si no cumple con el mínimo ej. (n.nnn.nnn)
                if (cuerpo.length < 7) {
                    rut.setCustomValidity("RUT incompleto");
                    return false;
                }
                
                // Si no posee caracteres
                if (cuerpo.length = 0) {
                    rut.setCustomValidity("Ingresar RUT");
                    return false;
                }

                // Calcular Dígito Verificador
                suma = 0;
                multiplo = 2;

                // Para cada dígito del Cuerpo
                for (i = 1; i <= cuerpo.length; i++) {

                    // Obtener su Producto con el Múltiplo Correspondiente
                    index = multiplo * valor.charAt(cuerpo.length - i);

                    // Sumar al Contador General
                    suma = suma + index;

                    // Consolidar Múltiplo dentro del rango [2,7]
                    if (multiplo < 7) {
                        multiplo = multiplo + 1;
                    } else {
                        multiplo = 2;
                    }

                }

                // Calcular Dígito Verificador en base al Módulo 11
                dvEsperado = 11 - (suma % 11);

                // Casos Especiales (0 y K)
                dv = (dv == 'K') ? 10 : dv;
                dv = (dv == 0) ? 11 : dv;

                // Validar que el Cuerpo coincide con su Dígito Verificador
                if (dvEsperado != dv) {
                    rut.setCustomValidity("RUT Inválido");
                    return false;
                }

                // Si todo sale bien, eliminar errores (decretar que es válido)
                rut.setCustomValidity('');
            }
        </script>
    </body>
</html>
