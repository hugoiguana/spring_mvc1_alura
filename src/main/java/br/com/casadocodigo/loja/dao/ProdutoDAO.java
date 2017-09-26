package br.com.casadocodigo.loja.dao;

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

}
