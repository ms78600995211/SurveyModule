/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apogee.gnss.projectConfigurationMapping;

import com.apogee.gnss.surveyConfiguration.SurveyConfigurationBean;
import com.apogee.gnss.miscellaneousConfiguration.MiscellaneousConfigurationBean;
import com.apogee.gnss.dynamicProjectConfiguration.DynamicConfigMappingBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProjectConfigurationMappingModel {

    private Connection connection;
    private String COLOR_ERROR = "red";
    private String COLOR_OK = "green";
    private String message;
    private String color;

    public void saveProjectConfigurationMappingData(List<String> parameters) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        int count = 0;
        try {
            String project_configuration_mapping_name = parameters.get(0);
            String survey_configuration_id = parameters.get(1);
            String device_configuration_id = parameters.get(2);
            String satellite_configuration_id = parameters.get(3);
            String miscellaneous_configuration_id = parameters.get(4);
            String defaultConfig = parameters.get(5);
            String name = parameters.get(6);
            String remark = parameters.get(7);
            String query1 = "SELECT COUNT(*) AS count FROM project_configuration_mapping WHERE project_configuration_mapping_name = ? AND active = 'Y';";
            pstmt = connection.prepareStatement(query1);
            pstmt.setString(1, project_configuration_mapping_name);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }

            if (count > 0) {
                message = project_configuration_mapping_name + "Name Already Exist.";
                color = COLOR_ERROR;
            } else {
                if (!project_configuration_mapping_name.trim().isEmpty()) {
                    pstmt = null;
                    String query = "INSERT INTO project_configuration_mapping (project_configuration_mapping_name,  survey_configuration_id, device_configuration_id, satellite_configuration_id, miscellaneous_configuration_id, created_by,defaultConfig, active, remark, revision_no) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?);";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, project_configuration_mapping_name);
                    pstmt.setString(2, survey_configuration_id);
                    pstmt.setString(3, device_configuration_id);
                    pstmt.setString(4, satellite_configuration_id);
                    pstmt.setString(5, miscellaneous_configuration_id);
                    pstmt.setString(6, name);
                    pstmt.setString(7, defaultConfig);
                    pstmt.setString(8, "Y");
                    pstmt.setString(9, remark);
                    pstmt.setInt(10, 0);
                    rowsAffected = pstmt.executeUpdate();
                }
                if (rowsAffected > 0) {
                    message = "Data Added Successfully.";
                    color = COLOR_OK;
                } else {
                    message = "Some Error Try Again.";
                    color = COLOR_ERROR;
                }
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingModel.saveProjectConfigurationMappingData()" + e);
        }
    }

    public List getProjectConfigurationMappingList(String projectName) {
        PreparedStatement pstmt = null;
        ResultSet stmt = null;
        List<ProjectConfigurationMappingBean> getProjectConfigurationMappingList = new ArrayList<>();
        String query = "select pcm.project_configuration_mapping_name,sc.survey_configuration_id,sc.survey_configuration_name\n"
                + ",msc.miscConfig_id, msc.miscConfig_name,dc.device_configuration_id\n"
                + ",dc.device_configuration_name\n"
                + ",st.satellite_configuration_id,st.satellite_configuration_name,pcm.defaultConfig, pcm.config_time,pcm.remark\n"
                + "from project_configuration_mapping pcm\n"
                + "join survey_configuration sc on sc.survey_configuration_id=pcm.survey_configuration_id\n"
                + "join miscellaneous_configuration msc on msc.miscConfig_id=pcm.miscellaneous_configuration_id\n"
                + "join device_configuration dc on dc.device_configuration_id=pcm.device_configuration_id\n"
                + "join satellite_configuration st on st.satellite_configuration_id=pcm.satellite_configuration_id\n"
                + "where pcm.active='Y'  and  dc.active='Y' and  st.active='Y' and  msc.active='Y'  ";
        

        try {
            pstmt = connection.prepareStatement(query);
            stmt = pstmt.executeQuery();
            while (stmt.next()) {
                ProjectConfigurationMappingBean bean = new ProjectConfigurationMappingBean();
                bean.setProject_configuration_mapping_name(stmt.getString("project_configuration_mapping_name"));
                bean.setSurvey_configuration_id(stmt.getString("survey_configuration_id"));
                bean.setSurvey_configuration_name(stmt.getString("survey_configuration_name"));
                bean.setMiscellaneous_configuration_id(stmt.getString("miscConfig_id"));
                bean.setMiscellaneous_configuration_name(stmt.getString("miscConfig_name"));
                bean.setDevice_configuration_id(stmt.getString("device_configuration_id"));
                bean.setDevice_configuration_name(stmt.getString("device_configuration_name"));
                bean.setSatellite_configuration_id(stmt.getString("satellite_configuration_id"));
                bean.setSatellite_configuration_name(stmt.getString("satellite_configuration_name"));
                bean.setDefaultConfig(stmt.getString("defaultConfig"));
                bean.setConfig_time(stmt.getString("config_time"));
                bean.setRemark(stmt.getString("remark"));
                getProjectConfigurationMappingList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingModel.getProjectConfigurationMappingList()" + e);
        }
        return getProjectConfigurationMappingList;
    }


    public List getSurveyConfigurationList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProjectConfigurationMappingBean> getSurveyConfigurationList = new ArrayList<>();
        String query = "select survey_configuration_id,survey_configuration_name from survey_configuration where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProjectConfigurationMappingBean bean = new ProjectConfigurationMappingBean();
                bean.setSurvey_configuration_id(rs.getString("survey_configuration_id"));
                bean.setSurvey_configuration_name(rs.getString("survey_configuration_name"));
                getSurveyConfigurationList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingModel.getSurveyConfigurationList()" + e);
        }
        return getSurveyConfigurationList;
    }

    public List getDeviceConfigurationList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProjectConfigurationMappingBean> getDeviceConfigurationList = new ArrayList<>();
        String query = "select device_configuration_id,device_configuration_name from device_configuration where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProjectConfigurationMappingBean bean = new ProjectConfigurationMappingBean();
                bean.setDevice_configuration_id(rs.getString("device_configuration_id"));
                bean.setDevice_configuration_name(rs.getString("device_configuration_name"));
                getDeviceConfigurationList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingModel.getDeviceConfigurationList()" + e);
        }
        return getDeviceConfigurationList;
    }

    public List getSatelliteConfigurationList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProjectConfigurationMappingBean> getSatelliteConfigurationList = new ArrayList<>();
        String query = "select satellite_configuration_id,satellite_configuration_name from satellite_configuration where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProjectConfigurationMappingBean bean = new ProjectConfigurationMappingBean();
                bean.setSatellite_configuration_id(rs.getString("satellite_configuration_id"));
                bean.setSatellite_configuration_name(rs.getString("satellite_configuration_name"));
                getSatelliteConfigurationList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingModel.getSatelliteConfigurationList()" + e);
        }
        return getSatelliteConfigurationList;
    }

    public List getMiscellaneousConfigurationList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProjectConfigurationMappingBean> getMiscellaneousConfigurationList = new ArrayList<>();
        String query = "select miscConfig_id,miscConfig_name from miscellaneous_configuration where active='Y' ";
        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProjectConfigurationMappingBean bean = new ProjectConfigurationMappingBean();
                bean.setMiscellaneous_configuration_id(rs.getString("miscConfig_id"));
                bean.setMiscellaneous_configuration_name(rs.getString("miscConfig_name"));
                getMiscellaneousConfigurationList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingModel.getMiscellaneousConfigurationList()" + e);
        }
        return getMiscellaneousConfigurationList;
    }


    public List getSurveyConfigurationData(String survey_configuration_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<SurveyConfigurationBean> getSurveyConfigurationData = new ArrayList<>();
        String query = "select sc.survey_configuration_name,d.name,zd.zone,et.elevationType,du.disUnit_name,au.angUnit_name,pp.zone_name,pp.origin_lat,pp.origin_lng,pp.scale,\n"
                + "pp.paralell_1,pp.false_easting,pp.false_northing,pp.paralell_2,pt.projectionType,sc.config_time,sc.remark\n"
                + "from survey_configuration sc\n"
                + "inner join datum d on  d.datum_id=sc.datum_id\n"
                + "inner join zonedata zd on zd.zonedata_id=sc.zonedata_id\n"
                + "inner join elevationtype et on et.elevationtype_id=sc.elevationtype_id\n"
                + "inner join distanceunit du on du.distanceunit_id=sc.distanceunit_id\n"
                + "inner join angleunit au on au.angleunit_id=sc.angleunit_id\n"
                + "inner join projectionparameters pp on pp.projectionParam_id=sc.projectionParam_id\n"
                + "inner join projectiontype pt on pt.projectiontype_id=pp.projectiontype_id\n"
                + "where sc.active='Y' ";
        if (!survey_configuration_id.isEmpty() && survey_configuration_id != null) {
            query += "and survey_configuration_id= ?";
        }
        try {
            pstmt = connection.prepareStatement(query);
            if (!survey_configuration_id.isEmpty() && survey_configuration_id != null) {
                pstmt.setString(1, survey_configuration_id);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SurveyConfigurationBean bean = new SurveyConfigurationBean();
                bean.setSurvey_configuration_name(rs.getString("survey_configuration_name"));
                bean.setDatum_name(rs.getString("name"));
                bean.setZone(rs.getString("zone"));
                bean.setElevationType(rs.getString("elevationType"));
                bean.setDisUnit_name(rs.getString("disUnit_name"));
                bean.setAngUnit_name(rs.getString("angUnit_name"));
                bean.setZone_name(rs.getString("zone_name"));
                bean.setOrigin_lat(rs.getString("origin_lat"));
                bean.setOrigin_lng(rs.getString("origin_lng"));
                bean.setScale(rs.getString("scale"));
                bean.setFalse_easting(rs.getString("false_easting"));
                bean.setFalse_northing(rs.getString("false_northing"));
                bean.setParalell_1(rs.getString("paralell_1"));
                bean.setParalell_2(rs.getString("paralell_2"));
                bean.setProjectionType(rs.getString("projectionType"));
                bean.setConfig_time(rs.getString("config_time"));
                bean.setRemark(rs.getString("remark"));
                getSurveyConfigurationData.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingModel.getSurveyConfigurationData()" + e);
        }
        return getSurveyConfigurationData;
    }
/*
    public List getMiscellaneousConfigList(String miscellaneous_configuration_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MiscellaneousConfigurationBean> getMiscellaneousConfigList = new ArrayList<>();
        String query = "select * from miscellaneous_configuration where active='Y'";
        if (!miscellaneous_configuration_id.isEmpty() && miscellaneous_configuration_id != null) {
            query += "and miscellaneous_configuration_id= ?";
        }
        try {
            pstmt = connection.prepareStatement(query);
            if (!miscellaneous_configuration_id.isEmpty() && miscellaneous_configuration_id != null) {
                pstmt.setString(1, miscellaneous_configuration_id);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MiscellaneousConfigurationBean bean = new MiscellaneousConfigurationBean();
                bean.setPoint_name_visibility(rs.getInt("point_name_visibility"));
                bean.setLRF(rs.getInt("LRF"));
                bean.setCode_name(rs.getInt("code_name"));
                bean.setOsm_view(rs.getInt("osm_view"));
                bean.setSatellite_view(rs.getInt("satellite_view"));
                bean.setRemark(rs.getString("remark"));
                getMiscellaneousConfigList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingModel.getMiscellaneousConfigList()" + e);
        }
        return getMiscellaneousConfigList;
    }
*/
    public List getDynamicProjectConfigData(String dynamic_project_config_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<DynamicConfigMappingBean> getDynamicProjectConfigList = new ArrayList<>();
        String query = "select dpc.dynamic_project_config_id,dpc.dynamic_project_config_name,ma.byte_value,ma.display_value,sp.form_name,\n"
                + "sp.total_time,sp.sampling_rate,dmd.device_work_mode_name,dmd.mode_value,\n"
                + "ppk.file_name,ppk.total_time,mbt.latitude,mbt.longitude,mbt.altitude,dpc.remark\n"
                + "from dynamic_project_config dpc\n"
                + "inner join mask_angle ma on ma.mask_angle_id=dpc.mask_angle_id\n"
                + "inner join static_params sp on sp.static_params_id=dpc.static_params_id\n"
                + "inner join dynamic_mode_data dmd on dmd.dynamic_mode_data_id=dpc.dynamic_mode_data_id\n"
                + "inner join ppk_params ppk on ppk.ppk_params_id=dpc.ppk_params_id\n"
                + "inner join manual_base_table mbt on mbt.manual_base_table_id=dpc.manual_base_table_id\n"
                + "where dpc.active='Y' ";

        if (!dynamic_project_config_id.isEmpty() && dynamic_project_config_id != null) {
            query += "and dynamic_project_config_id= ?";
        }
        try {
            pstmt = connection.prepareStatement(query);
            if (!dynamic_project_config_id.isEmpty() && dynamic_project_config_id != null) {
                pstmt.setString(1, dynamic_project_config_id);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DynamicConfigMappingBean bean = new DynamicConfigMappingBean();
                bean.setDynamic_project_config_name(rs.getString("dynamic_project_config_name"));
                bean.setByte_value(rs.getString("byte_value"));
                bean.setDisplay_value(rs.getString("display_value"));
                bean.setForm_name(rs.getString("form_name"));
                bean.setTotal_time(rs.getString("total_time"));
                bean.setSampling_rate(rs.getString("sampling_rate"));
                bean.setDevice_work_mode_name(rs.getString("device_work_mode_name"));
                bean.setMode_value(rs.getString("mode_value"));
                bean.setFile_name(rs.getString("file_name"));
                bean.setTotalTime(rs.getString("total_time"));
                bean.setLatitude(rs.getString("latitude"));
                bean.setLongitude(rs.getString("longitude"));
                bean.setAltitude(rs.getString("altitude"));
                bean.setReamrks(rs.getString("remark"));
                getDynamicProjectConfigList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingModel.getDynamicProjectConfigData()" + e);
        }
        return getDynamicProjectConfigList;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCOLOR_OK() {
        return COLOR_OK;
    }

    public void setCOLOR_OK(String COLOR_OK) {
        this.COLOR_OK = COLOR_OK;
    }

    public String getCOLOR_ERROR() {
        return COLOR_ERROR;
    }

    public void setCOLOR_ERROR(String COLOR_ERROR) {
        this.COLOR_ERROR = COLOR_ERROR;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("com.apogee.gnss.projectConfigurationMapping.ProjectConfigurationMappingModel.closeConnection()" + e);
        }
    }

}
