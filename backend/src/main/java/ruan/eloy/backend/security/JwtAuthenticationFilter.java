package ruan.eloy.backend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    private StudentDetailsService studentDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, StudentDetailsService studentDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.studentDetailsService = studentDetailsService;
    }

    public JwtAuthenticationFilter() {}

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String BEARER_ = "Bearer ";
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_)) {
            return bearerToken.substring(BEARER_.length(), bearerToken.length());
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getTokenFromRequest(request);

            if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                Long studentId = jwtTokenProvider.getUserIdFromToken(jwt);

                StudentPrincipal studentPrincipal = studentDetailsService.loadUserById(studentId);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(studentPrincipal, null, studentPrincipal.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }
}
