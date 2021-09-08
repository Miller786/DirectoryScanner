package macie;

import java.io.*;

public class FileOperator {
	 private String FilePath=null;	//file path for the file it's are working on
	 private FileReader MyReader=null;	// FileReader reads text files in the default encoding.
	 private BufferedReader BuffReader=null;
	 private FileWriter MyWriter=null;
	 private BufferedWriter BuffWriter=null;
	 private boolean bIsOpenOnRead=true;		//file mode (true for read, false for write)
	 
	 public FileOperator(boolean bIsOpenOnRead, String FilePath) {	//class constructor
		 this.FilePath=FilePath;
		 this.bIsOpenOnRead=bIsOpenOnRead;
	 }

	 private void OpenFile() {	 //Checks if files are already open,else open them according to their mode (r/w).
		if (bIsOpenOnRead==true) {		//execute only if operator is set to reading mode
			if (BuffReader==null) {		//If the file is not already open, open it, else do nothing.
				try {
					
					MyReader = new FileReader(FilePath);
	       
					// Wrap FileReader in BufferedReader.
					BuffReader = new BufferedReader(MyReader);
				} 
				catch(IOException ex) {
					System.out.println("Error reading file '" + FilePath + "'");                  
					ex.printStackTrace();
				}
			}
		}
		else if (bIsOpenOnRead==false){	//execute only if operator is set to writing mode
			if (BuffWriter==null) {		//If the file is not already open, open it, else do nothing.
				 try {
					 MyWriter = new FileWriter(FilePath);
					 BuffWriter = new BufferedWriter(MyWriter);
				 }
			 
				 catch(IOException ex) {
					 System.out.println("Error reading file '" + FilePath + "'");  
					 ex.printStackTrace();               
				 }
			 }	
		}
	 }
	 	
	 public String ReadLine() { //read a single line from file
		 String line;
		 OpenFile();	//call OpenFile for every line, and let it check if the file needs to be opened or it's already open
		 try {
			line = BuffReader.readLine();
		} catch (IOException e) {
			line=null;
			e.printStackTrace();
		}
	        return line;   
	 }
	 
	 public void WriteLine(String line) {	//write a line in the file
		OpenFile();
		try {
			BuffWriter.write(line);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	 public void CloseFile() {	//close the file, and reset some attributes
		 FilePath=null;
		 if (bIsOpenOnRead==true) {	//execute only if operator is set to reading mode
			 try {
				 BuffReader.close();
				 BuffReader=null;
				 MyReader=null;
			 } catch (IOException e) {
				 e.printStackTrace();
			}
		 }
		 else { 	//execute only if operator is set to writing mode
			 try {
				 BuffWriter.close();
				 MyWriter=null;
				 BuffWriter=null;
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		}
		 
	 }
}