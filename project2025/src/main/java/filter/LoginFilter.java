package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/WEB-INF/jsp/*", "/MissionReview", "/CarrierConfig", "/FlightPlanner"})
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("loginUser") != null);

        if (!isLoggedIn) {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            chain.doFilter(request, response); // 通過
        }
    }
}