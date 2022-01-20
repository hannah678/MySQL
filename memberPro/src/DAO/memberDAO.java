package DAO;

import java.util.ArrayList;
import java.util.List;

import DTO.MemberDTO;
import dbConn.MysqlConnection; //패키지가 다르므로 import

public class memberDAO extends MysqlConnection{
	//회원선택
	public List<MemberDTO> memberList(){
		//dto객체를 담을 ArrayList객체 생성
		new ArrayList<MemberDTO>();
		
		try {
			getConn(); // DB연결
			
			//select
			String sql = "select num, username, tel, email, birth, gender, writedate"
					+ "from member order by num";
			
			pstmt = conn.prepareStatement(sql);
		
			//rs
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNum(rs.getInt(1));
				dto.setUsername(rs.getString(2));
				dto.setTel(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setBirth(rs.getString(5));
				dto.setGender(rs.getString(6));
				dto.setWritedate(rs.getString(7));
				
				list.add(dto);

			}
			
			//dto, dto, dto, dto
			
		}catch(Exception e) {
			System.out.println("회원선택 예외 발생!!");
			e.printStackTrace();
		}finally {
			////
		}
		
		return list;
	}
	//회원등록
	//회원수정
	//회원삭제
	

}
