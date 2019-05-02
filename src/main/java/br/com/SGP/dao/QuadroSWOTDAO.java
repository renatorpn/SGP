/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.dao;

import br.com.SGP.entities.QuadroSWOT;
import br.com.SGP.entities.Cadastro;
import br.com.SGP.exception.CadastroException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class QuadroSWOTDAO {

    public void save(QuadroSWOT quadroSWOT){
        EntityManager em = ConnectionFactory.getEntityManager();

        try {
            em.getTransaction().begin();
            if(quadroSWOT.getIdquadroswot() == null){
                em.persist(quadroSWOT);
            }else{
                em.merge(quadroSWOT);
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
    
    public QuadroSWOT findById(Long id){
        EntityManager em = new ConnectionFactory().getEntityManager();
        QuadroSWOT quadroSWOT = null;
        
        try{
            
            quadroSWOT = em.find(QuadroSWOT.class, id);
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return quadroSWOT;
    }
    
    public List<QuadroSWOT> findAll(){
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<QuadroSWOT> quadroSWOTs = null;
        
        try{
            quadroSWOTs = em.createQuery("SELECT c FROM Balanco c ").getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return quadroSWOTs;
    }
    
    public List<QuadroSWOT> findAllByCliente(Cadastro cliente){
        EntityManager em = new ConnectionFactory().getEntityManager();
        List<QuadroSWOT> quadroSWOTs = null;
        
        try{
            quadroSWOTs = em.createQuery("SELECT b FROM QuadroSWOT b WHERE b.cliente = :idcadastro")
                    .setParameter("idcadastro", cliente)
                    .getResultList(); 
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        
        return quadroSWOTs;
    }
}
