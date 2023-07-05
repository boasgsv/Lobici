package br.ufscar.dc.dsw.controllers;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocadoraDAO locadoraDAO = new LocadoraDAO();
        String cidade = (String) req.getParameter("cidade");
        List<Locadora> todasLocadoras = locadoraDAO.getAll();
        List<Locadora> locadorasSelecionadas = null;
        if (cidade != null && !("".equals(cidade)))
            locadorasSelecionadas = locadoraDAO.getAllWhereCity(cidade);
        else
            locadorasSelecionadas = todasLocadoras;

        List<String> listaCidades = reduzirParaListaDeCidades(todasLocadoras);
        req.setAttribute("listaLocadorasSelecionadas", locadorasSelecionadas);
        req.setAttribute("listaLocadoras", todasLocadoras);
        req.setAttribute("listaCidades", listaCidades);
        req.getRequestDispatcher("home.jsp").include(req, resp);
    }

    private List<String> reduzirParaListaDeCidades(List<Locadora> todasLocadoras) {
        ArrayList<String> listaCidades = new ArrayList<>();

        for (Locadora locadora : todasLocadoras) {
            String cidade = locadora.getCidade();
            if (listaCidades.contains(cidade))
                continue;
            listaCidades.add(cidade);
        }
        return listaCidades;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
