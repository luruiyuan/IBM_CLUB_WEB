package club.istc.validation;

/**
 * ���ע��ʱ�����ѧ�Ÿ�ʽ�Ƿ���ȷ
 */

public class IDCheck {
	
	String id;
	boolean result;
	
//	public void validate(Object arg0){
//		// TODO Auto-generated method stub
//		String fieldName = getFieldName();
//		try{
//		Object value = this.getFieldValue(fieldName,arg0); 
//		this.id=(String)value;
//		if (!checkID()) 
//			addFieldError(fieldName, arg0);
//		}
//		catch(ValidationException exception){
//			addFieldError(fieldName, arg0);
//		}
//	}
	public IDCheck(String id) {
		// TODO Auto-generated constructor stub
		this.id=id.trim();
		result=checkID();
		//System.out.println("id����֤����ǣ�"+result);
	}
	
	private boolean checkID() {
		//�˴���Ҫ�ο�ѧ�Ź���
		try {
			Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
		if (id.length()!=10) {
			return false;
		}
		return true;
	}
	
	public boolean getResult(){
		return result;
	}
}
