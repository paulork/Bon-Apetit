package br.com.paulork.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.paulork.manager.interceptor.Public;
import br.com.paulork.utils.Repositorio;
import javax.inject.Inject;
import br.com.paulork.manager.Sessao;

/**
 * @author Paulo R. Kraemer <paulork10@gmail.com>
 */
@Controller
@Public
public class LoginController {
    
    @Inject private Result result;
    @Inject private Sessao userSession;
    @Inject private Repositorio repositorio;

    @Get("/login")
    public void form() {
    }
    
    @Post("/login/check")
    public void check(String usuario, String senha){
        if(repositorio.checkLogin(usuario, senha)){
            userSession.setLogged(true);
            userSession.setUsuario(repositorio.getUsuarioByName(usuario));
            result.use(Results.json()).withoutRoot().from("ok").serialize();
        } else {
            result.use(Results.json()).withoutRoot().from("erro").serialize();
        }
    }
    
    @Get("/logout")
    public void logout(){
        userSession.setLogged(false);
        userSession.setUsuario(null);
        result.redirectTo(this).form();
    }

}
