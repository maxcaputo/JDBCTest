/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acn.nemo.dao;

import com.acn.nemo.dto.DTOEmployee;
import com.acn.nemo.utils.MainUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author massimo.caputo
 */
public class DAOEmployee {

    private static final String SQL_EMP = " select * from EMPLOYEES";
    private static final String SQL_EMP_ID = " select * from EMPLOYEES WHERE EMPLOYEE_ID = ?";
    private static final String INSERT_EMP = " INSERT INTO EMPLOYEES (EMPLOYEE_ID, FIRST_NAME,LAST_NAME, EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID) "
            + "values (998, 'massimo', 'caputo', 'maxcaputo@gmail.com', '23333333', '03/02/2018', 'IT_PROG', '12000', '0.3', 100, 80)";

    private Statement stm;
    private PreparedStatement pstm;
    private ResultSet rs;
    private List<DTOEmployee> dTOEmployee;

    private int rowDB = 0;

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public DAOEmployee() throws FileNotFoundException, IOException {
        dTOEmployee = new ArrayList<>();
    }

    /**
     * Il metodo recupera tutti i record da EMPLOYEES
     *
     * @return List DTOEmployee
     */
    public List<DTOEmployee> findAllEmployee() {
        try {
            //Richiedo la connesione (open connection)
            //Creo lo statement che conterrà la query
            stm = MainUtil.getConnetion().createStatement();
            //Eseguo la query
            rs = stm.executeQuery(SQL_EMP);

            //Il DTO di appoggio è del tipo ritornato dalla query
            DTOEmployee appEmp;

            //Leggo il risultato della query
            while (rs.next()) {
                //Istanziato il DTO
                appEmp = new DTOEmployee();
                //Riempio il DTO con i valori desiderti
                appEmp.setFirstName(rs.getString("FIRST_NAME"));
                appEmp.setLastName(rs.getString("LAST_NAME"));
                appEmp.setEmail(rs.getString("EMAIL"));
                //Aggiungo ogni singlo DTO al mio ArrayList di DTO
                dTOEmployee.add(appEmp);
            }

        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             try {
                stm.close();
                rs.close();
                //Chiudo la connesione!!
                MainUtil.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dTOEmployee;
    }

    public int insertNewEmployee() {
        try {
            //Richiedo la connesione (open connection)
            //Creo lo statement che conterrà la query
            stm = MainUtil.getConnetion().createStatement();
            System.out.println("1");
            //Eseguo la query
            rowDB = stm.executeUpdate(INSERT_EMP);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                rs.close();
                //Chiudo la connesione!!
                MainUtil.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rowDB;
    }

    
    public DTOEmployee findEmployeeByID(String id){
        //Il DTO di appoggio è del tipo ritornato dalla query
        DTOEmployee  appEmp = new DTOEmployee();
        try {
            //Richiedo la connesione (open connection)
            //Creo lo statement che conterrà la query
            pstm = MainUtil.getConnetion().prepareStatement(SQL_EMP_ID);
            
            pstm.setString(1, id);
            //Eseguo la query
            rs = pstm.executeQuery(SQL_EMP_ID);


            //Leggo il risultato della query
            while (rs.next()) {                
                //Riempio il DTO con i valori desiderti
                appEmp.setFirstName(rs.getString("FIRST_NAME"));
                appEmp.setLastName(rs.getString("LAST_NAME"));
                appEmp.setEmail(rs.getString("EMAIL"));
                
            }

        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             try {
                stm.close();
                rs.close();
                //Chiudo la connesione!!
                MainUtil.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return appEmp;
    }

}
