package jimwu.itest.portal.security;


import jimwu.itest.portal.security.oauth2.CustomAuthenticationSuccessHandler;
import jimwu.itest.portal.security.oauth2.CustomerOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomerOAuth2UserService oAuth2UserService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(
                        "/login.html",
                        "/register.html",
                        "/forgot.html",
                        "/reset.html",
                        "/register",
                        "/reset",
                        "/resetRequest",
                        "/login",
                        "/img/**",
                        "/css/*",
                        "/js/*"
                )
                .permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureUrl("/login.html?error")
                .defaultSuccessUrl("/index.html")
                .and()
                .oauth2Login()
                .loginPage("/login.html")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                .and()
                .logout() //配置logout功能
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html?logout");


    }
}