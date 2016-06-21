package br.com.prodama.repository.cadastros;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.prodama.enun.TipoEmpresa;
import br.com.prodama.model.cadastro.Empresa;
import br.com.prodama.model.cadastro.Filial;
import br.com.prodama.model.cadastro.Usuario;

public class Filiais implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public void adicionar(Filial filial){
		manager.merge(filial);
	}
	
	public Filial pesquisaPorId(Long id) {
		return manager.find(Filial.class, id);
	}
	
	public boolean pesquisaPorNome(Filial filial) {
		Query query = manager.createQuery("From filial where RazaoSocial = :filial", Filial.class);
		query.setParameter("filial", filial.getRazaoSocial());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public String pesquisaFilial(Filial filial) {
		Query query = manager.createQuery("From Filial where razaoSocial = :empresa or Cnpj = :cnpj or Cpf = :cpf",
				Filial.class);
		query.setParameter("empresa", filial.getRazaoSocial());
		query.setParameter("cnpj", filial.getCnpj());
		query.setParameter("cpf", filial.getCpf());
		List<?> resultList = query.getResultList();
		if (!resultList.isEmpty()) {
			if (filial.getTipoEmpresa().equals(TipoEmpresa.FISICA)) {
				return "Já existe uma filial com os dados informados! \n Empresa: " + filial.getRazaoSocial() + "\n" + "CPF: "
						+ filial.getCpf();
			} else {
				return "Já existe uma filial com os dados informados! \n Empresa: " + filial.getRazaoSocial() + "\n" + "CNPJ: "
						+ filial.getCnpj();
			}			
		} else {
			return "OK";
		}
	}
	
	public void excluir(Filial filial) {
		filial = pesquisaPorId(filial.getCodigo());
		manager.remove(filial);

	}

	public List<Filial> todos() {
		return manager.createQuery("from Filial", Filial.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public  List<Filial> filiaisNaoAssociadas(Usuario usuario,Empresa empresa) {
		Query query = manager.createQuery("Select e From Filial e Where e.empresa = :empresa and not exists (Select u from e.abrangenciaUsuarios u Where u = :usuario)", Filial.class);
		query.setParameter("usuario", usuario);
		query.setParameter("empresa", empresa);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Filial> filiaisAssociadas(Usuario usuario, Empresa empresa) {
		Query query = manager.createQuery("Select e From Filial e Where e.empresa = :empresa and exists (Select u from e.abrangenciaUsuarios u Where u = :usuario)", Filial.class);
		query.setParameter("usuario", usuario);
		query.setParameter("empresa", empresa);
		return query.getResultList();
	}
}
