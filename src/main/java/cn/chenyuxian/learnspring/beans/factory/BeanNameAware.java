package cn.chenyuxian.learnspring.beans.factory;

public interface BeanNameAware extends Aware{
	
	void setBeanName(String name);
}
