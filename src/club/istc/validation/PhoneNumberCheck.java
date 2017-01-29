package club.istc.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * �ֻ�����Ϸ��Լ��
 */

public class PhoneNumberCheck {
	
	String phoneNumber;
	boolean result;
	
	public PhoneNumberCheck(String phonenumber) {
		// TODO Auto-generated constructor stub
		this.phoneNumber=phonenumber.trim();
		result=checkPhonenumber();
		//System.out.println("�ֻ��ż�����ǣ�"+false);
		
	}
	
	private boolean checkPhonenumber() {
		try {
			Long.parseLong(phoneNumber);
			return isChinaPhoneLegal(phoneNumber) || isHKPhoneLegal(phoneNumber);  
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
	}
	
    private static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException { 
        /** 
         * ��½�ֻ�����11λ����ƥ���ʽ��ǰ��λ�̶���ʽ+��8λ������ 
         * �˷�����ǰ��λ��ʽ�У� 
         * 13+������ 
         * 15+��4�������� 
         * 18+��1��4�������� 
         * 17+��9�������� 
         * 147 
         */  
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
  

    private static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {  
        /** 
         * ����ֻ�����8λ����5|6|8|9��ͷ+7λ������ 
         */  
        String regExp = "^(5|6|8|9)\\d{7}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }
    
	public boolean getResult(){
		return result;
	}

}
