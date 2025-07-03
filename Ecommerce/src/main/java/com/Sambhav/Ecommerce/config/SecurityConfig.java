package com.Sambhav.Ecommerce.config;

import com.Sambhav.Ecommerce.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity //Tells the spring security go with this flow not make own
public class SecurityConfig
{
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(customizer -> customizer.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/register").permitAll() // ✅ Allow this endpoint without login
                            .anyRequest().authenticated());                      // 🔐 Require login for all other routes


         // You can also use .formLogin(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }
    //Customizer<CsrfConfigurer<HttpSecurity>> custmcsrf = new Customizer<CsrfConfigurer<HttpSecurity>>()
//        {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> customizer)
//            {
//                customizer.disable();
//            }
//        };
//        http.csrf(custmcsrf);
//        return http.build();
}

//Authentication
//You enter correct username and password
//Spring verifies it → you're now authenticated
//You're assigned a role: e.g., "ROLE_USER"
//        ✅ Authorization
//You try to access /admin/products
//Spring checks:
//Does your role match ROLE_ADMIN?
//If yes → ✅ Authorized
//If no → ❌ Access denied (403 Forbidden)
//cd path/to/Ecommercedemo
//git init
//git add .
//git commit -m "Initial commit"
//git remote add origin https://github.com/Sambhav88/Ecommercedemo.git
//git branch -M main
//git push -u origin main