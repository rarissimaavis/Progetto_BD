//Stampa di un report che mostri i dati delle prenotazioni che ancora 
//non sono state effettuate ed il costo di ognuna di esse

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op16
{
	public static void Query16()
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
		
		System.out.println("Dati delle prenotazioni ancora non attive e costo di ognuna di esse");
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select * \r\n"
					+ "from Prenotazione\r\n"
					+ "where DataCheckIn > '2023-01-01'");
			while (result.next())
			{
				String codice = result.getString("Codice");
				String datacheckin = result.getString("DataCheckIn");
				String datacheckout = result.getString("DataCheckOut");
				String numosp = result.getString("NumOspitiTotali");
				String prezzotot = result.getString("PrezzoTotale");
				String note = result.getString("Note");
				String sconto = result.getString("Sconto");
				String cliente = result.getString("Cliente");
				String agenzia = result.getString("Agenzia");
				String struttura = result.getString("Struttura");
				System.out.println(" | "+codice+" | "+datacheckin+" | "+datacheckout+" | "
						+numosp+" | "+prezzotot+" | "+note+" | "+sconto+" | "
						+cliente+" | "+agenzia+" | "+struttura+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}