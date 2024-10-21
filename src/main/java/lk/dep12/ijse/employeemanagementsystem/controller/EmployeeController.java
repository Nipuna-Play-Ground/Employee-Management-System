package lk.dep12.ijse.employeemanagementsystem.controller;


import lk.dep12.ijse.employeemanagementsystem.dto.EmployeeDTO;
import lk.dep12.ijse.employeemanagementsystem.dto.ResponseDTO;
import lk.dep12.ijse.employeemanagementsystem.repo.EmployeeRepo;
import lk.dep12.ijse.employeemanagementsystem.service.EmployeeService;
import lk.dep12.ijse.employeemanagementsystem.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try{
           String rsp = employeeService.saveEmployee(employeeDTO);
           if (rsp.equals("00")){
               responseDTO.setCode(VarList.RSP_SUCCESS);
               responseDTO.setMessage("New Employee Record Added Successfully");
               responseDTO.setContent(employeeDTO);
               return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
           }else if (rsp.equals("06")){
               responseDTO.setCode(VarList.RSP_DUPLICATED);
               responseDTO.setMessage("Employee Already Exists");
               responseDTO.setContent(employeeDTO);
               return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
           }else{
               responseDTO.setCode(VarList.RSP_FAIL);
               responseDTO.setMessage("New Employee Record Adding Failed");
               responseDTO.setContent(null);
               return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
           }
        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try{
            String rsp = employeeService.updateEmployee(employeeDTO);
            if (rsp.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Employee Record Updated Successfully");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if (rsp.equals("01")){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Record Found");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Employee Record Update Failed");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllEmployees")
    public ResponseEntity getAllEmployees() {
        try{
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("All Employee Records Found");
            responseDTO.setContent(employeeService.getAllEmployee());
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchEmployeeById/{employeeId}")
    public ResponseEntity searchEmployeeById(@PathVariable int employeeId) {
        try{
            EmployeeDTO employeeDTO = employeeService.searchEmployeeById(employeeId);
           if(employeeDTO != null){
               responseDTO.setCode(VarList.RSP_SUCCESS);
               responseDTO.setMessage("Employee Record Found Successfully");
               responseDTO.setContent(employeeDTO);
               return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
           }else{
               responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
               responseDTO.setMessage("No Employee Record Found");
               responseDTO.setContent(employeeDTO);
               return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
           }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteEmployeeById/{employeeId}")
    public ResponseEntity deleteEmployeeById(@PathVariable int employeeId) {
        try{
            Boolean rsp = employeeService.deleteEmployeeById(employeeId);
            if(rsp == true){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Employee Record Deleted Successfully");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Record Found");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
