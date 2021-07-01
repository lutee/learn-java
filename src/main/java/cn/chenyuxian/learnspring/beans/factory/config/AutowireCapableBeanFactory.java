package cn.chenyuxian.learnspring.beans.factory.config;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory{
	
	/**
	 * 执行BeanPostProcessors接口实现类的postProcessBeforeInitialization方法
	 * @param existingBean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;
	
	
	/**
	 * 执行BeanPostProcessors接口实现类的postProcessorsAfterInitialization方法
	 * @param existingBean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
