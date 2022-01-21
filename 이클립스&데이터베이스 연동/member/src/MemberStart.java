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
				//������ ���̽����� ȸ������ ��ü�� �����ؼ� 
				//������
				memberAllList();
			}else if(menu ==2) {
				memberInsert();
			}else if(menu == 3) {
				memberEdit();
			}else if(menu == 4) {
				memberDel();
			}else if(menu == 5) {
				System.exit(0); //���α׷��� �����Ų��.
			}else {
				System.out.println("�޴��� �߸� �Է� �Ͽ����ϴ�.");
			}
		}
		
	}
	//ȸ������ �����
	public void memberDel() {
		//������ ȸ����ȣ
		System.out.println("������ ȸ����ȣ->");
		int num = Integer.parseInt(scan.nextLine());
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberDelete(num);
		
		if(result>0) {
			System.out.println(num+"�� ȸ���� ���� �Ǿ����ϴ�.");
		} else {
			System.out.println(num+"�� ȸ�� ���� �����Ͽ����ϴ�.");
		}
	}
	
	// ȸ�� ����ϱ�
	public void memberInsert(){
		// ����� ȸ�� ������ �Է¹޾Ƽ� DTO��ü���ٰ� setter�� �Ѵ�.	
		MemberDTO dto = new MemberDTO();
		
		System.err.print("�̸� -> ");
		dto.setUsername(scan.nextLine());

		System.err.print("����ó -> ");
		dto.setTel(scan.nextLine());
		
		System.err.print("�̸��� -> ");
		dto.setEmail(scan.nextLine());
		
		System.err.print("�������(ex: 1900-10-10 -> ");
		dto.setBirth(scan.nextLine());
		
		System.err.print("���� (M, F)-> ");
		dto.setGender(scan.nextLine());
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberInsert(dto);
		
		if(result>0) {//ȸ�����
			System.out.println(dto.getUsername()+"�� ������ ��ϵǾ����ϴ�.");
		}else {//��� ����
			System.out.println("ȸ�� ����� �����Ͽ����ϴ�.");
		}
		
	}

	
	//��ü ȸ�� ��� �����ϱ�
	public void memberAllList() {
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> list = dao.memberList();
		// 					   size() : ��ü�� ��
	for(int idx = 0; idx < list.size(); idx++) {
		 oneMemberOutput(list.get(idx));	 
		}
	}
	
	public void memberEdit() {
        // ������ ȸ����ȣ�� �Է¹ް�
        //����ó, �̸��� Ȯ���� �� �� 
        //����ó �Ǵ� �̸����� �Է��� ���� �� 
        // ������Ʈ

        MemberDTO dto = new MemberDTO();
        System.out.print("������ ȸ����ȣ->");
        dto.setNum(Integer.parseInt(scan.nextLine()));

        System.out.println("������ �׸��� �Է�(1. ����ó, 2. �̸��� -> ");
        String que = scan.nextLine();

        if(que.equals("1")) {//����ó(tel)
            System.out.println("������ ����ó ->");
            dto.setTel(scan.nextLine());

        }else if(que.equals("2")) {//�̸���(email)
            System.out.println("������ �̸��� ->");
            dto.setEmail(scan.nextLine());
        }

        //������Ʈ
        MemberDAO dao = new MemberDAO();
        int cnt = dao.memberUpdate(que, dto);

        //���
        if(cnt>0) {
            System.out.println("ȸ�������� �����Ǿ����ϴ�.");
        }else {
            System.out.println("ȸ������ ���� �����߽��ϴ�.");
        }

    } 

	
	
	
	public void oneMemberOutput(MemberDTO dto) {
		System.out.printf("%5d\t%10s\t%20s\t%20s\t%10s\t%5s\t%20s\n",
                dto.getNum(), dto.getUsername(), dto.getTel(), dto.getEmail(),
                dto.getBirth(), dto.getGender(), dto.getWritedate());
	}
	
	public void menuOutput() {
		System.out.print("[�޴�]1. ȸ�� ���, 2. ȸ�� ���, "
				+ "3. ȸ�� ����" + "4. ȸ�� ����" + "5. ����?");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MemberStart().start();
	}

	//public void memberList() {
	//	MemberDAO dao = new MemberDAO();
	//	dao.memberList2(); //��� ȸ�� ���
	//}
}