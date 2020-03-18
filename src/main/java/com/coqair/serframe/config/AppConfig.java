package com.coqair.serframe.config;

import javax.validation.constraints.NotBlank;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
//@ConfigurationProperties
@Validated
public class AppConfig {
	
	@NotBlank
	@Value("${env.name}")
	private String envName;
	
	@NotBlank
	@Value("${env.Common}")
	private String envCommon;

	

	public String getEnvName() {
		return envName;
	}

	public String getEnvCommon() {
		return envCommon;
	}

//	public void setEnvName(String envName) {
//		System.out.println("envName:"+envName);
//		this.envName = envName;
//	}
//
//	public void setEnvCommon(String envCommon) {
//		System.out.println("envCommon:"+envCommon);
//		this.envCommon = envCommon;
//	}
	
	
	
}
