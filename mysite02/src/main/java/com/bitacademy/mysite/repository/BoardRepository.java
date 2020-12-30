package com.bitacademy.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.mysite.vo.BoardVo;

public class BoardRepository {
  
	//
	public BoardVo findByNo(Long boardNo) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = " select title, contents, no, user_no, hit, group_no, depth, order_no" + " from board b" + " where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, boardNo);

			// 5. sql문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			if (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long no = rs.getLong(3);
				Long userNo = rs.getLong(4);
				Long hit = rs.getLong(5);
				Long groupNo = rs.getLong(6);
				int depth = rs.getInt(7);
				int orderNo = rs.getInt(8);

				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setNo(no);
				vo.setUserNo(userNo);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setDepth(depth);
				vo.setOrderNo(orderNo);
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
		return vo;
	}
	
	//글쓰기
	public boolean insert(BoardVo boardVo) {
		System.out.println("BoardRepository Insert: " + boardVo);
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// --   					no  , title, contents,date, h, gno,ono,depth,userno 
			// insert into board values(null, '제목1','콘텐츠1',now(), 1, 1,  1,   1,    1);
			String sql = "insert into board values(null, ?, ?,now(), ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setLong(3, boardVo.getHit());
			pstmt.setLong(4, boardVo.getGroupNo());
			pstmt.setInt(5, boardVo.getOrderNo());
			pstmt.setInt(6, boardVo.getDepth());
			pstmt.setLong(7, boardVo.getUserNo());
			
			System.out.println("======================================");
			System.out.println("BoardRepository Insert: 바인딩 후 " +pstmt);
			
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
	
	// 글삭제
	public boolean delete(BoardVo boardVo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql =
					" delete" +
					" from board" +
					" where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩
			pstmt.setLong(1, boardVo.getNo());
			
			// 5. sql문 실행
			System.out.println(pstmt.executeUpdate());
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 3. 자원정리
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	//글보기				//글 수를 받아고?
	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//글수를 나누고 바인딩 해줘야한다?
		try {
			conn = getConnection();

			String sql = // username
					"  select a.no, a.title, date_format(a.req_date, '%Y-%m-%d') as req_date, a.hit, b.name, a.group_no, a.order_no, a.depth, a.user_no"
							+ "  from board a, user b" + "  where a.user_no = b.no"
							+ "  order by a.group_no desc, a.order_no asc";
//					+ "  limit 10";

			// 리미트는 바인등을 해주자!!

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String reqDate = rs.getString(3);
				Long hit = rs.getLong(4);
				String userName = rs.getString(5);
				Long groupNo = rs.getLong(6);
				int orderNo = rs.getInt(7);
				int depth = rs.getInt(8);
				Long userNo = rs.getLong(9);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setReqDate(reqDate);
				vo.setHit(hit);
				vo.setUserName(userName);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);

				list.add(vo);
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

		return list;
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