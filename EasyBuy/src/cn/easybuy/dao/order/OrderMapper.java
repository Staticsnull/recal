package cn.easybuy.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.easybuy.entity.Order;

/**
 * 订单映射接口
 * @author Administrator
 *
 */
public interface OrderMapper {
	/**
	 * OrderMapper接口方法：
	 *（1）增加订单（返回类型：int，参数：Order对象）。
	 *（2）根据查询条件，分页显示订单信息列表（返回类型：List<Order>，参数：当前页码、页码容量，用户id）。
	 *（3）根据条件查询订单表总记录数（返回类型：int，参数：用户id）。
	 */
	int add(Order order) throws Exception;
	
	List<Order> getOrderList(@Param("userId")Integer userId,
			  						@Param("from")Integer currentPageNo,
			  						@Param("pageSize")Integer pageSize) throws Exception;
	
	int count(@Param("userId")Integer userId) throws Exception;

}
