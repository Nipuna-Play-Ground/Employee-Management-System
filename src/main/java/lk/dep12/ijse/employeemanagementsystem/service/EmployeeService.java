package lk.dep12.ijse.employeemanagementsystem.service;

import jakarta.transaction.Transactional;
import lk.dep12.ijse.employeemanagementsystem.dto.EmployeeDTO;
import lk.dep12.ijse.employeemanagementsystem.entity.Employee;
import lk.dep12.ijse.employeemanagementsystem.repo.EmployeeRepo;
import lk.dep12.ijse.employeemanagementsystem.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveEmployee(EmployeeDTO employeeDTO){
        if(employeeRepo.existsById(employeeDTO.getEmpId())){
            return VarList.RSP_DUPLICATED;
        }else{
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }

}
