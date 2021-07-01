package cn.chenyuxian.learnspring.beans.factory.support;

import java.lang.reflect.Constructor;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.factory.config.BeanDefinition;

public class SimpleInstantiationStrategy implements InstantiationStrategy{

	@Override
	public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args)
			throws BeansException {
		Class clazz = beanDefinition.getClass();
		try {
			if(constructor != null) {
				return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
			}else {
				return clazz.newInstance();
			}
		} catch (Exception e) {
			throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
		}
	}

}
