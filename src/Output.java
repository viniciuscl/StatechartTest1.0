import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Output {

	//The table to which we are printing the output
	private JTable table;

	Output(JTable table) {
		this.table = table;
	}
			
	//Prints a row in the table
	public void printRow(String state, String transition, String path, String expected) {
		DefaultTableModel myModel = (DefaultTableModel) table.getModel();
		myModel.addRow(new Object[]{state, transition, path, expected});
	}
	
	//write to the file used by SPMF
	public void writeSPMFToFile(String filePath, Set<String> lines) {
		try
		{
		    FileWriter writer = new FileWriter(filePath);
			for (String line : lines) {
				writer.append(line+"\n");
			}
				
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		}
	}
	
	//write to csv file
	public void writeCsvToFile(String filePath, Set<String> csvContent) {
		try {
			if(!filePath.contains(".csv")) {
				filePath += ".csv";
			}
						
		    FileWriter writer = new FileWriter(filePath);
		    writer.append("State,Transition,Test Path,Expected State\n");
			for (String line : csvContent) {
				line = line.replace(System.getProperty("line.separator"), "").replace("\n", "").replace("\r", "");
				//System.out.println(line.trim());
				writer.append(line.trim().replace(",", ";")+"\n");
			}
				
		    writer.flush();
		    writer.close();
		} catch(IOException e) {
		     e.printStackTrace();
		}
	}
}
