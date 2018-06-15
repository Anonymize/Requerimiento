/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Objetos.CnDatos;
import Objetos.Requerimiento;
import Objetos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anonymize
 */
@WebServlet(name = "RetornoComboBoxServlet", urlPatterns = {"/Seleccion"})
public class RetornoComboBoxServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession ses = request.getSession();
            Usuario usu = (Usuario) ses.getAttribute("usuario");
            if (usu == null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            try {
                String tipo = ((String)request.getParameter("t"));
                if (tipo==null) {
                    tipo="";
                }
                switch (tipo) {
                    case "1":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setGerencia(Integer.parseInt(request.getParameter("g")));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Ingresar.jsp");
                            break;
                        }
                    case "2":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setDepartamento(Integer.parseInt(request.getParameter("d")));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Ingresar.jsp");
                            break;
                        }
                    case "3":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setAsignar(Integer.parseInt(request.getParameter("a")));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Ingresar.jsp");
                            break;
                        }
                    case "4":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setEncargado(Integer.parseInt(request.getParameter("e")));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Ingresar.jsp");
                            break;
                        }
                    case "5":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setGerencia(Integer.parseInt(request.getParameter("g")));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Consultar.jsp");
                            break;
                        }
                    case "6":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setDepartamento(Integer.parseInt(request.getParameter("d")));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Consultar.jsp");
                            break;
                        }
                    case "7":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setAsignar(Integer.parseInt(request.getParameter("a")));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Consultar.jsp");
                            break;
                        }
                    case "8":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setGerencia(Integer.parseInt(request.getParameter("g")));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Cerrar.jsp");
                            break;
                        }
                    case "9":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setDepartamento(Integer.parseInt(request.getParameter("d")));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Cerrar.jsp");
                            break;
                        }
                    case "10":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setAsignar(Integer.parseInt(request.getParameter("a")));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Cerrar.jsp");
                            break;
                        }
                    case "100":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setId(1);
                            CnDatos DB = new CnDatos();
                            ses.setAttribute("consultar",DB.getRequerimientos(req.getGerencia(), req.getDepartamento(), req.getAsignar()));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Consultar.jsp");
                            break;
                        }
                    case "101":
                        {
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            req.setId(1);
                            CnDatos DB = new CnDatos();
                            ses.setAttribute("consultar",DB.getRequerimientos(req.getGerencia(), req.getDepartamento(), req.getAsignar()));
                            ses.setAttribute("requerimiento",req);
                            response.sendRedirect("Cerrar.jsp");
                            break;
                        }
                    case "102":
                        {
                            CnDatos DB = new CnDatos();
                            DB.ActualizarRequerimiento("C", Integer.parseInt(request.getParameter("id")));
                            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
                            ses.setAttribute("consultar",DB.getRequerimientos(req.getGerencia(), req.getDepartamento(), req.getAsignar()));
                            response.sendRedirect("Cerrar.jsp");
                            break;
                        }
                    default:
                        response.sendRedirect("Menu.jsp");
                        break;
                }
            } catch (IOException | NumberFormatException e) {
            }
            //response.sendRedirect("Menu.jsp");
        } catch (Exception e) {
            response.sendRedirect("Menu.jsp");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
