package com.keepitclean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class KeepItClean extends SpringBootServletInitializer{

	public static void main(String[] args) {
		
		SpringApplication.run(KeepItClean.class, args);
		
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
        
        	String[] beans = ctx.getBeanDefinitionNames();

            for(int i = 0; i < beans.length;i++)
            	if(beans[i].startsWith("Re") || 
            	beans[i].startsWith("Adm") || 
            	beans[i].startsWith("Jo") || 
            	beans[i].startsWith("Cli"))
            		System.out.println(beans[i]);
            
        };
	}	
}
