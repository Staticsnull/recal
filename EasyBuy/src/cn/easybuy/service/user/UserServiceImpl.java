package cn.easybuy.service.user;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.order.UserAddressDao;
import cn.easybuy.dao.order.UserAddressDaoImpl;
import cn.easybuy.dao.user.UserDao;
import cn.easybuy.dao.user.UserDaoImpl;
import cn.easybuy.dao.user.UserMapper;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.MybatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cn.easybuy.entity.User;

public class UserServiceImpl implements UserService {
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public boolean add(User user){
		SqlSession sqlSession = null;
		boolean flag = false;
		try {
			sqlSession = MybatisUtil.createSqlSession();
			if(sqlSession.getMapper(UserMapper.class).add(user) > 0)
               flag = true;
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			flag = false;
		}finally{
			MybatisUtil.closeSqlSession(sqlSession);
		}
		return flag;
//		Connection connection = null;
//		Integer count=0;
//		try {
//			connection = DataSourceUtil.openConnection();
//			UserDao userDao = new UserDaoImpl(connection);
//			count=userDao.add(user);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DataSourceUtil.closeConnection(connection);
//			return  count>0;
//		}
	}

	@Override
	public boolean update(User user) {
		SqlSession sqlSession = null;
		boolean flag = false;
		try {
			sqlSession = MybatisUtil.createSqlSession();
			if(sqlSession.getMapper(UserMapper.class).update(user) > 0)
               flag = true;
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			flag = false;
		}finally{
			MybatisUtil.closeSqlSession(sqlSession);
		}
		return flag;
//		Connection connection = null;
//		Integer count=0;
//		try {
//			connection = DataSourceUtil.openConnection();
//			UserDao userDao = new UserDaoImpl(connection);
//			count=userDao.update(user);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DataSourceUtil.closeConnection(connection);
//			return  count>0;
//		}
	}

	@Override
	public boolean deleteUserById(Integer userId) {
		SqlSession sqlSession = null;
		boolean flag = false;
		try {
			sqlSession = MybatisUtil.createSqlSession();
			if(sqlSession.getMapper(UserMapper.class).deleteUserById(userId) > 0)
               flag = true;
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			flag = false;
		}finally{
			MybatisUtil.closeSqlSession(sqlSession);
		}
		return flag;
//		Connection connection = null;
//		Integer count=0;
//		try {
//			connection = DataSourceUtil.openConnection();
//			UserDao userDao = new UserDaoImpl(connection);
//			count=userDao.deleteUserById(userId+"");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DataSourceUtil.closeConnection(connection);
//			return  count>0;
//		}
	}

	@Override
	public User getUser(Integer userId, String loginName) {
		SqlSession sqlSession = null;
		User user = new User();
		try {
			sqlSession = MybatisUtil.createSqlSession();
			user = sqlSession.getMapper(UserMapper.class).getUser(userId, loginName);
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}finally{
			MybatisUtil.closeSqlSession(sqlSession);
		}
		return user;
//		Connection connection = null;
//		User user=null;
//		try {
//			connection = DataSourceUtil.openConnection();
//			UserDao userDao = new UserDaoImpl(connection);
//			user=userDao.getUser(userId,loginName);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DataSourceUtil.closeConnection(connection);
//			return user;
//		}
	}

	@Override
	public List<User> getUserList(Integer currentPageNo, Integer pageSize) {
		SqlSession sqlSession = null;
		List<User> userList = new ArrayList<User>();
		try {
			sqlSession = MybatisUtil.createSqlSession();
			currentPageNo = (currentPageNo-1)*pageSize;
			userList = sqlSession.getMapper(UserMapper.class).getUserList(currentPageNo, pageSize);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			userList = null;
		}finally{
			MybatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("userList size: " + userList.size());
		return userList;
//		Connection connection = null;
//		List<User> userList=null;
//		try {
//			connection = DataSourceUtil.openConnection();
//			UserDao userDao = new UserDaoImpl(connection);
//			userList=userDao.getUserList(currentPageNo,pageSize);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DataSourceUtil.closeConnection(connection);
//			return userList;
//		}
	}

	@Override
	public int count() {
		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = MybatisUtil.createSqlSession();
			count = sqlSession.getMapper(UserMapper.class).count();
		} catch (Exception e) {
			e.printStackTrace();
			count = 0;
		}finally{
			MybatisUtil.closeSqlSession(sqlSession);
		}
		return count;
//		Connection connection = null;
//		Integer count=null;
//		try {
//			connection = DataSourceUtil.openConnection();
//			UserDao userDao = new UserDaoImpl(connection);
//			count=userDao.count();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DataSourceUtil.closeConnection(connection);
//			return count;
//		}
	}
}
