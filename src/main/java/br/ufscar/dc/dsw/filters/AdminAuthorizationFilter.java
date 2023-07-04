package br.ufscar.dc.dsw.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminAuthorization", urlPatterns = {"/clientes/*", "/locadoras/*"})
public class AdminAuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String userType = (String) req.getSession().getAttribute("user_type");
        if (!"admin".equals(userType)) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(req, res);

    }
}
