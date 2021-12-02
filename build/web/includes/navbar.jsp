<%-- 
    Document   : navbar
    Created on : 04-11-2021, 23:45:15
    Author     : diego
--%>
<style>
    
    .btn{
        background-color: transparent;
        color: black;

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
    .nav-item{
        color: black;
    }
    input{
        margin: -2.1px;
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
    ul{
        list-style: none;
        padding: 0;
        margin: 0;
    }
    ul li{
        float: left;
        margin-top: 20px;
    }
    ul li a{
        display: block;
        text-decoration: none;
        text-align: center;
        border-radius: 10px;
        border-color: black;
    }
    a:hover{
        background-color: darkcyan;
        color: white;
    }
    ul li ul{
        background-color: transparent;
    }
    ul li ul li{
        float: none;
    }
    ul li ul{
        display: none;
    }
    ul li:hover ul{
        display: block;
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
	<li class="nav-item"><a class="nav-link" href="menu_comprar.jsp">Menús</a></li>
	<li class="nav-item"><a class="nav-link" href="menu_carro.jsp">Carro <span class="badge badge-danger">${carro_list.size()}</span> </a></li>
        <li class="nav-item"><a class="nav-link accordion" href="metodoDePago.jsp">Metodo de pago</a>
            <ul>
                <li><a href="pago-efectivo" onclick="alert('El Garzón vendrá en breve, espere un momento.')">En efectivo</a></li>
                <li><a href="carro-comprar-webpay">Pago webpay</a></li>
            </ul>
        </li>
        <li class="nav-item"><form role="form" name="form" id="form" action="Resv" method="POST">
                                    <input class="nav-link" type="submit" onclick="loadForm();" name="accion" value="Salir">
                                </form></li>
	</ul>
    </div>
 </div>
</nav>

