package cn.didano.weichat.message.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.didano.weichat.message.Greeting;
import cn.didano.weichat.message.HelloMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "测试消息订阅", tags = "测试消息订阅")
@Controller
public class GreetingController {


    /*@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }*/

	@ApiOperation(value = "问候", notes = "问候")
	@PostMapping(value = "greeting")
	@ResponseBody
    @MessageMapping("/erlang")
    @SendTo("/topic/mygod")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
