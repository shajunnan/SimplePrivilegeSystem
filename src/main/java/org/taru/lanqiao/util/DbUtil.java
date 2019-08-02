package org.taru.lanqiao.util;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * 数据库访问工具类
 * @author kexi
 * 手动提交事务
 * 查询返回多条结果的处理方式: List<Map<String,Object>>、ResultSet、企业通过反射解析(返回对象)
 */

public class DbUtil {
	// 建立一个连接
	private static Connection conn = null;
	// ThreadLocal解决的问题是同一个线程保证连接只能有一个,并且不同的线程连接为不同的
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	// 准备SQL语句
	private static PreparedStatement pst = null;
	// 结果集
	private static ResultSet result = null;

	private static Properties properties = new Properties();
	// 加载数据库连接配置信息
	static {
		try {
			// properties.load(ClassLoader.getSystemResourceAsStream("db.properties"));
			properties.load(DbUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			System.out.println("初始化配置文件成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 加载驱动
	static {
		try {
			Class.forName(properties.getProperty("driver"));
			System.out.println("驱动加载成功！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection() {
		conn = local.get();
		try {
			if( conn==null || conn.isClosed() ) {
				conn = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("passwd"));
				conn.setAutoCommit(true); // 设置自动提交
				local.set(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}


	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return List<Map<String,Object>>
	 * 忘了关闭ResultSet会造成麻烦，最好在函数内关闭
	 */
	public static List<Map<String,Object>> query(String sql,Object ... params) {
		Connection conn = getConnection();
		List<Map<String,Object>> ret = new ArrayList<Map<String,Object>>();
		ResultSet result = null;
		try {
			pst = conn.prepareStatement(sql);	// 此处conn可能为null，是否需要处理
			for(int i = 0;i < params.length;i++) {
				pst.setObject(i+1, params[i]);
			}
			result = pst.executeQuery();

			ResultSetMetaData rsmd = result.getMetaData(); // 获得元数据（结果的列）
			int colCount = rsmd.getColumnCount(); // 获得列数
			while(result.next()) {	// 处理每一条数据
				Map<String,Object> hashMap = new HashMap<String,Object>();
				for(int i = 0;i < colCount ; i++) {	// 处理每一列数据
					hashMap.put(rsmd.getColumnLabel(i+1), result.getObject(i+1));
				}
				ret.add(hashMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
			throw new RuntimeException("未获取到连接!");
		}finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}


	/**
	 * 增删改
	 * @param sql
	 * @param params
	 * @return row
	 */
	public static int update(String sql,Object ... params) {
		Connection conn = getConnection();
		int row = 0;
		try {
			pst = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				pst.setObject(i+1, params[i]);
			}
			row = pst.executeUpdate(); // 是否需要抛出，以便回滚
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("执行DML错误!");
		}catch(NullPointerException e) {
			e.printStackTrace();
			throw new RuntimeException("未获取到连接!");
		}
		return row;
	}


	/**
	 * 提交事务
	 * @return
	 */
	public static boolean commit(){
		boolean ret = false;
		try {
			conn.commit();
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}


	/**
	 * 回滚事务
	 * @return
	 */
	public static boolean rollback() {
		boolean ret = false;
		try {
			conn.rollback();
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}



	/**
	 * 关闭
	 */
	public static void close() {
		if(result!=null) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
