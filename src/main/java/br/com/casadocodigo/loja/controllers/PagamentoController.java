package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;
import br.com.casadocodigo.loja.models.Usuario;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {

	@Autowired
	CarrinhoCompras carrinho;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private MailSender sender;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model) {

		return () -> {

			try {

				String uri = "http://book-payment.herokuapp.com/payment";
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()),
						String.class);

				model.addFlashAttribute("sucesso", response);
				System.out.println(response);

				// envia email para o usuário
				enviaEmailCompraProduto(usuario);

				// model.addFlashAttribute("sucesso", "Pagamento Realizado com
				// Sucesso");

				return new ModelAndView("redirect:/produtos");
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				model.addFlashAttribute("falha", "Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}
		};
	}

	private void enviaEmailCompraProduto(Usuario usuario) {

		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra finalizada com sucesso");
		email.setTo(usuario.getEmail());
		email.setText("Compra aprovada com sucesso no valor de " + carrinho.getTotal());
		email.setFrom("compras@casadocodigo.com.br");

		sender.send(email);
	}

}
