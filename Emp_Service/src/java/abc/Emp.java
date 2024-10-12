/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author uzair
 */
@Entity
@XmlRootElement
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    private String Emp_Dept;

    /**
     * Get the value of Emp_Dept
     *
     * @return the value of Emp_Dept
     */
    public String getEmp_Dept() {
        return Emp_Dept;
    }

    /**
     * Set the value of Emp_Dept
     *
     * @param Emp_Dept new value of Emp_Dept
     */
    public void setEmp_Dept(String Emp_Dept) {
        this.Emp_Dept = Emp_Dept;
    }

    private int Emp_Sal;

    /**
     * Get the value of Emp_Sal
     *
     * @return the value of Emp_Sal
     */
    public int getEmp_Sal() {
        return Emp_Sal;
    }

    /**
     * Set the value of Emp_Sal
     *
     * @param Emp_Sal new value of Emp_Sal
     */
    public void setEmp_Sal(int Emp_Sal) {
        this.Emp_Sal = Emp_Sal;
    }

    private String Emp_Add;

    /**
     * Get the value of Emp_Add
     *
     * @return the value of Emp_Add
     */
    public String getEmp_Add() {
        return Emp_Add;
    }

    /**
     * Set the value of Emp_Add
     *
     * @param Emp_Add new value of Emp_Add
     */
    public void setEmp_Add(String Emp_Add) {
        this.Emp_Add = Emp_Add;
    }

    private int Emp_id;

    /**
     * Get the value of Emp_id
     *
     * @return the value of Emp_id
     */
    public int getEmp_id() {
        return Emp_id;
    }

    /**
     * Set the value of Emp_id
     *
     * @param Emp_id new value of Emp_id
     */
    public void setEmp_id(int Emp_id) {
        this.Emp_id = Emp_id;
    }

    private String Emp_name;

    /**
     * Get the value of Emp_name
     *
     * @return the value of Emp_name
     */
    public String getEmp_name() {
        return Emp_name;
    }

    /**
     * Set the value of Emp_name
     *
     * @param Emp_name new value of Emp_name
     */
    public void setEmp_name(String Emp_name) {
        this.Emp_name = Emp_name;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emp)) {
            return false;
        }
        Emp other = (Emp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "abc.Emp[ id=" + id + " ]";
    }
    
}
