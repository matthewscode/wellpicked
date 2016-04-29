package com.puppey.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.News;


@Repository("newsDao")
public class HibernateNewsDao implements NewsDao {

    @Autowired
    private SessionFactory sessionFactory;
    
	@Override
	public void addNews(News news){
		sessionFactory.getCurrentSession().save(news);
	}

	@Override
	public News getNewsBySlug(String slug) {
		return (News) sessionFactory.getCurrentSession().createCriteria(News.class)
                .add(Restrictions.eq("slug", slug)).uniqueResult();
	}

	@Override
	public News getNewsById(int newsId) {
		return (News) sessionFactory.getCurrentSession().get(News.class, newsId);
	}

	@Override
	public void updateNews(News newsToConfirm) {
	        sessionFactory.getCurrentSession().update(newsToConfirm);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getAllNews() {
		return sessionFactory.getCurrentSession().createCriteria(News.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getLatestNews(int amount) {
		return sessionFactory.getCurrentSession().createCriteria(News.class).addOrder(Order.desc("creation")).setMaxResults(amount).list();
	}
}
