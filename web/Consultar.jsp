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
        <title>Consultar Requerimiento</title>
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
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="text-primary">Consultar Requerimiento</h3>
                            <div class="Linea"></div>
                            <br>
                            <div class="col-md-7">

                                <div class="row">
                                    <div class="col-md-12">
                                        <label class="text-info">Gerencia: </label>
                                        <select>
                                            <%
                                                Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                                                if (req == null) {
                                                    ses.setAttribute("requerimiento", new Requerimiento());
                                                    req = (Requerimiento) ses.getAttribute("requerimiento");
                                                    req.setId(0);
                                                    req.setGerencia(0);
                                                    req.setDepartamento(0);
                                                    req.setAsignar(0);
                                                    ses.setAttribute("requerimiento",req);
                                                }
                                                CnDatos DB = new CnDatos();
                                                ArrayList<Integer> gerencias = DB.RetornarGerencias();
                                                out.println("<option onclick=\"location.href='Seleccion?t=5&g=0'\" selected>Todas</option>");
                                                for (int i = 0; i < gerencias.size(); i++) {
                                                    String sel = "";
                                                    if (req.getGerencia() != 0) {
                                                        sel = ((req.getGerencia() == gerencias.get(i)) ? "Selected" : "");
                                                    }
                                                    out.println("<option " + sel + " onclick=\"location.href='Seleccion?t=5&g=" + gerencias.get(i) + "'\">" + DB.getNombreGerencia(gerencias.get(i)) + "</option>");
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
                                                    out.println("<option onclick=\"location.href='Seleccion?t=6&d=0'\" selected>Todos</option>");
                                                    for (int i = 0; i < departamentos.size(); i++) {
                                                        String sel = "";
                                                        sel = ((req.getDepartamento() == departamentos.get(i)) ? "Selected" : "");
                                                        out.println("<option " + sel + " onclick=\"location.href='Seleccion?t=6&d=" + departamentos.get(i) + "'\">" + DB.getNombreDepartamento(departamentos.get(i)) + "</option>");
                                                    }
                                                }else if(req.getGerencia() == 0) {
                                                    ArrayList<Integer> departamentos = DB.ListaDepartamentos();
                                                    out.println("<option onclick=\"location.href='Seleccion?t=6&d=0'\" selected>Todos</option>");
                                                    for (int i = 0; i < departamentos.size(); i++) {
                                                        String sel = "";
                                                        sel = ((req.getDepartamento() == departamentos.get(i)) ? "Selected" : "");
                                                        out.println("<option " + sel + " onclick=\"location.href='Seleccion?t=6&d=" + departamentos.get(i) + "'\">" + DB.getNombreDepartamento(departamentos.get(i)) + "</option>");
                                                    }
                                                }
                                                else {
                                                    out.println("<option onclick=\"location.href='Seleccion?t=6&d=0'\" selected>Todos</option>");
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
                                                out.println("<option onclick=\"location.href='Seleccion?t=7&a=0'\" selected>Todos</option>");
                                                for (int i = 0; i < departamentos2.size(); i++) {
                                                    String sel = "";
                                                    sel = ((req.getAsignar() == departamentos2.get(i)) ? "Selected" : "");
                                                    if (sel.equals("Selected")) {
                                                        ger = "Gerencia de " + DB.getNombreGerencia(DB.getNombreDepartamento(departamentos2.get(i)));
                                                    }
                                                    out.println("<option " + sel + " onclick=\"location.href='Seleccion?t=7&a=" + departamentos2.get(i) + "'\">" + DB.getNombreDepartamento(departamentos2.get(i)) + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>  
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-3">
                                <button type="button" onclick="location.href='Seleccion?t=100'" class="btn btn-primary cienp">Buscar</button>
                            </div>   
                            <br>
                            <div class="row">
                                <div class="col-md-12">
                                    <br>
                                    <div class="col-md-12 MiniM">                            
                                        <table class="table table-bordered">
                                            <thead class="text-primary">
                                                <tr bgcolor= "#1C1C1C">
                                                    <th scope="col">Gerencia</th>
                                                    <th scope="col">Departamento</th>
                                                    <th scope="col">Asignado a:</th>
                                                    <th scope="col">Requerimiento</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    ArrayList<Requerimiento> Req = null;
                                                    Req = (ArrayList<Requerimiento>) ses.getAttribute("consultar");
                                                    if (req.getId() == 0) {
                                                        if (Req == null) {
                                                            Req = DB.getRequerimientos();
                                                            ses.setAttribute("consultar", DB.getRequerimientos());
                                                            Req = (ArrayList<Requerimiento>) ses.getAttribute("consultar");
                                                        }
                                                    }
                                                    if (Req != null) {
                                                        for (int i = 0; i < Req.size(); i++) {
                                                        out.print("<tr>");
                                                        out.print("<tr bgcolor= \"#424242\" class=\"LetrasB\">");
                                                        out.print("<th bgcolor= \"#2E2E2E\" >" + DB.getNombreGerencia(Req.get(i).getGerencia()) + "</th>");
                                                        out.print("<td bgcolor= \"#2E2E2E\" >" + DB.getNombreDepartamento(Req.get(i).getDepartamento()) + "</td>");
                                                        out.print("<td bgcolor= \"#2E2E2E\" >" + DB.getNombreDepartamento(Req.get(i).getAsignar()) + "</td>");
                                                        out.print("<td>" + Req.get(i).getDescripcion() + "</td>");
                                                        out.print("</tr>");
                                                        }
                                                    }
                                                    
                                                %>
                                            </tbody>
                                        </table>
                                    </div> 

                                </div>  
                            </div> 
                            <br>
                            <!--Aquivael DIV-->

                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <button type="button" onclick="location.href='Menu.jsp'" class="btn btn-primary cienp">Volver al menú</button>
                        </div>
                    </div>
                    <br>
                </div>
            </div>
        </div>
    </body>
</html>
