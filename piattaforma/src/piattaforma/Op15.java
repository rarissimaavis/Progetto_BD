//stampa di un report che mostri i dati delle strutture ricettive 
//per una specifica città e che hanno ricevuto meno di 3 prenotazioni

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Op15
{
	public static void Query15()
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
		
		System.out.println("Dati delle strutture per una specifica città con meno di 3 prenotazioni:");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserire una città:");
		String city = sc.nextLine();		
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select Struttura.*\r\n"
					+ "from Struttura, Prenotazione\r\n"
					+ "where Struttura.Codice = Prenotazione.Struttura\r\n"
					+ "and Struttura.Citta = '"+city+"'\r\n"
					+ "group by Prenotazione.Struttura\r\n"
					+ "having count(Prenotazione.Struttura) < 3");
			while (result.next())
			{
				String codice = result.getString("Codice");
				String nome = result.getString("Nome");
				String via = result.getString("Via");
				String cap = result.getString("CAP");
				String citta = result.getString("Citta");
				String annoisc = result.getString("AnnoDiIscrizione");
				System.out.println(" | "+codice+" | "+nome+" | "+via+" | "
						+cap+" | "+citta+" | "+annoisc+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
		
		//sc.close();
	}
}