package com.etc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;



/**
 * ���ݿ�ͨ�ù���,��������,ͨ�õ�����,ɾ��,�޸ĵĲ���,��ѯ����;�ͷ���Դ
 * 
 * @author Administrator
 *
 */
public class DBUtil {
	private static final String url = "jdbc:mysql://127.0.0.1:3306/testgui";
	private static final String user = "root";
	private static final String password = "root";

	/**
	 * ����һ�����Ӷ���
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConn() throws SQLException {
		// ��������(����ʡ��)
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * ͨ�õ�����,ɾ��,�޸ĵĲ��� Object... params:�ɱ����������
	 */
	public static boolean execUpdate(String sql, Object... params) {
		Connection con = null;
		PreparedStatement p = null;
		int n = 0;
		try {
			con = getConn();
			// ͨ��Connection����õ�prepareStatement
			p = con.prepareStatement(sql);
			// ��?��ֵ
			setPreparedStatement(p, params);
			// ִ��sql
			n = p.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			closeAll(null, p, con);
		}
		return n > 0;
	}

	/**
	 * ����ռλ��?��ֵ
	 * 
	 * @param PreparedStatement
	 *            ����
	 * @param params
	 *            �ɱ����(����)
	 */
	public static void setPreparedStatement(PreparedStatement p, Object... params) {
		if (null != params && null != p) {
			for (int i = 0; i < params.length; i++) {
				try {
					p.setObject(i + 1, params[i]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * �ͷ���Դ
	 */
	public static void closeAll(ResultSet rs, PreparedStatement p, Connection con) {
		try {
			if (null != rs)
				rs.close();
			if (null != p)
				p.close();
			if (null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ��ѯԱ��
	 */

	public static CachedRowSet execQuery(String sql,Object... params) {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		CachedRowSet crs =null;
		try {
			crs=new CachedRowSetImpl();
			con = getConn();
			p = con.prepareStatement(sql);
			setPreparedStatement(p, params);
			rs = p.executeQuery();
			crs.populate(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs,p,con);
		}
		return crs;

	}
}