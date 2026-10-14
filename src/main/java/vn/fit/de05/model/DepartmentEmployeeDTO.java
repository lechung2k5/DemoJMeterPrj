package vn.fit.de05.model;

import java.util.List;

public class DepartmentEmployeeDTO {
    public Department department;
    public List<Employee> employees;

    public DepartmentEmployeeDTO() {
    }

    public DepartmentEmployeeDTO(Department department, List<Employee> employees) {
        this.department = department;
        this.employees = employees;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
