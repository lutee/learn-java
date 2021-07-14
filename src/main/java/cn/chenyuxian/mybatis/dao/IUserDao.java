package cn.chenyuxian.mybatis.dao;

import cn.chenyuxian.mybatis.pojo.User;

public interface IUserDao {
	
	User queryUserInfoById(Long id);
}
