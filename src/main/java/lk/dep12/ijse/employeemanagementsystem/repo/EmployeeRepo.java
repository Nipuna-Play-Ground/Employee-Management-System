package lk.dep12.ijse.employeemanagementsystem.repo;

import lk.dep12.ijse.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
