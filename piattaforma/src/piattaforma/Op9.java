//visualizzazione dei migliori 10 clienti premium che abbiano effettuato
//il maggior numero di prenotazioni nelle diverse strutture ricettive

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op9
{
	public static void Query9()
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
		
		System.out.println("Migliori 10 clienti premium con il maggior numero di prenotazioni:");
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select Cliente.Nome, Cliente.Cognome, NumPrenotazioni\r\n"
					+ "from Prenotazione, Cliente\r\n"
					+ "where Prenotazione.Cliente = Cliente.CF\r\n"
					+ "and Sconto is not null\r\n"
					+ "order by NumPrenotazioni desc\r\n"
					+ "limit 10");
			while (result.next())
			{
				String nome = result.getString("Nome");
				String cognome = result.getString("Cognome");
				String nump = result.getString("NumPrenotazioni");
				System.out.println(" | "+nome+" | "+cognome+" | "+nump+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}