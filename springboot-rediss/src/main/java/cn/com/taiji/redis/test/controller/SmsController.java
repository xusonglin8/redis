package cn.com.taiji.redis.test.controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

	@Autowired
	private StringRedisTemplate template;
	String phoneNum="13107664667";
	int i=1;

	@RequestMapping("/sendMsg")
	public String sendMsg() {
		String startTime = String.valueOf(System.currentTimeMillis());
		template.opsForValue().set("startTime"+i, startTime);	
		if(!template.hasKey(phoneNum)&&(Long.parseLong(template.opsForValue().get("startTime"+i))-Long.parseLong(template.opsForValue().get("startTime"+String.valueOf(i-2))))<=60*30) {
			String code = UUID.randomUUID().toString();
			template.opsForValue().set(phoneNum, code, 60, TimeUnit.SECONDS); 
			System.out.println(startTime);
			System.out.println(template.opsForValue().get("startTime"+i));
			i++;
			return "发送成功，已发送" + template.opsForValue().get(phoneNum);
		}
		else if (!template.hasKey(phoneNum)) {
		     	  return "发送频繁"; 
		}
		else {
            return "发送成功，已发送" + template.opsForValue().get(phoneNum);
		}
	}

}
