package ru.SecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.SecurityApp.services.PersonDetailsService;
//import ru.SecurityApp.security.AuthProviderImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
        .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // конфигурируем сам spring security (какая страница отвечает за вход, за ошибки)
        // конфигурируем авторизацию (двавть или не давать доступ к страницам)
        httpSecurity
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/auth/login","/auth/registration", "error").permitAll()
//                .anyRequest().authenticated()
             //   .anyRequest().hasAnyAuthority("","") //list of user actions
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
       // return NoOpPasswordEncoder.getInstance();
        return  new BCryptPasswordEncoder();
    }


    //    private AuthProviderImpl authProvider;

//    @Autowired
//    public SecurityConfig(AuthProviderImpl authProvider) {
//        this.authProvider = authProvider;
//    }
//
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authProvider);
//    }
}
