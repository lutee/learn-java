package cn.chenyuxian.learnmybatis.dao;

import cn.chenyuxian.learnmybatis.pojo.User;

public interface IUserDao {
	
	User queryUserInfoById(Long id);
}
