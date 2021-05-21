

package uz.pdp.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.project.security.JwtAuthenticationEntryPoint;
import uz.pdp.project.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
 /*   @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");

    }*/



 /*   @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
        .csrf().disable()
               .authorizeRequests()
               .antMatchers("/admin/**").hasRole("ADMIN")
               .antMatchers("/anonymous*").anonymous()
               .antMatchers("/login*").permitAll()
              .anyRequest().authenticated();
    }*/

/*    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("userPass"))
                .authorities("USER").and()
                .withUser("admin").password(passwordEncoder().encode("adminPass"))
                .authorities("ADMIN");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/api/auth/**", "/api/test/public", "/api/region")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/district/*", "/api/region/*", "/api/university/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/district/*", "/api/region/*", "/api/university/*").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/**").authenticated();

        /*http.addFilterAfter(new CustomFilter(),
                BasicAuthenticationFilter.class);*/
        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
