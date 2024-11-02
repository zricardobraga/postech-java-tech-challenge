package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.filial;

import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.filial.entity.BarbeiroFilial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilialBarbeiroRepository extends JpaRepository<BarbeiroFilial, Long> {

    List<BarbeiroFilial> findAllByFilialId(Long filialId);

    Optional<BarbeiroFilial> findByBarbeiroId(Long id);
}
