package com.abewkayew.serafelagi;

/**
 * Created by Abebe Kayimo on 3/20/2018.
 */

public class sera_datas {
    private int image_resource;
    private String company_name, job_title, job_date, job_desc, job_specs, job_degree_level;
    //empty constructor
    public sera_datas(){
    }

    public sera_datas(int image_resource, String company_name, String job_title,
                      String job_desc, String job_date, String job_specs, String job_degree_level){
        this.image_resource = image_resource;
        this.company_name = company_name;
        this.job_title = job_title;
        this.job_date = job_date;
        this.job_desc = job_desc;
        this.job_specs = job_specs;
        this.job_degree_level = job_degree_level;
    }

    public String getJob_specs() {
        return job_specs;
    }

    public void setJob_specs(String job_specs) {
        this.job_specs = job_specs;
    }

    public String getJob_degree_level() {
        return job_degree_level;
    }

    public void setJob_degree_level(String job_degree_level) {
        this.job_degree_level = job_degree_level;
    }

    public void setJob_desc(String job_desc) {
        this.job_desc = job_desc;
    }

    public String getJob_desc() {
        return job_desc;
    }

    public int getImage_resource() {
        return image_resource;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getJob_title() {
        return job_title;
    }

    public String getJob_date() {
        return job_date;
    }

    public void setImage_resource(int image_resource) {
        this.image_resource = image_resource;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public void setJob_date(String job_date) {
        this.job_date = job_date;
    }
}
