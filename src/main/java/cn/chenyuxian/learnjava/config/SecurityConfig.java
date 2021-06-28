package cn.chenyuxian.learnjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
		http.csrf().disable();
		
		//开启跨域访问
		http.cors();
		
		//不使用默认退出，自定义退出
		http.logout().disable();
		
		//其余的都需要授权访问
		http.authorizeRequests().anyRequest().authenticated();
		
		
		
		//全局不创建session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//禁用页面缓存，返回的都是json
		http.headers()
		.frameOptions().disable()
		.cacheControl();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**");
	}
}
