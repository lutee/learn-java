package cn.chenyuxian.learnspring.context.support;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.factory.config.BeanPostProcessor;
import cn.chenyuxian.learnspring.context.ApplicationContext;
import cn.chenyuxian.learnspring.context.ApplicationContextAware;

public class ApplicationContextAwareProcessor implements BeanPostProcessor{

	private final ApplicationContext applicationContext;
	
	public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof ApplicationContextAware) {
			((ApplicationContextAware) bean).setApplicationContext(applicationContext);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
