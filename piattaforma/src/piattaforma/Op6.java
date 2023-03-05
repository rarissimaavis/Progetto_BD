//visualizzazione di tutte le strutture ricettive disponibili in un periodo di tempo specificato

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Op6
{
	public static void Query6()
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
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Data inizio periodo di tempo:");
		String data1 = sc.nextLine();
		System.out.println("Data fine periodo di tempo:");
		String data2 = sc.nextLine();
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select distinct Composizione.*\r\n"
					+ "from Composizione\r\n"
					+ "where (Composizione.TipoStanza, Composizione.NumOspitiStanza, Composizione.Hotel) not in \r\n"
					+ "	(select Scelta.TipoStanza, Scelta.NumOspitiStanza, Prenotazione.Struttura\r\n"
					+ "    from Scelta, Prenotazione\r\n"
					+ "	where Scelta.Prenotazione = Prenotazione.Codice\r\n"
					+ "    and ((DataCheckIn > '"+data1+"' and DataCheckIn < '"+data2+"') \r\n"
					+ "		or (DataCheckOut < '"+data2+"' and DataCheckOut > '"+data1+"')\r\n"
					+ "        or (DataCheckIn < '"+data1+"' and DataCheckOut > '"+data2+"')))\r\n");
			System.out.println("Stanze disponibili:");
			while (result.next())
			{
				String hotel = result.getString("Hotel");
				String tipos = result.getString("TipoStanza");
				String numos = result.getString("NumOspitiStanza");
				System.out.println(" | "+hotel+" | "+tipos+" | "+numos+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Statement query2;
		try
		{
			query2 = con.createStatement();
			ResultSet result = query2.executeQuery("select Struttura\r\n"
					+ "from Ostello\r\n"
					+ "where Struttura not in \r\n"
					+ "	(select Struttura\r\n"
					+ "    from Prenotazione\r\n"
					+ "	where ((DataCheckIn > '"+data1+"' and DataCheckIn < '"+data2+"') \r\n"
					+ "		or (DataCheckOut < '"+data2+"' and DataCheckOut > '"+data1+"')\r\n"
					+ "        or (DataCheckIn < '"+data1+"' and DataCheckOut > '"+data2+"')))\r\n"
					+ "or (select count(NumOspitiTotali) \r\n"
					+ "	from Prenotazione\r\n"
					+ "	where Prenotazione.Struttura = Ostello.Struttura\r\n"
					+ "    and ((DataCheckIn > '"+data1+"' and DataCheckIn < '"+data2+"') \r\n"
					+ "		or (DataCheckOut < '"+data2+"' and DataCheckOut > '"+data1+"')\r\n"
					+ "        or (DataCheckIn < '"+data1+"' and DataCheckOut > '"+data2+"'))\r\n"
					+ "    group by Prenotazione.Struttura) < Ostello.NumPostiLettoTotali\r\n"
					+ "union\r\n"
					+ "select Struttura\r\n"
					+ "from Appartamento\r\n"
					+ "where Struttura not in \r\n"
					+ "	(select Struttura\r\n"
					+ "    from Prenotazione\r\n"
					+ "	where ((DataCheckIn > '"+data1+"' and DataCheckIn < '"+data2+"') \r\n"
					+ "		or (DataCheckOut < '"+data2+"' and DataCheckOut > '"+data1+"')\r\n"
					+ "        or (DataCheckIn < '"+data1+"' and DataCheckOut > '"+data2+"')))");
			System.out.println("Strutture disponibili:");
			while (result.next())
			{
				String struttura = result.getString("Struttura");
				System.out.println(" | "+struttura+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}