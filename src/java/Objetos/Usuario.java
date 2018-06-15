package Objetos;

public class Usuario{
    private int Id;
    private String User;
    private String Pass;

    public Usuario(int Id, String User, String Pass) {
        this.Id = Id;
        this.User = User;
        this.Pass = Pass;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    
    
}
