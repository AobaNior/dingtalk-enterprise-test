package com.dfire.dingtalk.enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:applicationContext.xml")
public class DingtalkEnterpriseTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DingtalkEnterpriseTestApplication.class, args);
		synchronized (DingtalkEnterpriseTestApplication.class) {
			while (true) {
				try {
					DingtalkEnterpriseTestApplication.class.wait();
				} catch (Throwable e) {
				}
			}
		}
	}

}
