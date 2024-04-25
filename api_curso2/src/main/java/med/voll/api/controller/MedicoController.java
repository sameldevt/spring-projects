package med.voll.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosAtualizacaoMedico;
import med.voll.api.dto.DadosCadastroMedico;
import med.voll.api.dto.DadosDetalhamentoMedico;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.entities.medico_entity.Medico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired // indica para o spring que a interface repository precisa ser instanciada nessa classe
	// essa tecnica é chamada de injeção de dependencia e é feita automaticamente pelo spring
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) { //@RequestBody = corpo da requisição
		var medico = new Medico(dados);
		repository.save(medico);
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}
	
    // metodo retornando todas as entidades jpa
//	@GetMapping
//	public List<Medico> listar() {
//	    return repository.findAll();
//	}
	
	// metodo retornando uma lista de dto's referente a todas as entidades jpa
//	@GetMapping
//	public List<DadosListagemMedico> listar(){
//		return repository.findAll().stream().map(DadosListagemMedico::new).toList();
//	}
	
	// metodo retornando uma lista de dto's referente a todas as entidade jpa com paginação
	// http://localhost:8080/medicos?size=1 -> size é a quantidade de elementos da pagina
	// http://localhost:8080/medicos?size=3&page=0 -> page é a pagina
	// http://localhost:8080/medicos?sort=nome -> sort retorna os registros ordenados pelo parametro (nome)
	// http://localhost:8080/medicos?sort=nome,desc -> ordena o retorno de forma decrescente
	
	// @PageableDefault altera o padrao de peginação. isso é util para substituir os comandos acima
//	@GetMapping
//	public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
//		return repository.findAll(paginacao).map(DadosListagemMedico::new);
//	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
	}
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		
		medico.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) { // @PathVariable indica que o parametro vem da url
		var medico = repository.getReferenceById(id);
		
		medico.excluir();
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) { // @PathVariable indica que o parametro vem da url
		var medico = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	
}
