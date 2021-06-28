package cn.chenyuxian.spring.beans.factory.support;

import java.lang.reflect.Constructor;

import cn.chenyuxian.spring.beans.BeansException;
import cn.chenyuxian.spring.beans.PropertyValue;
import cn.chenyuxian.spring.beans.PropertyValues;
import cn.chenyuxian.spring.beans.factory.config.BeanDefinition;
import cn.chenyuxian.spring.beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanUtil;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

	private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
		Object bean = null;
		try {
			bean = createBeanInstance(beanDefinition, beanName, args);
			applyPropertyValues(beanName, bean, beanDefinition);
		} catch (Exception e) {
			throw new BeansException("Instantiation of bean failed", e);
		}
		addSingleton(beanName, bean);
		return bean;
	}

	protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
		Constructor constructorToUse = null;
		Class<?> beanClass = beanDefinition.getBeanClass();
		Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
		for (Constructor constructor : declaredConstructors) {
			if (null != args && constructor.getParameterTypes().length == args.length) {
				constructorToUse = constructor;
				break;
			}
		}
		return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
	}

	protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
		try {
			PropertyValues propertyValues = beanDefinition.getPropertyValues();
			for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
				String name = propertyValue.getName();
				Object value = propertyValue.getValue();
				if (value instanceof BeanReference) {
					BeanReference beanReference = (BeanReference) value;
					value = getBean(beanReference.getBeanName());
				}
				BeanUtil.setFieldValue(bean, name, value);
			}
		} catch (Exception e) {
			throw new BeansException("Error settting property values: " + beanName);
		}
	}

	public InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}

	public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
		this.instantiationStrategy = instantiationStrategy;
	}

}
