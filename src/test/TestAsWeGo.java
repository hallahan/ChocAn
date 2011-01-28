package test;
import model.*;
import java.util.Vector;

public class TestAsWeGo {

	public static void printVector(Vector<Member> input) {
		for (Member each : input)
			each.print();
	}
	
	public static void main(String[] args) {
		SQLiteInterface db = new SQLiteInterface();
		
		Vector<Member> all;
		all = db.retrieveMemberTable();
		printVector(all);
		
//		System.out.println("test adding a member...");
//		Member nm = new Member();
//		nm.first = "Derrick";
//		nm.middle = "F.";
//		nm.last = "Kennedy";
//		nm.address = "99 E. Infinate Loop";
//		nm.city = "Cupertino";
//		nm.state = "CA";
//		nm.zip = "95205";
//		db.addMember(nm);
//		all = null;
//		all = db.retrieveMemberTable();
//		printVector(all);
//		
//		System.out.println("delete member 3...");
//		db.deleteMember(3);
//		all = null;
//		all = db.retrieveMemberTable();
//		printVector(all);
//		
//		System.out.println("update member 5...");
//		Member um = db.retrieveMember(5);
//		um.first = "UpdatedMember5";
//		db.updateMember(um);
//		all = db.retrieveMemberTable();
//		printVector(all);
//		
//		System.out.println("Testing retrieve member with id that doesn't exit...");
//		Member aMem = db.retrieveMember(234);
//		if (aMem != null)
//			aMem.print();
//
//		System.out.println("deleting member #8...");
//		db.deleteMember(8);
//		all = db.retrieveMemberTable();
//		printVector(all);
		
	}

}
