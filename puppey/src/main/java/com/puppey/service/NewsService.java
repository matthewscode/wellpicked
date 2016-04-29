package com.puppey.service;

import java.util.List;

import org.springframework.validation.ObjectError;

import com.puppey.domain.News;

public interface NewsService {
	public ObjectError addNews(News news);

	public Boolean checkIfSlugExists(String slug);

	public News getNewsById(int newsId);

	public void updateNews(News newsToConfirm);

	public List<News> getAllNews();

	public List<News> getLatestNews(int amount);
}
