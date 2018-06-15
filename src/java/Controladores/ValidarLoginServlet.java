/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Objetos.CnDatos;
import Objetos.D_Cookies;
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
@WebServlet(name = "ValidarLoginServlet", urlPatterns = {"/Login"})
public class ValidarLoginServlet extends HttpServlet {

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
            
            
            String usuario = request.getParameter("txtUser");
            String password = request.getParameter("txtPass");
            
            if (usuario.equals("") && password.equals("")) {
                RedirE(request, response, 0);
            } else if (usuario.equals("")) {
                RedirE(request, response, 1);
            } else if (password.equals("")) {
                RedirE(request, response, 2);
            } else {
                CnDatos DB = new CnDatos();
                if (DB.ComprobarUsuario(usuario)) {
                    if (DB.ComprobarPassword(usuario, password)) {
                        //Conectar HTTPSession
                        HttpSession ses = request.getSession();
                        Usuario usu = DB.RetornarUsuario(usuario);
                        ses.setAttribute("usuario", usu);
                        boolean opc= (request.getParameter("txtRecordar")!=null);
                        D_Cookies coo = new D_Cookies();
                        response.addCookie(coo.saveCookie("Opcion", String.valueOf(opc)));
                        if (opc) {
                            response.addCookie(coo.saveCookie("User", usu.getUser()));
                            response.addCookie(coo.saveCookie("Pass", usu.getPass()));
                        }else{
                            response.addCookie(coo.saveCookie("User", ""));
                            response.addCookie(coo.saveCookie("Pass", ""));
                        }
                        Redir(request,response,"Menu.jsp");
                    } else {
                        RedirE(request, response, 4);
                    }
                } else {
                    RedirE(request, response, 3);
                }

            }
            
            
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
    
    public void RedirE(HttpServletRequest request,HttpServletResponse response,int Error) throws ServletException, IOException{
            request.getRequestDispatcher("index.jsp?d="+Error).forward(request, response);
    }
    
    public void Redir(HttpServletRequest request,HttpServletResponse response,String cadena) throws ServletException, IOException{
            request.getRequestDispatcher(cadena).forward(request, response);
    }
    
}
