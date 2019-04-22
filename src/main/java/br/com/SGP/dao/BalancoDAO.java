/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.dao;

import br.com.SGP.entities.Balanco;
import br.com.SGP.entities.Cadastro;

import br.com.SGP.exception.CadastroException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author lucas
 */
public class BalancoDAO {
    
    
    public void save(Balanco balanco) {
        
        EntityManager em = ConnectionFactory.getEntityManager();

        try {
            em.getTransaction().begin();
            if(balanco.getId() == null){
                em.persist(balanco);
            }else{
                em.merge(balanco);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();

            throw new CadastroException("Erro ao realizar cadastro.\n" +
                    e.getMessage());
        } finally {
            em.close();
        }
    }
    
    
     public Balanco findById(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        Balanco balanco = null;
        
        try{
            
            balanco = em.find(Balanco.class, id);
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return balanco;
        
    }
    
    public List<Balanco> findAll(){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<Balanco> balancos = null;
        
        try{
            balancos = em.createQuery("SELECT c FROM Balanco c ").getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return balancos;
    }
    
        public List<Balanco> findAllByCliente(Cadastro cliente){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<Balanco> balancos = null;
        
        try{
            balancos = em.createQuery("SELECT b FROM Balanco b WHERE b.cliente = :idcadastro")
                    .setParameter("idcadastro", cliente)
                    .getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return balancos;
    }
    
    public void remove(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        Balanco balanco = null;
        
        try{
            
            balanco = em.find(Balanco.class, id);
            
            em.getTransaction().begin();
            em.remove(balanco);
            em.getTransaction().commit();
            
        }catch(Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
              
    }
    
}
