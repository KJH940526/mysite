package com.bitacademy.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	public UserVo findNo(Long userVoNo) {
		System.out.println("findNo");
		System.out.println("findNo userVoNo : " + userVoNo);
		UserVo userVo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql =
					" select no, name, email, gender" +
					"   from user" +
					"  where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, userVoNo);

			// 5. sql문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String gender = rs.getString(4);

				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
				userVo.setEmail(email);
				userVo.setGender(gender);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 3. 자원정리
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userVo;
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo userVo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = " select no, name" + " from user" + " where email=? and password=?";
			pstmt = conn.prepareStatement(sql);

			// 4.바인딩
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());

			// 5. sql문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기! 로그인 이기 떄문에! 여기서는 안나오거나 한개 나옴!!
			// 쿼리를 실행하고!! 데이터가 있어야지 들어감! 데이터가 없으면!! 위에서 null로 설정했기 때문에
			// return userVo가 null이 나온다!!
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				userVo = new UserVo();

				userVo.setNo(no);
				userVo.setName(name);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 3. 자원정리
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userVo;
	}
	
	public boolean update(UserVo userVo) {
		System.out.println("update 입니다!");
		System.out.println("update userVo : "+ userVo);
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = " update user set name=?, password=? , gender=? where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getGender());
			pstmt.setLong(4, userVo.getNo());
			// now는 일단 셋을 하지말아봐!~!

			// 5. sql문 실행
			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 3. 자원정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	};
	

	public boolean insert(UserVo userVo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = " insert" + "   into user" + " values(null, ?, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getEmail());
			pstmt.setString(3, userVo.getPassword());
			pstmt.setString(4, userVo.getGender());
			// now는 일단 셋을 하지말아봐!~!

			// 5. sql문 실행
			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 3. 자원정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.0.114:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

}
