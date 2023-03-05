//registrazione di un cliente

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Op1
{
	public static void Query1()
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
		
		System.out.println("Inserire i dati della tessera");
		System.out.println("Codice:");
		String codice = sc.nextLine();
		System.out.println("Tipo:");
		String tipo = sc.nextLine();
		System.out.println("Data di scandenza:");
		String datascad = sc.nextLine();
		System.out.println("Periodo di validità:");
		int perval = sc.nextInt();
		
		Scanner sc2 = new Scanner(System.in);
		
		System.out.println("Inserire i dati del cliente");
		System.out.println("Codice fiscale:");
		String cf = sc2.nextLine();
		System.out.println("Nome:");
		String nome = sc2.nextLine();
		System.out.println("Cognome:");
		String cognome = sc2.nextLine();
		System.out.println("Telefono:");
		String telefono = sc2.nextLine();
		System.out.println("Email:");
		String email = sc2.nextLine();
		System.out.println("Numero prenotazioni:");
		int nump = sc2.nextInt();
		System.out.println("Numero prenotazioni attive:");
		int numpa = sc2.nextInt();
		
		Statement query;
		try
		{
			query = con.createStatement();
			query.execute("insert into Tessera (Codice, Tipo,\r\n"
					+ "DataDiScadenza, PeriodoDiValidita) values ('"+codice+"', '"+tipo+"',\r\n"
					+ "'"+datascad+"',"+perval+" )");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Statement query2;
		try
		{
			query2 = con.createStatement();
			query2.execute("insert into Cliente (CF, Nome, Cognome,\r\n"
					+ "Telefono, Email, NumPrenotazioni, NumPrenotazioniAttive, CodiceTessera)\r\n"
					+ "values ('"+cf+"', '"+nome+"', '"+cognome+"', '"+telefono+"',\r\n"
					+ "'"+email+"', "+nump+", "+numpa+", '"+codice+"')");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}