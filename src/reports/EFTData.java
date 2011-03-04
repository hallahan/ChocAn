package reports;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.*;

public class EFTData extends Report {
	Vector<String> lines;
	
	public EFTData() {
		lines = SQLiteInterface.singleton().generateEFTData();
		printFile(lines);
		JOptionPane.showMessageDialog(null, "The EFT Data has been written \nto the main directory of the application as:\n\n" + fileName);
	}
}
