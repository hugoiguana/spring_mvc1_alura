package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produtos;

@Transactional
@Repository
public class ProdutoDAO {

	@PersistenceContext
	EntityManager manager;

	public void gravar(Produtos produto) {
		manager.persist(produto);
	}

	public List<Produtos> listar() {
		return manager.createQuery("SELECT p FROM Produtos p", Produtos.class).getResultList();
	}

	public Produtos find(Integer id) {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id",
				Produtos.class).setParameter("id", id).getSingleResult();
	}

}
