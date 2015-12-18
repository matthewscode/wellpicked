package com.puppey.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.Template;

@Repository("templateDao")
public class HibernateTemplateDao implements TemplateDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Template> getAllTemplates() {
        return sessionFactory.getCurrentSession().createCriteria(Template.class).list();
    }

    @Override
    public Template getTemplateById(int id) {
        return (Template) sessionFactory.getCurrentSession().get(Template.class, id);
    }

}
