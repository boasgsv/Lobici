package br.ufscar.dc.dsw.controllers;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
//import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(urlPatterns = "/locadoras/*")
public class LocadoraController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LocadoraDAO dao;

    @Override
    public void init() {
        dao = new LocadoraDAO();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/listar":
                    listarLocadoras(request, response);
                    break;
                case "/buscar":
                    buscarLocadora(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualizarLocadora(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                default:
                    listarLocadoras(request, response);
                    break;
            } }
        catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }

    }
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadoras/formulario.jsp");
        dispatcher.forward(request, response);
    }
    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Locadora locadora = dao.get(id);
        request.setAttribute("locadora", locadora);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadoras/formulario.jsp");
        dispatcher.forward(request, response);
    }
    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        String nome = request.getParameter("nome");
        String CNPJ = request.getParameter("CNPJ");
        String cidade = request.getParameter("cidade");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario(email, senha);
        usuarioDAO.insert(usuario);
        usuario = usuarioDAO.find(email);
        long id = usuario.getId();

        Locadora locadora = new Locadora(id, nome, CNPJ, cidade);
        dao.insert(locadora);

        response.sendRedirect("listar");
    }


    private void atualizarLocadora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(request.getParameter("id"));

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String CNPJ = request.getParameter("CNPJ");
        String cidade = request.getParameter("cidade");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario(id, email, senha);
        usuarioDAO.update(usuario);

        Locadora locadoraAtualizado = new Locadora(id, nome, CNPJ, cidade);

        dao.update(locadoraAtualizado);
        response.sendRedirect(request.getContextPath() + "/locadoras/listar");

    }



    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        dao.excluirLocacoesPorLocadora(id);
        dao.delete(id);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario(id);
        usuarioDAO.delete(usuario);

        response.sendRedirect("listar");
    }

    private void listarLocadoras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Locadora> listaLocadoras = dao.getAll();
        request.setAttribute("listaLocadoras", listaLocadoras);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadoras/listar.jsp");
        dispatcher.forward(request, response);
    }

    private void buscarLocadora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Locadora locadora = dao.get(id);
        request.setAttribute("locadora", locadora);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadoras/detalhes.jsp");
        dispatcher.forward(request, response);
    }
}
