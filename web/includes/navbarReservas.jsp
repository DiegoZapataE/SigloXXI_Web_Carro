<%-- 
    Document   : navbarReservas
    Created on : 22-11-2021, 20:46:22
    Author     : diego
--%>

<style>
    
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
    .a{
        color: black;
        background-color: black;
    }
    .nav-link{
        background-color: transparent;
        color: black;
        border-color: transparent;
    }
    input{
        margin: -2.1px;
    }
</style>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <img class="logo" src="images/Logo.png" alt="logo">
  <a class="navbar-brand" href="menu_comprar.jsp">RESTAURANT SIGLO XXI</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	<ul class="navbar-nav ml-auto">
	<li class="nav-item"><a class="nav-link" href="Inicio.jsp">Volver al inicio</a></li>
	<li class="nav-item"><form role="form" name="form" id="form" action="Resv" method="POST">
                                    <input class="nav-link" type="submit" onclick="loadForm();" name="accion" value="Salir">
                                </form></li>
	</ul>
    </div>
 </div>
</nav>
