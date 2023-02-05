package com.nowcoder.community;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.OpenTest4JAwareThrowableCollector;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.ScrollPaneUI;
import java.util.*;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void applicationContextTest(){
		System.out.println(applicationContext);
	}

	//  /a?current=11&limit=20

	//  /student/123
	@Test
	public void a(){
		List<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(0,2);
		list.add(list.size(),3);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
		}
	}
}
