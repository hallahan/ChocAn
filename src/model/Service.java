package model;

public class Service {
	private static String EMPTY = "";
	
	public int	service_id		= 0;
	public String	name		= EMPTY;
	public String	fee			= EMPTY;

	
	public void print() {
		System.out.println("-------SERVICE ROW-------");
		System.out.println(service_id);
		System.out.println(name);
		System.out.println(fee);
	}
}
