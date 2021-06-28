package cn.chenyuxian.spring.context.support;

import java.util.Map;

import cn.chenyuxian.spring.beans.BeansException;
import cn.chenyuxian.spring.beans.factory.ConfigurableListableBeanFactory;
import cn.chenyuxian.spring.beans.factory.config.BeanFactoryPostProcessor;
import cn.chenyuxian.spring.beans.factory.config.BeanPostProcessor;
import cn.chenyuxian.spring.beans.factory.config.ConfigurableBeanFactory;
import cn.chenyuxian.spring.context.ConfigurableApplicationContext;
import cn.chenyuxian.spring.core.io.DefaultResourceLoader;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext{
	
	@Override
	public void refresh() throws BeansException {
		refreshBeanFactory();
		ConfigurableListableBeanFactory beanFactory = getBeanFactory();
		invokeBeanFactoryPostProcessors(beanFactory);
		registerBeanPostProcessors(beanFactory);
		beanFactory.preInstantiateSingletons();
	}
	
	protected abstract void refreshBeanFactory() throws BeansException;
	
	protected abstract ConfigurableListableBeanFactory getBeanFactory();
	
	private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
		Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
		for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
			beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
		}
	}
	
	private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
		Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
		for(BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
			beanFactory.addBeanPostProcessor(beanPostProcessor);
		}
	}
	
	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
		return getBeanFactory().getBeansOfType(type);
	}
	
	@Override
	public String[] getBeanDefinitionNames() {
		return getBeanFactory().getBeanDefinitionNames();
	}

	@Override
	public Object getBean(String name) throws BeansException {
		return getBeanFactory().getBean(name);
	}
	
	@Override
	public Object getBean(String name, Object... args) throws BeansException {
		return getBeanFactory().getBean(name, args);
	}
	
	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return getBeanFactory().getBean(name, requiredType);
	}
	
	@Override
	public void registerShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(this::close));
	}
	
	@Override
	public void close() {
		getBeanFactory().destroySingletons();
	}
}
