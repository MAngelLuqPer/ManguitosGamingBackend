/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entities.Expulsados;
import model.services.exceptions.NonexistentEntityException;

/**
 *
 * @author mangel
 */
public class ExpulsadosService implements Serializable {
     
    public ExpulsadosService(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Expulsados expulsado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(expulsado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Expulsados expulsado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            expulsado = em.merge(expulsado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = expulsado.getId();
                if (findExpulsados(id) == null) {
                    throw new NonexistentEntityException("The actividad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Expulsados expulsado;
            try {
                expulsado = em.getReference(Expulsados.class, id);
                expulsado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividad with id " + id + " no longer exists.", enfe);
            }
            em.remove(expulsado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Expulsados> findExpulsadosEntities() {
        return findExpulsadosEntities(true, -1, -1);
    }

    public List<Expulsados> findExpulsadosEntities(int maxResults, int firstResult) {
        return findExpulsadosEntities(false, maxResults, firstResult);
    }

    private List<Expulsados> findExpulsadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Expulsados.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Expulsados findExpulsados(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Expulsados.class, id);
        } finally {
            em.close();
        }
    }

    public int getExpulsadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Expulsados> rt = cq.from(Expulsados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<Expulsados> findExpulsadosByComunidad(Long comunidadId) {
    return emf.createEntityManager()
        .createQuery("SELECT e FROM Expulsados e WHERE e.comunidad.id = :comunidadId", Expulsados.class)
        .setParameter("comunidadId", comunidadId)
        .getResultList();
}

public List<Expulsados> findExpulsionesByComunidadId(Long comunidadId) {
    EntityManager em = getEntityManager();
    return em.createQuery("SELECT e FROM Expulsados e WHERE e.comunidad.id = :comunidadId", Expulsados.class)
             .setParameter("comunidadId", comunidadId)
             .getResultList();
}
    
}
