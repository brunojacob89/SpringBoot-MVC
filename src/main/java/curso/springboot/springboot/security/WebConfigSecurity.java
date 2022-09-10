package curso.springboot.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	@Bean // configura as solicitações de acesso HTTP
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf()
		.disable() // Desativa as configurações padrão de memória
		.authorizeRequests((authorize) -> authorize // Permitir restringuir acessos
				.antMatchers("/","/materialize/**").permitAll() //Qualquer usuario acessa a pagina interna
				.antMatchers("/cadastropessoa").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				)
		.formLogin(form -> form
				.loginPage("/login")
				.permitAll()	//permite qualquer usuário
			)
		.logout(logout -> logout //Mapeia URL de Logout e invalida usuário autenticado
				.logoutUrl("/logout").logoutSuccessUrl("/")
					);
		
		return http.build();
	}
	

	@Autowired // Cria autenticação do usuario com banco de dados ou em memória
	 public void configure(AuthenticationManagerBuilder auth) throws Exception{
		 //Implementação no Banco de Dados
		auth.userDetailsService(implementacaoUserDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
		// Implementação em memoria
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//			.withUser("bruno")
//			.password("$2a$10$eh/al3hduuhkD9f43PGApeiUjESWXnv.fig6OFiREM2uKKlNo5V7e")
//			.roles("ADMIN");
		
	 }
	
	
}
