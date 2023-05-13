package modelo;

public class Usuarios {
    private Integer id;
    private String usuario;
    private String senha;

    public Usuarios(String usuario, String senha){
        this.usuario=usuario;
        this.senha=senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}
