package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.entities.medico_entity.Especialidade;

public record DadosCadastroMedico(
		// @NotNull
		@NotBlank // verifica se nao esta em branco. tambem verifica se nao esta nulo. 
				  // dessa forma nao precisamos usar o @NotNull
				  // @NotBlank so funciona com Strings
		String nome, 
		
		@NotBlank
		@Email
		String email,
		
		@NotBlank
		String telefone,
		
		@NotBlank
		@Pattern(regexp = "\\d{4,6}") // verifica se o crm esta no padrao especificado.
									  // nesse caso verifica se o valor é numero e possui de 4 a 6 digitos
		String crm, 
		
		@NotNull 
		Especialidade especialidade, 
		
		@NotNull
		@Valid	// valida outro objeto dentro de um objeto que esta sendo validado
		DadosEndereco endereco) 
{

}
