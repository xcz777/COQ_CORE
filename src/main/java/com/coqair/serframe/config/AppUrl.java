package com.coqair.serframe.config;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
//@ConfigurationProperties
@Validated
public class AppUrl {
	
	@Value("${env.url}")
	@URL
	private String envUrl;

	public String getEnvUrl() {
		envUrl = "www";
		return envUrl;
	}

	public void setEnvUrl(String envUrl) {
		System.out.println("envUrl="+envUrl);
		this.envUrl = envUrl;
	}

	
}
