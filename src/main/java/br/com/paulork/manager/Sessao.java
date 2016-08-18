package br.com.paulork.manager;

import br.com.paulork.model.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

/**
 * @author Paulo R. Kraemer <paulork10@gmail.com>
 */
@SessionScoped
public class Sessao implements Serializable {
    
    private boolean logged = false;
    private Usuario usuario;

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
