package proj.musica.playmysongs.util;

public class Usuario {
    private String email;
    private boolean ativo;

    public Usuario(String email, boolean ativo) {
        this.email = email;
        this.ativo = ativo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
