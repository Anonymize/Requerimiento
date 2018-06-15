<%-- 
    Document   : Menu
    Created on : 11-06-2018, 1:03:51
    Author     : Anonymize
--%>

<%@page import="Objetos.CnDatos"%>
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
        <title>Ingresar Requerimiento</title>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession();
            Usuario usu = (Usuario) ses.getAttribute("usuario");
            if (usu == null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        %>
        <div class="container">
            <div class="row desp"></div>
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2 login bg-info">
                    <form method="POST" class="col-lg-12" action="Save">
                        <div class="row">
                            <div class="col-md-12">
                                <h3 class="text-primary">Ingresar Requerimiento</h3>
                                <div class="Linea"></div>
                                <br>
                                <div class="col-md-12">

                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="text-info">Gerencia: </label>
                                            <select>
                                                <%
                                                    Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                                                    if (req == null) {
                                                        ses.setAttribute("requerimiento", new Requerimiento());
                                                        req = (Requerimiento) ses.getAttribute("requerimiento");
                                                    }
                                                    CnDatos DB = new CnDatos();
                                                    ArrayList<Integer> gerencias = DB.RetornarGerencias();
                                                    out.println("<option disabled selected>Selecciona una gerencia</option>");
                                                    for (int i = 0; i < gerencias.size(); i++) {
                                                        String sel = "";
                                                        if (req.getGerencia() != 0) {
                                                            sel = ((req.getGerencia() == gerencias.get(i)) ? "Selected" : "");
                                                        }
                                                        out.println("<option " + sel + " onclick=\"location.href='Seleccion?t=1&g=" + gerencias.get(i) + "'\">" + DB.getNombreGerencia(gerencias.get(i)) + "</option>");
                                                    }

                                                %>
                                            </select> 
                                        </div>  
                                    </div> 
                                    <br>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="text-info">Departamento: </label>
                                            <select>
                                                <%                                                
                                                    if (req.getGerencia() != 0) {
                                                        ArrayList<Integer> departamentos = DB.RetornarDepartamentos(req.getGerencia());
                                                        out.println("<option disabled selected>Seleccione un departamento</option>");
                                                        for (int i = 0; i < departamentos.size(); i++) {
                                                            String sel = "";
                                                            sel = ((req.getDepartamento() == departamentos.get(i)) ? "Selected" : "");
                                                            out.println("<option " + sel + " onclick=\"location.href='Seleccion?t=2&d=" + departamentos.get(i) + "'\">" + DB.getNombreDepartamento(departamentos.get(i)) + "</option>");
                                                        }
                                                    } else {
                                                        out.println("<option disabled selected> -Vacío- </option>");
                                                    }
                                                %>
                                            </select> 
                                        </div>  
                                    </div> 
                                    <br>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="text-info">Asignar a: </label>
                                            <select>
                                                <%
                                                    String ger = "";
                                                    ArrayList<Integer> departamentos2 = DB.ListaDepartamentos();
                                                    out.println("<option disabled selected>Selecciona un departamento</option>");
                                                    for (int i = 0; i < departamentos2.size(); i++) {
                                                        String sel = "";
                                                        sel = ((req.getAsignar() == departamentos2.get(i)) ? "Selected" : "");
                                                        if (sel.equals("Selected")) {
                                                            ger = "Gerencia de " + DB.getNombreGerencia(DB.getNombreDepartamento(departamentos2.get(i)));
                                                        }
                                                        out.println("<option " + sel + " onclick=\"location.href='Seleccion?t=3&a=" + departamentos2.get(i) + "'\">" + DB.getNombreDepartamento(departamentos2.get(i)) + "</option>");
                                                    }
                                                %>
                                            </select>
                                            <div class="row">
                                                <div class="col-md-5 col-md-offset-2">
                                                    <input type="text" value="<%out.println(ger);%>" name="textarea" class="cienp" disabled style="height:30px;">
                                                </div>
                                            </div>
                                        </div>  
                                    </div> 
                                    <br>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="text-info">Encargado: </label>
                                            <select>
                                                <%
                                                    if (req.getAsignar() != 0) {
                                                        ArrayList<Integer> encargados = DB.RetornarEncargados(req.getAsignar());
                                                        out.println("<option disabled selected>Seleccione un encargado</option>");
                                                        for (int i = 0; i < encargados.size(); i++) {
                                                            String sel = "";
                                                            sel = ((req.getEncargado() == encargados.get(i)) ? "Selected" : "");
                                                            out.println("<option " + sel + " onclick=\"location.href='Seleccion?t=4&e=" + encargados.get(i) + "'\">" + DB.getNombreEncargado(encargados.get(i)) + "</option>");
                                                        }
                                                    } else {
                                                        out.println("<option disabled selected> -Vacío- </option>");
                                                    }
                                                %>
                                            </select> 
                                        </div>  
                                    </div> 
                                    <br>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="text-info">Requerimiento: </label>
                                            <%
                                                String txt = "";
                                                if (req.getDescripcion() != null) {
                                                    if (!req.getDescripcion().equals("")) {
                                                        txt = req.getDescripcion();
                                                    }

                                                }
                                            %>
                                            <textarea name="txtDescripcion" class="cienp" style="height:150px;"><%out.println(txt);%></textarea>
                                        </div>  
                                    </div> 
                                    <br>
                                </div> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-3">
                                <button type="submit" class="btn btn-success cienp">Guardar</button>
                            </div>
                            <div class="col-xs-12 col-md-4 ">
                                <button type="button" onclick="location.href='Menu.jsp'" class="btn btn-primary cienp">Volver al menú</button>
                            </div>
                        </div>
                        <br>
                    </form>
                    <center>
                        <%
                            try {
                                String dato = request.getParameter("d");
                                switch (Integer.parseInt(dato)) {
                                    case 0:
                                        out.println("<p class=\"text-info\">Seleccione una gerencia</p>");
                                        break;
                                    case 1:
                                        out.println("<p class=\"text-info\">Seleccione un departamento</p>");
                                        break;
                                    case 2:
                                        out.println("<p class=\"text-info\">Asigne un departamento responsable</p>");
                                        break;
                                    case 3:
                                        out.println("<p class=\"text-info\">Seleccione un encargado</p>");
                                        break;
                                    case 4:
                                        out.println("<p class=\"text-info\">Ingrese una descripción</p>");
                                        break;
                                    case 5:
                                        out.println("<p class=\"text-success\">Requerimiento ingresado correctamente</p>");
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
