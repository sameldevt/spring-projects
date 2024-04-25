package med.voll.api.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice // indica que essa classe sera responsavel por tratar erros da api
public class TratadorDeErros {
	
	@ExceptionHandler(EntityNotFoundException.class) // captura a excessao EntityNotFoundException sempre q for lançada
	public ResponseEntity tratarErro500() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors(); // retorna quais campos estao invalidos
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new));
	}
	
	private record DadosErrosValidacao(String campo, String mensagem) {
		public DadosErrosValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
