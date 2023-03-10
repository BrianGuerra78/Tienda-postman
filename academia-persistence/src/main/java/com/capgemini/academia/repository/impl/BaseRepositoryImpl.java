package com.capgemini.academia.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;

/**
 * It is a base class of all the Repository's and every repository must extends this class.
 */
public abstract class BaseRepositoryImpl<T>{
	
	private static final Logger log = LoggerFactory.getLogger(BaseRepositoryImpl.class);
	
	private Class<T> entityClass;

	@PersistenceContext
	private EntityManager entityManager;


    public BaseRepositoryImpl() {
        this.entityClass = getEntityClass();
    }

    public EntityManager getEntityManager() {
    	return entityManager;
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(Integer entity) {
        getEntityManager().remove(getEntityManager().find(entityClass, entity));
    }

    public void remove(Long entity) {
        getEntityManager().remove(getEntityManager().find(entityClass, entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }


    @SuppressWarnings("unchecked")
    public <ID extends Number> List<T> findIn( ID... ids) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        Expression idsExpresion = root.in(ids);
        cq.where(idsExpresion);
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public String getPublicNameClass() {
        return entityClass.getSimpleName();
    }

    public <T> T getReference(Integer id) {
        try {
            return (T) getEntityManager().getReference(entityClass, id);
        } catch (EntityNotFoundException e) {
            log.error("Entidad no encontrada");
            return null;
        }

    }

    private Class<T> getEntityClass() {
        Class<T> entityClassTmp = null;
        try {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            entityClassTmp = (Class<T>) type.getActualTypeArguments()[0];
        } catch (Exception err) {
        	entityClassTmp = null;
        }

        return entityClassTmp;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void forcedFlush() {
        getEntityManager().flush();
    }


}
