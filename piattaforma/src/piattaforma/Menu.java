package piattaforma;

import java.util.Scanner;

public class Menu
{
	public static void main(String[] args)
	{		
		while (true)
		{
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Quale operazione scegli?");
			String risp = sc.nextLine();
			
			if (risp.equals("0"))
			{
				break;
			}
			
			switch (risp)
			{
				case "1": Op1.Query1(); break;
				case "2": Op2.Query2(); break;
				case "3": Op3.Query3(); break;
				case "4": Op4.Query4(); break;
				case "5": Op5.Query5(); break;
				case "6": Op6.Query6(); break;
				case "7": Op7.Query7(); break;
				case "8": Op8.Query8(); break;
				case "9": Op9.Query9(); break;
				case "10": Op10.Query10(); break;
				case "11": Op11.Query11(); break;
				case "12": Op12.Query12(); break;
				case "13": Op13.Query13(); break;
				case "14": Op14.Query14(); break;
				case "15": Op15.Query15(); break;
				case "16": Op16.Query16(); break;
			}
		}
	}
}