package br.com.paulork.manager.interceptor;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.paulork.controller.LoginController;
import java.lang.annotation.Annotation;
import javax.inject.Inject;
import br.com.paulork.manager.Sessao;

/**
 * @author Paulo R. Kraemer <paulork10@gmail.com>
 */
@Intercepts
public class LoginInterceptor {

    @Inject private Sessao userSession;
    @Inject private Result result;
    @Inject private ControllerMethod controllerMethod;

    @Accepts
    public boolean accepts() {
        boolean exist = true;
        Annotation[] annotations = controllerMethod.getController().getAnnotations();
        for (Annotation annotation : annotations) {
            if(annotation.annotationType().equals(Public.class)){
                return false; // Não intercepta se existir a anotação @Public
            }
        }
        return exist;
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {
        if (!userSession.isLogged()) {
            result.redirectTo(LoginController.class).form();
            return;
        }
        stack.next();
    }
}
