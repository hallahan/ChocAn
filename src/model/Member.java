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
	public String	state		= EMPTY;
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
}
