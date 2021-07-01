package cn.chenyuxian.learnspring.beans.factory;

public interface DisposableBean {
	
	void destroy() throws Exception;
}
