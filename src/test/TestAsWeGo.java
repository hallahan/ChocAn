package test;
import model.*;

public class TestAsWeGo {


	public static void main(String[] args) {
		SQLiteInterface db = new SQLiteInterface();
		db.testFetchStates();
	}

}
