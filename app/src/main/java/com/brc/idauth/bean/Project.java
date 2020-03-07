package com.brc.idauth.bean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-12
       Time     :  17:34
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class Project {

    private String projectName;
    private String projectId;


    public Project() {
    }

    public Project(String projectName, String projectId) {
        this.projectName = projectName;
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
