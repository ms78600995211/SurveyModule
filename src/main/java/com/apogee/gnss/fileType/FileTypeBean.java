/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.fileType;

/**
 *
 * @author lENOVO
 */
public class FileTypeBean {

    private int file_type_id;
    private String file_extension;
    private String file_category;
    private String remark;

    /**
     * @return the file_type_id
     */
    public int getFile_type_id() {
        return file_type_id;
    }

    /**
     * @param file_type_id the file_type_id to set
     */
    public void setFile_type_id(int file_type_id) {
        this.file_type_id = file_type_id;
    }

    /**
     * @return the file_extension
     */
    public String getFile_extension() {
        return file_extension;
    }

    /**
     * @param file_extension the file_extension to set
     */
    public void setFile_extension(String file_extension) {
        this.file_extension = file_extension;
    }

    /**
     * @return the file_category
     */
    public String getFile_category() {
        return file_category;
    }

    /**
     * @param file_category the file_category to set
     */
    public void setFile_category(String file_category) {
        this.file_category = file_category;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
