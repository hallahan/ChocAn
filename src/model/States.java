package model;


public class States {
	public static final String abbrev[] = {
		"AL","AK","AZ","AR","CA","CO","CT","DC","DE","FL",
		"GA","HI","ID","IL","IN","IA","KS","KY","LA",
		"ME","MD","MA","MI","MN","MS","MO","MT","NE",
		"NV","NH","NJ","NM","NY","NC","ND","OH","OK",
		"OR","PA","RI","SC","SD","TN","TX","UT","VT",
		"VA","WA","WV","WI","WY" 
	};
	public static final String full[] = {
		"AL - Alabama",
		"AK - Alaska",
		"AZ - Arizona",
		"AR - Arkansas",
		"CA - California",
		"CO - Colorado",
		"CT - Connecticut",
		"DC - Washington D.C.",
		"DE - Deleware",
		"FL - Florida",
		"GA - Georgia",
		"HI - Hawaii",
		"ID - Idaho",
		"IL - Illinois",
		"IN - Indiana",
		"IA - Iowa",
		"KS - Kansas",
		"KY - Kentucky",
		"LA - Louisiana",
		"ME - Maine",
		"MD - Maryland",
		"MA - Massachusetts",
		"MI - Michigan",
		"MN - Minnesota",
		"MS - Mississippi",
		"MO - Missouri",
		"MT - Montana",
		"NE - Nebraska",
		"NV - Nevada",
		"NH - New Hampshire",
		"NJ - New Jersey",
		"NM - New Mexico",
		"NY - New York",
		"NC - North Carolina",
		"ND - North Dakota",
		"OH - Ohio",
		"OK - Oklahoma",
		"OR - Oregon",
		"PA - Pennsylvania",
		"RI - Rhode Island",
		"SC - South Carolina",
		"SD - South Dakota",
		"TN - Tennessee",
		"TX - Texas",
		"UT - Utah",
		"VT - Vermont",
		"VA - Virginia",
		"WA - Washington",
		"WV - West Virginia",
		"WI - Wisconsin",
		"WY - Wyoming"
	};
	
	public static int getIndex(String state) {
		for (int i=0; i < abbrev.length; ++ i) {
			if (abbrev[i].equals(state))
				return i;
		}
		for (int i=0; i < full.length; ++ i) {
			if (full[i].equals(state))
				return i;
		}
		return 0;
	}
	
	public static void test() {
		int res = -1;
		
		res = getIndex("AL");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res] + "\n");
		
		res = getIndex("Alaska");
		res = 1;
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("AZ");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("AR");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("CA");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("CO");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("CT");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("DC");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("DE");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("FL");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("GA");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("NV");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("IL");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
		
		res = getIndex("TN");
		System.out.println(res);
		System.out.println(full[res]);
		System.out.println(abbrev[res]+ "\n");
	}
	
	public static void main(String[] args) {
		test();
	}
}
//public class States {
//	public String[] abbrev = new String[50];
//	public String[] name = new String[50];
//	
//
//	//format = "AK - Alaska"
//	public String[] abbrevAndName() {
//		String allStates[] = new String[50];
//		for (int i=0; i<50; ++i) {
//			allStates[i] = abbrev[i] + " - " + name[i];
//		}
//		return allStates;
//	}
//}