package model;

public class ProviderType {
	private static String EMPTY = "";
	
	public int		providertype_id	= 0;
	public String	name			= EMPTY;
	public String	desc			= EMPTY;
	
	
	public void print() {
		System.out.println("-------PROVIDERTYPE ROW-------");
		System.out.println(providertype_id);
		System.out.println(name);
		System.out.println(desc);
	}
}
