package cn.chenyuxian.learnspring.beans.factory;

import cn.chenyuxian.learnspring.beans.BeansException;

public interface BeanFactoryAware extends Aware{

	void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
