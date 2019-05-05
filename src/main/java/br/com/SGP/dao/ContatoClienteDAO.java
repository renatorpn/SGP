package br.com.SGP.dao;

import br.com.SGP.entities.Cadastro;
import br.com.SGP.exception.CadastroException;
import br.com.SGP.entities.ContatoCliente;
import java.util.List;

import javax.persistence.EntityManager;

public class ContatoClienteDAO {

    public void save(ContatoCliente contato) {

        EntityManager em = ConnectionFactory.getEntityManager();

        try {
            em.getTransaction().begin();
            if (contato.getId() == null) {
                em.persist(contato);
            } else {
                em.merge(contato);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();

            throw new CadastroException("Erro ao realizar cadastro.\n"
                    + e.getMessage());
        } finally {
            em.close();
        }
    }

    public ContatoCliente findById(Long id) {

        EntityManager em = new ConnectionFactory().getEntityManager();
        ContatoCliente contato = null;

        try {

            contato = em.find(ContatoCliente.class, id);

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }

        return contato;

    }

    public List<ContatoCliente> findAll(Cadastro cliente) {

        EntityManager em = new ConnectionFactory().getEntityManager();
        List<ContatoCliente> contatos = null;

        try {
            contatos = em.createQuery("SELECT b FROM ContatoCliente b WHERE b.cliente = :idcadastro")
                    .setParameter("idcadastro", cliente)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }

        return contatos;
    }

    public void remove(Long id) {

        EntityManager em = new ConnectionFactory().getEntityManager();
        ContatoCliente contato = null;

        try {

            contato = em.find(ContatoCliente.class, id);

            em.getTransaction().begin();
            em.remove(contato);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.err.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

}
