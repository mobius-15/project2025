package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import soldiers.SoldierBean;
import util.LogUtil;

public class SoldierDAO extends BaseDAO {

	public ArrayList<SoldierBean> loadAll() {
		LogUtil.println(this.getClass().getSimpleName() + "#loadAll");
		PreparedStatement pstmt = null;
		ArrayList<SoldierBean> alSoldier = new ArrayList<SoldierBean>();
		String strSql = "Select * From Soldier Order By id ASC";
		try {
			open();
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			String sql = "SELECT * FROM soldiers";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SoldierBean sb = new SoldierBean();
				sb.setId(rs.getInt("id"));
				sb.setName(rs.getString("name"));
				sb.setZip(rs.getString("zip"));
				sb.setAdd1(rs.getString("add1"));
				sb.setAdd2(rs.getString("add2"));
				sb.setTel(rs.getString("tel"));
				sb.setMail(rs.getString("mail"));
				alSoldier.add(sb);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(); // ログ出力（最低限）
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignored) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ignored) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception ignored) {
			}
		}
		return alSoldier;

	}

	public ArrayList<SoldierBean> searchByName(String name) {
		LogUtil.println(this.getClass().getSimpleName() + "#searchByName");
		PreparedStatement pstmt = null;
		ArrayList<SoldierBean> alSoldier = new ArrayList<SoldierBean>();
		alSoldier.clear();
		String strSql = "Select * From Soldier WHERE name LIKE ? ORDER By id ASC";
		try {
			open();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SoldierBean soldier = new SoldierBean();
				soldier.setId(rs.getInt("ID"));
				soldier.setZip(rs.getString("zip"));
				//以下略
				alSoldier.add(soldier);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(); // ログ出力（最低限）
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignored) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ignored) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception ignored) {
			}
		}
		return alSoldier;

	}

	public SoldierBean load(int id) {
		LogUtil.println(this.getClass().getSimpleName() + "#load");
		PreparedStatement pstmt = null;
		String strSql = "SELECT * FROM SOLDIER WHERE id=?";
		SoldierBean soldier = null;
		try {
			open();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				soldier = new SoldierBean();
				soldier.setId(rs.getInt("ID"));
				soldier.setZip(rs.getString("zip"));
				//以下略
				rs.close();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(); // ログ出力（最低限）
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}return soldier;

	}

	public String delete(int id) {
		LogUtil.println(this.getClass().getSimpleName() + "#delete");
		String errMessage = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM SOLDIER WHERE id= ? ";
		try {
			open();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			if (result != 1) {
				errMessage = "CAN'T_DELETE";
			}
		} catch (ClassNotFoundException | SQLException e) {
			errMessage = e.getMessage();
		} finally {
			try {
				pstmt.close();
				close();
			} catch (SQLException e) {
				errMessage = e.getMessage();
			}
		}
		return errMessage;
	}

	public String add(SoldierBean soldier){
		LogUtil.println(this.getClass().getSimpleName()+"#add");
		String errMessage = null;
		PreparedStatement pstmt=null;
		String sql = "INSERT INTO SOLDIER(ID,NAME,ZIP) ";
		try {
			open();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, soldier.getId());
			int result = pstmt.executeUpdate();
			if (result != 1) {
				errMessage = "CAN'T_ADD";
			}
		} catch (ClassNotFoundException | SQLException e) {
			errMessage = e.getMessage();
		} finally {
			try {
				pstmt.close();
				close();
			} catch (SQLException e) {
				errMessage = e.getMessage();
			}
		}return errMessage;
	}

	public String update(SoldierBean soldier){
		LogUtil.println(this.getClass().getSimpleName()+"#update");
		String errMessage = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE SOLDIER SET ID=?,NAME=?, ";
		try {
			open();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, soldier.getId());
			int result = pstmt.executeUpdate();
			if (result != 1) {
				errMessage = "CAN'T_UPDATE";
			}
		} catch (ClassNotFoundException | SQLException e) {
			errMessage = e.getMessage();
		} finally {
			try {
				pstmt.close();
				close();
			} catch (SQLException e) {
				errMessage = e.getMessage();
			}
		}
return errMessage;
	}
}