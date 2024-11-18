package grupointegrado.com.educacional.cursos.repository;

import grupointegrado.com.educacional.cursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByCodigo(String codigo);
}
