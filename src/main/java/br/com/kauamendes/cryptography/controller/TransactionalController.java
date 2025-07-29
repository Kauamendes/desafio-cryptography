package br.com.kauamendes.cryptography.controller;

import br.com.kauamendes.cryptography.dto.CreateTransactionRequest;
import br.com.kauamendes.cryptography.dto.TransactionResponse;
import br.com.kauamendes.cryptography.service.TransactionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> listAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(transactionalService.listAll(pageable));
    }
}
