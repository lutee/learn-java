package cn.chenyuxian.learnspring.beans.factory.support;

import java.lang.reflect.Constructor;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.factory.config.BeanDefinition;

public interface InstantiationStrategy {
	
	Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;
}
