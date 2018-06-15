package Objetos;

import java.sql.*;
import java.util.ArrayList;

public class CnDatos extends Conexion{
    Connection cn=null;
    
    private Connection Conectar(){
        try {
            return getConexion();
        } catch (Exception e) {}
        return null;
    }
    private void Desconectar(){
        try {
            cn.close();
        } catch (SQLException e) {}
    }
    
    public boolean ComprobarUsuario(String Usuario){
        try {
            cn=Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT User FROM usuarios WHERE User='" + Usuario+"'");
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getString("User").equals(Usuario)) {
                Desconectar();
                return true;
            }else{
                Desconectar();
                return false;
            }
        } catch (SQLException e) {}
        Desconectar();
        return false;
    }
    
    public boolean ComprobarUsuario(String Usuario, String ID){
        try {
            int Id = Integer.parseInt(ID);
            cn=Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT User FROM usuarios WHERE User='" + Usuario+"' and Id!="+Id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getString("User").equals(Usuario)) {
                Desconectar();
                return true;
            }else{
                Desconectar();
                return false;
            }
        } catch (SQLException e) {}
        Desconectar();
        return false;
    }
    
    public boolean ComprobarPassword(String Usuario,String Password){
        try {
            cn=Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM usuarios WHERE User='" + Usuario+"'");
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getString("Pass").equals(Password)) {
                Desconectar();
                return true;
            }else{
                Desconectar();
                return false;
            }
        } catch (SQLException e) {}
        Desconectar();
        return false;
    }
    
    public Usuario RetornarUsuario(String Usuario) {
        try {
            cn = Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM usuarios WHERE User='"+Usuario+"'");
            ResultSet rs = ps.executeQuery();
            rs.next();
            Usuario Us = new Usuario(Integer.parseInt(rs.getString("Id")), rs.getString("User"), rs.getString("Pass"));
            Desconectar();
            return Us;
        } catch (SQLException e) {
        }
        Desconectar();
        return null;
    }
    
    public ArrayList<Integer> RetornarGerencias(){
        ArrayList<Integer> Lista =new ArrayList<>();
        try {
            cn=Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT Id_Gerencia FROM gerencia");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Lista.add(rs.getInt("Id_Gerencia"));
            }
            Desconectar();
            return Lista;
        } catch (SQLException e) {}
        Desconectar();
        return null;
    }
    
    public ArrayList<Integer> RetornarDepartamentos(int Id){
        ArrayList<Integer> Lista =new ArrayList<>();
        try {
            cn=Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT departamento.Id_Departamento FROM departamento inner join gerencia on gerencia.Id_Gerencia=departamento.Id_Gerencia where gerencia.Id_Gerencia="+Id+";");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Lista.add(rs.getInt("departamento.Id_Departamento"));
            }
            Desconectar();
            return Lista;
        } catch (SQLException e) {}
        Desconectar();
        return null;
    }
    
    public ArrayList<Integer> ListaDepartamentos(){
        ArrayList<Integer> Lista =new ArrayList<>();
        try {
            cn=Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT departamento.Id_Departamento FROM departamento;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Lista.add(rs.getInt("departamento.Id_Departamento"));
            }
            Desconectar();
            return Lista;
        } catch (SQLException e) {}
        Desconectar();
        return null;
    }
    
    public ArrayList<Integer> RetornarEncargados(int Id){
        ArrayList<Integer> Lista =new ArrayList<>();
        try {
            cn=Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT encargados.Id_Encargado FROM encargados inner join departamento on encargados.Id_Departamento=departamento.Id_Departamento where departamento.Id_Departamento="+Id+";");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Lista.add(rs.getInt("encargados.Id_Encargado"));
            }
            Desconectar();
            return Lista;
        } catch (SQLException e) {}
        Desconectar();
        return null;
    }
    
    public boolean InsertarRequerimiento(int Encargado,int Departamento,String Descripcion,String Estado){
        try {
            cn=Conectar();
            String query = "INSERT INTO Requerimientos VALUES (null,?,?,?,?);";      
            PreparedStatement st = cn.prepareStatement(query);
            st.setInt(1, Encargado);
            st.setInt(2, Departamento);
            st.setString(3, Descripcion);
            st.setString(4, Estado);
            st.executeUpdate();
            Desconectar();
            return true;
        } catch (SQLException e) {}
        Desconectar();
        return false;
    }
    
    private int getIdGerencia(int IdDepartamento) {//Metodo Privado
        try {
            cn = Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT Id_Gerencia FROM departamento Where Id_Departamento="+IdDepartamento+";");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int Id_Gerencia = rs.getInt("Id_Gerencia");
            Desconectar();
            return Id_Gerencia;
        } catch (SQLException e) {
        }
        Desconectar();
        return 0;
    }
    
    private int getIdDepartamento(int IdEncargado) {//Metodo Privado
        try {
            cn = Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT Id_Departamento FROM encargados Where Id_Encargado="+IdEncargado+";");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int Id_Departamento = rs.getInt("Id_Departamento");
            Desconectar();
            return Id_Departamento;
        } catch (SQLException e) {
        }
        Desconectar();
        return 0;
    }
    
    public ArrayList<Requerimiento> getRequerimientos(){
        ArrayList<Requerimiento> Lista =new ArrayList<>();
        try {
            cn=Conectar();
            //PreparedStatement ps = cn.prepareStatement("SELECT * FROM requerimientos");
            PreparedStatement ps = cn.prepareStatement("SELECT requerimientos.Id_Requerimiento,requerimientos.Id_Encargado,requerimientos.Id_Departamento,requerimientos.Descripcion,requerimientos.Estado,gerencia.Id_Gerencia,encargados.Id_Departamento FROM requerimientos inner join departamento on departamento.Id_Departamento=requerimientos.Id_Departamento inner join gerencia on gerencia.Id_Gerencia=departamento.Id_Gerencia inner join encargados on encargados.Id_Encargado=requerimientos.Id_Encargado;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Requerimiento req= new Requerimiento();
                req.setId(rs.getInt("requerimientos.Id_Requerimiento"));
                req.setGerencia(rs.getInt("gerencia.Id_Gerencia"));
                req.setDepartamento(rs.getInt("requerimientos.Id_Departamento"));
                req.setAsignar(rs.getInt("encargados.Id_Departamento"));
                req.setEncargado(rs.getInt("requerimientos.Id_Encargado"));
                req.setDescripcion(rs.getString("requerimientos.Descripcion"));
                req.setEstado(rs.getString("requerimientos.Estado"));
                Lista.add(req);
            }
            Desconectar();
            return Lista;
        } catch (SQLException e) {}
        Desconectar();
        return null;
    }
    
    public ArrayList<Requerimiento> getRequerimientos(int Gerencia,int Departamento,int Asignar){
        ArrayList<Requerimiento> Lista =new ArrayList<>();
        try {
            cn=Conectar();
            PreparedStatement ps = cn.prepareStatement(""
                    + "SELECT requerimientos.Id_Requerimiento,requerimientos.Id_Encargado,requerimientos.Id_Departamento"
                    + ",requerimientos.Descripcion,requerimientos.Estado,gerencia.Id_Gerencia,encargados.Id_Departamento "
                    + "FROM requerimientos inner join departamento on departamento.Id_Departamento=requerimientos.Id_Departamento "
                    + "inner join gerencia on gerencia.Id_Gerencia=departamento.Id_Gerencia "
                    + "inner join encargados on encargados.Id_Encargado=requerimientos.Id_Encargado;");
            ResultSet rs = ps.executeQuery();
            boolean Agregar;
            while(rs.next()){
                Agregar=true;
                Requerimiento req= new Requerimiento();
                req.setId(rs.getInt("requerimientos.Id_Requerimiento"));
                req.setGerencia(rs.getInt("gerencia.Id_Gerencia"));
                req.setDepartamento(rs.getInt("requerimientos.Id_Departamento"));
                req.setAsignar(rs.getInt("encargados.Id_Departamento"));
                req.setEncargado(rs.getInt("requerimientos.Id_Encargado"));
                req.setDescripcion(rs.getString("requerimientos.Descripcion"));
                req.setEstado(rs.getString("requerimientos.Estado"));
                int v1=req.getGerencia();
                int v2=req.getDepartamento();
                int v3=req.getAsignar();
                
                if (Gerencia!=0) {
                    if (Gerencia!=v1) {
                        Agregar=false;
                    }
                }
                if (Departamento!=0) {
                    if (Departamento!=v2) {
                        Agregar=false;
                    }
                }
                if (Asignar!=0) {
                    if (Asignar!=v3) {
                        Agregar=false;
                    }
                }
                if (Agregar) {
                    Lista.add(req);
                }
            }
            Desconectar();
            return Lista;
        } catch (SQLException e) {}
        Desconectar();
        return null;
    }
    
    public String getNombreGerencia(int Id) {
        try {
            cn = Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT Nombre FROM gerencia Where Id_Gerencia="+Id+";");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String Nombre = rs.getString("Nombre");
            Desconectar();
            return Nombre;
        } catch (SQLException e) {
        }
        Desconectar();
        return null;
    }
    
    public String getNombreGerencia(String Departamento) {
        try {
            cn = Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT gerencia.Nombre FROM gerencia inner join departamento on gerencia.Id_Gerencia=departamento.Id_Gerencia Where departamento.Nombre='"+Departamento+"';");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String Nombre = rs.getString("gerencia.Nombre");
            Desconectar();
            return Nombre;
        } catch (SQLException e) {
        }
        Desconectar();
        return null;
    }
    
    public String getNombreDepartamento(int Id) {
        try {
            cn = Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT Nombre FROM departamento Where Id_Departamento="+Id+";");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String Nombre = rs.getString("Nombre");
            Desconectar();
            return Nombre;
        } catch (SQLException e) {
        }
        Desconectar();
        return null;
    }
    
    public String getNombreEncargado(int Id) {
        try {
            cn = Conectar();
            PreparedStatement ps = cn.prepareStatement("SELECT Nombre FROM encargados Where Id_Encargado="+Id+";");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String Nombre = rs.getString("Nombre");
            Desconectar();
            return Nombre;
        } catch (SQLException e) {
        }
        Desconectar();
        return null;
    }
    
    public boolean ActualizarRequerimiento(String Estado,int Id){
        try {
            cn=Conectar();          
            PreparedStatement ps = cn.prepareStatement("UPDATE requerimientos SET Estado = ? where Id_Requerimiento=?");
            ps.setString(1, Estado);
            ps.setInt(2, Id);
            ps.executeUpdate();
            
            Desconectar();
            return true;
        } catch (SQLException e) {}
        Desconectar();
        return false;
    }
    
    
}