/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author uzair
 */
@Stateless
public class EmpFacade extends AbstractFacade<Emp> {

    @PersistenceContext(unitName = "Emp_ServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpFacade() {
        super(Emp.class);
    }
    
}
