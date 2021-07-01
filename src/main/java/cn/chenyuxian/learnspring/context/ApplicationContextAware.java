package cn.chenyuxian.learnspring.context;

import cn.chenyuxian.learnspring.beans.BeansException;

public interface ApplicationContextAware {
	
	void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
