package cn.easybuy.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * sqlsession 工具包
 * @author Administrator
 *
 */
public class MybatisUtil {
	private static SqlSessionFactory factory;
	static{
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static SqlSession createSqlSession(){
		return factory.openSession(false);
	}
	public static void closeSqlSession(SqlSession session){
		if(null != session){
			session.close();
		}
	}
	
	

}
