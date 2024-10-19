package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.barbeiro;

import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.entity.BarbeiroHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarbeiroHorarioRepository extends JpaRepository<BarbeiroHorario, Long> {

    Optional<BarbeiroHorario> findByBarbeiroIdAndHorario(Long barbeiroId, String horario);

    List<BarbeiroHorario> findAllByBarbeiroId(Long barbeiroId);
}
