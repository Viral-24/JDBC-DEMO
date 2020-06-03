import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
;

public class DB_Connection {

	 static PreparedStatement pstat;
	 static Connection con;
	public boolean getConnected() {
		String user="sa",pwd="sasa",url="jdbc:sqlserver://localhost:1433;databaseName=Detail";
		try {
			con=DriverManager.getConnection(url,user,pwd);
			//System.out.print("Coonection Sucessfully Done "+con.getCatalog());
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	public void getWriteDB() {
		if(getConnected()) {
			String query="insert into otherdetail values('Sai Road','52512121513')";
			try {
				pstat=con.prepareStatement(query);
				pstat.executeUpdate();
				System.out.println(" Update Done");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	public void getReadfromDB() {
		if(getConnected()) {
			String query="select * from otherdetail";
			try {
				pstat=con.prepareStatement(query);
				ResultSet result=pstat.executeQuery();
				
				while(result.next()) {
					System.out.print("Add :"+result.getString("addrs2")+"  ");
					System.out.println("Ph No:	"+result.getString("phoneNo2"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	public void getUpdate(String fname,String lname,String add,String city, String no,String res,String tec,String cou,int id ) {
		if(getConnected()) {
		String query="update Student set FirstName=?, LastName=?, Address=?,City=?,Number=?,Result=?,Teacher=?,Cource=? where StudentID=?";
		try {
			pstat=con.prepareStatement(query);
			pstat.setString(1,fname);
			pstat.setString(2,lname);
			pstat.setString(3,add);
			pstat.setString(4,city);
			pstat.setString(5,no);
			pstat.setString(6,res);
			pstat.setString(7,tec);
			pstat.setString(8,cou);
			pstat.setInt(9,id);
			pstat.executeUpdate();
			pstat.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error");
		}
		}
	}
		
	public static void main(String[] args) {
		

		DB_Connection d=new DB_Connection();
	//	d.getConnected();
	//	d.getWriteDB();
	//	d.getReadfromDB();
	//	d.getUpdate("Sachin","Das","juhu Road","Mumbai","98641651161","Pass","Sumit","ShareMarket", 5);
	}

}
