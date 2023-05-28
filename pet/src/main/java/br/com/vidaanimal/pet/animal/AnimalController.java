package br.com.vidaanimal.pet.animal;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.com.vidaanimal.pet.animal.model.AnimalDTO;
import br.com.vidaanimal.pet.animal.repository.AnimalRepository;
import br.com.vidaanimal.pet.cliente.ClienteController;
import br.com.vidaanimal.pet.cliente.model.Cliente;
import br.com.vidaanimal.pet.cliente.repository.ClienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/animais")
public class AnimalController {

    private AnimalRepository animalRepository;
    private ClienteRepository clienteRepository;

    public AnimalController(AnimalRepository animalRepository, ClienteRepository clienteRepository) {
        this.animalRepository = animalRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public Iterable<AnimalDTO> listar() {
       ArrayList<AnimalDTO> animais = new ArrayList<>();
        for(Animal animal:animalRepository.findAll()){
        animais.add(adicionarDono(animal));
        }
        return animais;
 
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
    public ResponseEntity<Object> atualizar(@RequestBody Animal animal, @PathVariable(value = "codigo") Integer codigo) {
        Optional<Animal> animalEncontrado = animalRepository.findById(codigo);
        if (animalEncontrado.isEmpty()) {
           return new ResponseEntity<Object>("Animal não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(animalRepository.save(animal));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Object> buscarAnimalPorCodigo(@PathVariable(value = "codigo") Integer codigo) {
        Optional<Animal> animalEncontrado = animalRepository.findById(codigo);
        if (animalEncontrado.isEmpty()) {
            return new ResponseEntity<Object>("Animal não encontrado", HttpStatus.NOT_FOUND);
        }
        Animal animal = animalEncontrado.get();
        return ResponseEntity.ok(adicionarDono(animal));
    }

    public AnimalDTO adicionarDono(Animal animal){
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(animal.getDono());
        if (clienteEncontrado.isPresent()) {
            return new AnimalDTO(animal, clienteEncontrado.get());
        }
        return new AnimalDTO(animal);
    }
}