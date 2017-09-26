package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produtos;

@Controller
public class ProdutosController {

	
	@Autowired
	ProdutoDAO produtoDao;
	
    @RequestMapping("/produtos/form")
    public String form(){
        return "produtos/form";
    }
    
    @RequestMapping("/produtos")
    public String gravar(Produtos produto){
    	
        System.out.println(produto.toString());

        produtoDao.gravar(produto);
        
        return "/produtos/ok";
    }

}