package util;

public class CommonUtil {
	
	
	public static String Nullchk(String chk){
		String val = chk;
		
		if(val.equals("")||val == null) 
			val ="";
		return val;
	}
}
