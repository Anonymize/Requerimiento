<%-- 
    Document   : index
    Created on : 10-06-2018, 19:53:58
    Author     : Anonymize
--%>

<%@page import="Objetos.CnDatos"%>
<%@page import="Objetos.D_Cookies"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-escale=1.0">
        <link rel="stylesheet" href="css/Estilo.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <title>Autentificación</title>
    </head>
    <body>
        <div class="container">
            <div class="row desp"></div>
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2 login bg-info">
                    <div class="row">
                        <form id="formulario" method="POST" class="col-lg-12" action="Login">
                            <h3 class="text-primary">Autentificación</h3>
                            <div class="Linea"></div>
                            <br>
                            <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-xs-12">
                                <div class="form-group">
                                    <label>Usuario</label>
                                    <input type="text" class="form-control" name="txtUser" placeholder="Ingrese un usuario">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" class="form-control" name="txtPass" placeholder="Contraseña">
                                </div>
                                <div class="form-group">
                                    <input type="checkbox" name="txtRecordar">
                                    <label>Recordar</label>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-5">
                                        <button type="submit" class="btn btn-primary cienp">Ingresar</button>
                                    </div>  
                                </div> 
                                <br>
                            </div>
                        </form>
                    </div>
                    
                    <center>
                        <%
                            try {
                                String dato = request.getParameter("d");
                                int num=-100;
                                if (dato!=null && !dato.equals("")) {
                                    num = Integer.parseInt(dato);
                                }
                                
                                if (num != 6) {
                                    D_Cookies coo = new D_Cookies();
                                    if (coo.loadCookie(request, "Opcion").equals("true")) {
                                        coo.logCookie(request, response);
                                    }
                                }
                                switch (num) {
                                    case 0:
                                        out.println("<p class=\"text-info\">No ha ingresado ningún dato</p>");
                                        break;
                                    case 1:
                                        out.println("<p class=\"text-info\">No ingresó un usuario</p>");
                                        break;
                                    case 2:
                                        out.println("<p class=\"text-info\">No ingresó una contraseña</p>");
                                        break;
                                    case 3:
                                        out.println("<p class=\"text-info\">Este usuario no existe</p>");
                                        break;
                                    case 4:
                                        out.println("<p class=\"text-info\">Contraseña incorrecta</p>");
                                        break;
                                    case 5:
                                        out.println("<p class=\"text-info\">Usuario creado correctamente</p>");
                                        break;
                                    case 6:
                                        out.println("<p class=\"text-info\">Se cerró la sesión correctamente</p>");
                                        break;
                                    case 7:
                                        out.println("<p class=\"text-info\">Usuario no reconocido</p>");
                                        break;
                                }
                            } catch (Exception e) {
                            }
                        %>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
