package cn.didano.weichat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class PortfolioController {
	@Autowired
	private SimpMessagingTemplate messageTemplate;

	@MessageMapping("/trade")
	@SendToUser("/queue/position-updates")
	public void executeTrade(String test) {
		messageTemplate.convertAndSendToUser("001", "/queue/reply", "hello");
	}

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + message.getName() + "!");
	}

}
