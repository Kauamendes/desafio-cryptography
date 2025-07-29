package br.com.kauamendes.cryptography.controller;

import br.com.kauamendes.cryptography.dto.CreateTransactionRequest;
import br.com.kauamendes.cryptography.dto.TransactionResponse;
import br.com.kauamendes.cryptography.dto.UpdateTransactionRequest;
import br.com.kauamendes.cryptography.service.TransactionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/transactions")
public class TransactionalController {

    private final TransactionalService transactionalService;

    public TransactionalController(TransactionalService transactionalService) {
        this.transactionalService = transactionalService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTransactionRequest request) {
        transactionalService.create(request);
        return ResponseEntity.created(URI.create("/transactions/")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id,
                                       @RequestBody UpdateTransactionRequest request) {
        transactionalService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> listAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(transactionalService.listAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionalService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        transactionalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
