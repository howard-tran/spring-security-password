package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public SecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/", "/index.html")
      .permitAll()
      .antMatchers("/api/user/**")
      .hasRole(UserRole.NORMAL_USER.toString())
      .antMatchers("/api/course/**")
      .hasRole(UserRole.ADMIN.toString())
      .anyRequest()
      .authenticated()
      .and()
      .formLogin()
      .permitAll()
      .and()
      .logout()
      .permitAll()
      .and()
      .csrf()
      .disable()
      .httpBasic();
  }

  @Override
  @Bean
  protected UserDetailsService userDetailsService() {
    UserDetails user = User
      .builder()
      .username("ad")
      .password(this.passwordEncoder.encode("123"))
      .roles(UserRole.NORMAL_USER.toString())
      .build();

    UserDetails myWife = User
      .builder()
      .username("wife")
      .password(this.passwordEncoder.encode("123"))
      .roles(UserRole.ADMIN.toString())
      .build();

    return new InMemoryUserDetailsManager(user, myWife);
  }
}
