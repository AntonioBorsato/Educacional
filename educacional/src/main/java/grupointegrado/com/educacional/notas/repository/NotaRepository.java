package grupointegrado.com.educacional.notas.repository;

import grupointegrado.com.educacional.notas.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
}