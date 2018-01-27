package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(name="/home")
public class HomeController {

	@RequestMapping(name="/")
	public String index() {
		
		System.out.println("Exibindo a home da CDC");
		
		return "home";
	}

}
