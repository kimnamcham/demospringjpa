package com.example.demojpa.repository;

import com.example.demojpa.entity.Mobile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
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

    public List<Mobile> filterMobileByTypeAndActive(Integer type, Integer active) {
        CriteriaQuery<Mobile> criteriaQuery = criteriaBuilder.createQuery(Mobile.class);
        Root<Mobile> root = criteriaQuery.from(Mobile.class);
        List<Predicate> predicates = new ArrayList<>();
        if (type != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
        }
        if (active != null) {
            predicates.add(criteriaBuilder.equal(root.get("active"), active));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Mobile> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

//    public List<Mobile> filterMobileList(Integer typeFrom, Integer typeTo,
//                                         Integer active,
//                                         Date dateFrom, Date dateTo,
//                                         String name,
//                                         Integer start,
//                                         Integer limit) {
//
//        CriteriaQuery<Mobile> criteriaQuery = criteriaBuilder.createQuery(Mobile.class);
//        Root<Mobile> root = criteriaQuery.from(Mobile.class);
//        List<Predicate> predicates = new ArrayList<>();
//        if (typeFrom != null && typeTo != null) {
//            predicates.add(criteriaBuilder.between(root.get("type"), typeFrom, typeTo));
//        }
//        if (active != null) {
//            predicates.add(criteriaBuilder.equal(root.get("active"), active));
//        }
//        if (dateFrom != null && dateTo != null) {
//            predicates.add(criteriaBuilder.between(root.get("createdTime"), dateFrom, dateTo));
//        }
//        if (StringUtils.hasText(name)) {
//            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
//        }
//        criteriaQuery.where(predicates.toArray(new Predicate[0]));
//        TypedQuery<Mobile> query = entityManager.createQuery(criteriaQuery);
//        return query.getResultList();
//    }

    public static Specification<Mobile> buildFilterSpecification(Integer typeFrom, Integer typeTo,
                                                                 Integer active,
                                                                 Date dateFrom, Date dateTo,
                                                                 String name) {
        return (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (typeFrom != null && typeTo != null) {
                predicates.add(criteriaBuilder.between(root.get("type"), typeFrom, typeTo));
            }
            if (active != null) {
                predicates.add(criteriaBuilder.equal(root.get("active"), active));
            }
            if (dateFrom != null && dateTo != null) {
                predicates.add(criteriaBuilder.between(root.get("createdTime"), dateFrom, dateTo));
            }
            if (StringUtils.hasText(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }

}
