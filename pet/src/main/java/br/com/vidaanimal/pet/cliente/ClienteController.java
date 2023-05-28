package br.com.vidaanimal.pet.cliente;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteRepository.save(cliente));
        } catch (Exception e) {
            if (e.getCause() instanceof ConstraintViolationException ex) {
                if ("cliente.cpf".equals(ex.getConstraintName()))
                    return ResponseEntity.unprocessableEntity().body("CPF já cadastrado");
                if ("cliente.email".equals(ex.getConstraintName()))
                    return ResponseEntity.unprocessableEntity().body("Email já cadastrado");
            }
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable(value = "codigo") Integer codigo) {
        clienteRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Object> atualizar(@RequestBody Cliente cliente,
            @PathVariable(value = "codigo") Integer codigo) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(codigo);
        if (clienteEncontrado.isEmpty()) {
            return new ResponseEntity<Object>("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Object> buscarClientePorCodigo(@PathVariable(value = "codigo") Integer codigo) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(codigo);
        if (clienteEncontrado.isEmpty()) {
            return new ResponseEntity<Object>("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(clienteEncontrado.get());
    }

}
