package com.sidorov.pet.book_store.configs;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityConfig {

    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/profile/**", "/cart/**").authenticated()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
                        .anyRequest().permitAll())
//                .httpBasic(httpBasic -> httpBasic
//                        .securityContextRepository(
//                                new HttpSessionSecurityContextRepository()
//                        ))
//                .sessionManagement(sessionManagement -> sessionManagement
//                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                )
                .formLogin(login -> {
                    login.loginPage("/login");
                    login.loginProcessingUrl("/authenticate");
                })
                .build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

//    @Bean
//    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
//        //return new JdbcUserDetailsManager(); //if default realization is enough
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$lODrq2TZ35l7Zzs.TI4.b.gMcL8/.mfL4wLG3LS5rdAEkOQ80ST3m")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$g38h7sS5eijnJ6hYcJjqDub8G0x0L93B5h6tFWxuRhkoORnNS/yXa")
//                .roles("USER", "ADMIN")
//                .build();
//
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//
//        if (users.userExists(user.getUsername())){
//            users.deleteUser(user.getUsername());
//        }
//        if (users.userExists(admin.getUsername())){
//            users.deleteUser(admin.getUsername());
//        }
//
//        users.createUser(user);
//        users.createUser(admin);
//
//        return users;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$lODrq2TZ35l7Zzs.TI4.b.gMcL8/.mfL4wLG3LS5rdAEkOQ80ST3m")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$g38h7sS5eijnJ6hYcJjqDub8G0x0L93B5h6tFWxuRhkoORnNS/yXa")
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
}
