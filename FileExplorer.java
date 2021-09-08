package macie;
import java.io.File;
import java.util.*;

public class FileExplorer {
	private String MainDirPath;
	private String line;
	private Parser MyParser = new Parser();
	private FileOperator FileReader = null;
	private boolean bIsHMode=false;	//set to true if heuristic mode is on
	ArrayList<String> Entries = new ArrayList<String>();	//list of all the found entries per file
	
	
	public FileExplorer(String MainDirPath, boolean bIsHMode) {
		this.MainDirPath=MainDirPath;
		this.bIsHMode=bIsHMode;
	}
	
	private boolean bIsExtensionSupported(File f) {
		boolean status=false;
		if((f.getPath()).endsWith(".txt"))
			status=true;
		if((f.getPath()).endsWith(".html"))
			status=true;
		return status;
	}
	
    private void RecursiveFileFinder(File[] arr)  
    { 

        // for-each loop for files 
        for (File f : arr)  
        { 
            if(f.isFile()) {
            	if((bIsExtensionSupported(f))||(bIsHMode==true)) {		//Parse if the file is supported or if heuristic mode is on
            		FileReader = new FileOperator(true,f.getPath());
            		System.out.println("Analyzing " + f.getPath() + "...");
            		
            		while ((line=FileReader.ReadLine())!=null) {		//Keep parsing until end of file
            			Entries=MyParser.ParseAll(line);				//Update the local entry list with the one in Parser class
            			
            		}
            		if(Entries.size()>0)	//if something was found
            		{
                		Reporter.PrintFileName(f.getPath());	//print the found path in the output file
                		for (String a : Entries)			//print each element in the list
                		{
                			Reporter.PrintEntry(a);
                		}
                		
            		}
            		System.out.println("Found "+ Entries.size() + " elements.\n");
            		
                	MyParser.ResetEntries();		//Empty the entry list in parser class, prep for new file
            		FileReader.CloseFile();
            	}
            }
            else if(f.isDirectory())  
            {  	
                // recursion for sub-directories 
                RecursiveFileFinder(f.listFiles()); //call RecursiveFileFinder with the array of files in this folder
            } 
        }
    }
    
    public void FindFiles(){

    	// File object 
    	File maindir = new File(MainDirPath); 
    
    	if(maindir.exists() && maindir.isDirectory()) { 
    		File arr[] = maindir.listFiles(); 

    		RecursiveFileFinder(arr);  
    		Reporter.PrintReport(MyParser.GetGlobalFoundEntries());
        	System.out.println("Found " + MyParser.GetGlobalFoundEntries() + " entries across all files analyzed.");
    	}
    	else {
    		System.out.println("The specified folder path doesn't exist");
    	}
    	
   }  
}
