package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.avaliacao;

import br.com.postech.java.tech.challenge.cortistyle.domain.avaliacao.entity.AvaliacaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoClientRepository extends JpaRepository<AvaliacaoCliente, Long> {
  
}
