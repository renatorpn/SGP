/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.dao;

import br.com.SGP.entities.Balanco;
import br.com.SGP.entities.Cadastro;
import br.com.SGP.entities.ItemBalanco;
import br.com.SGP.exception.CadastroException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author lucas
 */
public class ItemBalancoDAO {
  public void save(ItemBalanco itembalanco) {
        
        EntityManager em = ConnectionFactory.getEntityManager();

        try {
            em.getTransaction().begin();
            if(itembalanco.getIdItemBalanco()== null){
                em.persist(itembalanco);
            }else{
                em.merge(itembalanco);
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
    
    
     public ItemBalanco findById(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        ItemBalanco itembalanco = null;
        
        try{
            
            itembalanco = em.find(ItemBalanco.class, id);
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return itembalanco;
        
    }
    
    public List<ItemBalanco> findAll(){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<ItemBalanco> itensbalanco = null;
        
        try{
            itensbalanco = em.createQuery("SELECT c FROM Balanco c ").getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return itensbalanco;
    }
    
    
     public List<ItemBalanco> findAllByBalanco(Balanco balanco){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<ItemBalanco> itens = null;
        
        try{
            itens = em.createQuery("SELECT b FROM ItemBalanco b WHERE b.idBalanco = :balanco")
                    .setParameter("balanco", balanco)
                    .getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return itens;
    }
    
    public void remove(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        ItemBalanco itembalanco = null;
        
        try{
            
            itembalanco = em.find(ItemBalanco.class, id);
            
            em.getTransaction().begin();
            em.remove(itembalanco);
            em.getTransaction().commit();
            
        }catch(Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
              
    }  
}
