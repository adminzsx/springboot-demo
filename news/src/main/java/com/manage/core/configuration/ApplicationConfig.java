package com.manage.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

	
//	@Value("${tripledes.secretkey}")
    private String tripleDesKey;

    @Value("${aes.secretkey}")
    private String aesKey;
     
	public String getTripleDesKey() {
		return tripleDesKey;
	}

	public void setTripleDesKey(String tripleDesKey) {
		this.tripleDesKey = tripleDesKey;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}
 
 
}
