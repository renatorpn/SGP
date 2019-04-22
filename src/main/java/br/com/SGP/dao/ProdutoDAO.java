/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.dao;

import br.com.SGP.entities.Produto;
import br.com.SGP.exception.CadastroException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author lucas
 */
public class ProdutoDAO {
    
    public void save(Produto produto) {
        
        EntityManager em = ConnectionFactory.getEntityManager();

        try {
            em.getTransaction().begin();
            if(produto.getIdproduto() == null){
                em.persist(produto);
            }else{
                em.merge(produto);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();

            throw new CadastroException("Erro ao realizar produto.\n" +
                    e.getMessage());
        } finally {
            em.close();
        }
    }
    
    
     public Produto findById(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        Produto produto = null;
        
        try{
            
            produto = em.find(Produto.class, id);
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return produto;
        
    }
    
    public List<Produto> findAll(){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<Produto> produtos = null;
        
        try{
            produtos = em.createQuery("SELECT c FROM Produto c ").getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return produtos;
    }
    
    public void remove(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        Produto produto = null;
        
        try{
            
            produto = em.find(Produto.class, id);
            
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
            
        }catch(Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
              
    }
}
