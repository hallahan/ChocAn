package reports;

import model.*;
import java.util.Vector;
import java.io.*;

import javax.swing.JOptionPane;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class Report {
	protected String fileName;
	protected SQLiteInterface db = SQLiteInterface.singleton();
	
	public void printFile(Vector<String> everyLine) {
		PrintWriter out = null;
		FileOutputStream fos;
		OutputStreamWriter osw;
		
		try {
			fos = new FileOutputStream(fileName, false);
    		osw = new OutputStreamWriter(fos,"UTF-8");
    		out = new PrintWriter(osw, false);	//false so we don't automatically flush
    		
    		for (String eachLine : everyLine) {
    			out.println(eachLine);
    		}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "FileWriter Error: unable to write to file!", "I/O Exception", JOptionPane.ERROR_MESSAGE);
		} finally {
			if(out != null)
    			out.close();
		}
	}
	
	protected static String stringPadded(String str, int totalLength) {
		int len = str.length();
		if (len > totalLength) {
			return str;
		}
		if (len == totalLength)
			return str+ " ";
		
		int padLen = totalLength - len;
		String pad = "";
		for (int i=0; i <= padLen; ++i) {
			pad += " ";
		}
		
		return str + pad;
	}
	
	public Report() {
		String fileNamePrefix = this.getClass().getName();
		String fileNameMiddle = fileTimestamp();
		String fileNameSuffix = ".txt";
		fileName = fileNamePrefix + fileNameMiddle + fileNameSuffix;
	}
	
	private String fileTimestamp() {
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy_HH.mm.ss");
		Date date = new Date();
		return df.format(date);
	}
	
	
	
}

