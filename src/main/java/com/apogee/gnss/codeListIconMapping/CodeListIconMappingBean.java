/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.codeListIconMapping;

/**
 *
 * @author admin
 */
public class CodeListIconMappingBean {
    
private String code;
private int code_list_id;
private String parent_code;
private int generation;
private String is_super_child;
private String prefix;
private String remark;
private int image_id;
private String image_path;

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

  

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }


    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCode_list_id() {
        return code_list_id;
    }

    public void setCode_list_id(int code_list_id) {
        this.code_list_id = code_list_id;
    }
    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getIs_super_child() {
        return is_super_child;
    }

    public void setIs_super_child(String is_super_child) {
        this.is_super_child = is_super_child;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }






}
