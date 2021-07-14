package cn.chenyuxian.spring.context.event;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import cn.chenyuxian.spring.beans.BeansException;
import cn.chenyuxian.spring.beans.factory.BeanFactory;
import cn.chenyuxian.spring.beans.factory.BeanFactoryAware;
import cn.chenyuxian.spring.util.ClassUtils;
import cn.hutool.core.util.ClassUtil;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware{

	public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();
	
	private BeanFactory beanFactory;
	
	@Override
	public void addApplicationListener(ApplicationListener<?> listener) {
		// TODO Auto-generated method stub
		applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
	}
	
	@Override
	public void removeApplicationListener(ApplicationListener<?> listener) {
		// TODO Auto-generated method stub
		applicationListeners.remove(listener);
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		this.beanFactory = beanFactory;
	}
	
	protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
		LinkedList<ApplicationListener> allListeners = new LinkedList<>();
		for(ApplicationListener<ApplicationEvent> listener : applicationListeners) {
			if(support)
		}
	}
	
	protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
		Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
		
		Class<?> targetClass = ClassUtils.is
	}
}
