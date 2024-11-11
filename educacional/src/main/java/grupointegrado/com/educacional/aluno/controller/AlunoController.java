package grupointegrado.com.educacional.aluno.controller;

import grupointegrado.com.educacional.aluno.model.Aluno;
import grupointegrado.com.educacional.aluno.dto.AlunoRequestDTO;
import grupointegrado.com.educacional.aluno.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID especificado"));
    }

    @GetMapping("/matricula/{matricula}")
    public Aluno findByMatricula(@PathVariable String matricula) {
        return this.repository.findByMatricula(matricula)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com a matrícula especificada"));
    }

    @PostMapping
    public Aluno save(@RequestBody AlunoRequestDTO dto) {
        if (this.repository.findByMatricula(dto.matricula()).isPresent()) {
            throw new IllegalArgumentException("O aluno não pode ser criado: já existe um aluno com o número de matrícula " + dto.matricula());
        }

        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());

        return this.repository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Integer id,
                        @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID especificado"));

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());

        return this.repository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID especificado"));

        this.repository.delete(aluno);
    }

}