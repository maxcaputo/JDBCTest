/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acn.nemo.service;

import com.acn.nemo.dao.DAOEmployee;
import com.acn.nemo.dto.DTOEmployee;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author massimo.caputo
 */
public class EmpService {
    
    private DAOEmployee daoEmp;
    private List<DTOEmployee> dtoEmp;
    

    /**
     * Il metodo della Business Logic restituiesce una lista valorizzata di DTO. 
     * @return List DTOEmployee
     * @throws IOException 
     */
    public List<DTOEmployee> retriveEmployees() throws IOException {
        
        daoEmp = new DAOEmployee();
        
        dtoEmp = daoEmp.findAllEmployee();
        
        return dtoEmp;
    }
    
    
    
    public int insertEmployee(){
        return daoEmp.insertNewEmployee();
    }
    
     public DTOEmployee findEmployeeID(String id){
        return daoEmp.findEmployeeByID(id);
    }
}
