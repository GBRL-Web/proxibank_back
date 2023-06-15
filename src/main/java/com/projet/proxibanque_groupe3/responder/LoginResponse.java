package com.projet.proxibanque_groupe3.responder;

import com.projet.proxibanque_groupe3.model.Employee;

public class LoginResponse {
    private Employee employee;
    private String errorMessage;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
