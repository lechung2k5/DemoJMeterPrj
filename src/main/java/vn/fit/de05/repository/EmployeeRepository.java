package vn.fit.de05.repository;

import vn.fit.de05.model.Employee;
import vn.fit.de05.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    public List<Employee> findAll(){
        List<Employee> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees ORDER BY id");
             ResultSet rs = ps.executeQuery()){
            while (rs.next()) list.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("salary"),
                    rs.getInt("department_id")
            ));
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public Employee findById(int id){
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE id = ?")){

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("salary"),
                            rs.getInt("department_id")
                    );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Employee save(Employee o){
        String sql = "INSERT INTO employees(name,salary,department_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, o.getName());
            ps.setBigDecimal(2, o.getSalary());
            ps.setInt(3, o.getDepartmentId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next())
                    o.setId(rs.getInt(1));
            }
            return o;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    public List<Employee> findByDepartmentSalaryRange(int departmentId, BigDecimal minSalary, BigDecimal maxSalary){
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department_id = ? AND salary BETWEEN ? AND ? ORDER BY salary ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, departmentId);
                ps.setBigDecimal(2, minSalary);
                ps.setBigDecimal(3, maxSalary);
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()){
                        list.add(new Employee(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("salary"),
                                rs.getInt("department_id")
                        ));
                    }
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
