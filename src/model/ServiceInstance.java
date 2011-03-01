package model;

public class ServiceInstance {
	private static String EMPTY = " ";
	
	public int	instance_id		= 0;
	public int	member_id		= 0;
	public int	service_id		= 0;
	public int	provider_id		= 0;
	private String	date_provided= EMPTY;
	private String	time_stamp	= EMPTY;
	public String	comments	= EMPTY;
	
	//properly formatted for use as display (in GUI)
	public String getDate_provided() {
		return date_provided;
	}
	//properly formatted for input into the database
	public String getDate_providedDB() { 
		String month = date_provided.substring(0,2);
		String day = date_provided.substring(3,5);
		String year = date_provided.substring(6,10);
		return year + "-" + month + "-" + day;
	}
    //uses DateAndTime utility class to insure that that date input is correct
	public void setDate_provided(String date_provided) {
		this.date_provided = DateAndTime.formatDate(date_provided);
	}
	//takes the date output string from the database and properly formats it for display
	public void setDate_providedDB(String dateDB) {
		String year = dateDB.substring(0,4);
		String month = dateDB.substring(5,7);
		String day = dateDB.substring(8,10);
		date_provided = month + "-" + day + "-" + year;
//		System.out.println(date_provided);
	}
	//properly formatted for use as display (in GUI)
	public String getTime_stamp() {
		return time_stamp;
	}
	//properly formatted for input into DB
	public String getTime_stampDB() {
		String month = time_stamp.substring(0,2);
		String day = time_stamp.substring(3,5);
		String year = time_stamp.substring(6,10);
		String hour = time_stamp.substring(11,13);
		String min = time_stamp.substring(14,16);
		String sec = time_stamp.substring(17,19);
		
		return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
	}
	//uses DateAndTime utility class to insure that the timestamp input is correct
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = DateAndTime.formatTimestamp(time_stamp);
	}
	//takes the timestamp output from the database and properly formats it for display
	public void setTime_stampDB(String timeDB) {
		String year = timeDB.substring(0,4);
		String month = timeDB.substring(5,7);
		String day = timeDB.substring(8,10);
		String hour = timeDB.substring(11,13);
		String min = timeDB.substring(14,16);
		String sec = timeDB.substring(17,19);
		this.time_stamp = month + "-" + day + "-" + year + " " + hour + ":" + min + ":" + sec;
		System.out.println(this.time_stamp);
	}

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
