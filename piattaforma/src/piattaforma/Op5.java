//visualizzazione di tutte le strutture ricettive per città

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op5
{
	public static void Query5()
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
		
		System.out.println("Strutture ordinate per città:");
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select Nome, Citta\r\n"
					+ "from Struttura\r\n"
					+ "order by Citta");
			while (result.next())
			{
				String nome = result.getString("Nome");
				String citta = result.getString("Citta");
				System.out.println(" | "+nome+" | "+citta+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}