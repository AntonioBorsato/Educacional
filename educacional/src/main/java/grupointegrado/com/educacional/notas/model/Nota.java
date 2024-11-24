package grupointegrado.com.educacional.notas.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double nota;

    @Column
    private Date data_lancamento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(Date data_lancamento) {
        this.data_lancamento = data_lancamento;
    }
}