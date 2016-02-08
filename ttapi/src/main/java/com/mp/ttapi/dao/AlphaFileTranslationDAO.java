package com.mp.ttapi.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mp.ttapi.domain.FileTranslation;
import com.mp.ttapi.domain.ImageChecksum;

@Repository("fileTranslationDAO")
public class AlphaFileTranslationDAO implements FileTranslationDAO{

	@Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<FileTranslation> getAllFileTranslations() {
        return sessionFactory.getCurrentSession().createCriteria(FileTranslation.class).list();
    }

	@Override
	public boolean createImageChecksum(ImageChecksum ic) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(ic);
			return true;
		}catch(HibernateException e){
			return false;
		}
	}

	@Override
	public ImageChecksum getImageChecksumByChecksum(int checksum) {
		return (ImageChecksum) sessionFactory.getCurrentSession().createCriteria(ImageChecksum.class).add(Restrictions.eq("checksum", checksum)).uniqueResult();
	}

	@Override
	public boolean createFileTranslation(FileTranslation ft) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(ft);
			return true;
		}catch(HibernateException e){
			return false;
		}
	}


}