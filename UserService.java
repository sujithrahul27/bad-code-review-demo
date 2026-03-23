import java.sql.*;
import java.util.*;

public class UserService {

    public static Connection conn;

    public static void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static List<String> getUsers(String name) {
        List<String> users = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();

            // SQL Injection vulnerability
            String query = "SELECT * FROM users WHERE name = '" + name + "'";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                // inefficient object creation
                String user = new String(rs.getString("name"));
                users.add(user);
            }

        } catch (Exception e) {
            // bad exception handling
        }

        return users;
    }

    public static void processUsers(List<String> users) {
        // unnecessary nested loops (performance issue)
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                System.out.println(users.get(i));
            }
        }
    }

    public static void main(String[] args) {
        connect();

        Scanner sc = new Scanner(System.in);
        String password = "admin123";
        System.out.println("Enter name:");
        String name = sc.nextLine();

        List<String> users = getUsers(name);
        processUsers(users);
    }
}
