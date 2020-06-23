import java.io.FileNotFoundException;

import java.util.ArrayList;

class Simulator
{
	public static void main(String[] args) throws FileNotFoundException
	{
		int test = 1;
		// create new fileParser class - see class for details
		//fileParser fp = new fileParser();
				
		// create new ArrayList of instructions called instrStr
		ArrayList<Instructions> instrStr = fileParser.instructionStrings(args[0]);
		//ArrayList<String> labelStr = fp.labelStrings(args[0]);

		
		for(Instructions s : instrStr)
		{
			System.out.println(test);
			test++;
		}
	}
}
