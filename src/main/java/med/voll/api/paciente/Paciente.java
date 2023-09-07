package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //gera o equals somente em cima do id
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String CPF;
    private String telefone;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.CPF = dados.CPF();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }
}