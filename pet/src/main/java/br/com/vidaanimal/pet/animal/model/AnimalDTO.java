package br.com.vidaanimal.pet.animal.model;

import br.com.vidaanimal.pet.cliente.model.Cliente;

public class AnimalDTO {
    private Integer codigo;
    private Cliente dono;
    private String nome;
    private String raca;
    private String porte;
    private int idade;

    public AnimalDTO(Animal animal, Cliente cliente) {
        this.codigo = animal.getCodigo();
        this.nome = animal.getNome();
        this.raca = animal.getRaca();
        this.porte = animal.getPorte();
        this.idade = animal.getIdade();
        this.dono = cliente;
    }

    public AnimalDTO(Animal animal) {
        this.codigo = animal.getCodigo();
        this.nome = animal.getNome();
        this.raca = animal.getRaca();
        this.porte = animal.getPorte();
        this.idade = animal.getIdade();
    }

    public String getNome() {
        return this.nome;
    }

    public Cliente getDono() {
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