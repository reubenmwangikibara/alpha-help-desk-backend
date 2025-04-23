package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeRequestDTO;
import com.alpha.alpha_help_desk_backend.dto.request.UserDTO;
import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import com.alpha.alpha_help_desk_backend.mapper.EmployeeInvoiceMapper;
import com.alpha.alpha_help_desk_backend.mapper.EmployeeMapper;
import com.alpha.alpha_help_desk_backend.service.EmployeeService;
import com.alpha.alpha_help_desk_backend.service.UserService;
import com.alpha.alpha_help_desk_backend.utils.ResponseService;
import com.alpha.alpha_help_desk_backend.utils.UtilService;
import com.alpha.alpha_help_desk_backend.utils.db.EmployeeDBUtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDBUtilService employeeDBUtilService;
    private final UserService userService;
    private final ResponseService responseService;
    private final ModelMapper modelMapper;
    public BaseApiResponse addEmployeeDetails (EmployeeRequestDTO employeeRequestDTO) throws Exception {
       //let's create a user
        char firstChar = employeeRequestDTO.getFirstName().charAt(0);
        var employeeNumber =UtilService.employeeNumberGenerator();
        var userName = firstChar+ employeeRequestDTO.getLastName()+employeeNumber;
        var userDetails = UserDTO.builder()
                .phoneNumber(employeeRequestDTO.getPhoneNumber())
                .userName(userName)
                .status(1)
                .firstName(employeeRequestDTO.getFirstName())
                .lastName(employeeRequestDTO.getLastName())
                .password(userName)
                .role("USER")
                .build();
        var createdUser = userService.addNewUser(userDetails);

        var employee = EmployeeEntity.builder()
                .status(1)
                .employeeNumber(employeeNumber)
                .nationalId(employeeRequestDTO.getNationalId())
                .areaOfResidence(employeeRequestDTO.getAreaOfResidence())
                .userEntity(createdUser)
                .build();

        log.info("We are about to save employee details {}",new Gson().toJson(employee));
        var savedEmployee = employeeDBUtilService.saveEmployeeDetails(employee);
        log.info("employee details saved Successfully");
        return responseService.buildSuccessApiResponseDto(List.of(savedEmployee),1);
    }

    /**
     * @param tid
     * @param employeeRequestDTO
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse updateEmployeeDetails(Long tid, EmployeeRequestDTO employeeRequestDTO) throws Exception {
        var employeeDetails = employeeDBUtilService.getEmployeeByEmployeeEmployeeID(tid);
        if(employeeDetails.isEmpty())
        {
            throw new Exception("Employee details not found"+tid);
        }
        var employee = employeeDetails.get();
        employee.setStatus(Integer.valueOf(employeeRequestDTO.getStatus()));
        employee.setNationalId(employeeRequestDTO.getNationalId());
        employee.setAreaOfResidence(employeeRequestDTO.getAreaOfResidence());

        employee.setEmployeeNumber(employee.getEmployeeNumber());
        log.info("Updating this Details {}",employee);
        var savedRecord = employeeDBUtilService.saveEmployeeDetails(employee);
        var finalSavedRecord = EmployeeMapper.mapTODto(savedRecord);
        return responseService.buildSuccessApiResponseDto(List.of(finalSavedRecord),1);
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse fetchEmployeeDetails() throws Exception {
        var employees =  employeeDBUtilService.getActiveEmployees();
        log.info("Fetched employees {}",employees);
        var empDetails = employees.stream()
                .map(EmployeeMapper::mapTODto)
                .toList();
        return responseService.buildSuccessApiResponseDto(List.of(empDetails),employees.size());

    }


}
