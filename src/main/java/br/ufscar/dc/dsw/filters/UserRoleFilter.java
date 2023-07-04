package br.ufscar.dc.dsw.filters;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "UserRole", urlPatterns = {"/"})
public class UserRoleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Object userIdObj = req.getSession().getAttribute("user_id");
        if (userIdObj == null) {
            chain.doFilter(req, res);
            return;
        }

        long userId = (long) userIdObj;
        ClienteDAO clienteDAO = new ClienteDAO();
        LocadoraDAO locadoraDAO = new LocadoraDAO();
        Cliente cliente = clienteDAO.find(userId);
        Locadora locadora = locadoraDAO.find(userId);

        if (cliente != null)
            req.getSession().setAttribute("user_type", "cliente");
        else if (locadora != null)
            req.getSession().setAttribute("user_type", "locadora");
        else
            req.getSession().setAttribute("user_type", "admin");
        chain.doFilter(req, res);
    }
}
