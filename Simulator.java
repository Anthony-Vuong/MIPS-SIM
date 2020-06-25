import java.io.FileNotFoundException;

import java.util.ArrayList;

class Simulator
{
	
	public static void runSim(String fileName)
	{
		// create new fileParser class - see class for details
		//fileParser fp = new fileParser();
		ArrayList<Instructions> instrStr = new ArrayList<Instructions>();	

				
		// create new ArrayList of instructions called instrStr
		try {
			instrStr = fileParser.instructionStrings(fileName, instrStr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ArrayList<String> labelStr = fp.labelStrings(args[0]);

		
		for(Instructions s : instrStr)
		{
			System.out.println(s.toString());
		}
		
	}
	
	public static void main(String[] args)
	{
		runSim(args[0]);
	}
}
