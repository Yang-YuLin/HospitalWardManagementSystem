package geng.model;

public class Login {

	boolean loginSuccess = false;
	String Dno;
	String Dpassword;
	
	public String getDno() {
		return Dno;
	}
	
	public void setDno(String Dno) {
		this.Dno = Dno;
	}
	
	public String getDpassword() {
		return Dpassword;
	}
	
	public void setDpassword(String Dpassword) {
		this.Dpassword = Dpassword;
	}
	
	public boolean getLoginSuccess() {
		return loginSuccess;
	}
	
	public void setLoginSuccess(boolean bo) {
		loginSuccess = bo;
	}
	
}
