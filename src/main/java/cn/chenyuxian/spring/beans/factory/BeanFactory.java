package cn.chenyuxian.spring.beans.factory;

import cn.chenyuxian.spring.beans.BeansException;

public interface BeanFactory {
	
	Object getBean(String name) throws BeansException;
	
	Object getBean(String name, Object...args) throws BeansException;
}