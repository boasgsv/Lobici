package br.ufscar.dc.dsw.controllers;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.util.Erro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/locacoes/*"})
public class LocacaoController extends HttpServlet {
    LocacaoDAO locacaoDAO;

    @Override
    public void init() throws ServletException {
        this.locacaoDAO = new LocacaoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        if (action == null) {
            action = "";
        }

        switch(action) {
            case "/":
                listarLocacoes(req, resp);
                break;
            case "/criar":
                criarLocacao(req, resp);
                break;
            default:
                listarLocacoes(req, resp);
                break;
        }

    }

    private void criarLocacao(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try{
            String userType = (String) req.getSession().getAttribute("user_type");
            long locadoraId = Long.parseLong(req.getParameter("locadora_id"));
            long clienteId = Long.parseLong(req.getParameter("cliente_id"));
            LocalDateTime ldt = LocalDateTime.parse(req.getParameter("datahora_locacao"));
            if (!"cliente".equals(userType)){
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            Locacao locacao = locacaoDAO.findByClienteAndDateTime(clienteId, ldt);
            Erro erro = new Erro();
                if (locacao != null) {
                    throw new RuntimeException("customer");
                }
                locacao = locacaoDAO.findByLocadoraAndDateTime(locadoraId, ldt);
                if (locacao != null) {
                    throw new RuntimeException("rental");
                }
            locacaoDAO.insert(clienteId, locadoraId, ldt);
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
        resp.sendRedirect("/Lobici");
    }

    private void listarLocacoes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = (long) req.getSession().getAttribute("user_id");
        String userType = (String) req.getSession().getAttribute("user_type");
        if ("locadora".equals(userType)){
            List<Locacao> locacoes = this.locacaoDAO.readAllByLocadora(userId);
            ArrayList<Cliente> clientes = new ArrayList<>();
            ClienteDAO clienteDAO = new ClienteDAO();
            for (Locacao locacao : locacoes) {
                long clienteId = locacao.getClienteId();
                Cliente cliente = clienteDAO.find(clienteId);
                clientes.add(cliente);
            }
            req.setAttribute("locacoes", locacoes);
            req.setAttribute("clientes", clientes);
            req.getRequestDispatcher("/logado/locadora/lista_locacoes.jsp").include(req, resp);

        }
        else if ("cliente".equals(userType)){
            List<Locacao> locacoes = this.locacaoDAO.readAllByCliente(userId);
            ArrayList<Locadora> locadoras = new ArrayList<>();
            LocadoraDAO locadoraDAO = new LocadoraDAO();
            for (Locacao locacao : locacoes) {
                long locadoraId = locacao.getLocadoraId();
                Locadora locadora = locadoraDAO.get(locadoraId);
                locadoras.add(locadora);
            }
            req.setAttribute("locacoes", locacoes);
            req.setAttribute("locadoras", locadoras);
            req.getRequestDispatcher("/logado/cliente/lista_locacoes.jsp").include(req, resp);

        }
        else
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
