package br.com.vidaanimal.pet.animal;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vidaanimal.pet.animal.model.Animal;
import br.com.vidaanimal.pet.animal.repository.AnimalRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/animais")
public class AnimalController {

    private AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping
    public Iterable<Animal> listar() {
        return animalRepository.findAll();
    }

    
    @PostMapping
    public Animal cadastrar(@RequestBody @Valid Animal animal) {
        return animalRepository.save(animal);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable(value = "codigo") Integer codigo) {
        animalRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public Animal atualizar(@RequestBody Animal animal, @PathVariable(value = "codigo") Integer codigo) {
        Optional<Animal> animalEncontrado = animalRepository.findById(codigo);
        if (animalEncontrado.isEmpty()) {
            throw new RuntimeException("Animal não encontrado");
        }
        return animalRepository.save(animal);
    }

    @GetMapping("/{codigo}")
    public Animal buscarAnimalPorCodigo(@PathVariable(value = "codigo") Integer codigo) {
        Optional<Animal> animalEncontrado = animalRepository.findById(codigo);
        if (animalEncontrado.isEmpty()) {
            throw new RuntimeException("Animal não encontrado");
        }
        return animalEncontrado.get();
    }    
}