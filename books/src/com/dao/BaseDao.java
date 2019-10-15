package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {

	/*public static void main(String[] args) {
		BaseDao b = new BaseDao();
		b.openConnection();
	}*/
	
	protected Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	
	/**
	 * 鎵撳紑鏁版嵁搴撻摼鎺�
	 */
	public void openConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/books?characterEncoding=utf-8";
		String user = "root";
		String password = "123456";
		
		try {
			//鍔犺浇椹卞姩
			Class.forName(driver);
			//閾炬帴鏁版嵁搴�
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("getConnection");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 鍏抽棴鏁版嵁搴撻摼鎺�
	 */
	public void closeConnection() {
		
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 鎵ц鎵�鏈夎〃鐨勬彃鍏ユ搷浣�
		 * String
		 * StringBuffer
		 * StringBuilder
	 * @param table
	 * @param list
	 * @return
	 */
	public int insert(String table, List list) {
		int i = 0;
		//鎿嶄綔鐩稿搴旇〃鐨剆ql璇彞
		StringBuffer sql = null;	//StringBuffer鏄嚎绋嬪畨鍏ㄧ殑
		if(table.equals("userinfo")) {
			sql = new StringBuffer("insert into "+table+" values(");
		} else {
			sql = new StringBuffer("insert into "+table+" values("+list.get(0)+",");
			list.remove(0);
		}
		for(int j = 0; j<list.size(); j++) {
			if(j+1 == list.size()) {
				sql.append("?)");	//浼犲弬鏁扮粨鏉�
				break;
			}
			sql.append("?,");
		}
		openConnection();
		try {
			System.out.println(sql);
			ps = conn.prepareStatement(sql.toString());
			for (int j = 0; j < list.size(); j++) {
				ps.setObject(j+1, list.get(j));	//鐢╫bject鎺ユ敹
			}
			i = ps.executeUpdate();	//鎵ц鎴愬姛杩斿洖1 mysql瑕佷箞杩斿洖1瑕佷箞杩斿洖0
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return i;	
	}
	
	/**
	 * 鏁翠綋鐨�
	 * @param sql
	 * @param colums
	 * @return
	 */
	public List query(String sql,String[] colums) {
		List list = new ArrayList();
		Map map = null;
		openConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				map = new HashMap();
				for (int i = 0; i < colums.length; i++) {
					//鏍规嵁鍒楀悕鍙栨暟鎹簱鐨勫��
					map.put(colums[i], rs.getObject(colums[i]));
					
				}
				list.add(map);
			}
		} catch (Exception e) {
			if(e.getMessage().equals("鍒楀悕鏃犳晥")) {
				System.out.println("褰撳墠鏌ユ壘鐨勫垪鍚嶄笉瀛樺湪");
			} else {
				e.printStackTrace();
			}
		} finally {
			closeConnection();
		}		
		
		return list;
	}
	
	/**
	 * 鏇存柊鏁版嵁搴擄紝闃叉搴撳瓨涓鸿礋鏁�
	 * @param sql
	 * @return
	 */
	public int deleteOrUpdate(String sql) {
		int i = 0;
		openConnection();
		try {
			
			ps = conn.prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return i;
	}
	
	/**
	 * 鏍规嵁bid鏌ヨ搴撳瓨
	 * @param bid
	 * @return
	 * @throws SQLException 
	 */
	public int queryStock(int bid) throws SQLException {
		
		int stock = 0;
		String sql = "select stock from books where bid="+bid;
		openConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		rs.next();//绉诲姩娓告爣鍒扮涓�鏉¤褰�
		return rs.getInt(1);
	}

}
