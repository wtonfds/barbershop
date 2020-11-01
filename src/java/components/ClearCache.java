/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wtonf
 */
public class ClearCache implements Filter{

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest req = (HttpServletRequest) request;
        SessionContext sessionContext = (SessionContext) req.getSession().getAttribute("usuarioLogado");

        if (sessionContext.getUsuarioLogado() != null) {
            // User is logged in, so just continue request.
            chain.doFilter(request, response);
        } else {
            // User is not logged in, so redirect to index.
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getContextPath() + "/login.jsf");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    
}
