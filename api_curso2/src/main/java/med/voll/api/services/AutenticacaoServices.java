package med.voll.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import med.voll.api.repository.UsuarioRepository;

@Service // diz que essa classe é responsavel por fornecer um serviço
public class AutenticacaoServices implements UserDetailsService { // UserDetailsService é responsável por validar
	                                                              // os detalhes do usuario
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByLogin(username);
	} 

	
}
