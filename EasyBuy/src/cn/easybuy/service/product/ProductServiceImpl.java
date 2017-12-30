package cn.easybuy.service.product;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.dao.product.ProductMapper;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.MybatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cn.easybuy.entity.Product;
/**
 * 商品的业务类
 */
public class ProductServiceImpl implements ProductService {
	
	private Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Override
	public boolean add(Product product) {
		SqlSession sqlSession = null;
		boolean flag = false;
		try {
			sqlSession = MybatisUtil.createSqlSession();
			if(sqlSession.getMapper(ProductMapper.class).add(product) > 0)
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
//			ProductDao productDao = new ProductDaoImpl(connection);
//			count=productDao.add(product);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DataSourceUtil.closeConnection(connection);
//			return count>0;
//		}
	}

	@Override
	public boolean update(Product product) {
		SqlSession sqlSession = null;
		boolean flag = false;
		try {
			sqlSession = MybatisUtil.createSqlSession();
			if(sqlSession.getMapper(ProductMapper.class).update(product) > 0)
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
//			ProductDao productDao = new ProductDaoImpl(connection);
//			count=productDao.update(product);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DataSourceUtil.closeConnection(connection);
//			return count>0;
//		}
	}

	@Override
	public boolean deleteProductById(Integer productId) {
		SqlSession sqlSession = null;
		boolean flag = false;
		try {
			sqlSession = MybatisUtil.createSqlSession();
			if(sqlSession.getMapper(ProductMapper.class).deleteProductById(productId) > 0)
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
//			ProductDao productDao = new ProductDaoImpl(connection);
//			count=productDao.deleteProductById(productId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DataSourceUtil.closeConnection(connection);
//			return count>0;
//		}
	}

	@Override
	public Product getProductById(Integer productId) {
		SqlSession sqlSession = null;
		Product product = new Product();
		try {
			sqlSession = MybatisUtil.createSqlSession();
			product = sqlSession.getMapper(ProductMapper.class).getProductById(productId);
		} catch (Exception e) {
			e.printStackTrace();
			product = null;
		}finally{
			MybatisUtil.closeSqlSession(sqlSession);
		}
		return product;
//		Connection connection = null;
//		Product product=null;
//		try {
//			connection = DataSourceUtil.openConnection();
//			ProductDao productDao = new ProductDaoImpl(connection);
//			product=productDao.getProductById(productId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DataSourceUtil.closeConnection(connection);
//			return product;
//		}
	}

	@Override
	public List<Product> getProductList(Integer currentPageNo,Integer pageSize,String proName, Integer categoryId, Integer level) {
		SqlSession sqlSession = null;
		List<Product> productList = new ArrayList<Product>();
		try {
			sqlSession = MybatisUtil.createSqlSession();
			currentPageNo = (currentPageNo-1)*pageSize;
			productList = sqlSession.getMapper(ProductMapper.class).getProductList(currentPageNo, pageSize, proName, categoryId, level);
		} catch (Exception e) {
			e.printStackTrace();
			productList = null;
		}finally{
			MybatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("productList size: " + productList.size());
		return productList;
//		Connection connection = null;
//		List<Product> productList=null;
//		try {
//			connection = DataSourceUtil.openConnection();
//			ProductDao productDao = new ProductDaoImpl(connection);
//			productList=productDao.getProductList(currentPageNo,pageSize,proName,categoryId,level);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DataSourceUtil.closeConnection(connection);
//			return productList;
//		}
	}

	@Override
	public int count(String proName,Integer categoryId, Integer level) {
		SqlSession session = null;
		Integer count = 0;
		try {
			session = MybatisUtil.createSqlSession();
			count = session.getMapper(ProductMapper.class).count(proName, categoryId, level);
		} catch (Exception e) {
			e.printStackTrace();
			count = 0;
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
		return count;
//		Connection connection = null;
//		Integer count=0;
//		try {
//			connection = DataSourceUtil.openConnection();
//			ProductDao productDao = new ProductDaoImpl(connection);
//			count=productDao.queryProductCount(proName,categoryId,level);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DataSourceUtil.closeConnection(connection);
//			return count;
//		}
	}

	@Override
	public boolean updateStock(Integer productId, Integer stock) {
		SqlSession session = null;
		boolean flag = false;
		try {
			session = MybatisUtil.createSqlSession();
			if (session.getMapper(ProductMapper.class).updateStock(productId, stock)>0){
				flag = true;
			};
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
		return flag;
//		Connection connection = null;
//		Integer count=0;
//		try {
//			connection = DataSourceUtil.openConnection();
//			ProductDao productDao = new ProductDaoImpl(connection);
//			count=productDao.updateStock(productId,stock);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DataSourceUtil.closeConnection(connection);
//			return count>0;
//		}
	}
   
}
