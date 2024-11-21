package grupointegrado.com.educacional.aluno.controller;

import grupointegrado.com.educacional.aluno.model.Aluno;
import grupointegrado.com.educacional.aluno.dto.AlunoRequestDTO;
import grupointegrado.com.educacional.aluno.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Aluno> findByMatricula(@PathVariable String matricula) {
        Aluno aluno = this.repository.findByMatricula(matricula)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com a matrícula especificada"));

        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody AlunoRequestDTO dto) {
        if (this.repository.findByMatricula(dto.matricula()).isPresent()) {
            throw new IllegalArgumentException("O aluno não pode ser criado: já existe um aluno com o número de matrícula " + dto.matricula());
        }

        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());

        return ResponseEntity.ok(this.repository.save(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID especificado"));

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());

        return ResponseEntity.ok(this.repository.save(aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID especificado"));

        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
    }
}
