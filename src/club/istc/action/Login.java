package club.istc.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * ��¼��
 * ͨ��������û��������룬��ȡ���û���Ϣ������session��
 * ��session�������վ�Ĺ�����ȫ�̴��ڣ������û����ڵ�¼״̬
 * �κ��漰���ݿ�Ĳ�������Ҫ����session
 */

public class Login extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private Map<String, Object> session;
	// �û���¼ 
	
	public Login() {
		// TODO Auto-generated constructor stub
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
		System.out.println(session.get("info"));
	}
	
	public String execute() {
		//ͨ���û����������ȡMember�������Ϣ
		//������Ƕ�����ݿ���ش����Լ���Ƿ�ɹ��������ݿ�
		//ͨ���û����������ȡMember�������Ϣ
		//����session	
//		try {
//			curPerson=new Func_for_control().getMember(""+id, password, ""+id);
//			if (curPerson==null) {
//				//��¼ʧ�ܣ��ض�����ԭҳ�棬����һ��session��ʾ��¼ʧ��
//				session.put("faulttype", "NoPerson");
//				return ERROR;
//			}
//			else {
//				//��¼�ɹ�����ǰ��ͨ��memberInfo���key���ɻ��session�������䱣�棬��������ʱԭ�����ͻ���
//				session.put("memberInfo", curPerson);
//				return SUCCESS;
//			}
//		}
//		catch (IllegalAccessException e) {
//			// TODO: handle exception
//			session.put("faulttype", "Illegal");
//			return "illegal";
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			session.put("faulttype", "Unknown");
//			return ERROR;
//		}
		if ("2141601033".equals(id)) {
			if ("456".equals(password)) {
				return SUCCESS;
			}
		}
		session.put("faulttype", "������û�����ƥ�䣡");
		return "input";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
