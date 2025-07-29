package br.com.kauamendes.cryptography.service;

import br.com.kauamendes.cryptography.dto.CreateTransactionRequest;
import br.com.kauamendes.cryptography.dto.TransactionResponse;
import br.com.kauamendes.cryptography.dto.UpdateTransactionRequest;
import br.com.kauamendes.cryptography.entity.TransactionalEntity;
import br.com.kauamendes.cryptography.repository.TransactionalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionalService {

    private final TransactionalRepository transactionalRepository;

    public TransactionalService(TransactionalRepository transactionalRepository) {
        this.transactionalRepository = transactionalRepository;
    }

    public void create(CreateTransactionRequest createTransactionRequest) {
        TransactionalEntity entity = new TransactionalEntity();
        entity.setRawUserDocument(createTransactionRequest.userDocument());
        entity.setRawCreditCardToken(createTransactionRequest.creditCardToken());
        entity.setTransactionalValue(createTransactionRequest.value());
        transactionalRepository.save(entity);
    }

    public Page<TransactionResponse> listAll(Pageable pageable) {
        return transactionalRepository.findAll(pageable)
                .map(TransactionResponse::fromEntity);
    }

    public TransactionResponse findById(Long id) {
        var entity = transactionalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TransactionResponse.fromEntity(entity);
    }

    public void deleteById(Long id) {
        if (!transactionalRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        transactionalRepository.deleteById(id);
    }

    public void update(Long id, UpdateTransactionRequest request) {
        var entity = transactionalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setTransactionalValue(request.value());
        transactionalRepository.save(entity);
    }
}
