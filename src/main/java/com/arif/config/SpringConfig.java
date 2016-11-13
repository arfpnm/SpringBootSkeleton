package com.arif.config;
/**
 * @author arif.mohammed
 */
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.arif"})
//@PropertySource(value={"classpath:resource.properties"})
public class SpringConfig extends WebMvcConfigurerAdapter{

	@Value("${user-name}")
	String userName;
	@Value("${password}")
	String password;
	

	 @Bean
	    public EmbeddedServletContainerCustomizer containerCustomizer() {
	        return (container -> {
	            container.setPort(8081);
	            container.setContextPath("/api");
	        });
	    }
	


	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	

	
	/**
	@Bean
	public HttpHeaders httpHeaders(){
		return new HttpHeaders(){
			private static final long serialVersionUID = 1L;

			{
				String auth = userName + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64( 
						auth.getBytes(Charset.forName("US-ASCII")) );
				String authHeader = "Basic " + new String( encodedAuth );
				set( "Authorization", authHeader );
				setContentType(MediaType.APPLICATION_JSON);
				set("X-NXDocumentProperties", "*");
				set("X-NXenrichers.document", "breadcrumb");

			}
		};
	}
	**/


	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
		//propertySourcesPlaceholderConfigurer.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:META-INF/props/default/*.properties"));
		propertySourcesPlaceholderConfigurer.setLocations(new PathMatchingResourcePatternResolver().getResources("file:/Users/arif/resources/resource.properties"));
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		return factory;
	}

	 @Override
	    public void configureDefaultServletHandling(
	            DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }    


}
