package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produtos;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
public class ProdutosController {

	@Autowired
	ProdutoDAO produtoDao;

	@RequestMapping("/produtos/form")
	public ModelAndView form() {

		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());

		return modelAndView;
	}

	@RequestMapping(value = "/produtos", method = RequestMethod.POST)
	public String gravar(Produtos produto) {

		System.out.println(produto.toString());

		produtoDao.gravar(produto);

		return "/produtos/ok";
	}

	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtoDao.listar());

		return modelAndView;
	}

}