package com.project.common.tabletool;

import java.io.Serializable;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.project.util.SpringBeanInvoker;
import com.project.util.Toolkits;


/**
 * dao层封装
 * 
 * @param <T>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Slf4j
@Repository("dao")
public class MybatisDaoUtil<T> {

	private SqlSession sqlSession;

	protected Class<T> entityClass;
	
	public MybatisDaoUtil() {
		super();
	}

	public MybatisDaoUtil(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public SqlSession getSqlSession() {
		if (sqlSession == null) {
			sqlSession = (SqlSession) SpringBeanInvoker.getBean("sqlSession");
		}
		/**************** 打印数据库信息start ******************/
		try {
			Connection con = sqlSession.getConnection();
			log.info("database connnection isClosed : " + con.isClosed());
			if (con.isClosed() == false) {
				DatabaseMetaData dbMetaData = con.getMetaData();
				String currentDbUrl = dbMetaData.getURL();
				String driverName = dbMetaData.getDriverName();
				String userName = dbMetaData.getUserName();
				StringBuffer result = new StringBuffer();
				result.append(currentDbUrl);
				result.append("," + driverName);
				result.append("," + userName);
				log.info("【currentConnectionInfo】" + result.toString());
			}
		} catch (Exception e) {
			log.error(Toolkits.getExceptionInfo(e));
		}
		/**************** 打印数据库信息 end ******************/
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * @param statement
	 *            (Unique identifier matching the statement to execute)
	 * @param parameter
	 *            (A parameter object to pass to the statement)
	 * @return (int The number of rows affected by the insert.)
	 */
	public int addEntity(String statement, Object parameter) {
		parameter = getParameterObject(parameter);
		return this.getSqlSession().insert(statement, parameter);
	}

	/**
	 * @param statement
	 *            (Unique identifier matching the statement to execute)
	 * @param parameter
	 *            (A parameter object to pass to the statement)
	 * @return (int The number of rows affected by the insert.)
	 */
	public int updateEntity(String statement, Object parameter) {
		// log.info(statement+"------" + parameter);
		parameter = getParameterObject(parameter);
		return this.getSqlSession().update(statement, parameter);
	}

	/**
	 * @param statement
	 *            (Unique identifier matching the statement to execute)
	 * @param entity
	 *            (A parameter object to pass to the statement)
	 * @return (int The number of rows affected by the insert.)
	 */
	public int deleteEntity(String statement, Object parameter) {
		// log.info(statement+"------" + parameter);
		parameter = getParameterObject(parameter);
		return this.getSqlSession().delete(statement, parameter);
	}

	/**
	 * @param statement
	 *            (Unique identifier matching the statement to execute)
	 * @param parameter
	 *            (A parameter object to pass to the statement)
	 * @return (int The number of rows affected by the insert.)
	 */
	public int deleteEntityById(String statement, Object parameter) {
		// log.info(statement+"------" + parameter);
		parameter = getParameterObject(parameter);
		return this.getSqlSession().delete(statement, (Serializable) parameter);
	}

	/**
	 * @param statement
	 *            (statement Unique identifier matching the statement to use.)
	 * @param parameter
	 *            A parameter object to pass to the statement.
	 * @return (Mapped object) <T> the returned object type
	 */
	public T getEntity(String statement, Object parameter) {
		// log.info(statement+"------" + parameter);
		parameter = getParameterObject(parameter);
		return (T) this.getSqlSession().selectOne(statement, parameter);
	}

	/**
	 * @param statement
	 *            (statement Unique identifier matching the statement to use.)
	 * @param entity
	 *            parameter A parameter object to pass to the statement.
	 * @return (Mapped object) <T> the returned object type
	 */
	public T getEntityById(String statement, Object parameter) {
		// log.info(statement+"------" + parameter);
		parameter = getParameterObject(parameter);
		return (T) this.getSqlSession().selectOne(statement, (Serializable) parameter);
	}

	/**
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @param parameter
	 *            A parameter object to pass to the statement
	 * @return List of mapped object
	 */
	public List<T> getEntitys(String statement, Object parameter) {
		// log.info(statement+"------" + parameter);
		parameter = getParameterObject(parameter);
		return this.getSqlSession().selectList(statement, parameter);
	}

	/**
	 * 
	 * @param statement
	 *            statement Unique identifier matching the statement to use.
	 * @param parameter
	 *            parameter只接受Map类型参数 并且里边必须包含 page, pagesize两个键值对（值必须是整数）
	 * @return Page
	 */
	public Page<T> getEntitysPage(String statement, Object parameter) {
		// log.info(statement + "------" + parameter);
		parameter = getParameterObject(parameter);
		Page<T> page = new Page<T>();

		if (parameter instanceof Map<?, ?>) {
			Map parMap = (Map) parameter;
			try {
				if (!isEmpty(isNull(parMap.get("page")))) {
					page.setPageNo(Integer.parseInt(isNull(parMap.get("page")))); // 当前页
				}
				// 当前页大小
				if (!isEmpty(isNull(parMap.get("pagesize")))) {
					page.setPageSize(Integer.parseInt(isNull(parMap.get("pagesize"))));
				}
				parMap.put("pageObject", page);

				List<T> list = this.getSqlSession().selectList(statement, parMap);
				page.setResults(list);
			} catch (Exception e) {
				log.error(Toolkits.getExceptionInfo(e));
			}
			if (parMap.containsKey("pageObject")) { // 分页执行完去掉分页参数
				parMap.remove("pageObject");
			}
		}
		return page;
	}

	/* ***************************************
	 * *****************批量操作****************
	 * ***************************************
	 */
	/**
	 * sql入参编码转换，日志输入
	 * 
	 * @param object
	 * @return
	 */
	private static Object getParameterObject(Object object) {
		log.info("getParameterObject----->" + object);
		Object obj = CharacterSetUtil.getISOObject(object);
		log.info("getParameterObject----->" + obj);
		return obj;
	}

	/**
	 * 返回空字符串
	 * 
	 * @param str
	 * @return
	 * 
	 */
	private static String isNull(Object str) {
		if (isEmpty(String.valueOf(str))) {
			return "";
		} else {
			return String.valueOf(str);
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return 空 true 非空 false
	 */
	private static boolean isEmpty(String str) {
		if (str != null && !"".equals(str) && !"null".equals(str)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 插入动态SQL
	 * 
	 * @param sql
	 * @throws Exception
	 */
	public boolean insertDynamicSQL(String sql) throws Exception {
		sql = (String) CharacterSetUtil.getISOObject(sql);
		boolean bReturn = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = this.getSqlSession().getConnection();
			ps = con.prepareStatement(sql);
			bReturn = ps.execute();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				log.error(Toolkits.getExceptionInfo(e));
			}
		}
		return bReturn;
	}

	public List serchBySql(String sql) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List data = null;
		sql = sql.toUpperCase();
		try {
			if (sql.indexOf("INSERT") == -1 && sql.indexOf("UPDATE") == -1 && sql.indexOf("DELETE") == -1) {
				con = this.getSqlSession().getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				data = this.resultSetToList(rs);
			} else {
				throw new Exception("sql语句不合法");
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				log.error(Toolkits.getExceptionInfo(e));
			}
		}
		return data;
	}

	/**
	 * @Description:把resultSet转换为List
	 * @throws Exception
	 */
	public List resultSetToList(ResultSet rs) throws Exception {
		List list = new ArrayList();
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();// 得到当前的列数
		while (rs.next()) { // while控制行数
			Map map = new HashMap();
			for (int i = 1; i <= colCount; i++) {// for循环控制列数
				String key = rsmd.getColumnName(i);// 得到当前列的列名
				map.put(key, CharacterSetUtil.getUTF8Object(rs.getObject(key)));
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 针对表中包含CLOB字段的处理
	 */
	public int addEntityIncludeClob(String arg0, Object entity, String arg1) throws Exception {
		// log.info(arg0+"------"+entity+"======="+arg1);
		entity = getParameterObject(entity);// 处理乱码显示问题
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Map map = (Map) entity;
			log.info("数据参数--->>>" + map);
			SqlSessionTemplate temp = (SqlSessionTemplate) this.getSqlSession();
			con = temp.getSqlSessionFactory().openSession().getConnection();
			log.info("获得的数据库连接为--->>>" + con);
			con.setAutoCommit(false);
			final String sql = arg0;
			log.info("SQL--->>>" + sql);
			ps = con.prepareStatement(sql);
			String str = (String) map.get(arg1);
			log.info("STR--->>>" + str);
			StringReader reader = new StringReader(str);
			log.info("#######" + reader);
			ps.setCharacterStream(1, reader, str.getBytes("utf-8").length);
			ps.executeUpdate();
		} catch (Exception e) {
			log.error(Toolkits.getExceptionInfo(e));
			throw new SQLException();
		} finally {
			con.commit();
			if (ps != null) {
				ps.close();
			}
			// if(con != null){
			// con.close();
			// }
		}
		return 0;
	}

}
