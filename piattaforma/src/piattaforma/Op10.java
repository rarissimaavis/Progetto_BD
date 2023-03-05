//visualizzazione degli ostelli per i quali non è stata mai registrata una prenotazione di più di 7 giorni

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op10
{
	public static void Query10()
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
		
		System.out.println("Ostelli senza prenotazioni di più di 7 giorni:");
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select Ostello.Struttura\r\n"
					+ "from Ostello, Prenotazione\r\n"
					+ "where Ostello.Struttura = Prenotazione.Struttura\r\n"
					+ "and datediff(Prenotazione.DataCheckOut, Prenotazione.DataCheckIn) <= 7");
			while (result.next())
			{
				String ostello = result.getString("Ostello.Struttura");
				System.out.println(" | "+ostello+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}