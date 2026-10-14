package vn.fit.de05.model;

import java.math.BigDecimal;

public class ThongKeDTO {
    private int departmentId;
    private String departmentName;
    private long employeeCount;
    private BigDecimal totalSalary;
    private BigDecimal averageSalary;

    public ThongKeDTO() {
    }

    public ThongKeDTO(int departmentId, String departmentName, long employeeCount, BigDecimal totalSalary, BigDecimal averageSalary) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeCount = employeeCount;
        this.totalSalary = totalSalary;
        this.averageSalary = averageSalary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }

    public BigDecimal getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(BigDecimal averageSalary) {
        this.averageSalary = averageSalary;
    }
}
