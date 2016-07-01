package com.sense.cloud.entity;

public class Terminal {
    private String id;

    private String name;

    private String projectName;

    private String responsibleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName == null ? null : responsibleName.trim();
    }


    @Override
    public String toString() {
        return "Terminal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", projectName='" + projectName + '\'' +
                ", responsibleName='" + responsibleName + '\'' +
                '}';
    }
}