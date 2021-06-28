package cn.chenyuxian.spring.bean;

import cn.chenyuxian.spring.beans.factory.DisposableBean;
import cn.chenyuxian.spring.beans.factory.InitializingBean;

public class UserService implements InitializingBean, DisposableBean{
	
	private String uid;
	private String company;
	private String location;
	private UserDao userDao;
	
	public String queryUserInfo() {
		 return userDao.queryUserName(uid) + "," + company + "," + location;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("执行: UserService.destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("执行: UserService.afterPropertiesSet");
	}
	
	
}
