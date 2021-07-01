package cn.chenyuxian.learnspring.beans.factory;


import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.factory.config.AutowireCapableBeanFactory;
import cn.chenyuxian.learnspring.beans.factory.config.BeanDefinition;
import cn.chenyuxian.learnspring.beans.factory.config.BeanPostProcessor;
import cn.chenyuxian.learnspring.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory{
	
	BeanDefinition getBeanDefinition(String beanName) throws BeansException;
	
	void preInstantiateSingletons() throws BeansException;
	
	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
