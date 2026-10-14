package vn.fit.de05.repository;

import vn.fit.de05.model.Department;
import vn.fit.de05.model.ThongKeDTO;
import vn.fit.de05.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {
    public List<Department> findAll(){
        List<Department> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM departments ORDER BY id");
             ResultSet rs = ps.executeQuery()){
            while (rs.next()) list.add(new Department(
                    rs.getInt("id"),
                    rs.getString("name")
            ));
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public Department findById(int id){
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM departments WHERE id = ?")){

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return new Department(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<ThongKeDTO> getThongKe (){
        List<ThongKeDTO> list = new ArrayList<>();
        String sql = "SELECT p.id AS departmentId, p.name AS departmentName, " +
                "   COUNT(c.id) AS employeeCount, " +
                "   COALESCE(SUM(c.salary), 0) AS totalSalary, " +
                "   ROUND(COALESCE(AVG(c.salary),0),2) AS averageSalary " +
                "FROM departments p " +
                "LEFT JOIN employees c ON c.department_id = p.id " +
                "GROUP BY p.id, p.name " +
                "ORDER BY totalSalary DESC";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    list.add(new ThongKeDTO(
                            rs.getInt("departmentId"),
                            rs.getString("departmentName"),
                            rs.getLong("employeeCount"),
                            rs.getBigDecimal("totalSalary"),
                            rs.getBigDecimal("averageSalary")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
