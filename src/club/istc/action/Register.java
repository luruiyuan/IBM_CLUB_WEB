package club.istc.action;


import java.util.Map;

import club.istc.bean.*;
import club.istc.validation.IDCheck;
import club.istc.validation.InjectionCheck;
import club.istc.validation.PhoneNumberCheck;
import club.istc.validation.QQCheck;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ע��
 */

public class Register extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String id;
	private int age;
	private String name;
	private String password;
	private String repassword;
	private String QQ;
	private String phoneNumber;
	private String gender;
	private Person curPerson=new Person();
	private Map<String, Object> session;
	// �û���¼ 
	
	public Register() {
		// TODO Auto-generated constructor stub
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
	}
	
	@Override
	public String execute() {
		//�������ݿ�Ĳ���
		
		try {
//			if(new Func_for_control().registerPerson(curPerson)){
				session.put("personInfo", curPerson);
//				return SUCCESS;
//			}
		}
//		catch (IllegalAccessException e) {
//			// TODO: handle exception
//			addFieldError("registerfault", "���ݿ�д��ʧ�ܣ����Ժ����ԡ�");
//		}
		catch (Exception e) {
			// TODO: handle exception
			addFieldError("registerfault", "������δ֪�������Ժ����ԡ�");
			return INPUT;
		}
		
		return SUCCESS;
		//System.out.println("gender is"+gender);

	}
	
	
	@Override
	public void validate(){
		if (id==null || id=="") {
			addFieldError("id", "����������ѧ�ţ�");
		}
		else {
//			if (condition) {
//			addFieldError("id", "����ѧ���Ѿ���ע��������¼�����һ����롣");
//		}
			if (!new IDCheck(id).getResult()) {
				addFieldError("id", "����ѧ�������������鲢�������롣");
			}
			else {
				curPerson.setID(this.id);
			}
		}
		if (password==null || password=="") {
			addFieldError("password", "�������������룡");
		}
		else {
			if (!new InjectionCheck(password).getResult()) {
				addFieldError("password", "�벻Ҫ�������а�����Щ������ţ�* ' ; - + / % #");
			}
			else if (!password.equals(repassword)) {
				addFieldError("repassword", "������������벻һ�£�");
			} else {
				curPerson.setPassword(password);
			}

		}
		if (name==null || name=="") {
			addFieldError("name", "����������������");
		}
		else {
			if (!new InjectionCheck(name).getResult()) {
				addFieldError("name", "��������ȷ��������Ϣ��");
			}
			else {
				curPerson.setName(name);
			}
		}
		if (phoneNumber==null || phoneNumber=="") {
			addFieldError("phoneNumber", "��������������ֻ����Է�����ϵ����");
		}
		else {
			if (!new PhoneNumberCheck(phoneNumber).getResult()) {
				addFieldError("phoneNumber", "��������Ч�Ĵ�½�ֻ����룡");
			}
			else {
				curPerson.setPhoneNumber(phoneNumber);
			}
		}
		if (QQ==null || QQ=="") {
			addFieldError("QQ", "�������������QQ���Է����պ���������������Ա�Ļ�����");
		}
		else {
			if (!new QQCheck(QQ).getResult()) {
				addFieldError("QQ", "��������ȷ��QQ���룡");
			}
			else {
				curPerson.setQQ(this.QQ);
			}
		}
		//������ͨ��ѧ�������¹涨����������ѧ���䲻�õ���14��
		if (age<14 || age>100) {
			addFieldError("age", "�������������Ϣ�������������룡");
		}
		else {
			curPerson.setAge(age);
		}
		if (gender.equals("0")) {
			curPerson.setGender(false);
		}
		session.put("personInfo", curPerson);
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	public String getQQ() {
		return QQ;
	}

	public void setQQ(String QQ) {
		this.QQ = QQ;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
