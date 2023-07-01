package br.ufscar.dc.dsw.controllers.locadora;

import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.domain.Locacao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/locadora/locacoes")
public class LocadoraLocacoesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locadoraIdStr = (String) req.getSession().getAttribute("user_id");
        String resource = null;
        try{
            int locadoraId = Integer.parseInt(locadoraIdStr);
            LocacaoDAO dao = new LocacaoDAO();
            ArrayList<Locacao> locacoes = dao.readAllByLocadora(locadoraId);
            req.setAttribute("locacoes", locacoes);
            resource = "locadora_locacoes.jsp";
        } catch (Exception e) {
            req.setAttribute("error",  "EXCEPTION: " + e.getMessage());
            resource = "error_page.jsp";
        } finally {
            req.getRequestDispatcher("/" + resource).include(req, resp);
        }
    }
}
