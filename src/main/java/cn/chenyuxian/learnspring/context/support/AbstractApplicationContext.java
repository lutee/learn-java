package cn.chenyuxian.learnspring.context.support;

import java.util.Map;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.factory.ConfigurableListableBeanFactory;
import cn.chenyuxian.learnspring.beans.factory.config.BeanFactoryPostProcessor;
import cn.chenyuxian.learnspring.beans.factory.config.BeanPostProcessor;
import cn.chenyuxian.learnspring.context.ConfigurableApplicationContext;
import cn.chenyuxian.learnspring.core.io.DefaultResourceLoader;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext{
	
	@Override
	public void refresh() throws BeansException {
		
		// 创建BeanFactory，并加载BeanDefinition
		refreshBeanFactory();
		
		// 获取BeanFactory
		ConfigurableListableBeanFactory beanFactory = getBeanFactory();
		
		// 添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware的Bean对象都能感知所属的ApplicationContext
		beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
		
		// 在Bean实例化之前，执行BeanFactoryPostProcessor
		invokeBeanFactoryPostProcessors(beanFactory);
		
		// BeanPostProcessor 需要提前于其他Bean对象实例化之前执行注册操作
		registerBeanPostProcessors(beanFactory);
		
		// 提前实例化单例Bean对象
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
