package med.voll.api.dto;

import med.voll.api.entities.endereco_entity.Endereco;
import med.voll.api.entities.medico_entity.Especialidade;
import med.voll.api.entities.medico_entity.Medico;

public record DadosDetalhamentoMedico(
		Long id,
		String nome,
		String email,
		String crm,
		String telefone,
		Especialidade especialidade,
		Endereco endereco
		) 
{
	public DadosDetalhamentoMedico(Medico medico) {
		this(medico.getId(), 
				medico.getNome(), 
				medico.getEmail(), 
				medico.getCrm(), 
				medico.getTelefone(), 
				medico.getEspecialidade(),
				medico.getEndereco());
	}
}
