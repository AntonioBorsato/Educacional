package grupointegrado.com.educacional.professores.repository;

import grupointegrado.com.educacional.professores.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Optional<Object> findByEspecialidade(String especialidade);
}
