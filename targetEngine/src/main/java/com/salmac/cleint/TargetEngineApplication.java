package com.salmac.cleint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.salmac.cleint.engine.files.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class TargetEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TargetEngineApplication.class, args);
	}
}
