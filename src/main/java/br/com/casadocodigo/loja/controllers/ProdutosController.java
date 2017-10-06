package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produtos;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping(value = "/produtos")
public class ProdutosController {

	@Autowired
	ProdutoDAO produtoDao;

	@RequestMapping("form")
	public ModelAndView form() {

		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(Produtos produto, RedirectAttributes redirectAtrib) {

		System.out.println(produto.toString());

		produtoDao.gravar(produto);
		
		redirectAtrib.addFlashAttribute("sucesso", "Produto " + produto.getDescricao() + " cadastrado com sucesso!");

		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtoDao.listar());

		return modelAndView;
	}

}