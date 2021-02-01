package com.keepitclean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class LoadDatabase {

	@Autowired
	private ApplicationContext ctx;
	
	public LoadDatabase() {
		
	}
	
	@Bean
	CommandLineRunner initDatabase() {
	  return args -> {
		  
		  /*
		  final HashSet<Key> keys =new HashSet<>();
	      if(repository.count()==0) {
	    	for(int i = 0; i < 10;i++) {
				Key key = new Key();
				keys.add(key);
				//log.info("Preloading Key" + repository.save(key));
				System.out.println("Key " + i + " : " + key.getKey() + "\tKey Type " + key.getType());
			}
	    }*/
	  };
	}
}

