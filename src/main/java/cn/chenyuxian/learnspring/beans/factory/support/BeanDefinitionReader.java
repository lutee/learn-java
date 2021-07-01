package cn.chenyuxian.learnspring.beans.factory.support;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.core.io.Resource;
import cn.chenyuxian.learnspring.core.io.ResourceLoader;

public interface BeanDefinitionReader {
	
	BeanDefinitionRegistry getRegistry();
	
	ResourceLoader getResourceLoader();
	
	void loadBeanDefinitions(Resource resource) throws BeansException;
	
	void loadBeanDefinitions(Resource...resources) throws BeansException;
	
	void loadBeanDefinitions(String location) throws BeansException;
	
	void loadBeanDefinitions(String...locations) throws BeansException;
}
