package cn.chenyuxian.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

import lombok.Data;

@Data
public class AdvisedSupport {

	private TargetSource targetSource;
	
	private MethodInterceptor methodInterceptor;
	
	private MethodMatcher methodMatcher;
}
