package br.ufscar.dc.dsw.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;

@WebServlet(urlPatterns = "/locadoras/*")
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private LocadoraDAO dao;

    @Override
    public void init() {
        dao = new LocadoraDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
                
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

    try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);}
    }
    
    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Locadora> listaLocadoras = dao.getAll();
        request.setAttribute("listaLocadoras", listaLocadoras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/locadoras/lista.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Integer, String> getLocadoras() {
        Map <Integer, String> locadoras = new HashMap<>();
        for (Locadora locadora: new LocadoraDAO().getAll()) {
            locadoras.put(locadora.getId(), locadora.getNome());
        }
        return locadoras;
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("locadoras", getLocadoras());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/locadoras/gerenciamentoLocadora.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Locadora locadora = dao.get(id);
        request.setAttribute("locadora", locadora);
        request.setAttribute("locadoras", getLocadoras());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/locadoras/gerenciamentoLocadora.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        String CNPJ = request.getParameter("CNPJ");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");
        
        Locadora locadora = new Locadora(id, nome, CNPJ, cidade);
        dao.insert(locadora);
        response.sendRedirect("/locadoras/lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String CNPJ = request.getParameter("CNPJ");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");
        
        Locadora locadora = new Locadora(id, CNPJ, nome, cidade);
        dao.update(locadora);
        response.sendRedirect("/locadoras/lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Locadora locadora = new Locadora(id);
        dao.delete(locadora);
        response.sendRedirect("/locadoras/lista");
    }
}
