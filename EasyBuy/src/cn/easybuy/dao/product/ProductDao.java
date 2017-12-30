package cn.easybuy.dao.product;

import java.util.List;

import cn.easybuy.dao.IBaseDao;
import cn.easybuy.entity.News;
import cn.easybuy.entity.Product;
import cn.easybuy.param.NewsParams;
import cn.easybuy.param.ProductParam;

/**
 * 商品查询Dao
 *
 * deleteById(Integer id)
 * getById(Integer id)
 * getRowCount(params)
 * getRowList(params)
 *
 */
public interface ProductDao extends IBaseDao {
	//更新指定商品的库存量
	Integer updateStock(Integer id, Integer quantity) throws Exception;
	//增加商品
	Integer add(Product product) throws Exception;
	//修改商品
	Integer update(Product product) throws Exception;
	//根据id删除商品
	Integer deleteProductById(Integer id) throws Exception;
	//根据id查看商品
	Product getProductById(Integer id)throws Exception;
	//根据查询条件.分页显示商品信息列表
	List<Product> getProductList(Integer currentPageNo,Integer pageSize,String proName,Integer categoryId,Integer level)throws Exception;
	//根据查询条件,查询商品表的总记录数
	Integer queryProductCount(String proName,Integer categoryId,Integer level)throws Exception;
}
