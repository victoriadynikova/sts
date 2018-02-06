package us.sts.mantis.tests;

import org.testng.annotations.Test;


import java.sql.*;
import java.util.ArrayList;

public class DbConnectionTests {

    public static ArrayList<String> testDbConnection(){
        Connection conn = null;
        ArrayList <String > users = new ArrayList<String>();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?"+"user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select username from mantis_user_table");
            while(rs.next()){
                users.add(new String (rs.getString("username")));
            }
            //System.out.println(users);
            rs.close();
            st.close();
            conn.close();


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return users;
    }

}
