package cn.chenyuxian.learnspring.context.support;

import cn.chenyuxian.learnspring.beans.BeansException;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

	private String[] configLocations;
	
	public ClassPathXmlApplicationContext() {
	}

	public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
		this(new String[] {configLocations});
	}
	
	public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException{
		this.configLocations = configLocations;
		refresh();
	}
	
	@Override
	protected String[] getConfigLocations() {
		return configLocations;
	}

	
}
