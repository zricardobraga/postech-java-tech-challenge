package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.servico;

import br.com.postech.java.tech.challenge.cortistyle.domain.servico.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    Optional<Servico> findByDescricao(String descricao);
}
