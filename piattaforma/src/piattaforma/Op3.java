//prenotazione di una struttura ricettiva da parte di un cliente

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Op3
{
	public static void Query3()
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
		
		System.out.println("Inserire i dati della prenotazione");
		System.out.println("Codice:");
		String codice = sc.nextLine();
		System.out.println("Data check-in:");
		String dataci = sc.nextLine();
		System.out.println("Data check-out:");
		String dataco = sc.nextLine();
		System.out.println("Numero ospiti totali:");
		int numot = sc.nextInt();
		System.out.println("Prezzo totale:");
		float pt = sc.nextFloat();
		
		Scanner sc2 = new Scanner(System.in);
		
		System.out.println("Note:");
		String note = sc2.nextLine();
		System.out.println("Sconto:");
		int sconto = sc2.nextInt();
		
		Scanner sc3 = new Scanner(System.in);
		
		System.out.println("Cliente:");
		String cliente = sc3.nextLine();
		System.out.println("Agenzia:");
		String agenzia = sc3.nextLine();
		System.out.println("Struttura:");
		String struttura = sc3.nextLine();
		
		Statement query;
		try
		{
			query = con.createStatement();
			query.execute("insert into Prenotazione (Codice, DataCheckIn, DataCheckOut, \r\n"
					+ "NumOspitiTotali, PrezzoTotale, Note, Sconto, Cliente, Agenzia, Struttura)\r\n"
					+ "values ('"+codice+"', '"+dataci+"', '"+dataco+"', "+numot+", "+pt+", \r\n"
					+ "'"+note+"', "+sconto+", '"+cliente+"', "+agenzia+", '"+struttura+"')");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Statement query2;
		try
		{
			query2 = con.createStatement();
			query2.executeUpdate("update Cliente\r\n"
					+ "set NumPrenotazioni = NumPrenotazioni+1 \r\n"
					+ "where CF = '"+cliente+"'");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Scanner sc4 = new Scanner(System.in);
		
		System.out.println("La struttura prenotata è un hotel?  si=1  no=0");
		int risp = sc4.nextInt();

		if (risp == 1)
		{
			System.out.println("Numero di stanze da prenotare?");
			int risp2 = sc4.nextInt();

			System.out.println("Stanze disponibili:");

			Statement query3;
			try
			{
				query3 = con.createStatement();
				ResultSet result = query3.executeQuery("select TipoStanza, NumOspitiStanza \r\n"
							+"from Composizione where Hotel = '"+struttura+"'");

				while (result.next())
				{
					String tipos = result.getString("TipoStanza");
					String numo = result.getString("NumOspitiStanza");
					System.out.println(" | "+tipos+" | "+numo+" | ");	
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
				
			Scanner sc5 = new Scanner(System.in);

			for(int i = 0; i < risp2; i++)
			{
				System.out.println("Tipo:");
				String tipo = sc5.nextLine();
				System.out.println("Numero ospiti:");
				String numospiti = sc5.nextLine();

				Statement query4;
				try
				{
					query4 = con.createStatement();
					query4.execute("insert into Scelta (Prenotazione, TipoStanza, NumOspitiStanza)\r\n"
							+" values ('"+codice+"','"+tipo+"','"+numospiti+"')");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}