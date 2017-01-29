package club.istc.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * �ǳ�������session����
 */

@SuppressWarnings("serial")
public class Logout extends ActionSupport {
	
	Map<String, Object> session;
	public Logout(){
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
	}
	
	public String execute(){   
	    System.out.println("�˳�ϵͳ"); 
	    //��¼����ζ��session�����ڵ�ǰҳ�棬�˳�ʱ����session
	    session.clear();
	    return "exit";
	}
}
