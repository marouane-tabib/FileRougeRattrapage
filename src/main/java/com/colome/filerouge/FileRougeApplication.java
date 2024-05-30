package com.colome.filerouge;

import com.colome.filerouge.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RsaKeyConfigProperties.class})
public class FileRougeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileRougeApplication.class, args);
	}

}
