package org.istc.action;

import com.opensymphony.xwork2.ActionSupport;;

/**
 * ��¼
 * ͨ��������û��������룬��ȡ���û���Ϣ������session��
 * ��session�������վ�Ĺ�����ȫ�̴��ڣ������û����ڵ�¼״̬
 * �κ��漰���ݿ�Ĳ�������Ҫ����session
 */

public class Login extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	// �û���¼ 
	
	public String execute() {
		//ͨ���û����������ȡMember�������Ϣ
		//������Ƕ�����ݿ���ش����Լ���Ƿ�ɹ��������ݿ�
		if ("123".equals(id)) {
			if ("456".equals(password)) {
				return SUCCESS;
			}
		}
		return ERROR;
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
