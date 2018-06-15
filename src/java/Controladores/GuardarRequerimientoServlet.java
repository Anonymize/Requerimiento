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
@WebServlet(name = "GuardarRequerimientoServlet", urlPatterns = {"/Save"})
public class GuardarRequerimientoServlet extends HttpServlet {

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
            String descripcion = (String) request.getParameter("txtDescripcion");
            if (descripcion==null){descripcion="";}
            Requerimiento req = (Requerimiento) ses.getAttribute("requerimiento");
            if (req == null) {
                request.getRequestDispatcher("Menu.jsp").forward(request, response);
            } else {
                try {
                    if (req.getGerencia() == 0) {
                        request.getRequestDispatcher("Ingresar.jsp?d=0").forward(request, response);
                        req.setDescripcion(descripcion);
                        ses.setAttribute("requerimiento", req);
                    } else if (req.getDepartamento()== 0) {
                        req.setDescripcion(descripcion);
                        ses.setAttribute("requerimiento", req);
                        request.getRequestDispatcher("Ingresar.jsp?d=1").forward(request, response);
                    } else if (req.getAsignar() == 0) {
                        req.setDescripcion(descripcion);
                        ses.setAttribute("requerimiento", req);
                        request.getRequestDispatcher("Ingresar.jsp?d=2").forward(request, response);
                    }else if (req.getEncargado()== 0) {
                        req.setDescripcion(descripcion);
                        ses.setAttribute("requerimiento", req);
                        request.getRequestDispatcher("Ingresar.jsp?d=3").forward(request, response);
                    }else if (descripcion.equals("")) {
                        request.getRequestDispatcher("Ingresar.jsp?d=4").forward(request, response);
                    }else{
                        CnDatos DB = new CnDatos();
                        if (DB.InsertarRequerimiento(req.getEncargado(), req.getDepartamento(), descripcion, "A")) {
                            ses.removeAttribute("requerimiento");
                            request.getRequestDispatcher("Ingresar.jsp?d=5").forward(request, response);
                        }
                    }
                } catch (IOException | ServletException e) {
                }
            }
            request.getRequestDispatcher("Menu.jsp").forward(request, response);
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
