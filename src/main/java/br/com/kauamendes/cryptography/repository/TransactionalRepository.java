package br.com.kauamendes.cryptography.repository;

import br.com.kauamendes.cryptography.entity.TransactionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionalRepository extends JpaRepository<TransactionalEntity, Long> {
}
