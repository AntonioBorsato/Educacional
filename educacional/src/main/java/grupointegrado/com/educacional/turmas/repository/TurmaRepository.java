package grupointegrado.com.educacional.turmas.repository;

import grupointegrado.com.educacional.turmas.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TurmaRepository extends JpaRepository<Turma, Integer> {
}