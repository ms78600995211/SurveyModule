<%-- 
    Document   : dataList.jsp
    Created on : 3 Jul, 2023, 1:32:09 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>         
<jsp:include page="layout/header.jsp" />

<!-- ============================================================== -->
<!-- Start right Content here -->
<!-- ============================================================== -->
<div class="main-content">
    <div class="page-content">
        <div class="container-fluid">
            <!-- start page title -->

            <c:if test="${not empty message}">
                <div class="msgWrap">
                    <c:if test="${msgbgcolor=='green'}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <i class="mdi mdi-check-all me-2"></i>
                            ${message}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>  
                    </c:if>
                    <c:if test="${msgbgcolor=='red'}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="mdi mdi-check-all me-2"></i>
                            ${message}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>  
                    </c:if>

                </div>
            </c:if>

            <div class="row">
                <div class="col-12">
                    <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                        <h4 class="mb-sm-0 font-size-18">Project Configuration Mapping</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                                <li class="breadcrumb-item active">Project Configuration Mapping</li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end page title -->
            <div class="row">
                <div class="col-12">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="px-3">
                                    <div class="mt-2">
                                        <!--                                        <p class="text-muted mb-2">Select Project </p>-->
                                        <div class="row">                
                                            <div class="col-md-8">
                                                <!--                                                <form method="post" action="ProjectConfigurationMappingController">
                                                                                                    <div class="row">  
                                                                                                        <div class="col-md-3">
                                                                                                            <div class="custom-select" class="form-control"     style="width:100%; border:  1px  solid #ccc ;border-radius:5px">
                                                                                                                <select class="form-control" id="dropdown" name="projectName" size="1" >
                                                                                                                    <option value="" selected disabled>Select One</option>
                                                                                                                    <option value="" selected>All</option>
                                                <c:forEach var="option" items="${getProject_detailsList}">
                                                    <option value="${option.project_name}" ${option.project_name eq requestScope.projectName ? 'selected' : ''}>${option.project_name}</option>
                                                </c:forEach>
                                            </select> 
                                        </div>

                                    </div>
                                    <div class="col-md-3">
                                        <div class="mb-3">
                                            <button class="btn btn-info w-100" type="submit" name=""><i class="bx bx-search font-size-16 align-middle me-2"></i> Search</button>
                                        </div>
                                    </div>
                                </div>
                            </form>-->
                                            </div>
                                            <div class="col-md-4">
                                                <div class="row">
                                                    <div class="col-md-1"></div>
                                                    <div class="col-md-3">
                                                        <div class="mb-1">
                                                            <button class="btn btn-danger w-100"><i class="far fa-file-pdf font-size-16 align-middle"></i> PDF</button>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <div class="mb-1">
                                                            <button class="btn btn-success w-100"><i class="far fa-file-excel font-size-16 align-middle"></i> Excel</button>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-5 text-end">
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Project Configuration Mapping</button>
                                                    </div>      
                                                </div>                        
                                            </div>
                                        </div>                            
                                    </div>
                                </div>




                                <div class="card-body pt-0">
                                    <div class="mt-1">

                                        <div class="table-responsive mt-0">
                                            <div class="row">
                                                <div class="col-12">
                                                    <table id="datatable"  class="table table-bordered dt-responsive  nowrap w-100"  >
                                                        <thead>
                                                            <tr>
                                                                <th>Sr.</th>
                                                                <th>Project Configuration</th>
                                                                <!--                                                                <th>Project Name</th>-->
                                                                <th>Survey Configuration</th>
                                                                <th>Miscellaneous Configuration</th>
                                                                <th>Device Configuration</th>
                                                                <!--                                                                <th>Dynamic Project Configuration</th>-->
                                                                <th>Satellite Configuration</th>
                                                                <th>DefaultConfig</th>

                                                                <th>Config_time</th>
                                                                <th>Remarks</th>

                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="ProjectConfigurationMapping" items="${requestScope['getProjectConfigurationMappingList']}"
                                                                       varStatus="loopCounter">
                                                                <tr>
                                                                    <td>${loopCounter.count}</td>
                                                                    <td>
                                                                        <p class="mb-0">${ProjectConfigurationMapping.project_configuration_mapping_name}</p>
                                                                    </td>



                                                                    <td>
                                                                        <span>${ProjectConfigurationMapping.survey_configuration_name}</span>                                                                      
<!--                                                                        <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry1"  onclick="showSurveyConfigData('${ProjectConfigurationMapping.survey_configuration_id}')">View</button>-->
                                                                    </td>

                                                                    <td>
                                                                        <span>${ProjectConfigurationMapping.miscellaneous_configuration_name}</span>                                                                      
<!--                                                                       <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry2"  onclick="showMiscConfigData('${ProjectConfigurationMapping.miscellaneous_configuration_id}')">View</button>-->
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${ProjectConfigurationMapping.device_configuration_name}</p>
                                                                    </td>


                                                                    <td>
                                                                        <p class="mb-0">${ProjectConfigurationMapping.satellite_configuration_name}</p>
                                                                    </td> 

                                                                    <td>
                                                                        <p class="mb-0">${ProjectConfigurationMapping.defaultConfig}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${ProjectConfigurationMapping.config_time}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${ProjectConfigurationMapping.remark}</p>
                                                                    </td>
                                                                    <td>
                                                                        <a href="" onclick="getSingleDocDetails(${documentData.documents_id})" data-bs-toggle="modal" data-bs-target=".bs-example-modal-lg-Add-Files" ><span class="badge badge-pill badge-soft-success font-size-14"><i class="fas fa-edit"></i> </span></a>
                                                                        <a href="" onclick="deleteDoc(${documentData.documents_id})" class="deleteBtn"><span class="badge badge-pill badge-soft-danger font-size-14"><i class="fas fa-trash-alt"></i></span></a>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end col -->
                    </div>
                    <div style='clear:both'></div>
                </div>
            </div>
        </div>
        <!-- container-fluid -->
    </div>
    <!-- End Page-content -->

    <!-- ADD Hemisphere Model Start -->

    <div class="modal fade bs-example-modal-xl-Add-Enquiry" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Project Configuration Mapping</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <form class="custom-validation" action="ProjectConfigurationMappingController" method="post">
                            <div class="row">  

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Project Configuration Mapping<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="project_configuration_mapping_name"
                                               placeholder="Project Configuration Mapping" required="">
                                    </div>
                                </div> 
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Survey Configuration<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="survey_configuration_id" id="survey_configuration_id"    required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="SurveyConfiguration" items="${requestScope['getSurveyConfigurationList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${SurveyConfiguration.survey_configuration_id}" value="${SurveyConfiguration.survey_configuration_id}" >${SurveyConfiguration.survey_configuration_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Device Configuration<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="device_configuration_id" id="device_configuration_id"    required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="DeviceConfiguration" items="${requestScope['getDeviceConfigurationList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${DeviceConfiguration.device_configuration_id}" value="${DeviceConfiguration.device_configuration_id}" >${DeviceConfiguration.device_configuration_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Satellite Configuration<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="satellite_configuration_id" id="satellite_configuration_id"    required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="SatelliteConfiguration" items="${requestScope['getSatelliteConfigurationList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${SatelliteConfiguration.satellite_configuration_id}" value="${SatelliteConfiguration.satellite_configuration_id}" >${SatelliteConfiguration.satellite_configuration_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Miscellaneous Configuration<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="miscellaneous_configuration_id" id="miscellaneous_configuration_id"    required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="MiscellaneousConfiguration" items="${requestScope['getMiscellaneousConfigurationList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${MiscellaneousConfiguration.miscellaneous_configuration_id}" value="${MiscellaneousConfiguration.miscellaneous_configuration_id}" >${MiscellaneousConfiguration.miscellaneous_configuration_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Default Config<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="defaultConfig" id="defaultConfig"  required>
                                            <option value="" selected disabled>Select One</option>
                                            <option value="Y">Y</option>
                                            <option value="N">N</option>
                                        </select>
                                    </div>
                                </div>


                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Remark<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="remark"
                                               placeholder="Remark">
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary waves-effect" data-bs-dismiss="modal">Close</button>
                                <button class="btn btn-primary" type="submit" name="submitFormBtn" value="Submit">Submit form</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div>
    </div>

    <!-- ADD Hemisphere Model End -->

    <div class="modal fade bs-example-modal-xl-Add-Enquiry1" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Survey Configuration Data </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>

                        <div class="card-body pt-0">
                            <div class="mt-1">

                                <div class="table-responsive mt-0">
                                    <div class="row">
                                        <div class="col-12">
                                            <table id="datatable"  class="table table-bordered dt-responsive  nowrap w-100"  >
                                                <thead>
                                                    <tr>
                                                        <th>Datum </th>
                                                        <th>Zonedata </th>
                                                        <th>Elevationtype </th>
                                                        <th>Distanceunit</th>
                                                        <th>Angleunit</th>
                                                        <th>Zone</th>
                                                        <th>Origin Latitude</th>
                                                        <th>Origin Longitude</th>
                                                        <th>Scale</th>
                                                        <th>False_easting</th>
                                                        <th>False_northing</th>
                                                        <th>Paralell_1</th>
                                                        <th>Paralell_2</th>
                                                        <th>ProjectionType</th>
                                                        <th>Config_time</th>
                                                        <th>Remarks</th>
                                                    </tr>
                                                </thead>
                                                <tbody  >
                                                    <tr id="surveyConfigData">


                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>   


                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div>
    </div>


    <div class="modal fade bs-example-modal-xl-Add-Enquiry2" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Miscellaneous Configuration Data </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>

                        <div class="card-body pt-0">
                            <div class="mt-1">

                                <div class="table-responsive mt-0">
                                    <div class="row">
                                        <div class="col-12">
                                            <table id="datatable"  class="table table-bordered dt-responsive  nowrap w-100"  >
                                                <thead>
                                                    <tr>
                                                        <th>Point Name Visibility </th>
                                                        <th>LRF </th>
                                                        <th>Code Name </th>
                                                        <th>Osm View</th>
                                                        <th>Satellite View</th>
                                                        <th>Remarks</th>
                                                    </tr>
                                                </thead>
                                                <tbody  >
                                                    <tr id="miscConfigData">


                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>   


                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div>
    </div>


    <div class="modal fade bs-example-modal-xl-Add-Enquiry3" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Dynamic Configuration Data </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>

                        <div class="card-body pt-0">
                            <div class="mt-1">

                                <div class="table-responsive mt-0">
                                    <div class="row">
                                        <div class="col-12">
                                            <table id="datatable"  class="table table-bordered dt-responsive  nowrap w-100"  >
                                                <thead>
                                                    <tr>
                                                        <th>Byte_Value</th>
                                                        <th>Display_Value </th>
                                                        <th>Form_Name </th>
                                                        <th>Total_Time </th>
                                                        <th>Sampling_rate</th>
                                                        <th>Device_work_mode_name</th>
                                                        <th>Mode_value</th>
                                                        <th>File_Name</th>
                                                        <th>Total_Time_(Ppk Params)</th>
                                                        <th>Latitude</th>
                                                        <th>Longitude</th>
                                                        <th>Altitude</th>
                                                        <th>Remarks</th>
                                                    </tr>
                                                </thead>
                                                <tbody  >
                                                    <tr id="dynamicConfigData">

                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>   


                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div>
    </div>

    <jsp:include page="layout/footer.jsp" />

    <script src="assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
    <script src="assets/libs/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="assets/libs/datatables.net-buttons-bs4/js/buttons.bootstrap4.min.js"></script>
    <script src="assets/libs/jszip/jszip.min.js"></script>
    <script src="assets/libs/pdfmake/build/pdfmake.min.js"></script>
    <script src="assets/libs/pdfmake/build/vfs_fonts.js"></script>
    <script src="assets/libs/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="assets/libs/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="assets/libs/datatables.net-buttons/js/buttons.colVis.min.js"></script>
    <script src="assets/libs/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="assets/libs/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js"></script>
    <script src="assets/js/pages/datatables.init.js"></script>
    <script src="assets/js/myScript.js"></script>

    <script>
                                                                            setTimeout(function () {
                                                                                var alertElement = document.querySelector('.alert');
                                                                                alertElement.classList.add('fade');
                                                                                setTimeout(function () {
                                                                                    alertElement.remove();
                                                                                }, 1000);
                                                                            }, 2000);

    </script>

    <script>
//
//        function  showSurveyConfigData(value) {
//
//            $.ajax({
//                url: "ProjectConfigurationMappingController",
//                data: "task=showSurveyConfigData&Survey_configuration_id=" + value,
//                type: 'POST',
//                dataType: 'json',
//                success: function (response) {
//
//                    var surveyConfigData = response.getData;
//
//                    $('#surveyConfigData').empty();
//
//                    for (var i = 0; i < surveyConfigData.length; i++) {
//
//
//                        var markup = "<td><p class='mb-0'>" + surveyConfigData[i].datum_name + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].zone
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].elevationType + "</p></td><td><p class='mb-0'>"
//                                + surveyConfigData[i].disUnit_name + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].angUnit_name
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].zone_name
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].origin_lat
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].origin_lng
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].scale
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].false_easting
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].false_northing
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].paralell_1
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].paralell_2
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].projectionType
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].config_time
//                                + "</p></td><td><p class='mb-0'>" + surveyConfigData[i].remark + "</p></td>";
//                        console.log(markup);
//                        $('#surveyConfigData').append(markup);
//
//                    }
//
//
//
//                },
//                error: function (e) {
//                    alert('Error:================= ' + e);
//                }
//            });
//
//        }


//        function  showMiscConfigData(value) {
//
//            $.ajax({
//                url: "ProjectConfigurationMappingController",
//                data: "task=showMiscConfigData&Miscellaneous_configuration_id=" + value,
//                type: 'POST',
//                dataType: 'json',
//                success: function (response) {
//
//                    var miscConfigData = response.getData;
//
//                    $('#miscConfigData').empty();
//
//                    for (var i = 0; i < miscConfigData.length; i++) {
//
//
//                        var markup = "<td><p class='mb-0'>" + miscConfigData[i].point_name_visibility + "</p></td><td><p class='mb-0'>" + miscConfigData[i].LRF
//                                + "</p></td><td><p class='mb-0'>" + miscConfigData[i].code_name + "</p></td><td><p class='mb-0'>"
//                                + miscConfigData[i].osm_view + "</p></td><td><p class='mb-0'>" + miscConfigData[i].satellite_view
//                                + "</p></td><td><p class='mb-0'>" + miscConfigData[i].remark + "</p></td>";
//                        console.log(markup);
//                        $('#miscConfigData').append(markup);
//
//                    }
//
//
//                },
//                error: function (e) {
//                    alert('Error:================= ' + e);
//                }
//            });
//
//        }

//        function  showDynamicConfigData(value) {
//
//            $.ajax({
//                url: "ProjectConfigurationMappingController",
//                data: "task=showDynamicConfigData&Dynamic_project_config_id=" + value,
//                type: 'POST',
//                dataType: 'json',
//                success: function (response) {
//
//                    var dynamicConfigData = response.getData;
//
//                    $('#dynamicConfigData').empty();
//
//                    for (var i = 0; i < dynamicConfigData.length; i++) {
//
//                        var markup = "<td><p class='mb-0'>" + dynamicConfigData[i].byte_value + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].display_value
//                                + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].form_name + "</p></td><td><p class='mb-0'>"
//                                + dynamicConfigData[i].total_time + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].sampling_rate
//                                + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].device_work_mode_name
//                                + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].mode_value
//                                + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].file_name
//                                + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].totalTime
//                                + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].latitude
//                                + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].longitude
//                                + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].altitude
//                                + "</p></td><td><p class='mb-0'>" + dynamicConfigData[i].reamrks + "</p></td>";
//                        console.log(markup);
//                        $('#dynamicConfigData').append(markup);
//                    }
//
//
//                },
//                error: function (e) {
//                    alert('Error:================= ' + e);
//                }
//            });
//
//        }



    </script>