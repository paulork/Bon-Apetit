package br.com.paulork.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Paulo R. Kraemer <paulork10@gmail.com>
 */
public class Voto implements Serializable {

    private Integer id;
    private Usuario usuario;
    private Restaurante restaurante;
    private LocalDate data;
    private boolean escolha = false;
    
    public Voto() {}
    
    public Voto(Usuario usuario, Restaurante restaurante, LocalDate data) {
        this.usuario = usuario;
        this.restaurante = restaurante;
        this.data = data;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isEscolha() {
        return escolha;
    }

    public void setEscolha(boolean escolha) {
        this.escolha = escolha;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.usuario);
        hash = 41 * hash + Objects.hashCode(this.restaurante);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voto other = (Voto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.restaurante, other.restaurante)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"usuario\": " + usuario + ", \"restaurante\": "+restaurante+", \"data\": \""+data+"\", \"escolha\": "+escolha+"}";
    }
    
    

}
