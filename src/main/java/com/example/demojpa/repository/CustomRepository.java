package com.example.demojpa.repository;

import com.example.demojpa.entity.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    @PostConstruct
    public void init() {
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Mobile> filterMobileByTypeAndActive(Integer type, Integer active){

//        Query query = e

        CriteriaQuery<Mobile> criteriaQuery = criteriaBuilder.createQuery(Mobile.class);
        Root<Mobile> root = criteriaQuery.from(Mobile.class);
        List<Predicate> predicates = new ArrayList<>();
        if(type != null){
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
        }
        if(active != null){
            predicates.add(criteriaBuilder.equal(root.get("active"),active));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Mobile> query = entityManager.createQuery(criteriaQuery);
        return  query.getResultList();

    }

}
