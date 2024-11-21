package grupointegrado.com.educacional.disciplinas.repository;

import grupointegrado.com.educacional.disciplinas.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    Optional<Object> findByCodigo(String codigo);
}
