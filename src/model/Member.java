package model;
import java.lang.Integer;


public class Member {
	private Integer	member_id;     //db automatically increments id if supplied with null
	private Integer	active_status; //1 is active, 0 is inactive
	private String	first;
	private String	middle;
	private String	last;
	private String	address;
	private String	city;
	private String	state;
	private String	zip;
	
	public Member(Integer id, Integer as, String f, String m, String l, String a, String c, String s, String z) {
		this.setMember_id(id);
		this.setActive_status(as);
		this.setFirst(f);
		this.setMiddle(m);
		this.setLast(l);
		this.setAddress(a);
		this.setCity(c);
		this.setState(s);
		this.setZip(z);
	}
	
	public Member() {
		this(null, null, null, null, null, null, null, null, null);
	}
	
 	public void print() {
		System.out.println("--------------------");
		System.out.println(member_id.toString());
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
	
	
	
	//setters and getters
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		//we want this to be null if set to null
		this.member_id = member_id;
	}
	public Integer getActive_status() {
		return active_status;
	}	
	public boolean getActive_statusBool() {
		if (active_status == 1)
			return true;
		else
			return false;
	}
	public void setActive_status(Integer active_status) {
		if (active_status == null)
			this.active_status = 1;
		else
			this.active_status = active_status;
	}
	public void setActive_statusBool(boolean tf) {
		if (tf == true)
			active_status = 1;
		else
			active_status = 0;
	}	
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		if (first == null)
			this.first = "";
		else
			this.first = first;
	}
	public String getMiddle() {
		return middle;
	}
	public void setMiddle(String middle) {
		if (middle == null)
			this.middle = "";
		else
			this.middle = middle;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		if (this.last == null)
			this.last = "";
		else
			this.last = last;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		if (address == null)
			this.address = "";
		else
			this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		if (city == null)
			this.city = "";
		else
			this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		if (state == null)
			this.state = "";
		else
			this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		if (zip == null)
			this.zip = "";
		else
			this.zip = zip;
	}

}
