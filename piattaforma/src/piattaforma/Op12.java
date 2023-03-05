//visualizzazione della somma degli incassi ottenuti dalle strutture ricettive registrate sulla piattaforma

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op12
{
	public static void Query12()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/piattaforma"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
			String username = "root";
			//String pwd = " "; inserire password
			
			con = DriverManager.getConnection(url, username, pwd);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Somma degli incassi ottenuti dalle strutture:");
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select Struttura, sum(PrezzoTotale) as Incassi\r\n"
					+ "from Prenotazione\r\n"
					+ "group by Struttura");
			while (result.next())
			{
				String struttura = result.getString("Struttura");
				String incassi = result.getString("Incassi");
				System.out.println(" | "+struttura+" | "+incassi+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}