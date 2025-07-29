package br.com.kauamendes.cryptography.entity;

import br.com.kauamendes.cryptography.service.CryptoService;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transactions")
public class TransactionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_document")
    private String encryptedUserDocument;

    @Column(name = "credit_card_token")
    private String encryptedCreditCardToken;

    @Column(name = "transactional_value")
    private Long transactionalValue;

    @Transient
    private String rawUserDocument;

    @Transient
    private String rawCreditCardToken;

    public TransactionalEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncryptedUserDocument() {
        return encryptedUserDocument;
    }

    public void setEncryptedUserDocument(String encryptedUserDocument) {
        this.encryptedUserDocument = encryptedUserDocument;
    }

    public String getEncryptedCreditCardToken() {
        return encryptedCreditCardToken;
    }

    public void setEncryptedCreditCardToken(String encryptedCreditCardToken) {
        this.encryptedCreditCardToken = encryptedCreditCardToken;
    }

    public Long getTransactionalValue() {
        return transactionalValue;
    }

    public void setTransactionalValue(Long transactionalValue) {
        this.transactionalValue = transactionalValue;
    }

    public String getRawUserDocument() {
        return rawUserDocument;
    }

    public void setRawUserDocument(String rawUserDocument) {
        this.rawUserDocument = rawUserDocument;
    }

    public String getRawCreditCardToken() {
        return rawCreditCardToken;
    }

    public void setRawCreditCardToken(String rawCreditCardToken) {
        this.rawCreditCardToken = rawCreditCardToken;
    }

    @PrePersist
    public void prePersist() {
        this.encryptedUserDocument = CryptoService.encrypt(this.rawUserDocument);
        this.encryptedCreditCardToken = CryptoService.encrypt(this.rawCreditCardToken);
    }

    @PostLoad
    public void postLoad() {
        this.rawUserDocument = CryptoService.decrypt(encryptedUserDocument);
        this.rawCreditCardToken = CryptoService.decrypt(encryptedCreditCardToken);
    }
}
