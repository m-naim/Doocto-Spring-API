package org.naim.doctoo.config;

import java.util.Arrays;
import java.util.List;

import org.naim.doctoo.security.CustomUserDetailsService;
import org.naim.doctoo.security.RestAuthenticationEntryPoint;
import org.naim.doctoo.security.TokenAuthenticationFilter;
import org.naim.doctoo.security.oauth2.CustomOAuth2UserService;
import org.naim.doctoo.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import org.naim.doctoo.security.oauth2.OAuth2AuthenticationFailureHandler;
import org.naim.doctoo.security.oauth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)

public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

   

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    
    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }
	
    /*
    By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
    the authorization request. But, since our service is stateless, we can't save it in
    the session. We'll save the request in a Base64 encoded cookie instead.
     */
    
  @Bean
  public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
      return new HttpCookieOAuth2AuthorizationRequestRepository();
  }
  
  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
      authenticationManagerBuilder
              .userDetailsService(customUserDetailsService)
              .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }


  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
              .cors()
                  .and()
              .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  .and()
              .csrf()
                  .disable()
              .formLogin()
                  .disable()
              .httpBasic()
                  .disable()
              .exceptionHandling()
                  .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                  .and()
              .authorizeRequests()
                  .antMatchers("/","/error")
                      .permitAll()
                  .antMatchers("/auth/**", "/oauth2/**")
                      .permitAll()
                  .antMatchers(HttpMethod.GET,"/docteurs/**","/professions/**","/communes/**","/doc/**")
                  	  .permitAll()
                  .antMatchers("/docteurs/**","/professions/**","/users/**","/communes/**","/doc/**")
                	  .hasRole("ADMIN")
                  .anyRequest()
                      .authenticated()
                  .and()
              .oauth2Login()
                  .authorizationEndpoint()
                      .baseUri("/oauth2/authorize")
                      .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                      .and()
                  .redirectionEndpoint()
                      .baseUri("/oauth2/callback/*")
                      .and()
                  .userInfoEndpoint()
                      .userService(customOAuth2UserService)
                      .and()
                  .successHandler(oAuth2AuthenticationSuccessHandler)
                  .failureHandler(oAuth2AuthenticationFailureHandler);

      // Add our custom Token based authentication filter
      http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      final CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("*"));
      configuration.setAllowedMethods(Arrays.asList("HEAD",
              "GET", "POST", "PUT", "DELETE", "PATCH"));
      // setAllowCredentials(true) is important, otherwise:
      // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
      configuration.setAllowCredentials(true);
      // setAllowedHeaders is important! Without it, OPTIONS preflight request
      // will fail with 403 Invalid CORS request
      configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
  }
}
