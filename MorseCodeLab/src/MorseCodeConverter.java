/**
 * @author Kenneth Shelton
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	public static MorseCodeTree mct = new MorseCodeTree();
	
	public static void setup()
	{
		
	}
	
	public static String convertToEnglish(String text) {
		if(mct==null)
			setup();
		
		String str = "";
		
		String[] words = text.split("/");
		
		for(int i=0;i<words.length;i++)
		{
			String[] codes = words[i].split(" ");
			for(int j=0;j<codes.length;j++)
			{
				//System.out.println(mct.fetch(codes[j]));
				str+=mct.fetch(codes[j]);
			}
			if(i!=words.length-1)
				str+=" ";
		}
		
		return str;
	}

	public static String convertToEnglish(File selectedFile) throws FileNotFoundException {
		if(mct==null)
			setup();
		
		String str = "";
		
		Scanner scanner = new Scanner(selectedFile);
		while(scanner.hasNextLine())
		{
			str += scanner.nextLine()+"/";
		}
		
		scanner.close();
		return convertToEnglish(str);
	}

	public static String printTree() {
		if(mct==null)
			setup();
		
		ArrayList<String> arr = mct.toArrayList();
		String str = "";
		for(int i=0;i<arr.size();i++)
		{
			str+=arr.get(i);
			if(i-1!=arr.size())
				str+=" ";
		}
		return str;
	}

}
