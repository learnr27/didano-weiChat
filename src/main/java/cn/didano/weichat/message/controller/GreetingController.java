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

	@ApiOperation(value = "微信消息列表", notes = "微信消息列表")
	@PostMapping(value = "greeting")
	@ResponseBody
    @MessageMapping("/notice")
    @SendTo("/topic/notice")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
	
	@ApiOperation(value = "电子班牌老师风采", notes = "电子班牌老师风采")
	@PostMapping(value = "teacherInfo")
	@ResponseBody
    @MessageMapping("/teacherInfo")
    @SendTo("/topic/teacherInfo")
    public Greeting teacherInfo(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
	
	@ApiOperation(value = "电子班牌老师推送", notes = "电子班牌老师推送")
	@PostMapping(value = "teacherAdvice")
	@ResponseBody
    @MessageMapping("/teacherAdvice")
    @SendTo("/topic/teacherAdvice")
    public Greeting teacherAdvice(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
	
	@ApiOperation(value = "电子班牌晨检信息", notes = "电子班牌晨检信息")
	@PostMapping(value = "detectionInfo")
	@ResponseBody
    @MessageMapping("/detectionInfo")
    @SendTo("/topic/detectionInfo")
    public Greeting detectionInfo(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
