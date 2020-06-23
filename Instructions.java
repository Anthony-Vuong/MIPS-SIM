
//import simulator.Instructions;

public class Instructions {
	
	public static String convertBinary(int num, int bin_length)
	{
		int binary[] = new int[bin_length];
		String b = "";
		int index = 0;
		if(num < 0) 
		{
			String bine = Integer.toBinaryString(num);	
			
			String[] conv = bine.split("(?!^)"); 
		    	 
			for(int i = 31;i >= bin_length; i--)
			{
				b = conv[i] + b;
			}
		    
			return b;
		}
		else 
		{
			while(bin_length > 0)
			{
				binary[index] = num%2;
				num = num/2;
				index++;
				bin_length--;
			}
			for(int i = index-1;i >= 0;i--)
			{
				String bin = Integer.toString(binary[i]);
				b = b + bin;
			}
			return b;
		}
	}
	
	public static String regOpcode(String cmd){
		String opcode = null;
		switch(cmd){
		case "zero":
			opcode = "00000";
			break;
		case "0":
			opcode = "00000";
			break;
		case "v0":
			opcode = "00010";
			break;
		case "v1":
			opcode = "00011";
			break;
		case "a0":
			opcode = "00100";
			break;
		case "a1":
			opcode = "00101";
			break;
		case "a2":
			opcode = "00110";
			break;
		case "a3":
			opcode = "00111";
			break;
		case "t0":
			opcode = "01000";
			break;
		case "t1":
			opcode = "01001";
			break;
		case "t2":
			opcode = "01010";
			break;
		case "t3":
			opcode = "01011";
			break;
		case "t4":
			opcode = "01100";
			break;
		case "t5":
			opcode = "01101";
			break;
		case "t6":
			opcode = "01110";
			break;
		case "t7":
			opcode = "01111";
			break;
		case "t8":
			opcode = "11000";
			break;
		case "t9":
			opcode = "11001";
			break;
		case "s0":
			opcode = "10000";
			break;
		case "s1":
			opcode = "10001";
			break;
		case "s2":
			opcode = "10010";
			break;
		case "s3":
			opcode = "10011";
			break;
		case "s4":
			opcode = "10100";
			break;
		case "s5":
			opcode = "10101";
			break;
		case "s6":
			opcode = "10110";
			break;
		case "s7":
			opcode = "10111";
			break;
		case "sp":
			opcode = "11101";
			break;
		case "ra":
			opcode = "11111";
			break;
		}
		return opcode;
	}

}

class add extends Instructions
{
	String rd, rs, rt;
	int lineCount, instrCount;
	   
	public add(String destReg, String Reg1, String Reg2, int ic, int lc)
	{	
		rd = destReg;
		rs = Reg1;
		rt = Reg2;
		lineCount = lc;
		instrCount = ic;
	}
	   
	public String toString()
	{
		return "000000 " + regOpcode(this.rs) + " " + regOpcode(this.rt) + " " + regOpcode(this.rd) + " 00000 100000";
	}
	   
}

class addi extends Instructions
{
	String rs, immediate, rt;
	int lineCount, instrCount;
	      
	public addi(String destReg, String Reg1, String immd, int ic, int lc)
	{
		rt = destReg;
		rs = Reg1;
		immediate = immd;
		lineCount = lc;
		instrCount = ic;
	}
	   
	public String toString()
	{
		return "001000 " + regOpcode(this.rt) + " " + regOpcode(this.rs) + " " + convertBinary(Integer.parseInt(immediate), 16);
	}
	    
}

class and extends Instructions
{
	String rs, rt, rd;
	int lineCount, instrCount;
	
	public and(String destReg, String Reg1, String Reg2, int ic, int lc)
	{
		rd = destReg;
		rs = Reg1;
		rt = Reg2;
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		return "000000 " + regOpcode(this.rs) + " " + regOpcode(this.rt) + " " + regOpcode(this.rd) + " 00000 100100";
	}
	   
}

//beq - rd, rs, rt, instructions to reach label, instruction count, line count, and string of label to jump to
class beq extends Instructions
{
	String rs, rt, labelName;
	int lineCount, instrCount;
	   
	public beq(String Reg1, String Reg2, String lblNme, int ic, int lc)
	{
		rs = Reg1;
		rt = Reg2;
		labelName = lblNme;
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		//1st arg - convert binary insert line of label - REMOVE COMMENT WHEN FIXED
		return "000100 " + regOpcode(this.rs) + " " +  regOpcode(this.rt) + " " + convertBinary(2, 16);
	}
	      
}

class bne extends Instructions
{
	String rs, rt, labelName;
	int lineCount, instrCount;
	     
	public bne(String Reg1, String Reg2, String lblNme, int ic, int lc)
	{
		rs = Reg1;
		rt = Reg2;
		labelName = lblNme;
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		//1st arg - convert binary insert line of label - REMOVE COMMENT WHEN FIXED
		return "000101 " + regOpcode(this.rs) + " " +  regOpcode(this.rt) + " " + convertBinary(2, 16);
	}
	    
}

class j extends Instructions
{
	String labelName;
	int lineCount, instrCount;
	    
	public j(String lblNme, int ic, int lc)
	{
		labelName = lblNme;
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		//1st arg - convert binary insert line of label - REMOVE COMMENT WHEN FIXED
		return "000010 " + convertBinary(2, 26);
	}

}

class jal extends Instructions
{	   
	String labelName;
	int lineCount, instrCount;
	 
	public jal(String lblNme, int ic, int lc)
	{
		labelName = lblNme;
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		//1st arg - convert binary insert line of label - REMOVE COMMENT WHEN FIXED
		return "000011 " + convertBinary(2, 26);
	}
	   
}

class jr extends Instructions
{
	String rs;
	int lineCount, instrCount;
	   
	public jr(String Reg1, int ic, int lc)
	{
		rs = Reg1;
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		return "000000 " +  regOpcode("ra") + " 000000000000000 001000";
	}
	   	   
}

class lw extends Instructions
{
	String rt, rs, immd;
	int instrCount, lineCount;
	   
	public lw(String Reg1, String Reg2, String immed, int ic, int lc)
	{
		rt = Reg1;
		rs = Reg2;
		immd = immed;
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		return "100011 " + regOpcode(this.rs) + " " +  regOpcode(this.rt) + " " + convertBinary(Integer.parseInt(this.immd), 16);
	}
	
}

class or extends Instructions
{
	String rs, rt, rd;
	int instrCount, lineCount;
	   
	public or(String destReg, String Reg1, String Reg2, int ic, int lc)
	{
		rd = destReg;
		rs = Reg1;
		rt = Reg2;
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		return "000000 " + regOpcode(this.rs) + " " + regOpcode(this.rt)  + " " + regOpcode(this.rd) + " 00000 100101";
	}
	   
}

class sll extends Instructions
{
	String rt, immd, rd;
	int lineCount, instrCount;
	   
	public sll(String destReg, String Reg1, String Immed, int ic, int lc)
	{
		rd = destReg;
		rt = Reg1;
		immd = Immed;
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		return "000000 " + "00000 " + regOpcode(this.rt)  + " " + regOpcode(this.rd) + " " +  convertBinary(Integer.parseInt(this.immd), 5) + " 000000";
	}
   
}


class slt extends Instructions
{
	String rs, rt, rd;
	int lineCount, instrCount;
	   
	public slt(String destReg, String Reg1, String Reg2, int ic, int lc) 
	{
		rd = destReg;
		rs = Reg1;
		rt = Reg2;		
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		return "000000 " + regOpcode(this.rs) + " " + regOpcode(this.rt)  + " " + regOpcode(this.rd) + " 00000 101010";
	}
	
}

class sub extends Instructions
{
	String rs, rt, rd;
	int lineCount, instrCount;
		   
	public sub(String destReg, String Reg1, String Reg2, int ic, int lc) 
	{
		rd = destReg;
		rs = Reg1;
		rt = Reg2;
		instrCount = ic;
		lineCount = lc;
	}
	
	public String toString()
	{
		return "000000 " + regOpcode(this.rs) + " " + regOpcode(this.rt)  + " " + regOpcode(this.rd) + " 00000 100010";
	}
	   
}

class sw extends Instructions
{
	String rt, rs, immd;
	int instrCount, lineCount;
	   
	public sw(String Reg1, String Reg2, String immed, int ic, int lc)
	{
		rt = Reg1;
		rs = Reg2;
		immd = immed;
		lineCount = lc;
		instrCount = ic;
	}
	
	public String toString()
	{
		return "101011 " + regOpcode(this.rs) + " " +  regOpcode(this.rt) + " " + convertBinary(Integer.parseInt(immd), 16);
	}	
	
}

class label extends Instructions
{
	String labelName;
	int lineCount;
	
	public label(String lblNme, int lc)
	{
		labelName = lblNme;
		lineCount = lc;
	}
	   
}
