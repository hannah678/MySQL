package DTO;

public class MemberDTO {
	int num;
	String username;
	String tel;
	String email;
	String birth;
	String gender;
	String writedate;

	public MemberDTO() {}

	//setter
	public void setNum(int num) {
		this.num = num;
	}
	
	//setter
	public void setUsername(String username) {
		this.username = username;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public int getNum() {
		return num;
	}

	public String getUsername() {
		return username;
	}
}
