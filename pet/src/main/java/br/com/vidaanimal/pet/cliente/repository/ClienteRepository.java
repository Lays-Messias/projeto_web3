package br.com.vidaanimal.pet.cliente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.vidaanimal.pet.cliente.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
