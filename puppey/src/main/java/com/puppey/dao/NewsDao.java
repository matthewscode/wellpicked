package com.puppey.dao;

import java.util.List;

import com.puppey.domain.News;

public interface NewsDao {
	public void addNews(News news);
	public News getNewsBySlug(String slug);
	public News getNewsById(int newsId);
	public void updateNews(News newsToConfirm);
	public List<News> getAllNews();
}
