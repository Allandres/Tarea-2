/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allan.zoologico;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author allan
 */
@Entity
@Table(name = "animales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animales.findAll", query = "SELECT a FROM Animales a")
    , @NamedQuery(name = "Animales.findById", query = "SELECT a FROM Animales a WHERE a.id = :id")
    , @NamedQuery(name = "Animales.findByNombre", query = "SELECT a FROM Animales a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Animales.findByTipo", query = "SELECT a FROM Animales a WHERE a.tipo = :tipo")
    , @NamedQuery(name = "Animales.findByRaza", query = "SELECT a FROM Animales a WHERE a.raza = :raza")
    , @NamedQuery(name = "Animales.findByDescripcion", query = "SELECT a FROM Animales a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Animales.findByPeligrosidad", query = "SELECT a FROM Animales a WHERE a.peligrosidad = :peligrosidad")
    , @NamedQuery(name = "Animales.findByFechaIngreso", query = "SELECT a FROM Animales a WHERE a.fechaIngreso = :fechaIngreso")})
public class Animales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 45)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 45)
    @Column(name = "RAZA")
    private String raza;
    @Size(max = 45)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "PELIGROSIDAD")
    private String peligrosidad;
    @Size(max = 45)
    @Column(name = "FECHA_INGRESO")
    private String fechaIngreso;
    
    @Transient
    private Date FechaIngresoDate;

    public Animales() {
    }

    public Animales(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(String peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animales)) {
            return false;
        }
        Animales other = (Animales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Date getFechaIngresoDate() {
        return FechaIngresoDate;
    }

    public void setFechaIngresoDate(Date FechaIngresoDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.fechaIngreso = format.format(FechaIngresoDate);
    }
    
    

    @Override
    public String toString() {
        return "com.allan.zoologico.Animales[ id=" + id + " ]";
    }
    
}
