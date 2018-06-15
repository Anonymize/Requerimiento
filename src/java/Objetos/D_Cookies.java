package Objetos;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class D_Cookies {
    private final int TiempoVida= (365*24*60*60);
    
    public Cookie saveCookie(String NombreCookie,String ValorCookie){
        Cookie co = new Cookie(NombreCookie,ValorCookie);
        co.setMaxAge(TiempoVida);
        return co;
    }
    
    public Cookie saveCookie(String NombreCookie,String ValorCookie,int Tiempo){
        Cookie co = new Cookie(NombreCookie,ValorCookie);
        co.setMaxAge(Tiempo);
        return co;
    }
    
    public String loadCookie(HttpServletRequest request, String NombreCookie) {
        Cookie[] listaCo = request.getCookies();
        try {
            if (listaCo != null) {
                for (Cookie co_temp : listaCo) {
                    if (co_temp.getName().equals(NombreCookie)) {
                        return co_temp.getValue();
                    }
                }
            }
        } catch (Exception e) {}
        return "";
    }
    
    public void logCookie(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String usuario=loadCookie(request, "User");
        String password=loadCookie(request, "Pass");
        if (!loadCookie(request, "Opcion").equals("true")) {
            return;
        }

        CnDatos DB = new CnDatos();
        if (DB.ComprobarUsuario(usuario)) {
            if (DB.ComprobarPassword(usuario, password)) {
                HttpSession ses = request.getSession();
                Usuario usu = DB.RetornarUsuario(usuario);
                ses.setAttribute("usuario", usu);
                response.sendRedirect("Menu.jsp");
            }
        }
    }
    
}
