package com.manage.core.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.manage.core.filter.MyAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled=true)
@EnableCaching
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private SuccessHandler successHandler;

    @Bean
    UserDetailsService customUserService()
    {
        return new CustomUserService();
    }
    
    @Bean  
    public AuthenticationManager authenticationManagerBean() throws Exception {  
        return super.authenticationManagerBean();  
    } 
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public boolean matches(CharSequence arg0, String arg1) {
                return arg1.equals(encode(arg0));
            }

            @Override
            public String encode(CharSequence arg0) {
                return (String) arg0;
            }
        });
    }
    

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
    	
        // http.csrf().disable();
        // 所有用户均可访问的资源
          http.authorizeRequests().antMatchers("/ads/**","/frontdevice/**", "/css/**", "/js/**","/plugins/**","/pics/**","/video/**", "/images/**","/**.ico","/captcha/**", "/loginin", "/api/**","/gifts/**","/other/**").permitAll()
            .anyRequest().authenticated() // .permitAll()
            .and().formLogin().loginPage("/login").loginProcessingUrl("/loginin").defaultSuccessUrl("/")
            .successHandler(successHandler)
                .failureUrl("/login/failure").permitAll()
                .and().logout().invalidateHttpSession(true).logoutSuccessUrl("/logout").logoutRequestMatcher(new RegexRequestMatcher("/logout", "GET"))
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll().and().csrf().disable();
          
          ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry  = http.authorizeRequests();
          registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
 
          
          http.addFilterBefore(MyAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
          http.headers().frameOptions().sameOrigin();
    }
    
    @Bean
    public UsernamePasswordAuthenticationFilter MyAuthenticationFilterBean() throws Exception {
        UsernamePasswordAuthenticationFilter myAuthenticationFilter = new MyAuthenticationFilter();
        myAuthenticationFilter.setAuthenticationManager(authenticationManager());
        myAuthenticationFilter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler());
        myAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
         
        return myAuthenticationFilter;
    }

    /**
     * 验证异常处理器
     *
     * @return
     */
    public SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login/failure");
    }
    
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new  UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
    
}