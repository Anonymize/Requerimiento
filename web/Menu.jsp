<%-- 
    Document   : Menu
    Created on : 11-06-2018, 1:03:51
    Author     : Anonymize
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Objetos.Requerimiento"%>
<%@page import="Objetos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-escale=1.0">
        <link rel="stylesheet" href="css/Estilo.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <title>Menú</title>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession();
            Usuario usu = (Usuario) ses.getAttribute("usuario");
            if (usu == null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
            if (req != null) {
                ses.removeAttribute("requerimiento");
            }
            ArrayList<Requerimiento> Req = (ArrayList<Requerimiento>) ses.getAttribute("consultar");
            if (Req != null) {
                ses.removeAttribute("consultar");
            }
        %>
        <div class="container">
            <div class="row desp"></div>
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2 login bg-info">
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="text-primary">Menú principal</h3>
                            <div class="Linea"></div>
                            <br>
                            <div class="col-md-8">
                                
                                <div class="row">
                                    <div class="col-md-12">
                                        <button type="button" onclick="location.href='Ingresar.jsp'" class="btn btn-success cienp">Ingresar requerimiento</button>
                                    </div>  
                                </div> 
                                <br>
                                <div class="row">
                                    <div class="col-md-12">
                                        <button type="button" onclick="location.href='Consultar.jsp'" class="btn btn-info cienp">Consultar requerimientos</button>
                                    </div>  
                                </div> 
                                <br>
                                <div class="row">
                                    <div class="col-md-12">
                                        <button type="button" onclick="location.href='Cerrar.jsp'" class="btn btn-primary cienp">Cerrar requerimiento</button>
                                    </div>  
                                </div> 
                                <br>
                                
                            </div>
                        
                        
                        </div>
                    </div>
                    <p><a href="Exit">Cerrar Sesión</a></p>
                </div>
            </div>
        </div>
    </body>
</html>
