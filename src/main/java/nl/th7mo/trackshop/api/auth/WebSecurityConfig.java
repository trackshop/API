// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity securitySettings) throws Exception {
        securitySettings.csrf().disable();
        securitySettings.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        EndpointSecuritySettings.setEndpointConstraints(securitySettings);
        securitySettings.authorizeRequests().anyRequest().authenticated();
        addFilters(securitySettings);
    }

    private void addFilters(HttpSecurity securitySettings) throws Exception {
        securitySettings.addFilter(
            new AuthenticationFilter(authenticationManager())
        );
        securitySettings.addFilterBefore(
            new AuthorizationFilter(),
            UsernamePasswordAuthenticationFilter.class
        );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
