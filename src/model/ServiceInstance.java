package model;

public class ServiceInstance {
	private static String EMPTY = "";
	
	public int	instance_id		= 0;
	public int	member_id		= 0;
	public int	service_id		= 0;
	public int	provider_id		= 0;
	public String	date_provided= EMPTY;
	public String	time_stamp	= EMPTY;
	public String	comments	= EMPTY;
	
	public void print() {
		System.out.println("-------SERVICE INSTANCE ROW-------");
		System.out.println(instance_id);
		System.out.println(member_id);
		System.out.println(service_id);
		System.out.println(provider_id);
		System.out.println(date_provided);
		System.out.println(time_stamp);
		System.out.println(comments);
	}
}
