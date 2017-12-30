package cn.easybuy.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.easybuy.entity.OrderDetail;

/**
 * 订单明细表映射接口
 * @author Administrator
 *
 */
public interface OrderDetailMapper {
	/**
	 * OrderDetailMapper接口方法：
	 *（1）增加订单明细（返回类型：int，参数：OrderDetail对象）。
	 *（2）根据订单id查询订单明细列表（返回类型：List<OrderDetail>，参数：订单id）。
	 */
	int add(OrderDetail orderDetail) throws Exception;
	
	List<OrderDetail> getOrderDetailList(@Param("orderId")Integer orderId) throws Exception;

}
