package net.ezens.pointnix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import lombok.AllArgsConstructor;
import net.ezens.pointnix.member.MemberService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final MemberService memberService;
	
	@Autowired
	private CustomEntryPointHandler entryPointHandler; 
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// static 디렉토리의 하위 파일 목록은 인증 무시
		web.ignoring().antMatchers("/resources/**", "/css/**", "/js/**", "/images/**", "/fonts/**", "/lib/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			// 페이지 별 권한 설정
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/member/myinfo").hasRole("MEMBER")
				.antMatchers("/**").permitAll()
				.and()
			// 로그인 설정
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/goLogin")
				.defaultSuccessUrl("/home")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
				.and()
			// 로그아웃 설정
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.and()
			// 403 예외처리 핸들링
			.exceptionHandling()
				.accessDeniedPage("/denied")
				.authenticationEntryPoint(entryPointHandler);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Spring security로 패스워드 암호화
		//auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
		
		// 패스워드 암호화 안함
		auth.userDetailsService(memberService);
	}
}
