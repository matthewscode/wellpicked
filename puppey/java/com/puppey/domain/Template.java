package com.puppey.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "template")
public class Template {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "template_id")
    private int templateId;
    
    @NotEmpty(message = "Please enter a template acronym")
    @Column(name= "template", unique = true)
    private String templateName;
    
//    @OneToMany(mappedBy = "template")
//    private List<Tournament> tournamentList;
    
    @Override
    public String toString(){
        return templateName;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplate() {
        return templateName;
    }

    public void setTemplate(String template) {
        this.templateName = template;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

//    public List<Tournament> getTournamentList() {
//        return tournamentList;
//    }
//
//    public void setTournamentList(List<Tournament> tournamentList) {
//        this.tournamentList = tournamentList;
//    }
//    
    
    

}
