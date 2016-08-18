package br.com.paulork.utils;

import br.com.paulork.model.Restaurante;
import br.com.paulork.model.Usuario;
import br.com.paulork.model.Voto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author Paulo R. Kraemer <paulork10@gmail.com>
 */
public class RepositorioTest {

    private final List<Usuario> logins;
    private final List<Voto> votos;
    private final List<Restaurante> restaurantes;

    public RepositorioTest() {
        logins = new ArrayList<>();
        logins.add(new Usuario(1, "paulo", "12"));
        logins.add(new Usuario(2, "pedro", "12"));
        logins.add(new Usuario(3, "joao", "12"));
        logins.add(new Usuario(4, "andre", "12"));

        restaurantes = new ArrayList<>();
        restaurantes.add(new Restaurante(1, "MC Donalds"));
        restaurantes.add(new Restaurante(2, "Burguer King"));
        restaurantes.add(new Restaurante(3, "Habib's"));
        restaurantes.add(new Restaurante(4, "China in Box"));

        votos = new ArrayList<>();
        votos.add(new Voto(logins.get(0), restaurantes.get(0), LocalDate.now().minusDays(3)));
        votos.add(new Voto(logins.get(1), restaurantes.get(1), LocalDate.now().minusDays(3)));
        votos.add(new Voto(logins.get(0), restaurantes.get(0), LocalDate.now().minusDays(2)));
        votos.add(new Voto(logins.get(1), restaurantes.get(1), LocalDate.now().minusDays(2)));
        votos.add(new Voto(logins.get(2), restaurantes.get(0), LocalDate.now().minusDays(2)));
        votos.add(new Voto(logins.get(0), restaurantes.get(1), LocalDate.now().minusDays(1)));
        votos.add(new Voto(logins.get(1), restaurantes.get(0), LocalDate.now().minusDays(1)));
        votos.add(new Voto(logins.get(2), restaurantes.get(1), LocalDate.now().minusDays(1)));
    }

    @Before
    public void setUp() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("=======================================================");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("=======================================================");
    }

    @Test
    public void testeCheckLogin_Correto() {
        System.out.println("checkLogin com dados corretos");
        String usuario = "paulo";
        String senha = "12";
        Repositorio instance = new Repositorio();
        boolean expResult = true;
        boolean result = instance.checkLogin(usuario, senha);
        assertEquals(expResult, result);
    }

    @Test
    public void testeCheckLogin_Errado() {
        System.out.println("checkLogin com dados errados");
        String usuario = "paulo";
        String senha = "123";
        Repositorio instance = new Repositorio();
        boolean expResult = false;
        boolean result = instance.checkLogin(usuario, senha);
        assertEquals(expResult, result);
    }

    @Test
    public void testNewVoto_PassingVoto() {
        System.out.println("newVoto passando um voto");
        Voto voto = new Voto(logins.get(0), restaurantes.get(0), LocalDate.now().minusDays(3));

        Voto expResult = new Voto(logins.get(0), restaurantes.get(0), LocalDate.now().minusDays(3));
        expResult.setId(votos.size()); // Simula uma Sequence da mesma forma como é feito internamente em "newVoto(voto)"

        Repositorio instance = new Repositorio();
        Voto result = instance.newVoto(voto);
        assertEquals(expResult, result);
    }

    @Test
    public void testNewVoto_PassingNull() {
        System.out.println("newVoto passando null");
        Voto voto = null;
        Repositorio instance = new Repositorio();
        Voto expResult = null;
        Voto result = instance.newVoto(voto);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRestauranteById_Exist() {
        System.out.println("getRestauranteById com id existente e igual...");
        Integer id = 1;
        Repositorio instance = new Repositorio();
        Restaurante expResult = new Restaurante(1, "MC Donalds");
        Restaurante result = instance.getRestauranteById(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRestauranteById_NotExist() {
        System.out.println("getRestauranteById com id existente e diferente...");
        Integer id = 10;
        Repositorio instance = new Repositorio();
        Restaurante expResult = null;
        Restaurante result = instance.getRestauranteById(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRestauranteById_Null() {
        System.out.println("getRestauranteById com id nulo...");
        Integer id = null;
        Repositorio instance = new Repositorio();
        Restaurante expResult = null;
        Restaurante result = instance.getRestauranteById(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetUsuarioById_Exist() {
        System.out.println("getUsuarioById com id existente");
        Integer id = 1;
        Repositorio instance = new Repositorio();
        Usuario expResult = new Usuario(1, "paulo", "12");
        Usuario result = instance.getUsuarioById(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetUsuarioById_NotExist() {
        System.out.println("getUsuarioById com id inexistente");
        Integer id = 10;
        Repositorio instance = new Repositorio();
        Usuario expResult = null;
        Usuario result = instance.getUsuarioById(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetUsuarioById_Null() {
        System.out.println("getUsuarioById com id nulo");
        Integer id = null;
        Repositorio instance = new Repositorio();
        Usuario expResult = null;
        Usuario result = instance.getUsuarioById(id);
        assertEquals(expResult, result);
    }

}
