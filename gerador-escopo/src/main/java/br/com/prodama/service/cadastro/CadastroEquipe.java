package br.com.prodama.service.cadastro;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.prodama.model.cadastro.Equipe;
import br.com.prodama.repository.cadastros.Equipes;
import br.com.prodama.service.NegocioException;
import br.com.prodama.util.Transactional;

public class CadastroEquipe implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Equipes equipes;
	
	@Transactional
	public void salvar(Equipe equipe) throws NegocioException {
		if (equipes.pesquisaPorNome(equipe) && (equipe.getCodigo() == null || equipe.getCodigo()==0)) {
			throw new NegocioException(
					"Já existe uma equipe com este nome: "+equipe.getDescricao());
		}
		
		this.equipes.adicionar(equipe);
	}

	@Transactional
	public void excluir(Equipe equipe) {
		equipes.excluir(equipe);
		
	}
	

}
