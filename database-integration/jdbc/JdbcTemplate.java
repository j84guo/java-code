// import jdbc package
import java.sql.*;

public class JdbcTemplate {

   // driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost/EMP";

   //  credentials
   static final String USER = "root";
   static final String PASS = "0439";

   public static void main(String[] args) {

      // connection context
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;

      try{
         // registering a driver means loading the class into memory as a jdbc implementation
         Class.forName("com.mysql.jdbc.Driver");

         // open connection
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER,PASS);

         // create statement
         System.out.println("Creating statement...");
         stmt = conn.createStatement();

         // execute query
         String sql = "SELECT id, first, last, age FROM Employees";
         rs = stmt.executeQuery(sql);

         // extract result data using result set iterator
         while(rs.next()){

            // get columns
            int id  = rs.getInt("id");
            int age = rs.getInt("age");
            String first = rs.getString("first");
            String last = rs.getString("last");

            // print
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);
         }

      }catch(SQLException se){

         // jdbc exceptions
         se.printStackTrace();
      }catch(Exception e){

         // other exceptions
         e.printStackTrace();
      }finally{

         // finally block used to close resources
         try{
            if(rs!=null)
               conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }

         try{
            if(stmt!=null) stmt.close();
         }catch(SQLException se){
            se.printStackTrace();
         }

         try{
            if(conn!=null)
               conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }
      }

      System.out.println("Goodbye!");
   }
}
