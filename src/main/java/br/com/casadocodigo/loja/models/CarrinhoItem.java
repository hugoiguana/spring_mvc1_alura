package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

public class CarrinhoItem {

	private Produtos produto;
	private TipoPreco tipoPreco;

	public CarrinhoItem(Produtos produto, TipoPreco tipoPreco) {
		super();
		this.produto = produto;
		this.tipoPreco = tipoPreco;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public TipoPreco getTipoPreco() {
		return tipoPreco;
	}

	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}

	public BigDecimal getPreco() {
		return produto.precoPara(tipoPreco);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((tipoPreco == null) ? 0 : tipoPreco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (tipoPreco != other.tipoPreco)
			return false;
		return true;
	}

	public BigDecimal getTotal(int quantidade) {
		return this.getPreco().multiply(new BigDecimal(quantidade));
	}

}
