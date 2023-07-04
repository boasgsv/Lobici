package br.ufscar.dc.dsw.controllers;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Cliente;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@WebServlet(urlPatterns = "/clientes/*")
public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClienteDAO dao;

    @Override
    public void init() {
        dao = new ClienteDAO();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/listar":
                    listarClientes(request, response);
                    break;
                case "/buscar":
                    buscarCliente(request, response);
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
                    atualizarCliente(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                default:
                    listarClientes(request, response);
                    break;
            } }
        catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }

    }
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/clientes/formulario.jsp");
        dispatcher.forward(request, response);
    }
    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cliente cliente = dao.buscarClientePorId(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/clientes/formulario.jsp");
        dispatcher.forward(request, response);
    }
    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        String CPF = request.getParameter("CPF");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("data_nascimento");
    
        // Formato da data de nascimento
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date data;
        try {
            // Convertendo a String para um objeto Date
            data = sdf.parse(dataNascimento);
        } catch (ParseException e) {
            // Tratamento de erro caso a data não esteja no formato esperado
            throw new ServletException("Erro ao converter data de nascimento", e);
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario(email, senha);
        usuarioDAO.insert(usuario);
        usuario = usuarioDAO.find(email);
        long id = usuario.getId();

        Cliente cliente = new Cliente(id, CPF, nome, telefone, sexo, data);
        dao.adicionarCliente(cliente);
    
        response.sendRedirect("listar");
    }


    private void atualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String CPF = request.getParameter("CPF");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("data_nascimento");
    
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data = sdf.parse(dataNascimento);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = new Usuario(id, email, senha);
            usuarioDAO.update(usuario);
    
            Cliente clienteAtualizado = new Cliente(id, CPF, nome, telefone, sexo, data);
    
            dao.atualizarCliente(clienteAtualizado);
            response.sendRedirect(request.getContextPath() + "/clientes/listar");
        } catch (ParseException e) {
            e.printStackTrace();
            // Trate o erro de conversão de data aqui
        }
    }

    
    
    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        dao.excluirLocacoesPorCliente(id);
        dao.delete(id);
        response.sendRedirect("listar");
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> listaClientes = dao.listarClientes();
        request.setAttribute("listaClientes", listaClientes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/clientes/listar.jsp");
        dispatcher.forward(request, response);
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cliente cliente = dao.buscarClientePorId(id);
        request.setAttribute("cliente", cliente);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/clientes/detalhes.jsp");
        dispatcher.forward(request, response);
    }
}
