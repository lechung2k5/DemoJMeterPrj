package vn.fit.de05.service;

import vn.fit.de05.model.Department;
import vn.fit.de05.model.DepartmentEmployeeDTO;
import vn.fit.de05.model.Employee;
import vn.fit.de05.model.ThongKeDTO;
import vn.fit.de05.repository.DepartmentRepository;
import vn.fit.de05.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;

public class DepartmentService {
    private final DepartmentRepository repo = new DepartmentRepository();
    private final EmployeeRepository empRepo = new EmployeeRepository();
    public List<Department> findAll(){
        return repo.findAll();
    }
    public Department findById(int id){
        return repo.findById(id);
    }
    public DepartmentEmployeeDTO getDepartmentWithChildren(int departmentId, BigDecimal minSalary, BigDecimal maxSalary){
        Department department = repo.findById(departmentId);
        if(department == null )
            return null;
        if(minSalary == null)
            minSalary = BigDecimal.ZERO;
        if(maxSalary == null)
            maxSalary = new BigDecimal("9999999999.99");
        List<Employee> list = empRepo.findByDepartmentSalaryRange(departmentId,minSalary,maxSalary);
        return new DepartmentEmployeeDTO(department, list);
    }
    public List<ThongKeDTO> getThongKe(){
        return repo.getThongKe();
    }
}
