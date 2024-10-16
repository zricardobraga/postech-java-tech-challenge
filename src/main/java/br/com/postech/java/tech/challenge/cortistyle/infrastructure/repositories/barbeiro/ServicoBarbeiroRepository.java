package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.barbeiro;

import br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.entity.BarbeiroServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoBarbeiroRepository extends JpaRepository<BarbeiroServico, Long> {
}
