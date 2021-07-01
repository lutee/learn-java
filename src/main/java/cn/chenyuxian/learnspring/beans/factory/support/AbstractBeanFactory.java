package cn.chenyuxian.learnspring.beans.factory.support;

import java.util.ArrayList;
import java.util.List;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.factory.config.BeanDefinition;
import cn.chenyuxian.learnspring.beans.factory.config.BeanPostProcessor;
import cn.chenyuxian.learnspring.beans.factory.config.ConfigurableBeanFactory;
import cn.chenyuxian.learnspring.util.ClassUtils;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

	private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();
	
	private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
	
	@Override
	public Object getBean(String name) throws BeansException {
		return doGetBean(name, null);
	}

	@Override
	public Object getBean(String name, Object... args) throws BeansException {
		return doGetBean(name, args);
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return (T) getBean(name);
	}
	
	protected <T> T doGetBean(final String name, final Object[] args) {
		Object bean = getSingleton(name);
		if(bean != null) {
			return (T) bean;
		}
		BeanDefinition beanDefinition = getBeanDefinition(name);
		return (T) createBean(name, beanDefinition, args);
	}
	
	protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

	protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

	@Override
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
		this.beanPostProcessors.remove(beanPostProcessor);
		this.beanPostProcessors.add(beanPostProcessor);
	}
	
	public List<BeanPostProcessor> getBeanPostProcessors() {
		return beanPostProcessors;
	}
	
	public ClassLoader getBeanClassLoader() {
		return this.beanClassLoader;
	}
	
}
