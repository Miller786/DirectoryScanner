package macie; 
import java.util.*;
import java.util.regex.*; 


public class Parser
{
	//Email regular expression from RFC 2822 standard (2001)
	//source: https://gist.github.com/gregseth/5582254

	private final String EmailRegex="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z](?:[a-z]*[a-z])?";
	
	private final String IPRegex="(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])[.](25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])[.](25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])[.](25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])"; // (0-255).(0-255).(0-255).(0-255)
	private final String PhoneRegex= "([+][0-9]{1,3}(|[ ])[0-9]{3,13})|" //+prefix Number or +prefixNumber
			+ "([(][+][0-9]{1,3}[)](|[ ])[0-9]{3,13})"; //(+prefix)number or (+prefix) number
	private final Pattern EmailPt = Pattern.compile(EmailRegex);
	private final Pattern IPPt = Pattern.compile(IPRegex);
	private final Pattern PhonePt= Pattern.compile(PhoneRegex);
	private int GlobalCounter=0;	//Keeps track of the total number of entries found in all files
	private ArrayList<String> Entries = new ArrayList<String>(); //Local list that keeps track of entries found in the current file
	
	
	public ArrayList<String> ParseAll (String InputString)
	{
		//Entries.clear();	//Empty the Entries string
		ParseString(InputString,EmailPt);
		ParseString(InputString,IPPt);
		ParseString(InputString,PhonePt);
		return Entries;
	}
	
	public int GetGlobalFoundEntries(){
		return GlobalCounter;
	}
	public void ResetEntries()	//empty the entries list
	{
		Entries.clear();
	}

	private void ParseString (String InputString,Pattern pt) {
		
    	Matcher mt = pt.matcher(InputString);
    	while(mt.find()){
    		System.out.println(mt.group());		//Print the entry in console
    		Entries.add(mt.group());			//Add the entry to the list
    		//Reporter.PrintEntry(mt.group());
    		GlobalCounter++;	//increase the counter for entries
    		}
    	}
}

	


	
	
	