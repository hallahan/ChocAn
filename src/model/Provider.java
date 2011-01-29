package model;

public class Provider {
	private static String EMPTY = "";
	
	public int		provider_id			= 0;
	public String	name				= EMPTY;
	public int 		providertype_id		= 0;
	public String	address				= EMPTY;
	public String	city				= EMPTY;
	public String	state				= EMPTY;
	public String	zip					= EMPTY;
	
	//refers to the corresponding provider type
	//not implemented
	//public ProviderType provider_type	= null;
	
	public void print() {
		System.out.println("-------PROVIDER ROW-------");
		System.out.println(provider_id);
		System.out.println(name);
		System.out.println(address);
		System.out.println(city);
		System.out.println(state);
		System.out.println(zip);
	}
}
