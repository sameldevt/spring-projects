package med.voll.api.dto;

import med.voll.api.entities.medico_entity.Especialidade;
import med.voll.api.entities.medico_entity.Medico;

public record DadosListagemMedico(
		Long id,
		String nome,
		String email,
		String crm,
		Especialidade especialide) {
	
		public DadosListagemMedico(Medico medico) {
			this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
		}

}
