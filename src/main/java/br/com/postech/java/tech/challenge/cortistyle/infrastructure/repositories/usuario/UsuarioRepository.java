package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario;

import br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
