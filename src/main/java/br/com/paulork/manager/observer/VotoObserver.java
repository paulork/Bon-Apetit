package br.com.paulork.manager.observer;

import br.com.paulork.model.Voto;
import br.com.paulork.utils.Repositorio;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * @author Paulo R. Kraemer <paulork10@gmail.com>
 */
public class VotoObserver {
    
    @Inject private Repositorio repositorio;
    
    public void adicionaVoto(@Observes Voto voto){
        repositorio.newVoto(voto);
    }

}
