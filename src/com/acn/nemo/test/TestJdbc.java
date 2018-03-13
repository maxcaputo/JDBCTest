/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acn.nemo.test;

import com.acn.nemo.controller.MainController;
import com.acn.nemo.dto.DTOEmployee;
import java.util.List;

/**
 *
 * @author massimo.caputo
 */
public class TestJdbc {
    
    
    public static void main(String[] args){
        
        MainController mc = new MainController();
        List<DTOEmployee> dtoEmp =  mc.retriveEmployees();
       
        mc.retriveEmployees();
        
    }
}
