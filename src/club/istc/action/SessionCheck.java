package club.istc.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class SessionCheck {
	
	
	public SessionCheck(Map<String, Object> session) {
		// TODO Auto-generated constructor stub
		check(session);
		
	}
	
	private boolean check(Map<String, Object> session) {
		//��ð�����ǰ��¼�û���Ϣ��session�������ж�Ȩ��
		try {
			session.get("PersonInfo");
		} catch (NullPointerException e) {
			// TODO: handle exception
			return false;
		}
		//��֤���֣�if����д��֤���룬�漰�������ݿ��е���֤
		if (true) {
			return true;
		} else {
			return false;
		}
	}

}
