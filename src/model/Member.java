package model;
import java.lang.Integer;


public class Member {
	private static final String EMPTY = "";
	private static Integer defaultActiveStatus = 1;
	
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
		if (this.getActive_status() == true) 
			System.out.println("active");
		else 
			System.out.println("inactive");
		System.out.println(this.getFirst());
		System.out.println(this.getMiddle());
		System.out.println(this.getLast());
		System.out.println(this.getAddress());
		System.out.println(this.getCity());
		System.out.println(this.getState());
		System.out.println(this.getZip());
	}
	
	
	
	//setters and getters
	public Integer getMember_id() {
		if (this.member_id == null)
			return 0;
		else
			return this.member_id;
	}
	public void setMember_id(Integer member_id) {
		//we want this to be null if set to null
		this.member_id = member_id;
	}
	public boolean getActive_status() {
		if (active_status == 1)
			return true;
		else
			return false;
	}	

	public void setActive_status(Integer active_status) {
		if (active_status == null)
			this.active_status = defaultActiveStatus;
		else
			this.active_status = active_status;
	}
	public void setActive_status(boolean tf) {
		if (tf == true)
			active_status = 1;
		else
			active_status = 0;
	}	
	public String getFirst() {
		if (this.first == null)
			return EMPTY;
		else
			return this.first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getMiddle() {
		if (this.middle == null)
			return EMPTY;
		else
			return this.middle;
	}
	public void setMiddle(String middle) {
		this.middle = middle;
	}
	public String getLast() {
		if (this.last == null)
			return EMPTY;
		else
			return this.last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getAddress() {
		if (this.address == null)
			return EMPTY;
		else
			return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		if (this.city == null)
			return EMPTY;
		else
			return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		if (this.state == null)
			return EMPTY;
		else
			return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		if (this.zip == null)
			return EMPTY;
		else
			return this.zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

}
