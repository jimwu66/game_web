/*
package jimwu.itest.portal.security.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .authorizationEndpoint(authorization -> authorization
                                .baseUri("/login/oauth2/authorization")
                        )
                );
    }
}

 */
/*
    // refactor as google/oauth2 login 2022-7-14

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(
                        "/login.html",
                        "/register.html",
                        "/register",
                        "/login",
                        "/img/**",
                        "/js/*"
                        )
                        .permitAll()
                        .anyRequest().authenticated()
                .and().formLogin()
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login.html?error")
                        .defaultSuccessUrl("/index.html")
                        .permitAll()
                        .and()
                .logout() //配置logout功能
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login.html?logout")
                        .permitAll()
                        .and()
                .oauth2Login()
                        .loginPage("/login")
                        .defaultSuccessUrl("/oauth2-login",true)
                        .authorizationEndpoint(authorizationEndpoint ->
                                authorizationEndpoint
                                .authorizationRequestResolver(
                                //        new CustomAuthorizationRequestResolver(this.clientRegistrationRepository))
                                .baseUri("/login/oauth2/authorization"));


    }
}
*/