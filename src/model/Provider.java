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
