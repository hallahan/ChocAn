package test;
import model.*;
import java.util.Vector;

public class TestAsWeGo {

	public static void printVector(Vector<Member> input) {
		for (Member each : input)
			each.print();
	}
	
	public static void printProvider(Vector<Provider> input) {
		for (Provider each : input)
			each.print();
	}
	
	public static void main(String[] args) {
//		SQLiteInterface db = new SQLiteInterface();
//		
//		Vector<Member> all;
//		all = db.retrieveMemberTable();
//		printVector(all);
//		
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
//		
//		
//		SQLiteInterface db = new SQLiteInterface();
//		Vector<Member> a = db.retrieveMemberTableSorted("rey", "last", true);
//		printVector(a);
		
		SQLiteInterface db = new SQLiteInterface();
		Provider p = new Provider();
		p.name = "Jack Talisman Inner Peace";
		p.address = "666 Satan's Pit";
		p.city = "Hell";
		p.state = "AR";
		p.zip = "42313";
		db.addProvider(p);
		printProvider(db.retrieveProviderTable());
	}

}
