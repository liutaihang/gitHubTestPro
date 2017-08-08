package liu.dao.BaseDao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.data.domain.Page;

import liu.constant.Constant;

public class MysqlDataBase<T extends Serializable, S extends Serializable> {


	@Resource
	private SessionFactory sessionFactory;

	private Class<T> classType;

	@SuppressWarnings("unchecked") 
	protected MysqlDataBase() {
		Type superClass = this.getClass().getGenericSuperclass();
		// 获取参数泛型的类
		if (superClass instanceof ParameterizedType) {
			this.classType = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
		}
	}

	/**
	 * 获取session
	 * 
	 * @return session
	 * @date 2015年11月25日 上午10:12:45
	 * @author maliang
	 */
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 保存实体，返回对应的ID
	 * <p>
	 * 保存或者更新该实体
	 * </p>
	 * <p>
	 * cascade="save-update"
	 * </p>
	 * 
	 * @param t
	 *            保存实体
	 * @return
	 * @date 2015年11月25日 上午10:15:24
	 * @author maliang
	 */
	protected T save(T t) {
//		dealBasePOForAdd(t);
		getSession().save(t);
		return t;
	}

	/**
	 * 保存实体
	 * <p>
	 * 保存实体
	 * </p>
	 * <p>
	 * cascade="persist"
	 * </p>
	 * 
	 * @param t
	 * @date 2015年11月25日 上午10:50:13
	 * @author maliang
	 */
	protected void persist(T t) {
//		dealBasePOForAdd(t);
		getSession().persist(t);
	}

	/**
	 * 更新实体
	 * <p>
	 * 保存或者更新
	 * </p>
	 * <p>
	 * cascade="save-update"
	 * </p>
	 * 
	 * @param t
	 *            更新实体
	 * @date 2015年11月25日 上午10:16:38
	 * @author maliang
	 */
	protected T update(T t) {
//		dealBasePOForUpdate(t);
		getSession().update(t);
		return t;
	}

	/**
	 * 更新实体
	 * <p>
	 * 更新实体
	 * </p>
	 * <p>
	 * cascade="merge"
	 * <p>
	 * 
	 * @param t
	 *            更新实体
	 * @date 2015年11月25日 上午10:17:35
	 * @author maliang
	 */
	protected void merge(T t) {
//		dealBasePOForUpdate(t);
		getSession().merge(t);
	}

	/**
	 * 删除实体
	 * <p>
	 * cascade="delete"
	 * <p>
	 * 
	 * @param t
	 *            删除实体
	 * @date 2015年11月25日 上午10:19:12
	 * @author maliang
	 */
	protected void delete(T t) {

		getSession().delete(t);
	}

	/**
	 * 根据ID获取
	 * 
	 * @param s
	 *            根据ID查找
	 * @return
	 * @date 2015年11月25日 上午10:22:10
	 * @author maliang
	 */
	@SuppressWarnings("unchecked")
	protected T findById(S s) {
		return (T) getSession().get(classType, s);
		/*
		 * String hql = " from " + classType.getName() + " tmp where tmp.id=:id"; return (T) getSession().createQuery(hql).setParameter("id", s) .uniqueResult();
		 */
	}

	/**
	 * 根据HQL 语句返回多条记录
	 * <p>
	 * 没有查询条件
	 * </p>
	 * <p>
	 * 不具有分页条件设置
	 * </p>
	 * 
	 * @param hql
	 *            根据HQL语句查找
	 * @return
	 * @date 2015年11月25日 上午10:24:40
	 * @author maliang
	 */
	protected List<T> findByHQL(String hql) {
		if (StringUtils.isBlank(hql)) {
			return null;
		}
		return this.findByHQLResultMore(hql, null);
	}

	/**
	 * 获取实体的所有信息
	 * 
	 * @return
	 * @date 2015年12月7日 下午2:51:45
	 * @author maliang
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findAll() {
		return getSession().createQuery("from " + classType.getName()).list();
	}

	/**
	 * 根据条件查询返回结果集
	 * <p>
	 * 不具有分页条件设置
	 * </p>
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            过滤条件
	 * @return
	 * @date 2015年11月25日 上午10:29:36
	 * @author maliang
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByHQLResultMore(String hql, Map<String, Object> params) {
		if (StringUtils.isBlank(hql)) {
			return null;
		}
		Query query = getSession().createQuery(hql);
		if (MapUtils.isNotEmpty(params)) {
			for (Entry<String, Object> entry : params.entrySet()) {
				if (StringUtils.isNotBlank(entry.getKey())) {
					if (entry.getValue().getClass().isArray()) {
						Object[] objects = (Object[]) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else if (List.class.isAssignableFrom(entry.getValue().getClass())) {
						List objects = (List) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else {
						query.setParameter(entry.getKey().toString(), entry.getValue());
					}
				}
			}
		}
		return query.list();
	}

	/**
	 * 根据条件查询返回结果集
	 * 
	 * @param hql
	 *            查找语句
	 * @param params
	 *            过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页长度
	 * @return 返回查找过滤以后，根据分页长度返回结果集
	 * @date 2015年11月25日 上午10:29:36
	 * @author maliang
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByHQLResultMore(String hql, Map<String, Object> params, int pageNo, int pageSize) {
		if (StringUtils.isBlank(hql)) {
			return null;
		}
		Query query = getSession().createQuery(hql);
		if (MapUtils.isNotEmpty(params)) {
			for (Entry<String, Object> entry : params.entrySet()) {
				if (StringUtils.isNotBlank(entry.getKey())) {
					if (entry.getValue().getClass().isArray()) {
						Object[] objects = (Object[]) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else if (List.class.isAssignableFrom(entry.getValue().getClass())) {
						List objects = (List) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else {
						query.setParameter(entry.getKey().toString(), entry.getValue());
					}
				}
			}
		}
		query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		return query.list();
	}

	/**
	 * 根据条件查询返回结果集
	 * <p>
	 * 对总条数封装 {@link Page#RESULT_COUNT}
	 * </p>
	 * <p>
	 * 对返回集合封装 {@link Page#RESULT_DATA}
	 * </p>
	 * 
	 * @param hql
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @date 2015年12月10日 上午11:32:14
	 * @author maliang
	 */
	protected Map<String, Object> findByHQLResultMap(String hql, Map<String, Object> params, int pageNo, int pageSize) {
		Map<String, Object> results = new HashMap<String, Object>();

		int count = this.findByHQLResultCount(hql, params);
		results.put(Constant.RESULT_COUNT, count);

		List<T> objs = this.findByHQLResultMore(hql, params, pageNo, pageSize);
		results.put(Constant.RESULT_DATA, objs);

		return results;
	}

	/**
	 * 按指定条数查询数据
	 * 
	 * @Title: findListByHQL
	 * @Description:按指定条数查询数据
	 * @param hql
	 *            查询语句
	 * @param params
	 *            查询条件
	 * @param from
	 *            起始位置
	 * @param size
	 *            查询条数
	 * @return 结果列表
	 * @exception
	 * @auth lc
	 * @date 2015年12月1日 下午5:56:14
	 */
	protected List<T> findListByHQL(String hql, Map<String, Object> params, int from, int size) {
		if (StringUtils.isBlank(hql)) {
			return null;
		}
		Query query = getSession().createQuery(hql);
		if (MapUtils.isNotEmpty(params)) {
			for (Entry<String, Object> entry : params.entrySet()) {
				if (StringUtils.isNotBlank(entry.getKey())) {
					query.setParameter(entry.getKey().toString(), entry.getValue());
				}
			}
		}
		query.setFirstResult(from).setMaxResults(size);
		return query.list();
	}

	/**
	 * 获取单个实体
	 * 
	 * @param hql
	 *            查找条件
	 * @param params
	 *            过滤值
	 * @return 返回该查找过滤以后的单个实体
	 * @date 2015年11月25日 上午10:37:10
	 * @author maliang
	 */
	@SuppressWarnings("unchecked")
	protected T findByHQLResultSingle(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		if (MapUtils.isNotEmpty(params)) {
			for (Entry<String, Object> entry : params.entrySet()) {
				if (StringUtils.isNotBlank(entry.getKey())) {
					if (entry.getValue().getClass().isArray()) {
						Object[] objects = (Object[]) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else if (List.class.isAssignableFrom(entry.getValue().getClass())) {
						List objects = (List) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else {
						query.setParameter(entry.getKey().toString(), entry.getValue());
					}
				}
			}
		}
		return (T) query.uniqueResult();
	}

	/**
	 * 针对多个返回结果，获取其中一个的情况
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @date 2015年12月8日 下午3:59:04
	 * @author maliang
	 */
	protected T findByHQLMoreResultSingle(String hql, Map<String, Object> params) {
		List<T> ts = this.findByHQLResultMore(hql, params);
		return ts == null || ts.size() == 0 ? null : ts.get(0);
	}

	/**
	 * 根据条件获取总条数
	 * <p>
	 * 需要自行添加 count关键字
	 * </p>
	 * 
	 * @param hql
	 *            查找条件
	 * @param params
	 *            过滤条件
	 * @return 返回该语句过滤以后的总条数
	 * @date 2015年11月25日 上午10:41:54
	 * @author maliang
	 */
	protected Integer findByHQLResultCount(String hql, Map<String, Object> params) {
		String subhql = StringUtils.substringAfterLast(hql, "from");
		if(StringUtils.isBlank(subhql)){
			subhql = StringUtils.substringAfterLast(hql, "FROM");
		}
		String tmp = "select count(*) from" + subhql;
		Object object = this.findByHQLResultSingle(tmp, params);
		return object == null ? 0 : Integer.valueOf(object.toString());
	}

	/**
	 * sql 查询，无分页
	 * 
	 * @param sql
	 * @param params
	 * @param type
	 * @return
	 * @date 2016年2月19日 下午6:46:52
	 * @author luogang
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<T> findBySQLResult(String sql, Map<String, Object> params, Class<?> type) {
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(type);
		if (MapUtils.isNotEmpty(params)) {
			for (Entry<String, Object> entry : params.entrySet()) {
				if (StringUtils.isNotBlank(entry.getKey())) {
					if (entry.getValue().getClass().isArray()) {
						Object[] objects = (Object[]) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else if (List.class.isAssignableFrom(entry.getValue().getClass())) {
						List objects = (List) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else {
						query.setParameter(entry.getKey().toString(), entry.getValue());
					}
				}
			}
		}
		return query.list();
	}

	/**
	 * 根据条件sql查询总记录数
	 * 
	 * @Description: 根据条件sql查询总记录数
	 * @param sql
	 * @param params
	 * @return
	 * @return int
	 * @throws
	 * @author tangzhi
	 * @date 2016-5-16
	 */
	protected int findBySQLResultCount(String sql, Map<String, Object> params) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (MapUtils.isNotEmpty(params)) {
			for (Entry<String, Object> entry : params.entrySet()) {
				if (StringUtils.isNotBlank(entry.getKey())) {
					if (entry.getValue().getClass().isArray()) {
						Object[] objects = (Object[]) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else if (List.class.isAssignableFrom(entry.getValue().getClass())) {
						List objects = (List) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else {
						query.setParameter(entry.getKey().toString(), entry.getValue());
					}
				}
			}
		}
		Object object = query.uniqueResult();
		return object == null ? 0 : Integer.valueOf(object.toString());
	}

	/**
	 * sql 分页查询数据
	 * 
	 * @param sql
	 * @param params
	 * @param type
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @date 2016年2月19日 下午6:47:07
	 * @author luogang
	 */
	protected List<T> findBySQLResultMore(String sql, Map<String, Object> params, Class<?> type, int pageNo, int pageSize) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (MapUtils.isNotEmpty(params)) {
			for (Entry<String, Object> entry : params.entrySet()) {
				if (StringUtils.isNotBlank(entry.getKey())) {
					if (entry.getValue().getClass().isArray()) {
						Object[] objects = (Object[]) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else if (List.class.isAssignableFrom(entry.getValue().getClass())) {
						List objects = (List) entry.getValue();
						query.setParameterList(entry.getKey().toString(), objects);
					} else {
						query.setParameter(entry.getKey().toString(), entry.getValue());
					}
				}
			}
		}
		query.addEntity(type);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	/**
	 * 批量保存
	 * */
	public List<T> saveBatch(List<T> listVal) {

		List<T> list = null;
		if (listVal != null && !listVal.isEmpty()) {
			list = new ArrayList<T>(listVal.size());
			for (T t : listVal) {
				list.add(save(t));
			}
		}
		return list;
	}
	
	/**
	 * 批量删除
	 * */
	public void deleteBatch(List<T> listVal) {

		if (listVal != null && !listVal.isEmpty()) {
			for (T t : listVal) {
				this.delete(t);
			}
		}
	}
	
	/**
	 * 根据对象record中不为null的字段匹配记录
	 * @param record
	 *            实体对象
	 * @return 返回该对象实体不为null的字段匹配出的记录list
	 * @author maolong
	 * @date 2016年6月3日下午3:00:56
	 */
	public List<T> querySelective(T record) {
		Example example = Example.create(record)
				.excludeZeroes();////排除值为0的属性
		Criteria criteria = getSession().createCriteria(record.getClass()).add(example);
		return criteria.list();
	}
	
	public int updateByHql(String hql){

		Transaction trans = getSession().beginTransaction();
		Query queryupdate=getSession().createQuery(hql);
		int ret=queryupdate.executeUpdate();
		trans.commit();
		return ret;
	}
	
	
	/**
	 *首次保存对象时 处理通用字段
	 * */
//	private <T> T dealBasePOForAdd(T record) {
//		if (ClassUtils.isAssignable(classType, BasePO.class) || ClassUtils.isAssignable(classType, BasePONoAud.class)) {
//			RequestParam requestParam = RequestParamCache.requestParamCache.get();
//
//			Field field = ReflectionUtils.findField(classType, IBasePO.createTime);
//			setField(field, record, requestParam.getLashtUpdateTime());
//			
//			return dealBasePOForUpdate(record);
//		}
//		return record;
//	}
//	
//	/**
//	 *更新对象时 处理通用字段
//	 * */
//	private <T> T dealBasePOForUpdate(T record) {
//		if (ClassUtils.isAssignable(classType, BasePO.class) || ClassUtils.isAssignable(classType, BasePONoAud.class)) {
//			RequestParam requestParam = RequestParamCache.requestParamCache.get();
//			
//			Field field = ReflectionUtils.findField(classType, IBasePO.serialNo);
//			setField(field, record, requestParam.getOperationNum());
//
//			field = ReflectionUtils.findField(classType, IBasePO.lastUpdateTime);	
//			setField(field, record, requestParam.getLashtUpdateTime());
//
//			field = ReflectionUtils.findField(classType, IBasePO.lastOperatorName);	
//			setField(field, record, requestParam.getLoginName());
//
//			field = ReflectionUtils.findField(classType, IBasePO.lastOperatorId);	
//			setField(field, record, Integer.valueOf(requestParam.getLoginId()));
//
//		}
//		return record;
//	}
	
	private void setField(Field field, Object target, Object value) {
		if (field == null) return ;
		if(!field.isAccessible()){
			field.setAccessible(true);
		}
		try {
			field.set(target, value);
		} catch (Exception e) {
			throw new IllegalStateException(
					"Unexpected reflection exception - " + e.getClass().getName() + ": " + e.getMessage());
		}

	}

}
