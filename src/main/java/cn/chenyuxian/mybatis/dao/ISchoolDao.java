package cn.chenyuxian.mybatis.dao;

import cn.chenyuxian.mybatis.pojo.School;

public interface ISchoolDao {
	
	School querySchoolInfoById(Long treeId);
}
