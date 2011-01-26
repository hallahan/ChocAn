package test;
import model.*;
import java.util.Vector;

public class TestAsWeGo {


	public static void main(String[] args) {
		SQLiteInterface db = new SQLiteInterface();
		db.testFetchStates();
		
		Vector<Member> all;
		all = db.retrieveMemberTable();
		for (Member each : all)
			each.print();
		System.out.println("Samanth's middle name...");
		System.out.println(all.get(7).getMiddle());
		
	}

}
