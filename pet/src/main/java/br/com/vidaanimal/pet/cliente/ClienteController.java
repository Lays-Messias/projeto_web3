package br.com.vidaanimal.pet.cliente;

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

import br.com.vidaanimal.pet.cliente.model.Cliente;
import br.com.vidaanimal.pet.cliente.repository.ClienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public Iterable<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente cadastrar(@RequestBody @Valid Cliente cliente) {
        return clienteRepository.save(cliente);
        
    }
    
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable(value = "codigo") Integer codigo) {
        clienteRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public Cliente atualizar(@RequestBody Cliente cliente, @PathVariable(value = "codigo") Integer codigo) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(codigo);
        if (clienteEncontrado.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{codigo}")
    public Cliente buscarClientePorCodigo(@PathVariable(value = "codigo") Integer codigo) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(codigo);
        if (clienteEncontrado.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        return clienteEncontrado.get();
    }

}
