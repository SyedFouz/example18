package com.example.example18.webconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
public class ProjectSecurityConfig {
    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    /*  @Bean
      SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

          http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers("/saveMsg"))
                  .authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated()
                          .requestMatchers("/home").permitAll()
                          .requestMatchers("/holidays/**").permitAll()
                          .requestMatchers("/contact").permitAll()
                          .requestMatchers("/saveMsg").permitAll()
                          .requestMatchers("/courses").permitAll()
                          .requestMatchers("/about").permitAll()
                          .requestMatchers("/login").permitAll()
                          .requestMatchers("/assets/**").permitAll().requestMatchers("/logout").permitAll())
                  .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login").successHandler(loginSuccessHandler).failureUrl("/login?error=true").permitAll())
                  .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true").permitAll()
                          .invalidateHttpSession(true).permitAll())
                  .httpBasic(Customizer.withDefaults());

          return http.build();
      }*/
    //ignoringRequestMatchers(PathRequest.toH2Console())
    // .requestMatchers(PathRequest.toH2Console()).permitAll()
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http.csrf((csrf) -> csrf.ignoringRequestMatchers(mvcMatcherBuilder.pattern("/saveMsg")).ignoringRequestMatchers("/public/**")
                )
                .authorizeHttpRequests((requests) -> requests.requestMatchers(mvcMatcherBuilder.pattern("/dashboard")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayMessages")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/admin/**")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/closeMsg/**")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayProfile")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/updateProfile")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/home")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/holidays/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/contact")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/saveMsg")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/courses")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/about")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/assets/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/error")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/login")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/logout")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/public/**")).permitAll()
                )
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

//        http.headers(headersConfigurer -> headersConfigurer
//                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();

    }

 /*   @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername("user").passwordEncoder(encoder::encode).password("12345").roles("USER").build();

        UserDetails admin = User.withUsername("admin").passwordEncoder(encoder::encode).password("12345").roles("ADMIN", "USER").build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/
}
