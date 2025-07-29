package br.com.kauamendes.cryptography.dto;

import br.com.kauamendes.cryptography.entity.TransactionalEntity;

public record TransactionResponse(Long id, String userDocument, String creditCardToken, Long value) {

    public static TransactionResponse fromEntity(TransactionalEntity transactionalEntity) {
        return new TransactionResponse(transactionalEntity.getId(),
                transactionalEntity.getRawUserDocument(),
                transactionalEntity.getRawCreditCardToken(),
                transactionalEntity.getTransactionalValue());
    }
}
