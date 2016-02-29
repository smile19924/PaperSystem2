package com.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.tabletool.MybatisDaoUtil;

@Service
public class BaseService<T> {

	@Autowired
	protected MybatisDaoUtil<T> dao;

	protected Class<T> entityClass;
	
	public BaseService() {
	}

	public BaseService(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 插入对象
	 * @param entity
	 * @return
	 */
	public int insert (T entity) {
		return dao.addEntity("paper."+entityClass.getSimpleName()+"Mapper.insert", entity);
	}
	
	/**
	 * 根据主键查询对象
	 * @param pk
	 * @return
	 */
	public T selectByPrimaryKey (String pk) {
		return dao.getEntity("paper."+entityClass.getSimpleName()+"Mapper.selectByPrimaryKey", pk);
	}
	
	/**
	 * 根据条件查询对象列表
	 * @param param
	 * @return
	 */
	public List<T> selectByCondition (Map<String, Object> param) {
		return dao.getEntitys("paper."+entityClass.getSimpleName()+"Mapper.selectByCondition", param);
	}
	
	/**
	 * 根据主键删除对象
	 * @param pk
	 * @return
	 */
	public int deleteByPrimaryKey (String pk) {
		return dao.deleteEntityById("paper."+entityClass.getSimpleName()+"Mapper.deleteByPrimaryKey", pk);
	}
	
	/**
	 * 根据主键修改需要修改的对象数据
	 * @param entity
	 * @return
	 */
	public int updateByPrimaryKeySelective (T entity) {
		return dao.updateEntity("paper."+entityClass.getSimpleName()+"Mapper.updateByPrimaryKeySelective", entity);
	}
	
}
