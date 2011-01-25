package model;
import java.lang.Integer;

public class Member {
	public Integer	member_id;
	public boolean	active_status;
	public String	first;
	public String	middle;
	public String	last;
	public String	address;
	public String	city;
	public String	state;
	public String	zip;
	
	public void print() {
		System.out.println("--------------------");
		System.out.println(member_id.toString());
		if (active_status == true) System.out.println("active");
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
