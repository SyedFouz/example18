package com.example.example18.security;

import com.example.example18.model.Person;
import com.example.example18.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EazySchoolAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EazySchoolPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person person = personRepository.readByName(name);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + person.getRoles().getRoleName()));
        if (person.getPersonId() > 0 && passwordEncoder.matches(password,person.getPwd())) {
            return new UsernamePasswordAuthenticationToken(name, password, grantedAuthorityList);
        } else {
            throw new BadCredentialsException("Invalid Credentials !");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
