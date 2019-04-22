/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.dao;

import br.com.SGP.entities.CategoriaProduto;
import br.com.SGP.exception.CadastroException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author lucas
 */
public class CategoriaProdutoDAO {
    
    public void save(CategoriaProduto categoria) {
        
        EntityManager em = ConnectionFactory.getEntityManager();

        try {
            em.getTransaction().begin();
            if(categoria.getIdcategoria()== null){
                em.persist(categoria);
            }else{
                em.merge(categoria);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();

            throw new CadastroException("Erro ao savlar categoria.\n" +
                    e.getMessage());
        } finally {
            em.close();
        }
    }
    
    
     public CategoriaProduto findById(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        CategoriaProduto categoria = null;
        
        try{
            
            categoria = em.find(CategoriaProduto.class, id);
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return categoria;
        
    }
    
    public List<CategoriaProduto> findAll(){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<CategoriaProduto> categorias = null;
        
        try{
            categorias = em.createQuery("SELECT c FROM CategoriaProduto c ").getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return categorias;
    }
    
    public void remove(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        CategoriaProduto categoria = null;
        
        try{
            
            categoria = em.find(CategoriaProduto.class, id);
            
            em.getTransaction().begin();
            em.remove(categoria);
            em.getTransaction().commit();
            
        }catch(Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
              
    }
}
