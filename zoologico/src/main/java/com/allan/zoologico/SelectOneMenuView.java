/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allan.zoologico;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

@ManagedBean
public class SelectOneMenuView {

 private String peligrosidad; 

 @PostConstruct
    public void init() {
        
    }
 
    public String getConsole() {
        return peligrosidad;
    }
 
    public void setConsole(String console) {
        this.peligrosidad  = console;
    }
 
}
