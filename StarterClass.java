package macie;

public class StarterClass {
	public static void main(String[] args) {
		if(args.length>=1){
			
			if(args.length==1){	// normal mode
				Reporter.AddHeader(args[0],false);
				FileExplorer f = new FileExplorer(args[0],false);
				f.FindFiles();	
				}
			else if(args[0].equals("-h") && args.length==2){	// heuristic mode
				Reporter.AddHeader(args[1],true);
				FileExplorer f = new FileExplorer(args[1],true);
				f.FindFiles();	
			}
		}
		
		
		else {
			System.out.println("Usage: macie [-h] <path to analyze>");
		}
	}

}
