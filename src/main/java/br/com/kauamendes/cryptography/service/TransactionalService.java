package br.com.kauamendes.cryptography.service;

import br.com.kauamendes.cryptography.dto.CreateTransactionRequest;
import br.com.kauamendes.cryptography.dto.TransactionResponse;
import br.com.kauamendes.cryptography.entity.TransactionalEntity;
import br.com.kauamendes.cryptography.repository.TransactionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
