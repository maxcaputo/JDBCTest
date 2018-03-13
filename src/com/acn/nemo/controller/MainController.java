/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acn.nemo.controller;

import com.acn.nemo.dto.DTOEmployee;
import com.acn.nemo.service.EmpService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author massimo.caputo
 */
public class MainController {

    private EmpService empService;
    private List<DTOEmployee> dto;

    public MainController() {
        empService = new EmpService();
    }
    
    
    /**
     * Il metodo ritorna una lista degli impiegati
     * @return List di Dto degli Employees
     */
    public List<DTOEmployee> retriveEmployees()  {
        try {
            dto = empService.retriveEmployees();
            
            for (DTOEmployee dTOEmployee : dto) {
                System.out.println("Welcome "+dTOEmployee.getFirstName()+ " "+ dTOEmployee.getLastName());
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
        return dto;
    }


    
    
}
