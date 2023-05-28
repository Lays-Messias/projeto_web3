package br.com.vidaanimal.pet.animal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    private Integer dono;
    private String nome;
    private String raca;
    private String porte;
    private int idade;

    public Animal() {
    }

    public Animal(Integer codigo, Integer dono, String nome, String raca, String porte, int idade) {
        this.codigo = codigo;
        this.nome = nome;
        this.dono = dono;
        this.raca = raca;
        this.porte = porte;
        this.idade = idade;
    }

    public String getNome() {
        return this.nome;
    }

    public Integer getDono() {
        return this.dono;
    }

    public String getRaca() {
        return this.raca;
    }

    public int getIdade() {
        return idade;
    }

    public String getPorte() {
        return porte;
    }

    public Integer getCodigo() {
        return this.codigo;
    }
}
