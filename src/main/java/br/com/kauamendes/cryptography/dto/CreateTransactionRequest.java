package br.com.kauamendes.cryptography.dto;

public record CreateTransactionRequest(String userDocument, String creditCardToken, Long value) {
}
