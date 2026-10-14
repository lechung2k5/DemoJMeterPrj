package vn.fit.de05.service;

import vn.fit.de05.model.Employee;
import vn.fit.de05.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    private final EmployeeRepository repo = new EmployeeRepository();
    public List<Employee> findAll(){
        return repo.findAll();
    }
    public Employee findById(int id){
        return repo.findById(id);
    }
    public Employee save(Employee o){
        return (o != null) ? repo.save(o): null;
    }

}
