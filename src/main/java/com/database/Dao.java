package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import org.simplilearn.workshop.util.StringUtil;


public class Dao {
	public Connection con=null;
	public Statement st=null;

	public Dao() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","Goyal@1234");
		System.out.println("connection established with database");
		st=con.createStatement();
	}
public List<String[]> getAvailableFlights(String f, String t, String d) {
		
		List<String[]> flights=new ArrayList<>();
		String query="SELECT * FROM flyaway.flights where fromf='"+f+"' and tof='"+t+"' and datef='"+d+"'";
		try {
			ResultSet rs=st.executeQuery(query);
			
			if(rs.next()) {
				String[] flight=new String[3];
				flight[0]=rs.getString("name");
				flight[1]=rs.getString("timef");
				flight[2]=rs.getString("price");
				flights.add(flight);
				return flights;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

public HashMap<String, String> checkUser(String email, String password) {
	
	HashMap<String,String> user=null;
	String query="select * from user where email='"+email+"' and password='"+password+"'";
	try {
		ResultSet rs=st.executeQuery(query);
		if(rs.next()) {
			user=new HashMap<>();
			user.put("name", rs.getString("name"));
			user.put("email",rs.getString("email"));
			user.put("phno",rs.getString("phno"));
			user.put("adno",rs.getString("adno"));
		}
		return user;
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return user;
}

public boolean insertUser(HashMap<String, String> user) {

	String query="INSERT INTO user (email, password, name, phno, adno) values('"+user.get("email")+"','"+user.get("password")+"','"+user.get("name")+"','"+user.get("phno")+"','"+user.get("adno")+"')";                   
	
	try {
		st.execute(query);
		return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}

public boolean checkAdmin(String email, String password) {
	
	try {
		ResultSet rs=st.executeQuery("select * from admin where email='"+email+"' and password='"+password+"'");
		if(rs.next())
			return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}

public boolean changeAdminPassword(String email, String password) {

	try {
		ResultSet rs=st.executeQuery("select * from admin where email='"+email+"'");
		if(!rs.next()) {
			return false;
		}
		st.execute("update admin set password='"+password+"' where email='"+email+"'");
		return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}

public boolean insertFlight(HashMap<String, String> flight) throws SQLException {
	PreparedStatement stm=con.prepareStatement("INSERT INTO flights ('name', 'fromf', 'tof', 'datef', 'timef', 'price') values(?,?,?,?,?,?)");
		String sql="INSERT INTO flights ('name','fromf','tof','datef','timef','price') values('"+flight.get("name")+"','"+flight.get("fromf")+"','"+flight.get("tof")+"','"+flight.get("datef")+"','"+flight.get("timef")+"','"+flight.get("price")+"');";
		//String sql="INSERT INTO `flyaway`.`flights` (`name`, `fromf`, `tof`, `datef`, `timef`, `price`) VALUES ('indigo', 'hyd', 'viz', '2021-04-08', '10:00', '2000');";
		System.out.println(flight.get("datef"));
		System.out.println(flight.get("timef"));
		//String query1="insert into flights (name,fromf,tof,datef,timef,price) values('SpiceJetAir', 'abc','xyz','2022-06-24','10:00',6000)";
		try {
			stm.execute(sql);
			//stm.execute(query1);
			return true;
		} catch (SQLException e) {
			System.out.print("error");
			e.printStackTrace();
		}
		return false;
	}

}
