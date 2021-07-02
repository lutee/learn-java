package cn.chenyuxian.learnmybatis.dao;

import cn.chenyuxian.learnmybatis.pojo.School;

public interface ISchoolDao {
	
	School querySchoolInfoById(Long treeId);
}
