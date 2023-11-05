package com.viettel.admin;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.viettel.admin.config.DateSqlDeserializer;
import com.viettel.admin.config.DateSqlSerializer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class ViettelAdminApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ViettelAdminApplication.class);
		Environment env = app.run(args).getEnvironment();
		logApplicationStartup(env);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


	@Bean
	public JavaTimeModule javaTimeModule() {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(Date.class, new DateSqlDeserializer());
		javaTimeModule.addSerializer(Date.class, new DateSqlSerializer());
		return javaTimeModule;
	}
	private static void logApplicationStartup(Environment env) {
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if (StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("The host name could not be determined, using localhost as fallback");
		}
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\t{}://localhost:{}{}\n\t" +
						"server: \t{}://{}:{}{}\n\t" +
						"swagger: \t {}://localhost:{}{}swagger-ui.html\n\t"	+
						"Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol,
				serverPort,
				contextPath,
				protocol,
				hostAddress,
				serverPort,
				contextPath,
				protocol,
				serverPort,
				contextPath,
				env.getActiveProfiles());
	}

	@Bean
	public OpenAPI openAPI(){
		return new OpenAPI().addServersItem(new Server().url("/"));
	}
}
