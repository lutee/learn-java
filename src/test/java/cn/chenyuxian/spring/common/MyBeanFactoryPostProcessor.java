package cn.chenyuxian.spring.common;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.PropertyValue;
import cn.chenyuxian.learnspring.beans.PropertyValues;
import cn.chenyuxian.learnspring.beans.factory.ConfigurableListableBeanFactory;
import cn.chenyuxian.learnspring.beans.factory.config.BeanDefinition;
import cn.chenyuxian.learnspring.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
		PropertyValues propertyValues = beanDefinition.getPropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("company", "改为:字节跳动"));
	}

	
}
