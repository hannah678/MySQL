import java.util.List;
import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberStart {
	Scanner scan = new Scanner(System.in);
	
	public void start() {
		while(true) {
			menuOutput();
			int menu = Integer.parseInt(scan.nextLine());
			if(menu == 1) {
				//데이터 베이스에서 회원정보 전체를 선택해서 
				//목록출력
				memberAllList();
			}else if(menu ==2) {
				memberInsert();
			}else if(menu == 3) {
				memberEdit();
			}else if(menu == 4) {
				memberDel();
			}else if(menu == 5) {
				System.exit(0); //프로그램을 종료시킨다.
			}else {
				System.out.println("메뉴를 잘못 입력 하였습니다.");
			}
		}
		
	}
	//회원정보 지우기
	public void memberDel() {
		//삭제할 회원번호
		System.out.println("삭제할 회원번호->");
		int num = Integer.parseInt(scan.nextLine());
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberDelete(num);
		
		if(result>0) {
			System.out.println(num+"번 회원이 삭제 되었습니다.");
		} else {
			System.out.println(num+"번 회원 삭제 실패하였습니다.");
		}
	}
	
	// 회원 등록하기
	public void memberInsert(){
		// 등록할 회원 정보를 입력받아서 DTO객체에다가 setter를 한다.	
		MemberDTO dto = new MemberDTO();
		
		System.err.print("이름 -> ");
		dto.setUsername(scan.nextLine());

		System.err.print("연락처 -> ");
		dto.setTel(scan.nextLine());
		
		System.err.print("이메일 -> ");
		dto.setEmail(scan.nextLine());
		
		System.err.print("생년월일(ex: 1900-10-10 -> ");
		dto.setBirth(scan.nextLine());
		
		System.err.print("성별 (M, F)-> ");
		dto.setGender(scan.nextLine());
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberInsert(dto);
		
		if(result>0) {//회원등록
			System.out.println(dto.getUsername()+"의 정보가 등록되었습니다.");
		}else {//등록 실패
			System.out.println("회원 등록이 실패하였습니다.");
		}
		
	}

	
	//전체 회원 목록 선택하기
	public void memberAllList() {
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> list = dao.memberList();
		// 					   size() : 객체의 수
	for(int idx = 0; idx < list.size(); idx++) {
		 oneMemberOutput(list.get(idx));	 
		}
	}
	
	public void memberEdit() {
        // 수정일 회원번호를 입력받고
        //연락처, 이메일 확인을 한 후 
        //연락처 또는 이메일을 입력을 받은 후 
        // 업데이트

        MemberDTO dto = new MemberDTO();
        System.out.print("수정할 회원번호->");
        dto.setNum(Integer.parseInt(scan.nextLine()));

        System.out.println("수정할 항목을 입력(1. 연락처, 2. 이메일 -> ");
        String que = scan.nextLine();

        if(que.equals("1")) {//연락처(tel)
            System.out.println("수정할 연락처 ->");
            dto.setTel(scan.nextLine());

        }else if(que.equals("2")) {//이메일(email)
            System.out.println("수정할 이메일 ->");
            dto.setEmail(scan.nextLine());
        }

        //업데이트
        MemberDAO dao = new MemberDAO();
        int cnt = dao.memberUpdate(que, dto);

        //결과
        if(cnt>0) {
            System.out.println("회원정보가 수정되었습니다.");
        }else {
            System.out.println("회원정보 수정 실패했습니다.");
        }

    } 

	
	
	
	public void oneMemberOutput(MemberDTO dto) {
		System.out.printf("%5d\t%10s\t%20s\t%20s\t%10s\t%5s\t%20s\n",
                dto.getNum(), dto.getUsername(), dto.getTel(), dto.getEmail(),
                dto.getBirth(), dto.getGender(), dto.getWritedate());
	}
	
	public void menuOutput() {
		System.out.print("[메뉴]1. 회원 목록, 2. 회원 등록, "
				+ "3. 회원 수정" + "4. 회원 삭제" + "5. 종료?");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MemberStart().start();
	}

	//public void memberList() {
	//	MemberDAO dao = new MemberDAO();
	//	dao.memberList2(); //모든 회원 출력
	//}
}