package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;



public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	// add DAO methods as per requirements
	
	public void insertUser(String pname,String pmail,String pnum) throws SQLException{
		
		int i=0;
		connect();
		String str="";
		Statement stmt= this.jdbcConnection.createStatement();
		 ResultSet rs=stmt.executeQuery("select max(cid) from coronakit");
		 if(!(rs.next())) { 
			 str="Insert into coronakit values("+1+",'"+pname+"','"+pmail+"','"+pnum+"','"+"','"+"','"+"','"+"')";
			 } 
		 else { 
			 i=rs.getInt(1)+1;
			 str="Insert into coronakit values("+i+",'"+pname+"','"+pmail+"','"+pnum+"','"+"','"+"','"+"','"+"')";
		 }
		 System.out.println(str);
		stmt.executeUpdate(str);
		rs=null;
		stmt=null;
		disconnect();
		
	}
	public void insertKit(String cid,String pid,String qty,int amt) throws SQLException{
		
		int i=0;
		connect();
		String str="";
		Statement stmt= this.jdbcConnection.createStatement();
		 ResultSet rs=stmt.executeQuery("select max(kid) from kitdetails");
		 if(!(rs.next())) { 
			 str="Insert into kitdetails values("+1+",'"+cid+"','"+pid+"','"+qty+"','"+amt+"')";
			 } 
		 else { 
			 i=rs.getInt(1)+1;
			 str="Insert into kitdetails values("+i+",'"+cid+"','"+pid+"','"+qty+"','"+amt+"')";
		 }
		 System.out.println(str);
		stmt.executeUpdate(str);
		rs=null;
		stmt=null;
		disconnect();
		
	}
	public CoronaKit getUser(String pname,String pnum) throws SQLException
	{
		CoronaKit coronaKit=null;
		connect();
		int amt=0;
		boolean confirm=false;
		Statement stmt= this.jdbcConnection.createStatement();
		ResultSet rs=stmt.executeQuery("select * from coronakit where pname='"+pname+"' and pnum='"+pnum+"'");
		while(rs.next()) {	
			//System.out.println(rs.getString("amt"));
			if(!(rs.getString("amt").isEmpty()))
				amt=Integer.parseInt(rs.getString("amt"));
			if(!(rs.getString("orderfinal").isEmpty())) {
				if(rs.getString("orderfinal").equals("true"))
					confirm=true;
			}
				
				
			coronaKit=new CoronaKit(rs.getInt("cid"),rs.getString("pname"), rs.getString("pmail"), rs.getString("pnum"),amt , rs.getString("addr"), rs.getString("orderdate"), confirm);
		}
		rs=null;stmt=null;
		disconnect();
		return coronaKit;
		
	}
	
	public CoronaKit getUser(String cid) throws SQLException
	{
		CoronaKit coronaKit=null;
		connect();
		int amt=0;
		boolean confirm=false;
		Statement stmt= this.jdbcConnection.createStatement();
		ResultSet rs=stmt.executeQuery("select * from coronakit where cid='"+cid+"'");
		while(rs.next()) {	
			//System.out.println(rs.getString("amt"));
			if(!(rs.getString("amt").isEmpty()))
				amt=Integer.parseInt(rs.getString("amt"));
			if(!(rs.getString("orderfinal").isEmpty())) {
				if(rs.getString("orderfinal").equals("true"))
					confirm=true;
			}
				
				
			coronaKit=new CoronaKit(rs.getInt("cid"),rs.getString("pname"), rs.getString("pmail"), rs.getString("pnum"),amt , rs.getString("addr"), rs.getString("orderdate"), confirm);
		}
		rs=null;stmt=null;
		disconnect();
		return coronaKit;
		
	}
	public List<KitDetail> getKitDetails(String cid) throws SQLException
	{
		List<KitDetail> list= new ArrayList<KitDetail>();
		connect();
		Statement stmt= this.jdbcConnection.createStatement();
		ResultSet rs=stmt.executeQuery("select * from kitdetails where cid="+Integer.parseInt(cid));
		while(rs.next()) {
			KitDetail obj= new KitDetail(rs.getInt("kid"), rs.getInt("cid"), rs.getInt("pid"), rs.getInt("qty"),rs.getInt("amt"));
			list.add(obj);
		}
		rs=null;stmt=null;
		disconnect();
		return list;
		
	}
	
	/*
	 * public static void main(String[] arg) throws SQLException {
	 * 
	 * KitDao kitDao=new
	 * KitDao("jdbc:sqlserver://LAPTOP-CNI1RUUB;databaseName=corona", "su",
	 * "password"); CoronaKit obj=kitDao.getUser("kundan", "7801066666");
	 * System.out.println(obj.getTotalAmount()+"---"+obj.isOrderFinalized()); }
	 */
	
}