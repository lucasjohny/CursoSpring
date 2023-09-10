package med.voll.api.medico;

public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedico(Medico medico){//construindo um construtor para a pesquila de medicos e usar na paginação
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
