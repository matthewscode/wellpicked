package com.puppey.dao;

import java.util.List;

import com.puppey.domain.Template;

public interface TemplateDao {

    public List<Template> getAllTemplates();

    public Template getTemplateById(int id);

}
