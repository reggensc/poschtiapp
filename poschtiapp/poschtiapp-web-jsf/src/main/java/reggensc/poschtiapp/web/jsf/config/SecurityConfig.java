package reggensc.poschtiapp.web.jsf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
@Import(PersistenceConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // TODO introduce password hashing

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth.userDetailsService(userDetailsService);
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .formLogin()
                .loginPage("/login.xhtml").permitAll()
                .defaultSuccessUrl("/index.xhtml")
        .and()
            .authorizeRequests()
                .antMatchers("/javax.faces.resource/**").permitAll()
                .anyRequest().authenticated()
        .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index.xhtml")
        .and()
            .rememberMe()
                .rememberMeServices(rememberMeServices())
                .key("remember-me-token")
        .and()
            .csrf().disable();
        // @formatter:on
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        AbstractRememberMeServices abstractRememberMeServices = new TokenBasedRememberMeServices("remember-me-token",
                userDetailsService);
        abstractRememberMeServices.setParameter("remember_me_input");
        return abstractRememberMeServices;
    }

}
