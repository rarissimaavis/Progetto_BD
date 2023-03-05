//registrazione di una struttura ricettiva

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Op2
{
	public static void Query2()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/piattaforma"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
			String username = "root";
			String pwd = "RosaliaFortino99";
			
			con = DriverManager.getConnection(url, username, pwd);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Inserire i dati della struttura");
		System.out.println("Codice:");
		String codice = sc.nextLine();
		System.out.println("Nome:");
		String nome = sc.nextLine();
		System.out.println("Via:");
		String via = sc.nextLine();
		System.out.println("CAP:");
		String cap = sc.nextLine();
		System.out.println("Città:");
		String citta = sc.nextLine();
		System.out.println("Anno di iscrizione:");
		String annoisc = sc.nextLine();
		
		Statement query;
		try
		{
			query = con.createStatement();
			query.execute("insert into Struttura (Codice, Nome, Via, CAP, Citta, \r\n"
					+ "AnnoDiIscrizione) values ('"+codice+"', '"+nome+"', '"+via+"', '"+cap+"',\r\n"
					+ "'"+citta+"', "+annoisc+")");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		
		Scanner sc2 = new Scanner(System.in);
		
		System.out.println("La struttura è un Hotel, un Ostello o un Appartamento?");
		String risposta = sc2.nextLine();
		
		Statement query2;
		switch (risposta)
		{
			case "Hotel":
				try
				{
					query2 = con.createStatement();
					query2.execute("insert into Hotel (Struttura) values ('"+codice+"')");
					Statement query3 = con.createStatement();
					query3.execute("insert into Composizione (Hotel, TipoStanza, NumOspitiStanza) \r\n"
							+ "values ('"+codice+"', 'standard', 1)");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				break;
			case "Ostello":
				System.out.println("Inserire i dati dell'ostello");
				System.out.println("Prezzo:");
				float prezzoost = sc.nextFloat();
				System.out.println("Numero posti letto totali:");
				int numpt = sc.nextInt();
				try
				{
					query2 = con.createStatement();
					query2.execute("insert into Ostello (Struttura, Prezzo, NumPostiLettoTotali)\r\n"
							+ "values ('"+codice+"', "+prezzoost+", "+numpt+");");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				break;
			case "Appartamento":
				System.out.println("Inserire i dati dell'appartamento");
				System.out.println("Prezzo:");
				float prezzoapp = sc.nextFloat();
				System.out.println("Numero ospiti:");
				int numo = sc.nextInt();
				System.out.println("Numero vani:");
				int numv = sc.nextInt();
				System.out.println("Mq:");
				float mq = sc.nextFloat();
				try
				{
					query2 = con.createStatement();
					query2.execute("insert into Appartamento (Struttura, Prezzo, NumOspiti,\r\n"
							+ "NumVani, Mq) values ('"+codice+"', "+prezzoapp+", "+numo+", \r\n"
							+ ""+numv+", "+mq+")");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				break;
		}
	}
}