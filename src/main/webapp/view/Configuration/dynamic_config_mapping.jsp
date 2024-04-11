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
                        <h4 class="mb-sm-0 font-size-18">Dynamic Project Configuration</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                                <li class="breadcrumb-item active">Dynamic Project Configuration</li>
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
                                        <p class="text-muted mb-2">Select File Name</p>
                                        <div class="row">                
                                            <div class="col-md-8">
                                                <form method="post" action="DynamicConfigMappingController">
                                                    <div class="row">  
                                                        <div class="col-md-3">
                                                            <div class="custom-select" class="form-control"     style="width:100%; border:  1px  solid #ccc ;border-radius:5px">
                                                                <select class="form-control" id="dropdown" name="fileName" size="1" >
                                                                    <option value="" selected disabled>Select One</option>
                                                                    <option value="" selected>All</option>
                                                                    <c:forEach var="option" items="${getFile_name}">
                                                                        <option value="${option.file_name}" ${option.file_name eq requestScope.fileName ? 'selected' : ''}>${option.file_name}</option>
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
                                                </form>
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
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Dynamic Project Configuration</button>
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
                                                                <th>Dynamic Project Config</th>
                                                                <th>Device ConfigHierarchy_name</th>
                                                                <th>Manual_base_params_name </th>
                                                                <th>File_name </th>
                                                                <th>Total_Time </th>
                                                                <th>File_name(Static Params)</th>
                                                                <th>Total_Time(Static Params</th>
                                                                <th>Sampling_rate(Static Params</th>
                                                                <th>DefaultConfigs</th>
                                                                <th>Remarks</th>

                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="DynamicProjectConfig" items="${requestScope['getDynamicProjectConfigList']}"
                                                                       varStatus="loopCounter">
                                                                <tr>
                                                                    <td>${loopCounter.count}</td>
                                                                    <td>
                                                                        <p class="mb-0">${DynamicProjectConfig.dynamic_project_config_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DynamicProjectConfig.device_configHierarchy_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DynamicProjectConfig.manual_base_params_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DynamicProjectConfig.file_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DynamicProjectConfig.total_time}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DynamicProjectConfig.static_params_file_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DynamicProjectConfig.totalTime}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DynamicProjectConfig.sampling_rate}</p>
                                                                    </td> 

                                                                    <td>
                                                                        <p class="mb-0">${DynamicProjectConfig.defaultConfig}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${DynamicProjectConfig.reamrks}</p>
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
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Dynamic Project Configuration</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <form class="custom-validation" action="DynamicConfigMappingController" method="post">
                            <div class="row">  

                                
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Dynamic Config Name<span class="text-danger"></span>* </label>
                                        <input type="text" class="form-control" name="dynamic_config_name"
                                               placeholder="Dynamic Config Name" required>
                                    </div>
                                </div>
                                
                         
                                
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Device Config Hierarchy<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="device_configHierarchy_id" id="device_configHierarchy_id" required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="Device_confighierarchy" items="${requestScope['getDevice_confighierarchyList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${Device_confighierarchy.device_configHierarchy_id}" value="${Device_confighierarchy.device_configHierarchy_id}" >${Device_confighierarchy.device_configHierarchy_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <!--                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Byte_Value(Mask Angle)<span class="text-danger">*</span></label>
                                
                                                                        <select class="form-control"  name="byte_value" id="byte_value"   onchange="getDisplay_value(this.value)" required>
                                                                            <option value="" selected disabled>Select One</option>
                                <c:forEach var="Byte_value" items="${requestScope['getByte_value']}"
                                           varStatus="loopCounter">
                                    <option id ="${Byte_value.byte_value}" value="${Byte_value.byte_value}" >${Byte_value.byte_value}</option>
                                </c:forEach>                                      
                            </select>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">Display_Value(Mask Angle)<span class="text-danger">*</span></label>

                            <select class="form-control"  name="display_value" id="display_value" required>
                                <option value="" selected disabled>Select One</option>

                            </select>
                        </div>
                    </div>-->

                                <!--
                                                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Device_work_mode_name<span class="text-danger">*</span></label>
                                
                                                                        <select class="form-control"  name="device_work_mode_name" id="device_work_mode_name"   onchange="getMode_value(this.value)" required>
                                                                            <option value="" selected disabled>Select One</option>
                                <c:forEach var="Device_work_mode_name" items="${requestScope['getDevice_work_mode_name']}"
                                           varStatus="loopCounter">
                                    <option id ="${Device_work_mode_name.device_work_mode_name}" value="${Device_work_mode_name.device_work_mode_name}" >${Device_work_mode_name.device_work_mode_name}</option>
                                </c:forEach>                                      
                            </select>
                        </div>
                    </div>


                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">Mode_value<span class="text-danger">*</span></label>
                            <select class="form-control"  name="mode_value" id="mode_value"  required>
                                <option value="" selected disabled>Select One</option>

                            </select>
                        </div>
                    </div>-->


                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Manual Base Params<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="manual_base_params_id" id="manual_base_params_id"    required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="manualBaseParamsName" items="${requestScope['getAllManualBaseParamsName']}"
                                                       varStatus="loopCounter">
                                                <option value="${manualBaseParamsName.manual_base_params_id}" >${manualBaseParamsName.manual_base_params_name}</option>
                                            </c:forEach>                                      
                                        </select>
                                    </div>
                                </div>



                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">File Name(Ppk Params)<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="file_name" id="file_name"   onchange="getTotalTime(this.value)" required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="File_name" items="${requestScope['getFile_name']}"
                                                       varStatus="loopCounter">
                                                <option id ="${File_name.file_name}" value="${File_name.file_name}" >${File_name.file_name}</option>
                                            </c:forEach>                                      
                                        </select>
                                    </div>
                                </div>


                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Total Time(Ppk Params)<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="totalTime" id="totalTime"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>  


                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Form_Name(Static Params)<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="static_params_file_name" id="static_params_file_name"   onchange="getTotal_time(this.value)" required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="FileName" items="${requestScope['getFileName']}"
                                                       varStatus="loopCounter">
                                                <option id ="${FileName.file_name}" value="${FileName.file_name}" >${FileName.file_name}</option>
                                            </c:forEach>                                      
                                        </select>
                                    </div>
                                </div> 
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Total_Time(Static Params)<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="total_time" id="total_time" onchange="getSampling_rate(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>  
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Sampling_rate(Static Params)<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="sampling_rate" id="sampling_rate"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Default Config<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="defaultConfig" id="defaultConfig"  >
                                            <option value="" selected disabled>Select One</option>
                                            <option value="Y">Yes</option>
                                            <option value="N"  selected>No</option>
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


        function getDisplay_value(value) {
            $.ajax({
                url: "DynamicConfigMappingController",
                data: "task=getData&Byte_value=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    var getDisplay_value = response.getData;

                    $('#display_value').empty();
                    var defaultInput = "<option value='' selected disabled>Select One</option>";
                    $('#display_value').append(defaultInput);
                    for (var i = 0; i < getDisplay_value.length; i++) {

                        var newInput1 = '<option id="' + getDisplay_value[i].display_value + '" value="' + getDisplay_value[i].display_value + '" class="docDetails">' + getDisplay_value[i].display_value + '</option>';
                        console.log(newInput1);

                        $('#display_value').append(newInput1);
                    }

                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }




        function getTotal_time(value) {
            $.ajax({
                url: "DynamicConfigMappingController",
                data: "task=getStatic_params&File_name=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    var getTotal_timee = response.getData;
                    $('#total_time').empty();
                    var defaultInput = "<option value='' selected disabled>Select One</option>";
                    $('#total_time').append(defaultInput);
                    for (var i = 0; i < getTotal_timee.length; i++) {

                        var newInput1 = '<option id="' + getTotal_timee[i].total_time + '" value="' + getTotal_timee[i].total_time + '" class="docDetails">' + getTotal_timee[i].total_time + '</option>';
                        console.log(newInput1);

                        $('#total_time').append(newInput1);
                    }

                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }


        function getSampling_rate(value) {

            var file_name = document.getElementById("static_params_file_name").value;

            $.ajax({
                url: "DynamicConfigMappingController",
                data: "task=getStatic_params&Total_time=" + value + "&File_name=" + file_name,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    var getSampling_rate = response.getData;

                    $('#sampling_rate').empty();
                    var defaultInput = "<option value='' selected disabled>Select One</option>";
                    $('#sampling_rate').append(defaultInput);
                    for (var i = 0; i < getSampling_rate.length; i++) {

                        var newInput1 = '<option id="' + getSampling_rate[i].sampling_rate + '" value="' + getSampling_rate[i].sampling_rate + '" class="docDetails">' + getSampling_rate[i].sampling_rate + '</option>';
                        console.log(newInput1);

                        $('#sampling_rate').append(newInput1);
                    }

                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

        function getTotalTime(value) {

            $.ajax({
                url: "DynamicConfigMappingController",
                data: "task=getPpk_params&File_name=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    var getTotalTime = response.getData;

                    $('#totalTime').empty();
                    var defaultInput = "<option value='' selected disabled>Select One</option>";
                    $('#totalTime').append(defaultInput);
                    for (var i = 0; i < getTotalTime.length; i++) {

                        var newInput1 = '<option id="' + getTotalTime[i].total_time + '" value="' + getTotalTime[i].total_time + '" class="docDetails">' + getTotalTime[i].total_time + '</option>';
                        console.log(newInput1);

                        $('#totalTime').append(newInput1);
                    }

                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

        /*
         * 
         * 
         function getMode_value(value) {
         
         $.ajax({
         url: "DynamicConfigMappingController",
         data: "task=getDynamic_mode_data&Device_work_mode_name=" + value,
         type: 'POST',
         dataType: 'json',
         success: function (response) {
         var getMode_value = response.getData;
         
         $('#mode_value').empty();
         var defaultInput = "<option value='' selected disabled>Select One</option>";
         $('#mode_value').append(defaultInput);
         for (var i = 0; i < getMode_value.length; i++) {
         
         var newInput1 = '<option id="' + getMode_value[i].mode_value + '" value="' + getMode_value[i].mode_value + '" class="docDetails">' + getMode_value[i].mode_value + '</option>';
         console.log(newInput1);
         
         $('#mode_value').append(newInput1);
         }
         
         },
         error: function (e) {
         alert('Error:================= ' + e);
         }
         });
         }
         
         function getLongitude(value) {
         
         $.ajax({
         url: "DynamicProjectConfigurationController",
         data: "task=getManual_base_table&Latitude=" + value,
         type: 'POST',
         dataType: 'json',
         success: function (response) {
         var getLongitude = response.getData;
         
         $('#longitude').empty();
         var defaultInput = "<option value='' selected disabled>Select One</option>";
         $('#longitude').append(defaultInput);
         for (var i = 0; i < getLongitude.length; i++) {
         
         var newInput1 = '<option id="' + getLongitude[i].longitude + '" value="' + getLongitude[i].longitude + '" class="docDetails">' + getLongitude[i].longitude + '</option>';
         console.log(newInput1);
         
         $('#longitude').append(newInput1);
         }
         
         },
         error: function (e) {
         alert('Error:================= ' + e);
         }
         });
         }
         
         
         
         function getAltitude(value) {
         
         var latitude = document.getElementById("latitude").value;
         
         $.ajax({
         url: "DynamicProjectConfigurationController",
         data: "task=getManual_base_table&Longitude=" + value + "&Latitude=" + latitude,
         type: 'POST',
         dataType: 'json',
         success: function (response) {
         var getAltitude = response.getData;
         
         $('#altitude').empty();
         var defaultInput = "<option value='' selected disabled>Select One</option>";
         $('#altitude').append(defaultInput);
         for (var i = 0; i < getAltitude.length; i++) {
         
         var newInput1 = '<option id="' + getAltitude[i].altitude + '" value="' + getAltitude[i].altitude + '" class="docDetails">' + getAltitude[i].altitude + '</option>';
         console.log(newInput1);
         
         $('#altitude').append(newInput1);
         }
         
         },
         error: function (e) {
         alert('Error:================= ' + e);
         }
         });
         }
         
         
         */

    </script>
