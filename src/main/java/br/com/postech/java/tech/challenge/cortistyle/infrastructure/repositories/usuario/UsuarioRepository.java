package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.usuario;

import br.com.postech.java.tech.challenge.cortistyle.domain.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByIdAndTpUsuario(Long id, TipoUsuarioEnum tpUsuario);

    Optional<Usuario> findByUsernameAndPassword(String username, String password);

    Optional<Usuario> findByToken(String token);
}
