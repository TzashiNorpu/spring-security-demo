package com.tzashinorpu.springsecuritydemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tzashinorpu.springsecuritydemo.filter.LoginFilter;
import com.tzashinorpu.springsecuritydemo.filter.VerifyCodeFilter;
import com.tzashinorpu.springsecuritydemo.model.ResponseBean;
import com.tzashinorpu.springsecuritydemo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
//		return new BCryptPasswordEncoder();
	}

	@Bean
	RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
		hierarchy.setHierarchy("ROLE_admin > ROLE_user");
		return hierarchy;
	}

	/*@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userService, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}*/

    /*@Bean
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("123").roles("admin").build());
        manager.createUser(User.withUsername("norpu").password("123").roles("user").build());
        return manager;
    }*/

	/*@Bean
	public InMemoryUserDetailsManager userDetailsService(NoOpPasswordEncoder passwordEncoder) {
			*//*InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("norpu").password(passwordEncoder.encode("123")).roles("admin").build());
        return manager;*//*

        UserDetails admin = User
                .withUsername("norpu")
                .password(passwordEncoder.encode("123"))
                .roles("admin")
                .build();
        UserDetails user = User
                .withUsername("norpu")
                .password(passwordEncoder.encode("123"))
                .roles("admin")
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }*/

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
	                                               VerifyCodeFilter verifyCodeFilter,
	                                               LoginFilter loginFilter
	) throws Exception {
//		http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);

		// @formatter:off
        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/js/**", "/css/**","/verify-code/**")
                                .permitAll()
                                .requestMatchers("/admin/**").hasRole("admin")
                                .requestMatchers("/user/**").hasRole("user")
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(
                        formLoginCustomizer-> formLoginCustomizer
                                        .loginPage("/login.html")
                                        .loginProcessingUrl("/doLogin")
                                        .usernameParameter("name")
                                        .passwordParameter("passwd")
//		                                    .authenticationDetailsSource(myWebAuthenticationDetailsSource)
                                        .successHandler((req, resp, authentication) -> {
                                            Object principal = authentication.getPrincipal();
                                            resp.setContentType("application/json;charset=utf-8");
                                            PrintWriter out = resp.getWriter();
	                                        out.write(new ObjectMapper().writeValueAsString(ResponseBean.ok("success", authentication.getPrincipal())));
                                            out.flush();
                                            out.close();
                                        })
                                        .failureHandler((req, resp, e) -> {
                                            resp.setContentType("application/json;charset=utf-8");
                                            PrintWriter out = resp.getWriter();
	                                          out.write(new ObjectMapper().writeValueAsString(ResponseBean.error(e.getMessage())));
                                            out.flush();
                                            out.close();
                                        })
                                        .permitAll()
                )
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
		                        out.write(new ObjectMapper().writeValueAsString(ResponseBean.error("注销成功")));
                            out.flush();
                            out.close();
                        })
                        .permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(handler->handler
                        .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
		                        out.write(new ObjectMapper().writeValueAsString(ResponseBean.error("尚未登录，请先登录")));
                            out.flush();
                            out.close();
                }));
		// @formatter:off
		http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);
        // @formatter:on
		return http.build();
	}

	@Bean
	MyAuthenticationProvider myAuthenticationProvider(UserDetailsService userService) {
		MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
		myAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		myAuthenticationProvider.setUserDetailsService(userService);
		return myAuthenticationProvider;
	}

	@Bean
	protected AuthenticationManager authenticationManager(MyAuthenticationProvider myAuthenticationProvider) throws Exception {
		ProviderManager providerManager = new ProviderManager(Collections.singletonList(myAuthenticationProvider));
		return providerManager;
	}


	@Bean
	LoginFilter loginFilter(AuthenticationManager authenticationManager) throws Exception {
		LoginFilter loginFilter = new LoginFilter();
		loginFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				Object object = authentication.getPrincipal();
//				object.setPassword(null);
				ResponseBean ok = ResponseBean.ok("登录成功!", object);
				String s = new ObjectMapper().writeValueAsString(ok);
				out.write(s);
				out.flush();
				out.close();
			}
		});
		loginFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				ResponseBean respBean = ResponseBean.error(exception.getMessage());
				if (exception instanceof LockedException) {
					respBean.setMsg("账户被锁定，请联系管理员!");
				} else if (exception instanceof CredentialsExpiredException) {
					respBean.setMsg("密码过期，请联系管理员!");
				} else if (exception instanceof AccountExpiredException) {
					respBean.setMsg("账户过期，请联系管理员!");
				} else if (exception instanceof DisabledException) {
					respBean.setMsg("账户被禁用，请联系管理员!");
				} else if (exception instanceof BadCredentialsException) {
					respBean.setMsg("用户名或者密码输入错误，请重新输入!");
				}
				out.write(new ObjectMapper().writeValueAsString(respBean));
				out.flush();
				out.close();
			}
		});
		loginFilter.setAuthenticationManager(authenticationManager);
		loginFilter.setFilterProcessesUrl("/doLogin");
		return loginFilter;
	}
}
