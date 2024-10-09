package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.filial;

import br.com.postech.java.tech.challenge.cortistyle.domain.filial.entity.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {
}
