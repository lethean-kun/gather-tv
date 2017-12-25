package com.zz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author dzk
 */
@EnableScheduling
@SpringBootApplication
public class GatherTvApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatherTvApplication.class, args);
	}
}
