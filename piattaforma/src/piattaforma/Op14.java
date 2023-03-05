//stampa di un report che mostri i dati delle agenzie di viaggio 
//compreso il numero totale di prenotazioni effettuate

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op14
{
	public static void Query14()
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
		
		System.out.println("Dati delle agenzie di viaggio e numero totale di prenotazioni effettuate:");
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select Agenzia.*, count(Prenotazione.Agenzia) as NumPrenotazioni\r\n"
					+ "from Agenzia, Prenotazione\r\n"
					+ "where Agenzia.Codice = Prenotazione.Agenzia\r\n"
					+ "group by Agenzia");
			while (result.next())
			{
				String codice = result.getString("Agenzia.Codice");
				String nome = result.getString("Nome");
				String nump = result.getString("NumPrenotazioni");
				System.out.println(" | "+codice+" | "+nome+" | "+nump+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}