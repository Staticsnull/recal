package cn.easybuy.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.easybuy.entity.Product;

public interface ProductMapper {
	
	int count(@Param("proName")String proName,
			 @Param("categoryId")Integer categoryId,
			 @Param("level")Integer level) throws Exception;
	
	//更新指定商品的库存量
	Integer updateStock(@Param("id")Integer id, 
			          @Param("stock")Integer stock) throws Exception;
	//增加商品
	Integer add(Product product) throws Exception;
	//修改商品
	Integer update(Product product) throws Exception;
	//根据id删除商品
	Integer deleteProductById(Integer id) throws Exception;
	//根据id查看商品
	Product getProductById(Integer id)throws Exception;
	//根据查询条件.分页显示商品信息列表
	List<Product> getProductList(@Param("currentPageNo")Integer currentPageNo,
			 					  @Param("pageSize")Integer pageSize,
			 					 	@Param("proName")String proName,
			 					      @Param("categoryId")Integer categoryId,
			 					        @Param("level")Integer level)throws Exception;
	//根据查询条件,查询商品表的总记录数
	Integer queryProductCount(@Param("proName")String proName,
								@Param("categoryId")Integer categoryId,
								  @Param("level")Integer level)throws Exception;
}
