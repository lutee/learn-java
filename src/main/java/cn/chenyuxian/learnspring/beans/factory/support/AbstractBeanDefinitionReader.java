package cn.chenyuxian.learnspring.beans.factory.support;

import cn.chenyuxian.learnspring.core.io.DefaultResourceLoader;
import cn.chenyuxian.learnspring.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

	private final BeanDefinitionRegistry registry;
	
	private ResourceLoader resourceLoader;
	
	protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this(registry, new DefaultResourceLoader());
	}
	
	public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
		this.registry = registry;
		this.resourceLoader = resourceLoader;
	}
	
	@Override
	public BeanDefinitionRegistry getRegistry() {
		return registry;
	}
	
	@Override
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
	
}
