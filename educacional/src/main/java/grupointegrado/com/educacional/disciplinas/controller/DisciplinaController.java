package grupointegrado.com.educacional.disciplinas.controller;

import grupointegrado.com.educacional.disciplinas.model.Disciplina;
import grupointegrado.com.educacional.disciplinas.dto.DisciplinaRequestDTO;
import grupointegrado.com.educacional.disciplinas.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Disciplina> findByCodigo(@PathVariable String codigo) {
        Disciplina disciplina = (Disciplina) this.repository.findByCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com o código especificado"));

        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public ResponseEntity<Disciplina> save(@RequestBody DisciplinaRequestDTO dto) {
        if (this.repository.findByCodigo(dto.codigo()).isPresent()) {
            throw new IllegalArgumentException("A disciplina não pode ser criada: já existe uma disciplina com o código " + dto.codigo());
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        return ResponseEntity.ok(this.repository.save(disciplina));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id, @RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com o ID especificado"));

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        return ResponseEntity.ok(this.repository.save(disciplina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com o ID especificado"));

        this.repository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }
}
