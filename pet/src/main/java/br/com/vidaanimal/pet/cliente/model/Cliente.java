package br.com.vidaanimal.pet.cliente.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;


@Entity(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    private String nome;
    @CPF
    private String cpf;
    @Email
    private String email;

    public Cliente() {}
    //construtor
    public Cliente(Integer codigo, String nome, String cpf, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }   
   
    public String getCpf() {
        return this.cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    
}
