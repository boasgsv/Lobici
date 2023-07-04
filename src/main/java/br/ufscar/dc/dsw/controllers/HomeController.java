package br.ufscar.dc.dsw.controllers;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocadoraDAO locadoraDAO = new LocadoraDAO();
        List<Locadora> locadoras = locadoraDAO.getAll();
        req.setAttribute("listaLocadoras", locadoras);
        req.getRequestDispatcher("home.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
