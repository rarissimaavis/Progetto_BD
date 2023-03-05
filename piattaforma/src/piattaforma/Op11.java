//visualizzazione delle strutture ricettive che hanno una distanza di 10km specifica da un punto di interesse

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op11
{
	public static void Query11()
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
		
		System.out.println("Strutture che hanno una distanza di 10km da un punto di interesse:");
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select Nome\r\n"
					+ "from Struttura, Distanza\r\n"
					+ "where Struttura.Codice = Distanza.Struttura\r\n"
					+ "and Distanza.Km = 10");
			while (result.next())
			{
				String nome = result.getString("Nome");
				System.out.println(" | "+nome+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}