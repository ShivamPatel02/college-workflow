package app;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        
        // Allow access to login page, static resources, and login endpoint
        if (requestURI.equals("/login") || 
            requestURI.startsWith("/css/") || 
            requestURI.startsWith("/js/") || 
            requestURI.startsWith("/images/") ||
            requestURI.startsWith("/static/") ||
            requestURI.equals("/h2-console") ||
            requestURI.startsWith("/h2-console/")) {
            return true;
        }
        
        HttpSession session = request.getSession(false);
        User currentUser = null;
        
        if (session != null) {
            currentUser = (User) session.getAttribute("currentUser");
        }
        
        // If no user is logged in, redirect to login
        if (currentUser == null) {
            response.sendRedirect("/login");
            return false;
        }
        
        return true;
    }
}