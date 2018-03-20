package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produtos;
import br.com.casadocodigo.loja.models.TipoPreco;

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
		return manager.createQuery("SELECT distinct(p) FROM Produtos p JOIN FETCH p.precos WHERE p.id = :id",
				Produtos.class).setParameter("id", id).getSingleResult();
	}
	
	public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco) {
		
		TypedQuery<BigDecimal> query = manager.createQuery(
				"select sum(preco.valor) from Produtos p join p.precos preco where preco.tipo = :tipoPreco",
				BigDecimal.class);
		
		query.setParameter("tipoPreco", tipoPreco);
		
		return query.getSingleResult();
	}

}
