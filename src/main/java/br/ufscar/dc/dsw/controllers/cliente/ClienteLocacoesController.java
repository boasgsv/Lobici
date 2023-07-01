package br.ufscar.dc.dsw.controllers.cliente;

import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.domain.Locacao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/cliente/locacoes")
public class ClienteLocacoesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clienteIdStr = (String) req.getSession().getAttribute("user_id");
        String resource = null;
        try{
            int clienteId = Integer.parseInt(clienteIdStr);
            LocacaoDAO dao = new LocacaoDAO();
            ArrayList<Locacao> locacoes = dao.readAllByCliente(clienteId);
            req.setAttribute("locacoes", locacoes);
            resource = "cliente_locacoes.jsp";
        } catch (Exception e) {
            req.setAttribute("error",  "EXCEPTION: " + e.getMessage());
            resource = "error_page.jsp";
        } finally {
            req.getRequestDispatcher("/" + resource).include(req, resp);
        }
    }
}
