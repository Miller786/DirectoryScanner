package macie;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

public class Reporter {
	private static final FileOperator FileReader = new FileOperator(false,"output.txt");
	
	public static void AddHeader(String FilePath, Boolean mode) {
		FileReader.WriteLine("Scan results:\n");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyy");  
		DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		FileReader.WriteLine(df.format(now)+" @ " +tf.format(now));
		FileReader.WriteLine("\nRoot directory: "+FilePath);
		if(mode==true) {
			FileReader.WriteLine("\nScan mode: Heuristic\n\n");
		}
		else {
			FileReader.WriteLine("\nScan mode: Normal\n\n");
		}
	}
	public static void PrintFileName(String FilePath) {
	
		FileReader.WriteLine(FilePath+"\n");
	}
	public static void PrintEntry(String Entry) {

		FileReader.WriteLine("\t"+Entry+"\n");
	}
	public static void PrintReport(int EntriesFound) {
		FileReader.WriteLine("\n\nTotal entries found: " +EntriesFound);
		FileReader.CloseFile();
	}
	
	

}
