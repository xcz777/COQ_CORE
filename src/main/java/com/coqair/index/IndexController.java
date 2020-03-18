package com.coqair.index;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coqair.serframe.config.AppUrl;

@RestController
public class IndexController {

//	@Value("${env.name}")
//	private String envName;
//	
//	@Value(value="${env.url}")
//	private String envUrl;	

	@Value(value="${env.Common}")
	private String envCommon;
	
	@Autowired
	private AppUrl appUrl;
	

	
	@RequestMapping(value="/test.do", method=RequestMethod.GET)
	//@GetMapping(value="/test")
	public String test() {		
		

		
		//return envName + envUrl + envCommon;
		return envCommon+"--"+appUrl.getEnvUrl();
	}
	
	
	
}
