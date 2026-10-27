
import java.sql.*;

public class faulty {
    private static String URL="jdbc:mysql://localhost:3306/mydb";
    private static String USER="root";
    private static String PASSWORD="Miling@ndhi717";

    public static void main(String[] args) {
        try(Connection conn= DriverManager.getConnection(URL,USER,PASSWORD)){
            System.out.println("Database is connected");
            insert(conn,101,"Milin","CS","associateprofessor",10000);
            searchFaulty(conn,101);
            update(conn,"professor",10000,101);
            selectFaultyByDepartment(conn,"CS");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insert(Connection conn,int faulty_id,String faulty_name,String department,String designation,int salary){
        String sql="INSERT INTO faulty VALUES ("+faulty_id+",'"+faulty_name+"','"+department+"','"+designation+"',"+salary+")";
        try(Statement stmt=conn.createStatement()){
            int rows=stmt.executeUpdate(sql);
            System.out.println("Inserted"+rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void searchFaulty(Connection conn, int faulty_id) {
        String sql = "SELECT * FROM faulty WHERE faulty_id = " + faulty_id;

        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                int id = resultSet.getInt("faulty_id");
                String name = resultSet.getString("faulty_name");
                String department = resultSet.getString("department");
                String designation = resultSet.getString("designation");
                double salary = resultSet.getDouble("salary");

                System.out.println("Faculty Details:");
                System.out.println(id + ":" + name + ":" + department
                        + ":" + designation + ":" + salary);
            } else {
                System.out.println("Faculty ID not found!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void update(Connection conn,String designation,int salary,int id){
        String sql="UPDATE faulty SET designation='"+designation+"',salary='"+salary+"' WHERE faulty_id="+id;
        try(Statement stmt=conn.createStatement()){
            int rows=stmt.executeUpdate(sql);
            System.out.println("Updated"+rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void selectFaultyByDepartment(Connection conn, String department) {
        String sql = "SELECT * FROM faulty WHERE department = '" + department + "'";

        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);

            System.out.println("Faculty List:");

            boolean found = false;

            while (resultSet.next()) {
                found = true;

                int id = resultSet.getInt("faulty_id");
                String name = resultSet.getString("faulty_name");
                String dept = resultSet.getString("department");
                String designation = resultSet.getString("designation");
                double salary = resultSet.getDouble("salary");

                System.out.println(id + ":" + name + ":" + dept + ":"
                        + designation + ":" + salary);
            }

            if (!found) {
                System.out.println("No faculty found in this department.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
