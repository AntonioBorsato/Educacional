package grupointegrado.com.educacional.aluno.dto;

import java.util.Date;

public record AlunoRequestDTO(String nome, String email, String matricula, Date data_nascimento) {
}
