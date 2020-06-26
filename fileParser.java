import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



/*
 * File parsing class: contains methods to parse text files line by line into MIPS instructions
 */
public class fileParser {
	
	public static int lineCount = 0;
	public static int instrCount = 1;

	
	/*
	 * Method 	- parses a text file by line by line
	 * Type 	- public
	 * Input 	- a string object of user input file; 
	 * 			  specified on command line: args[0]
	 * Output 	- an ArrayList of INSTRUCTIONS
	 * Errors	- FileNotFoundException
	 */
	public static ArrayList<Instructions> instructionStrings(String inputFile, ArrayList<Instructions> instr) throws FileNotFoundException
	{

		File iF = new File(inputFile);
		
		instr = scanFile(iF, instr);
		
		return instr;
	}
	
	/*
	 * Method 	- parses a text file by line by line
	 * Type 	- public
	 * Input 	- a string object of user input file; 
	 * 			  specified on command line: args[0]
	 * Output 	- an ArrayList of LABELS
	 * Errors	- FileNotFoundException
	 */
	public static ArrayList<Instructions> labelStrings(String inputFile, ArrayList<Instructions> labels) throws FileNotFoundException
	{

		File iF = new File(inputFile);
		
		labels = scanFile(iF, labels);
		
		return labels;
	}
	
	/*
	 * Method 	- scans input text file line by line
	 * Type 	- private
	 * Input 	- a File object of user input file 
	 * Output 	- n/a
	 * Errors	- FileNotFoundException
	 */
	private static ArrayList<Instructions> scanFile(File inputFile, ArrayList<Instructions> instr) throws FileNotFoundException
	{
		Scanner scan = new Scanner(inputFile);
		//int lineCount = 0, instrCount = 1;
		while(scan.hasNext())
		{
			instr = parseFile(scan.nextLine(), instr);
		}
		scan.close();
		
		return instr;
	}
	
	/*
	 * Method 	- parses valid instructions line by line
	 * Type 	- private
	 * Input 	- a File object of user input file 
	 * Output 	- a string Object
	 * Errors	- FileNotFoundException
	 */
	private static ArrayList<Instructions> parseFile(String line, ArrayList<Instructions> instr)
	{

		// Handle empty lines
		if(line.trim().isEmpty())
		{
			lineCount++;
		}
		// Handle lines with only a comment
		else if(line.charAt(0) == '#')
		{
			lineCount++;
		}
		else
		{
			instr = formatLine(line, instr);
			lineCount = lineCount + 1;
			instrCount = instrCount + 1;
		}
		return instr;
	}
	
	/*
	 * Method 	- rids line of comments/left-right space 
	 * Type 	- private
	 * Input 	- a File object of user input file 
	 * Output 	- a string Object
	 */
	private static ArrayList<Instructions> formatLine(String line, ArrayList<Instructions> instr)
	{
		
		String formattedLine = formatHelper(line);

		instr = parseLine(formattedLine, instr);
		
		return instr;
	}
	
	/*
	 * Method 	- rids line of comments/left-right space 
	 * Type 	- private
	 * Input 	- a File object of user input file 
	 * Output 	- a string Object
	 */
	private static String formatHelper(String line)
	{
		
		// No left or right whitespace
		String formatted = line.trim();
		
		// No same line comments
		formatted = formatted.split("#")[0];
		
		// Add space between registers
		formatted = formatted.replaceAll("\\$", " \\$").trim();
		
		// Add space between commas
		formatted = formatted.replaceAll(",", " ");
		
		// No >1 space between text
		formatted = formatted.replaceAll("\\s+", " ");
		
		return formatted;
	}
	
	/*
	 * Method 	- rids line of comments/left-right space 
	 * Type 	- private
	 * Input 	- a File object of user input file 
	 * Output 	- a string Object
	 */
	private static ArrayList<Instructions> parseLine(String line, ArrayList<Instructions> instrAl)
	{
		String instr = null;
		
		if(line.contains(":") && line.split(":").length > 1)
		{
			line = line.split(":")[1].trim();
		}
		
		if(!line.contains(":"))
		{
			instr = parseInstruction(line).trim();
			
			if(addtoInstructionList(instrAl, line, instr, lineCount, instrCount) == 1)
			{
				System.out.println(lineCount + ": " + "Invalid Instruction Found. " + instr + " is not valid");	
				System.exit(1);
			}
		}
		else
		{
			//add to label arrays
		}

		return instrAl;
	}
	
	/*
	 * Method 	- rids line of comments/left-right space 
	 * Type 	- private
	 * Input 	- a File object of user input file 
	 * Output 	- a string Object
	 */
	private static String parseInstruction(String line)
	{
		String instr = line.split(" ")[0];
		return instr;
	}
	
	private static int addtoInstructionList(ArrayList<Instructions> tempList, String line, String instr, int lC, int iC)
	{
		switch(instr)
		{
			case "add":
				tempList.add(new add(reg1(line), reg2(line), reg3_Immd_Label(line), iC, lC));
				break;
			case "addi":
				tempList.add(new addi(reg1(line), reg2(line), reg3_Immd_Label(line), iC, lC));
				break;
			case "and":
				tempList.add(new and(reg1(line), reg2(line), reg3_Immd_Label(line), iC, lC));
				break; 
			case "beq":
				tempList.add(new beq(reg1(line), reg2(line), reg3_Immd_Label(line), iC, lC));   
				break;
		   case "bne":
			   	tempList.add(new bne(reg1(line), reg2(line), reg3_Immd_Label(line), iC, lC));   
			   	break;
		   case "lw":
			   	tempList.add(new lw(reg1(line), storeLoadReg2(line), storeLoadImmd(line), iC, lC));
			   	break;
		   case "j":
			   	tempList.add(new j(jumpLabel(line), iC, lC));
			   	break;
		   case "jal":
			   	tempList.add(new jal(jumpLabel(line), iC, lC));
			   	break;
		   case "jr":
			   	tempList.add(new jr(jumpLabel(line), iC, lC));
			   	break;
		   case "or":
			   	tempList.add(new or(reg1(line), reg2(line), reg3_Immd_Label(line), iC, lC));
			   	break;
		   case "sll":
			   	tempList.add(new sll(reg1(line), reg2(line), reg3_Immd_Label(line), iC, lC));
			   	break;
		   case "slt":
			   	tempList.add(new slt(reg1(line), reg2(line), reg3_Immd_Label(line), iC, lC));
			   	break;
		   case "sub":
			   	tempList.add(new sub(reg1(line), reg2(line), reg3_Immd_Label(line), iC, lC));
			   	break;
		   case "sw":
			   	tempList.add(new sw(reg1(line), storeLoadReg2(line), storeLoadImmd(line), iC, lC));
			   	break;
		   default:
			   	return 1;
			   //return setValid();
	   }
		   return 0;
	}
	
	private static String reg1(String line)
	{
		String reg1 = line.split(" ")[1];
		reg1 = reg1.split("\\$")[1];
		return reg1;
	}
	
	private static String reg2(String line)
	{
		String reg2 = line.split(" ")[2];
		reg2 = reg2.split("\\$")[1];
		return reg2;
	}
	
	private static String reg3_Immd_Label(String line)
	{
		String reg3 = line.split(" ")[3];
		if(reg3.contains("$"))
		{
			reg3 = reg3.split("\\$")[1];
		}
		return reg3;
	}
	
	private static String storeLoadReg2(String line)
	{
		String reg = line.split(" ")[3];
		reg = reg.split("\\$")[1];
		reg = reg.split("\\)")[0];
		return reg;
	}
	
	private static String storeLoadImmd(String line)
	{
		String immd = line.split(" ")[2];
		immd = immd.split("\\(")[0];
		return immd;
	}
	
	private static String jumpLabel(String line)
	{
		String label = line.split(" ")[1].trim();
		return label;
	}
	
	
}
