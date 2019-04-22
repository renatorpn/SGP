/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.dao;

import br.com.SGP.entities.Evento;
import br.com.SGP.exception.CadastroException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author lucas
 */
public class EventoDAO {
  public void save(Evento evento) {
        
        EntityManager em = ConnectionFactory.getEntityManager();

        try {
            em.getTransaction().begin();
            if(evento.getIdevento()== null){
                em.persist(evento);
            }else{
                em.merge(evento);
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
    
    
     public Evento findById(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        Evento evento = null;
        
        try{
            
            evento = em.find(Evento.class, id);
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return evento;
        
    }
    
    public List<Evento> findAll(){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<Evento> eventos = null;
        
        try{
            eventos = em.createQuery("SELECT c FROM Evento c ").getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return eventos;
    }
    
    public void remove(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        Evento evento = null;
        
        try{
            
            evento = em.find(Evento.class, id);
            
            em.getTransaction().begin();
            em.remove(evento);
            em.getTransaction().commit();
            
        }catch(Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
              
    }  
}
