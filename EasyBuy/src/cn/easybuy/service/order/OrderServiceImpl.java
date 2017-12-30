package cn.easybuy.service.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.session.SqlSession;

import cn.easybuy.dao.order.*;
import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.dao.product.ProductMapper;
import cn.easybuy.utils.*;
import cn.easybuy.entity.Order;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.User;

public class OrderServiceImpl implements OrderService {

    /**
     * 结算购物车物品包含以下步骤：
     * 1.生成订单
     * 2.生成订单明细
     * 3.更新商品表，减库存
     * 注意加入事务的控制
     */

    @Override
    public Order payShoppingCart(ShoppingCart cart, User user, String address) {
    	
    	SqlSession session = null;
    	Order order = new Order();
    	try {
			session = MybatisUtil.createSqlSession();
			order.setUserId(user.getId());
			order.setLoginName(user.getLoginName());
			order.setUserAddress(address);
			order.setCreateTime(new Date());
			order.setCost(cart.getTotalCost());
			order.setSerialNumber(StringUtils.randomUUID());
			session.getMapper(OrderMapper.class).add(order);
			//增加订单对应的明细信息
			for(ShoppingCartItem item : cart.getItems()){
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderId(order.getId());
				orderDetail.setCost(item.getCost());
				orderDetail.setProduct(item.getProduct());
				orderDetail.setQuantity(item.getQuantity());
				session.getMapper(OrderDetailMapper.class).add(orderDetail);
				//更新库存
				session.getMapper(ProductMapper.class)
									.updateStock(item.getProduct().getId(),
											item.getQuantity());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			order = null;
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
    	return order;
//        Connection connection = null;
//        Order order = new Order();
//        try {
//            connection = DataSourceUtil.openConnection();
//            connection.setAutoCommit(false);
//            ProductDao productDao = new ProductDaoImpl(connection);
//            OrderDao orderDao = new OrderDaoImpl(connection);
//            OrderDetailDao orderDetailDao = new OrderDetailDaoImpl(connection);
//            //增加订单
//            order.setUserId(user.getId());
//            order.setLoginName(user.getLoginName());
//            order.setUserAddress(address);
//            order.setCreateTime(new Date());
//            order.setCost(cart.getTotalCost());
//            order.setSerialNumber(StringUtils.randomUUID());
//            orderDao.add(order);
//            //增加订单对应的明细信息
//            for (ShoppingCartItem item : cart.getItems()) {
//                OrderDetail orderDetail = new OrderDetail();
//                orderDetail.setOrderId(order.getId());
//                orderDetail.setCost(item.getCost());
//                orderDetail.setProduct(item.getProduct());
//                orderDetail.setQuantity(item.getQuantity());
//                orderDetailDao.add(orderDetail);
//                //更新商品表的库存
//                productDao.updateStock(item.getProduct().getId(), item.getQuantity());
//                connection.commit();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//            order = null;
//        } finally {
//            try {
//                connection.setAutoCommit(true);
//                DataSourceUtil.closeConnection(connection);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return order;
    }

    @Override
    public List<Order> getOrderList(Integer userId, Integer currentPageNo, Integer pageSize) {
//        SqlSession session = null;
//        List<Order> orderList = new ArrayList<Order>();
//        try {
//			session = MybatisUtil.createSqlSession();
//			orderList = session.getMapper(OrderMapper.class).getOrderList(userId, currentPageNo, pageSize);
////			OrderDetailMapper orderDetailMapper = MybatisUtil.createSqlSession().getMapper(OrderDetailMapper.class);
////			for(Order order : orderList){
////				order.setOrderDetailList(orderDetailMapper.getOrderDetailList(order.getUserId()));
////			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.rollback();
//			orderList = null;
//		}finally{
//			MybatisUtil.closeSqlSession(session);
//		}
//    	return orderList;
    	Connection connection = null;
        List<Order> orderList = new ArrayList<Order>();
        try {
            connection = DataSourceUtil.openConnection();
            OrderDao orderDao = new OrderDaoImpl(connection);
            OrderDetailDao orderDetailDao=new OrderDetailDaoImpl(connection);
            orderList = orderDao.getOrderList(userId, currentPageNo, pageSize);
            for(Order order:orderList){
            	order.setOrderDetailList(orderDetailDao.getOrderDetailList(order.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
            return orderList;
        }
    }

    @Override
    public int count(Integer userId) {
    	SqlSession session = null;
    	Integer count = 0;
    	try {
    		session = MybatisUtil.createSqlSession();
			count = session.getMapper(OrderMapper.class).count(userId);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			count = 0;
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
    	return count;
    	
    	
//        Connection connection = null;
//        Integer count=0;
//        try {
//            connection = DataSourceUtil.openConnection();
//            OrderDao orderDao = new OrderDaoImpl(connection);
//            count=orderDao.count(userId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DataSourceUtil.closeConnection(connection);
//            return count;
//        }
    }

    /**
     * 调用dao接口：OrderDetailMapper的方法实现
     */
    @Override
    public List<OrderDetail> getOrderDetailList(Integer orderId) {
    	SqlSession session = null;
    	List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
    	try {
    		session = MybatisUtil.createSqlSession();
			orderDetailList = 
					session.getMapper(OrderDetailMapper.class).getOrderDetailList(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			orderDetailList = null;
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
    	return orderDetailList;
//        Connection connection = null;
//        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
//        try {
//            connection = DataSourceUtil.openConnection();
//            OrderDetailDao orderDetailDao = new OrderDetailDaoImpl(connection);
//            orderDetailList = orderDetailDao.getOrderDetailList(orderId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DataSourceUtil.closeConnection(connection);
//            return orderDetailList;
//        }
    }


}
