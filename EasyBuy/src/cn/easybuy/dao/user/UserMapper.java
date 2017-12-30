package cn.easybuy.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.easybuy.entity.User;

/**
 * userMapper 映射接口
 *  @author Administrator
 *
 */
public interface UserMapper {
	
	int add(User user) throws Exception;//新增用户信息

	int update(User user) throws Exception;//更新用户信息
	//根据id删除用户信息
	int deleteUserById(Integer id) throws Exception;
	//根据分页信息显示用户记录
	List<User> getUserList(@Param("currentPageNo")Integer currentPageNo,
						   @Param("pageSize")Integer pageSize)throws Exception;
	//查询用户记录数,用于计算分页显示
	int count() throws Exception;
	//根据id查找用户信息 以及根据查询条件验证用户登录信息
	User getUser(@Param("id")Integer id,@Param("loginName")String loginName) throws Exception;

}
