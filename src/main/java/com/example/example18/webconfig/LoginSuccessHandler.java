package com.example.example18.webconfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public  void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorityList = (Collection<SimpleGrantedAuthority>) userDetails.getAuthorities();
        String url = "/home";
        for (SimpleGrantedAuthority simpleGrantedAuthority : simpleGrantedAuthorityList) {
            if (simpleGrantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                url = "/dashboard";
            }
        }
        response.sendRedirect(url);
    }
}