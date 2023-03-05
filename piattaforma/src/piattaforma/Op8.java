//visualizzazione del numero di prenotazioni effettuate da tutti i clienti nell’ultimo mese

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op8
{
	public static void Query8()
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
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select count(*) as NumPrenotazioniUltimoMese\r\n"
					+ "from Prenotazione \r\n"
					+ "where (DataCheckIn > '2023-04-01' and DataCheckIn < '2023-05-01')");
			while (result.next())
			{
				String nump = result.getString("NumPrenotazioniUltimoMese");
				System.out.println("Numero di prenotazioni effettuate nell'ultimo mese: " + nump);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}