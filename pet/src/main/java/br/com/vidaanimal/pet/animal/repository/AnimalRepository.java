package br.com.vidaanimal.pet.animal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.vidaanimal.pet.animal.model.Animal;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
}
