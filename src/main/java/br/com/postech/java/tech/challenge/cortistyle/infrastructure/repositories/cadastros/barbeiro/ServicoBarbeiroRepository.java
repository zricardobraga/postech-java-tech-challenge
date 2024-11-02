package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.barbeiro;

import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.entity.BarbeiroServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ServicoBarbeiroRepository extends JpaRepository<BarbeiroServico, Long> {

    ArrayList<BarbeiroServico> findAllByBarbeiroId(Long barbeiroId);
}
