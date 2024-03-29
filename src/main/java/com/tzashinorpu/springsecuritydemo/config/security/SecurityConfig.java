package com.tzashinorpu.springsecuritydemo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tzashinorpu.springsecuritydemo.config.security.filter.CrypticFilter;
import com.tzashinorpu.springsecuritydemo.config.security.filter.LoginFilter;
import com.tzashinorpu.springsecuritydemo.pojo.vo.ResponseResult;
import com.tzashinorpu.springsecuritydemo.repo.JdbcTokenRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
//@EnableWebSecurity
public class SecurityConfig<S extends Session> {

	@Autowired
	DataSource dataSource;
	/*	@Bean
		SessionRegistryImpl sessionRegistry() {
			return new SessionRegistryImpl();
		}*/
	@Autowired
	FindByIndexNameSessionRepository<S> sessionRepository;
	@Autowired
	CrypticFilter crypticFilter;
	@Autowired
	private ApplicationEventPublisher publisher;

	/*@Bean
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
/*    @Autowired
    MyWebAuthenticationDetailsSource myWebAuthenticationDetailsSource;*/

	private AuthenticationSuccessHandler getSuccessHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
				// 抛出登录成功事件
				publisher.publishEvent(new LoginSuccessEvent("Spring Security login success handler"));
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				Object object = authentication.getPrincipal();
//				object.setPassword(null);
				ResponseResult ok = ResponseResult.ok("登录成功!", object);
				String s = new ObjectMapper().writeValueAsString(ok);
				out.write(s);
				out.flush();
				out.close();
			}
		};
	}

	private AuthenticationFailureHandler getFailureHandler() {
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				ResponseResult respBean = ResponseResult.error(exception.getMessage());
				if (exception instanceof LockedException) respBean.setMsg("账户被锁定，请联系管理员!");
				else if (exception instanceof CredentialsExpiredException) respBean.setMsg("密码过期，请联系管理员!");
				else if (exception instanceof AccountExpiredException) respBean.setMsg("账户过期，请联系管理员!");
				else if (exception instanceof DisabledException) respBean.setMsg("账户被禁用，请联系管理员!");
				else if (exception instanceof BadCredentialsException) respBean.setMsg("用户名或者密码输入错误，请重新输入!");
				out.write(new ObjectMapper().writeValueAsString(respBean));
				out.flush();
				out.close();
			}
		};
	}

	/*@Autowired
	MyWebAuthenticationDetailsSource myWebAuthenticationDetailsSource;*/
/*	@Autowired
	VerifyCodeFilter verifyCodeFilter;*/

	@Bean
	PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

	@Bean
	RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
		hierarchy.setHierarchy("ROLE_admin > ROLE_user");
		return hierarchy;
	}

	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userService, PasswordEncoder passwordEncoder/*, AuthenticationManagerBuilder builder*/) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(authenticationProvider);
	}

	@Bean
	JdbcTokenRepositoryImpl jdbcTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}

	@Bean
	SpringSessionBackedSessionRegistry<S> sessionRegistry() {
		return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginFilter loginFilter, SessionRegistry sessionRegistry) throws Exception {
		http.authorizeHttpRequests(authorize ->
						authorize.requestMatchers("/verify-code", "/login", "/api/**").permitAll()
								.requestMatchers(HttpMethod.POST, "/user/**").permitAll()
								.requestMatchers(HttpMethod.POST, "/role/**").permitAll()
								.requestMatchers("/admin/**").hasRole("admin")
								.anyRequest().authenticated())
				// 用 sessionID 退出
				.logout(logout -> logout.logoutUrl("/logout")
						.logoutSuccessHandler((req, resp, authentication) -> {
							resp.setContentType("application/json;charset=utf-8");
							PrintWriter out = resp.getWriter();
							out.write(new ObjectMapper().writeValueAsString(ResponseResult.error("注销成功")));
							out.flush();
							out.close();
						}).permitAll())
				//.rememberMe(remember -> remember.key("back-key").rememberMeCookieName("rem").tokenRepository(jdbcTokenRepository))
				.csrf(AbstractHttpConfigurer::disable)
//				.csrf(csrf -> csrf.csrfTokenRepository(new CookieCsrfTokenRepository()))// js 无法读取
				.sessionManagement(session -> session.maximumSessions(1).sessionRegistry(sessionRegistry())/*.maxSessionsPreventsLogin(true)*/)
				/*.exceptionHandling(handler -> handler.authenticationEntryPoint((req, resp, authException) -> {
					resp.setContentType("application/json;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.write(new ObjectMapper().writeValueAsString(ResponseResult.error("尚未登录，请先登录")));
					out.flush();
					out.close();
				}))*/
				.addFilterBefore(crypticFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterAt(new ConcurrentSessionFilter(sessionRegistry, event -> {
					HttpServletResponse resp = event.getResponse();
					resp.setContentType("application/json;charset=utf-8");
					resp.setStatus(401);
					PrintWriter out = resp.getWriter();
					out.write(new ObjectMapper().writeValueAsString(ResponseResult.error("您已在另一台设备登录，本次登录已下线!")));
					out.flush();
					out.close();
				}), ConcurrentSessionFilter.class);
		return http.build();
	}

	@Bean
	public SpringSessionRememberMeServices rememberMeServices() {
		SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
		// optionally customize
		rememberMeServices.setAlwaysRemember(true);
		return rememberMeServices;
	}

	@Bean
	HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	LoginFilter loginFilter(AuthenticationManager authenticationManager, SessionRegistry sessionRegistry, SpringSessionRememberMeServices rememberMeServices) throws Exception {
		LoginFilter loginFilter = new LoginFilter();
		loginFilter.setFilterProcessesUrl("/login");
		loginFilter.setAuthenticationSuccessHandler(getSuccessHandler());
		loginFilter.setAuthenticationFailureHandler(getFailureHandler());
		loginFilter.setAuthenticationManager(authenticationManager);
		ConcurrentSessionControlAuthenticationStrategy sessionControlAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
		sessionControlAuthenticationStrategy.setMaximumSessions(1);
		loginFilter.setSessionAuthenticationStrategy(sessionControlAuthenticationStrategy);
//		loginFilter.setAuthenticationDetailsSource(myWebAuthenticationDetailsSource);
		loginFilter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
		loginFilter.setRememberMeServices(rememberMeServices);
		return loginFilter;
	}

	@Bean
	CrypticFilter crypticFilter(){
		return new CrypticFilter();
	}
}
