package med.voll.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.entities.medico_entity.Medico;

// Repositories são interfaces que simplificam o conceito DAO (data access object), muito usado
// em aplicações que nao usam spring
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);

}
