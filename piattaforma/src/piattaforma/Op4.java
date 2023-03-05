//attribuzione ad un cliente di una tessera con fidelizzazione premium

package piattaforma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Op4
{
	public static void Query4()
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
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Inserire il codice fiscale del cliente:");
		String cf = sc.nextLine();
		String codice = null;
		
		Statement query;
		try
		{
			query = con.createStatement();
			ResultSet result = query.executeQuery("select CodiceTessera from Cliente where CF = '"+cf+"'");
			while (result.next())
			{
				codice = result.getString("CodiceTessera");
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
			query2.execute("update Tessera set Tipo = 'Premium' where Codice = '"+codice+"'");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}