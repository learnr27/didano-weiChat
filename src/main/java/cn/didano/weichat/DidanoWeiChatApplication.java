package cn.didano.weichat;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication

@ComponentScan({"cn.didano.weichat"})

public class DidanoWeiChatApplication {
	static Logger logger = Logger.getLogger(DidanoWeiChatApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DidanoWeiChatApplication.class, args);
	}
}
