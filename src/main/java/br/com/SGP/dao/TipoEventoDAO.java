/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.dao;

import br.com.SGP.entities.TipoEvento;
import br.com.SGP.exception.CadastroException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Renato Nascimento
 */
public class TipoEventoDAO {
    public void save(TipoEvento tipoEvento) {
        
        EntityManager em = ConnectionFactory.getEntityManager();

        try {
            em.getTransaction().begin();
            if(tipoEvento.getIdTipoEvento()== null){
                em.persist(tipoEvento);
            }else{
                em.merge(tipoEvento);
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
    
    
     public TipoEvento findById(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        TipoEvento tipoEvento = null;
        
        try{
            
            tipoEvento = em.find(TipoEvento.class, id);
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return tipoEvento;
        
    }
    
    public List<TipoEvento> findAll(){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<TipoEvento> tipoEventos = null;
        
        try{
            tipoEventos = em.createQuery("SELECT c FROM TipoEvento c ").getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return tipoEventos;
    }
    
    public void remove(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        TipoEvento tipoEvento = null;
        
        try{
            
            tipoEvento = em.find(TipoEvento.class, id);
            
            em.getTransaction().begin();
            em.remove(tipoEvento);
            em.getTransaction().commit();
            
        }catch(Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }  
    }  
}
