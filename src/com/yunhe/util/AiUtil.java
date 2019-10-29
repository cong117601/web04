package com.yunhe.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


    /**
    * @ClassName: AiUtil
    * @Description: jdbc工具类,默认读取src下jdbc.properties配置文件(语法DRIVER/URL/USERNAME/PASSWORD)，也可以通过静态方法setPro设置
    * @author Ai
    * @date 2019年9月11日
    *
    */
    
public class AiUtil {
	
	
	    /**
	    * @Fields DRIVER:{数据库连接驱动}
	    */
	    
	private static String DRIVER;
	
	    /**
	    * @Fields URL:{数据库连接地址} 
	    */
	    
	private static String URL;
	
	    /**
	    * @Fields USER:{连接数据库账号}
	    */
	    
	private  static String USERNAME ;
	
	    /**
	    * @Fields PASSWORD:{连接数据库密码}
	    */
	    
	private static String PASSWORD ;

 
	
	static{
		try{
		ResourceBundle rb=ResourceBundle.getBundle("JDBC");
		AiUtil.URL=rb.getString("URL");
		AiUtil.DRIVER = rb.getString("DRIVER");
		AiUtil.USERNAME = rb.getString("USERNAME");
		AiUtil.PASSWORD = rb.getString("PASSWORD");
		}catch(Exception e){
			System.out.println("DRIVER:"+DRIVER);
			System.out.println("URL:"+URL);
			System.out.println("USERNAME:"+USERNAME);
			System.out.println("PASSWORD:"+PASSWORD);
			System.out.println("加载配置文件出错");
		}
	}
	
	
	
	    /**
	    * @Title: setPro
	    * @Description: TODO(手动设置数据库连接属性)
	    * @param @param DRIVER
	    * @param @param URL
	    * @param @param USERNAME
	    * @param @param PASSWORD    参数
	    * @return void    返回类型
	    * @throws
	    * @author Ai
	    */
	    
	public static void setPro(String DRIVER , String URL, String USERNAME, String PASSWORD){
			AiUtil.URL=URL;
			AiUtil.DRIVER = DRIVER;
			AiUtil.USERNAME = USERNAME;
			AiUtil.PASSWORD = PASSWORD;
		 
	}
	
	
	
	
	
	    /**
	    * @Title: dbDQLWithSQL
	    * @Description: 用于sql查询的方法
	    * @param 查询的sql语句
	    * @param 指定对象的class
	    * @param 不确定参数 根据sql中？个数进行赋值
	    * @return List<E>  指定泛型的数据类型集合
	    * @author Ai
	    */
	    
	public static <E> ArrayList<E> dbDQLWithSQL(String sql, Class<E> class1, Object... objects) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ArrayList<E> list = new ArrayList<>();
		ResultSet set = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 1; i <= objects.length; i++) {
				Object o = objects[i - 1];
				statement.setObject(i, o);
			}
			ResultSetMetaData data = statement.getMetaData();
			String[] names = new String[data.getColumnCount()];
			for (int i = 1; i <= data.getColumnCount(); i++) {
				String name = data.getColumnLabel(i);
				names[i - 1] = name;
			}
			set = statement.executeQuery();
			while (set.next()) {
				E oE = class1.newInstance();
				for (String name : names) {
					Object value = set.getObject(name);
					Field field = class1.getDeclaredField(name);
					field.setAccessible(true);
					field.set(oE, value);
				}
				list.add(oE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(connection, statement, set);
		}
		return list;
	}

	
	    /**
	    * @Title: dbDMLWithSQL
	    * @Description: 用于sql更新操作（删除修改查询）
	    * @param sql 执行操作的sql语句
	    * @param  objects
	    * @return 不确定参数 根据sql中？个数进行赋值
	    * @return boolean  操作结果
	    * @author Ai
	    */
	    
	public static boolean dbDMLWithSQL(String sql, Object... objects) {

		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 1; i <= objects.length; i++) {
				Object object = objects[i - 1];
				statement.setObject(i, object);
			}
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(connection, statement);
		}
		return true;
	}

	private static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	private static void close(Connection connection, Statement statement, ResultSet set) {
		close(set);
		close(statement);
		close(connection);
	}

	private static void close(Connection connection, Statement statement) {
		close(statement);
		close(connection);

	}

	private static void close(ResultSet set) {
		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
}
