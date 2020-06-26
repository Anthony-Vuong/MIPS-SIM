import java.io.FileNotFoundException;

import java.util.ArrayList;

class Simulator
{
	
	public static void runSim(String fileName)
	{
		// create new ArrayList of instructions called instrStr
		ArrayList<Instructions> instrStr = new ArrayList<Instructions>();	
		ArrayList<Instructions> labelStr = new ArrayList<Instructions>();	


		try 
		{
			instrStr = fileParser.instructionStrings(fileName, instrStr);
			labelStr = fileParser.labelStrings(fileName, labelStr);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		
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
