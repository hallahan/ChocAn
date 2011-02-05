package model;

public class Member {
	private static String EMPTY = "";
	
	//Every Member retrieved from database should 
	//have a member_id that is not 0;
	public int	member_id		= 0;
	public int	active_status	= 1; //0 == inactive, 1 == active
	public String	first		= EMPTY;
	public String	middle		= EMPTY;
	public String	last		= EMPTY;
	public String	address		= EMPTY;
	public String	city		= EMPTY;
	public String	state		= "AL";
	public String	zip			= EMPTY;
	
	public void print() {
		System.out.println("-------MEMBER ROW-------");
		System.out.println(member_id);
		if (active_status == 1) System.out.println("active");
		else System.out.println("inactive");
		System.out.println(first);
		System.out.println(middle);
		System.out.println(last);
		System.out.println(address);
		System.out.println(city);
		System.out.println(state);
		System.out.println(zip);
	}
	
	private boolean isProperZipFormat(String input) {
		int length = input.length();
		char c;
		int i;
		
		if (length == 5 || length == 10) {
			if (length == 10 && input.charAt(5) != '-')
				return false;
			
			if (length == 5) {
				for(i=0;i<length;++i) {
					c = input.charAt(i);
					if (c < '0' || c > '9')
						return false;
				}
			} else {  //length would have to be 10
				for(i=0;i<length;++i) {
					if (i == 5) continue;
					c = input.charAt(i);
					if (c < '0' || c > '9')
						return false;
				}
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	public boolean setZip(String inZip) {
		if (isProperZipFormat(inZip) == true) {
			zip = inZip;
			return true;
		} else {
			return false;
		}
	}
}
