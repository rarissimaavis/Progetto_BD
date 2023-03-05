//stampa dei dati dei clienti che hanno prenotato solo appartamenti e ostelli

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op13
{
	public static void Query13()
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
		
		System.out.println("Clienti che hanno prenotato solo appartamenti e ostelli:");
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select distinct Cliente.*\r\n"
					+ "from Cliente, Prenotazione, Ostello, Appartamento\r\n"
					+ "where Cliente.Cf = Prenotazione.Cliente\r\n"
					+ "and (Prenotazione.Struttura = Ostello.Struttura \r\n"
					+ "	or Prenotazione.Struttura = Appartamento.Struttura)");
			while (result.next())
			{
				String cf = result.getString("CF");
				String nome = result.getString("Nome");
				String cognome = result.getString("Cognome");
				String telefono = result.getString("Telefono");
				String email = result.getString("Email");
				String nump = result.getString("NumPrenotazioni");
				String numpa = result.getString("NumPrenotazioniAttive");
				String codicet = result.getString("CodiceTessera");
				System.out.println(" | "+cf+" | "+nome+" | "+cognome+" | "+telefono+" | "
						+email+" | "+nump+" | "+numpa+" | "+codicet+" | ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}