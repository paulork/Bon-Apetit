package br.com.paulork.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.paulork.model.Voto;
import br.com.paulork.utils.Repositorio;
import java.time.LocalDate;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import br.com.paulork.manager.Sessao;

/**
 * @author Paulo R. Kraemer <paulork10@gmail.com>
 */
@Controller
public class VotoController {
    
    @Inject private Result result;
    @Inject private Sessao userSession;
    @Inject private Repositorio repositorio;
    @Inject @Any private Event<Voto> eventoVoto; // Evento CDI

    @Post({"/voto/save", "/voto/save/"})
    public void save(Integer id){
        if(!repositorio.jaVotouHoje()){
            if (!repositorio.jaEscolhidoNaSemana(id)) {
                Voto voto = new Voto(userSession.getUsuario(), repositorio.getRestauranteById(id), LocalDate.now());
                //Apenas para demonstrar um evento do CDI
                eventoVoto.fire(voto);
                result.redirectTo(this).resultado();
            } else {
                result.use(Results.json()).withoutRoot().from("escolhido").serialize();
            }
        } else {
            result.use(Results.json()).withoutRoot().from("votou").serialize();
        }
    }
    
    @Get({"/voto/form", "/voto/form/"})
    public void form(){
        result.include("restaurantes", repositorio.getRestaurantes());
    }
    
    @Get({"/voto/resultado", "/voto/resultado/"})
    public void resultado(){
        result.include("votos", repositorio.getVotosHoje());
    }
    
    @Get({"/voto/semana", "/voto/semana/"})
    public void semana(){
        result.include("votos", repositorio.getMaisVotadosDaSemana());
    }
    
    @Get({"/voto/restaurantes", "/voto/restaurantes/"})
    public void restaurantes(){
        result.use(Results.json()).withoutRoot().from(repositorio.getRestaurantes()).serialize();
    }
    
}
