package grupointegrado.com.educacional.cursos.controller;

import grupointegrado.com.educacional.cursos.model.Curso;
import grupointegrado.com.educacional.cursos.dto.CursoRequestDTO;
import grupointegrado.com.educacional.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Curso> findByCodigo(@PathVariable String codigo) {
        Curso curso = this.repository.findByCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com o código especificado"));

        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody CursoRequestDTO dto) {
        if (this.repository.findByCodigo(dto.codigo()).isPresent()) {
            throw new IllegalArgumentException("O curso não pode ser criado: já existe um curso com o código " + dto.codigo());
        }

        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return ResponseEntity.ok(this.repository.save(curso));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Integer id, @RequestBody CursoRequestDTO dto) {
        Curso curso = this.repository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com o ID especificado"));

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return ResponseEntity.ok(this.repository.save(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Curso curso = this.repository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com o ID especificado"));

        this.repository.delete(curso);
        return ResponseEntity.noContent().build();
    }
}
