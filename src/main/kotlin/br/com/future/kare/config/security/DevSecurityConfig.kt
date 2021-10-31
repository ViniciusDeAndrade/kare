package br.com.future.kare.config.security


import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@Profile("dev")
class DevSecurityConfig : WebSecurityConfigurerAdapter(){

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests().antMatchers("/**").permitAll()
                .and().csrf().disable();
    }

    /**
     *  static resources configuration (js, css ...)
     */
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**")
    }
}