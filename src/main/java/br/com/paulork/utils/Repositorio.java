package br.com.paulork.utils;

import br.com.paulork.model.Restaurante;
import br.com.paulork.model.Usuario;
import br.com.paulork.model.Voto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import br.com.paulork.manager.Sessao;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Paulo R. Kraemer <paulork10@gmail.com>
 */
@ApplicationScoped
public class Repositorio {

    private final List<Usuario> logins;
    private final List<Voto> votos;
    private final List<Restaurante> restaurantes;
    @Inject private Sessao userSession;

    public Repositorio() {
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

    public boolean checkLogin(String usuario, String senha) {
        return logins.contains(new Usuario(usuario, senha));
    }

    public List<Voto> getVotosList() {
        return votos;
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }
    
    public Restaurante getRestauranteById(Integer id) {
        if (id != null) {
            for (Restaurante r : restaurantes) {
                if (r.getId().equals(id)) {
                    return r;
                }
            }
        }
        return null;
    }

    public Usuario getUsuarioById(Integer id) {
        if (id != null) {
            for (Usuario login : logins) {
                if (login.getId().equals(id)) {
                    return login;
                }
            }
        }
        return null;
    }
    
    public Usuario getUsuarioByName(String user) {
        if (user != null && !user.isEmpty()) {
            for (Usuario login : logins) {
                if (login.getUser().equals(user)) {
                    return login;
                }
            }
        }
        return null;
    }

    public Voto newVoto(Voto voto) {
        if(voto != null){
            // Simula uma campo sequencial
            voto.setId(votos.size());
            votos.add(voto);
            return voto;
        }
        return null;
    }

    public boolean jaVotouHoje() {
        Stream<Voto> filter = votos.stream().filter((d) -> d.getData().isEqual(LocalDate.now()) && d.getUsuario().getId().equals(userSession.getUsuario().getId()));
        return filter.findAny().isPresent();
    }

    // Lista dos votos de hoje com o mais votado em primeiro
    public List<RestDto> getVotosHoje() {
        return getVotosHoje(null);
    }
    
    // Lista dos votos no dia com o mais votado em primeiro
    // Criado um DTO para tranferir os dados para Views
    public List<RestDto> getVotosHoje(LocalDate date) {
        HashMap<Restaurante, Long> collect = votos.stream()
                .filter(d -> d.getData().isEqual(date != null ? date : LocalDate.now()))
                .collect(
                        Collectors.groupingBy(Voto::getRestaurante, HashMap::new, Collectors.counting())
                );
        List<RestDto> list = new ArrayList<>();
        for (Map.Entry<Restaurante, Long> entry : collect.entrySet()) {
            Restaurante key = entry.getKey();
            Long value = entry.getValue();
            RestDto dto = new RestDto(key.getId(), key.getNome(), value);
            list.add(dto);
        }
        Comparator<RestDto> comp = Comparator.comparing(RestDto::getCounting);
        Collections.sort(list,comp.reversed());
        return list;
    }
    
    public Restaurante getMaisVotadoDia(){
        HashMap<Restaurante, Long> collect = votos.stream()
                .filter(d -> d.getData().isEqual(LocalDate.now()))
                .collect(
                        Collectors.groupingBy(Voto::getRestaurante, HashMap::new, Collectors.counting())
                );
        return Collections.max(collect.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
    // Faz a checagem se o Restaurante já foi escolhido na semana
    public boolean jaEscolhidoNaSemana(Integer id_restaurante){
        TreeMap<LocalDate, Restaurante> maisVotadosDaSemana = getMaisVotadosDaSemana();
        for (Map.Entry<LocalDate, Restaurante> entry : maisVotadosDaSemana.entrySet()) {
            LocalDate key = entry.getKey();
            Restaurante value = entry.getValue();
            if(value.getId().equals(id_restaurante)){
                return true;
            }
        }
        return false;
    }
    
    // Retorna os mais votados da semana
    public TreeMap<LocalDate, Restaurante> getMaisVotadosDaSemana(){
        TreeMap<LocalDate, Restaurante> map = new TreeMap<>();
        
        Stream<Voto> votosSemana = votos.stream().filter((d) -> d.getData().compareTo(LocalDate.now().minusWeeks(1)) >= 0);
        TreeMap<LocalDate, Map<Restaurante, Long>> collect = votosSemana.collect(Collectors.groupingBy(Voto::getData, TreeMap::new, Collectors.groupingBy(Voto::getRestaurante, Collectors.counting())));
        for (Map.Entry<LocalDate, Map<Restaurante, Long>> entry : collect.entrySet()) {
            LocalDate data = entry.getKey();
            Restaurante restMaisVotado = Collections.max(entry.getValue().entrySet(), Map.Entry.comparingByValue()).getKey();
            map.put(data, restMaisVotado);
        }
        
        return map;
    }

    public class RestDto implements Serializable{
        public Integer id;
        public String nome;
        public Long counting;

        public RestDto() {
        }

        public RestDto(Integer id, String nome, Long counting) {
            this.id = id;
            this.nome = nome;
            this.counting = counting;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Long getCounting() {
            return counting;
        }

        public void setCounting(Long counting) {
            this.counting = counting;
        }
        
    }

}
