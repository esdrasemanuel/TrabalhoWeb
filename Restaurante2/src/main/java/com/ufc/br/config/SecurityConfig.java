package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ufc.br.security.UserDetailsServiceImplemetacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	 
	@Autowired
    private UserDetailsServiceImplemetacao userDetailsServiceImpl;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/pessoa/cadastro").permitAll()
                .antMatchers(HttpMethod.POST, "/pessoa/cadastrar").permitAll()
                .antMatchers(HttpMethod.GET, "/index").permitAll()
                .antMatchers(HttpMethod.GET, "/pessoa/logar").permitAll()
                .antMatchers(HttpMethod.GET, "/adm/cadprato").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/adm/addprato").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/adm/addprato").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/gerente/listarPratos").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/pessoa/logar")
                .permitAll()
                .defaultSuccessUrl("/index")

                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl("/pessoa/logout")
                .logoutSuccessUrl("/pessoa/logar")
                .permitAll();
    }
    

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/img/**","/images/**");
}


}
