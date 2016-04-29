package com.puppey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;

import com.puppey.dao.NewsDao;
import com.puppey.domain.News;
import com.puppey.util.Utility;

@Service("newsService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public ObjectError addNews(News news) {
        news.setSlug(Utility.slugify(news.getNewsTitle()));
        if(checkIfSlugExists(news.getSlug())){
            return(new ObjectError("slug", "slug exists"));
        }else{
		newsDao.addNews(news);
		return null;
        }
	}

	@Override
	@Transactional
	public Boolean checkIfSlugExists(String slug) {
		News slugChecker = newsDao.getNewsBySlug(slug);
		if(slugChecker == null){
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public News getNewsById(int newsId) {
		return newsDao.getNewsById(newsId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateNews(News newsToConfirm) {
		newsDao.updateNews(newsToConfirm);
	}

	@Override
	@Transactional
	public List<News> getAllNews() {
		return newsDao.getAllNews();
	}

	@Override
	@Transactional
	public List<News> getLatestNews(int amount) {
		return newsDao.getLatestNews(amount);
	}

}
