package cn.chenyuxian.learnspring.beans.factory;

public interface BeanClassLoaderAware extends Aware{
	
	void setBeanClassLoader(ClassLoader classLoader);
}
