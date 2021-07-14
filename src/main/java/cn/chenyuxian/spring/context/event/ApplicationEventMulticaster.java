package cn.chenyuxian.spring.context.event;

import org.springframework.context.ApplicationListener;

import cn.chenyuxian.spring.context.ApplicationEvent;

public interface ApplicationEventMulticaster {
	
	void addApplicationListener(ApplicationListener<?> listener);
	
	void removeApplicationListener(ApplicationListener<?> listener);
	
	void multicastEvent(ApplicationEvent event);
}
