package eu.neclab.ngsildbroker.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringGatewayGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringGatewayGatewayApplication.class, args);
	}
//	@Value("${gateway.enablecors:false}")
//	boolean enableCors;
//	@Value("${gateway.enablecors.allowall:false}")
//	boolean allowAllCors;
//	
//	@Value("${gateway.enablecors.allowedorigin:null}")
//	String allowedOrigin;
//	
//	@Value("${gateway.enablecors.allowedheader:null}")
//	String allowedHeader;
//	
//	@Value("${gateway.enablecors.allowallmethods:false}")
//	boolean allowAllCorsMethods;
//	
//	@Value("${gateway.enablecors.allowedmethods:null}")
//	String allowedMethods;
//	
//	
//	@Bean
//	public CorsFilter corsFilter() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		if(!enableCors) {
//			return new CorsFilter(source);
//		}
//		final CorsConfiguration config = new CorsConfiguration();
//		if(allowAllCors) {
//			config.setAllowCredentials(true);
//			config.addAllowedOrigin("*");
//			config.addAllowedHeader("*");
//			config.addAllowedMethod(HttpMethod.DELETE);
//			config.addAllowedMethod(HttpMethod.POST);
//			config.addAllowedMethod(HttpMethod.GET);
//			config.addAllowedMethod(HttpMethod.OPTIONS);
//			config.addAllowedMethod(HttpMethod.PATCH);
//			config.addAllowedMethod(HttpMethod.PUT);
//			config.addAllowedMethod(HttpMethod.HEAD);
//			config.addAllowedMethod(HttpMethod.TRACE);
//			source.registerCorsConfiguration("/**", config);
//		}else {
//			if(allowedOrigin != null) {
//				for(String origin: allowedOrigin.split(",")) {
//					config.addAllowedOrigin(origin);
//				}
//				
//			}
//			if(allowedHeader != null) {
//				for(String header: allowedHeader.split(",")) {
//					config.addAllowedHeader(header);
//				}
//			}
//			if(allowAllCorsMethods) {
//				config.addAllowedMethod(HttpMethod.DELETE);
//				config.addAllowedMethod(HttpMethod.POST);
//				config.addAllowedMethod(HttpMethod.GET);
//				config.addAllowedMethod(HttpMethod.OPTIONS);
//				config.addAllowedMethod(HttpMethod.PATCH);
//				config.addAllowedMethod(HttpMethod.PUT);
//				config.addAllowedMethod(HttpMethod.HEAD);
//				config.addAllowedMethod(HttpMethod.TRACE);
//			}else {
//				if(allowedMethods != null) {
//					for(String method: allowedMethods.split(",")) {
//						config.addAllowedMethod(method);
//					}
//				}
//			}
//		}
//		
//		
//		return new CorsFilter(source);
//	}
//	@Bean
//	public ConfigurableServletWebServerFactory webServerFactory() {
//	    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//	    factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
//	        @Override
//	        public void customize(Connector connector) {
//	            connector.setProperty("relaxedQueryChars", "|{}[]");
//	        }
//	    });
//	    return factory;
//	}
}
