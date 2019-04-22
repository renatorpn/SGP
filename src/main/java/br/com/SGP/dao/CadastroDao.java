package br.com.SGP.dao;

import br.com.SGP.exception.CadastroException;
import br.com.SGP.entities.Cadastro;
import java.util.List;

import javax.persistence.EntityManager;

public class CadastroDao {

    public void save(Cadastro cadastro) {
        
        EntityManager em = ConnectionFactory.getEntityManager();

        try {
            em.getTransaction().begin();
            if(cadastro.getId() == null){
                em.persist(cadastro);
            }else{
                em.merge(cadastro);
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
    
    
     public Cadastro findById(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        Cadastro cadastro = null;
        
        try{
            
            cadastro = em.find(Cadastro.class, id);
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return cadastro;
        
    }
    
    public List<Cadastro> findAll(){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<Cadastro> cadastros = null;
        
        try{
            cadastros = em.createQuery("SELECT c FROM Cadastro c ").getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return cadastros;
    }
    
    public void remove(Long id){
        
        EntityManager em = new ConnectionFactory().getEntityManager();
        Cadastro cadastro = null;
        
        try{
            
            cadastro = em.find(Cadastro.class, id);
            
            em.getTransaction().begin();
            em.remove(cadastro);
            em.getTransaction().commit();
            
        }catch(Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
              
    }
    
}
