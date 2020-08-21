package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;



public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	
	public List<ProductMaster> getProducts() throws SQLException
	{
		List<ProductMaster> list= new ArrayList<ProductMaster>();
		connect();
		Statement stmt= this.jdbcConnection.createStatement();
		ResultSet rs=stmt.executeQuery("select * from products");
		while(rs.next()) {
			ProductMaster obj= new ProductMaster(rs.getInt("pid"), rs.getString("pname"), rs.getString("pcost"), rs.getString("pdesc"));
			list.add(obj);
		}
		rs=null;stmt=null;
		disconnect();
		return list;
		
	}
	
	public void insertProduct(String pname,String pdesc, String pcost) throws SQLException {
		
		int i=0;
		connect();
		String str="";
		Statement stmt= this.jdbcConnection.createStatement();
		 ResultSet rs=stmt.executeQuery("select max(pid) from products");
		 if(!(rs.next())) { 
			 str="Insert into products values("+1+",'"+pname+"','"+pdesc+"','"+pcost+"')";
			 } 
		 else { 
			 i=rs.getInt(1)+1;
			 str="Insert into products values("+i+",'"+pname+"','"+pdesc+"','"+pcost+"')";
		 }
		stmt.executeUpdate(str);
		rs=null;
		stmt=null;
		disconnect();
		
	}
	
	public void updateProduct(String pid,String pname,String pdesc, String pcost) throws SQLException {
		
		connect();
		//String ser="@@ROWCOUNT";
		Statement stmt= this.jdbcConnection.createStatement();
		String str1="update products set pname='"+pname+"',"+"pdesc='"+pdesc+"',"+"pcost='"+pcost+"' where pid="+pid;
		System.out.println(str1);
		stmt.executeUpdate(str1);
		stmt=null;
		disconnect();
		
	}
	public ProductMaster getARecord(String id) throws SQLException
	{
		ProductMaster pm=null;
		connect();
		Statement stmt= this.jdbcConnection.createStatement();
		ResultSet rs=stmt.executeQuery("select * from products where pid="+id);
		while(rs.next()) {
			pm= new ProductMaster(rs.getInt("pid"), rs.getString("pname"), rs.getString("pcost"), rs.getString("pdesc"));
		}
		rs=null;stmt=null;
		disconnect();
		return pm;
		
	}
	
	public void deleteProduct(String pid) throws SQLException{
		
		connect();
		Statement stmt= this.jdbcConnection.createStatement();
		stmt.executeUpdate("Delete from products where pid="+pid);
		stmt=null;
		disconnect();

	}
	 
	 
	// add DAO methods as per requirements
}