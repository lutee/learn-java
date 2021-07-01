package cn.chenyuxian.learnspring.beans.factory;

public interface InitializingBean {
	
	/**
	 * Bean处理了属性填充后调用
	 * @throws Exception
	 */
	void afterPropertiesSet() throws Exception;
}
