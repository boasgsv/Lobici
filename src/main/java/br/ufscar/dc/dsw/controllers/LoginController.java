package br.ufscar.dc.dsw.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Login", urlPatterns = { "/login.jsp"})
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		if (request.getParameter("bOK") != null) {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			if (email == null || email.isEmpty()) {
				erros.add("Email não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				UsuarioDAO dao = new UsuarioDAO();
				Usuario usuario = dao.find(email);
				if (usuario != null) {
					if (usuario.getSenha().equalsIgnoreCase(senha)) {
						request.getSession().setAttribute("usuarioLogado", usuario);
                        //response.sendRedirect("usuarios/");
                        ClienteDAO clientedao = new ClienteDAO();
                        Cliente cliente = clientedao.find(usuario.getId());
                        LocadoraDAO locadoradao = new LocadoraDAO();
                        Locadora locadora = locadoradao.find(usuario.getId()); 
                        if (cliente != null) { // se é cliente
                            response.sendRedirect("locacoes/"); // locações do cliente
                        } else if(locadora != null){ // se é locadora
                            response.sendRedirect("locacoes/"); // locações na locadora
                        } else { // se é admin
							// tem que incluir os dados de usuário e ter a opção de também redirecionar para locadoras no view
                            response.sendRedirect("clientes/");  
						}
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
		}
		request.getSession().invalidate();

		request.setAttribute("mensagens", erros);

		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}