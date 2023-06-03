package dcc.ufjf.dcc193.debora.tomato;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotBlank(message="Campo obrigatório!")
    String email;
    @NotBlank(message="Campo obrigatório!")
    String nome;
    @NotBlank(message="Campo obrigatório!")
    String senha;    
    
    public Usuario() {
        this(null,null,null,null);
    }

    public Usuario(String nome) {
        this(null,null,nome,null);
    }

    public Usuario(Long id, String email, String nome, String senha) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", email=" + email + ", nome=" + nome + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
