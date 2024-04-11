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
                        <h4 class="mb-sm-0 font-size-18">Survey Configuration</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                                <li class="breadcrumb-item active">Survey Configuration</li>
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
                                        <p class="text-muted mb-2">Select Angle Unit</p>
                                        <div class="row">                
                                            <div class="col-md-8">
                                                <form method="post" action="SurveyConfigurationController">
                                                    <div class="row">  
                                                        <div class="col-md-3">
                                                            <div class="custom-select" class="form-control"     style="width:100%; border:  1px  solid #ccc ;border-radius:5px">
                                                                <select class="form-control" id="dropdown" name="angleunitId" size="1" >
                                                                    <option value="" selected disabled>Select One</option>
                                                                    <option value="" selected>All</option>
                                                                    <c:forEach var="option" items="${getAngleunitList}">
                                                                        <option value="${option.angleunit_id}" ${option.angleunit_id eq requestScope.angleunitId ? 'selected' : ''}>${option.angUnit_name}</option>
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
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Survey Configuration</button>
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
                                                                <th style="max-width:120px;overflow: hidden;text-overflow: ellipsis;">Survey Conf. Name</th>
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
                                                                <th>DefaultConfig</th>
                                                                <th>Config_time</th>
                                                                <th>Remarks</th>

                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="SurveyConfiguration" items="${requestScope['getSurveyConfigurationList']}"
                                                                       varStatus="loopCounter">
                                                                <tr>
                                                                    <td>${loopCounter.count}</td>
                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.survey_configuration_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.datum_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.zone}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.elevationType}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.disUnit_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.angUnit_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.zone_name}</p>
                                                                    </td> 

                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.origin_lat}</p>
                                                                    </td>  

                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.origin_lng}</p>
                                                                    </td>    

                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.scale}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.false_easting}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.false_northing}</p>
                                                                    </td>


                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.paralell_1}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.paralell_2}</p>
                                                                    </td> 
                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.projectionType}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.defaultConfig}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.config_time}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${SurveyConfiguration.remark}</p>
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
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Survey Configuration</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <form class="custom-validation" action="SurveyConfigurationController" method="post">
                            <div class="row">  
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Survey Configuration Name<span class="text-danger"></span>* </label>
                                        <input type="text" class="form-control" name="survey_configuration_name"
                                               placeholder="Survey Configuration Name" required>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Datum<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="datum_id" id="datum_id" required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="datum" items="${requestScope['getDatumnList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${datum.datum_id}" value="${datum.datum_id}" >${datum.datum_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Zone Data<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="zonedata_id" id="zonedata_id" onchange="getClientName(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="Zonedata" items="${requestScope['getZonedataList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${Zonedata.zonedata_id}" value="${Zonedata.zonedata_id}" >${Zonedata.zone}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Elevation Type<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="elevationtype_id" id="elevationtype_id"    required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="Elevationtype" items="${requestScope['getElevationtypeList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${Elevationtype.elevationtype_id}" value="${Elevationtype.elevationtype_id}" >${Elevationtype.elevationType}</option>
                                            </c:forEach>                                       
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Distance Unit<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="distanceunit_id" id="distanceunit_id" required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="distanceunit" items="${requestScope['getdistanceunitList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${distanceunit.distanceunit_id}" value="${distanceunit.distanceunit_id}" >${distanceunit.disUnit_name}</option>
                                            </c:forEach>                                      
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Angle Unit<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="angleunit_id" id="angleunit_id" required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="Angleunit" items="${requestScope['getAngleunitList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${Angleunit.angleunit_id}" value="${Angleunit.angleunit_id}" >${Angleunit.angUnit_name}</option>
                                            </c:forEach>                                      
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Zone Name<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="zone_name" id="zone_name"   onchange="getOrigin_lat(this.value)" required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="Zone_name" items="${requestScope['getZone_nameList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${Zone_name.zone_name}" value="${Zone_name.zone_name}" >${Zone_name.zone_name}</option>
                                            </c:forEach>                                      
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Origin Latitude<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="projectionparam_origin_lat" id="projectionparam_origin_lat" onchange="getOrigin_lng(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Origin Longitude<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="projectionparam_origin_lng" id="projectionparam_origin_lng" onchange="getScale(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>  
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Scale<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="projectionparam_scale" id="projectionparam_scale" onchange="getFalse_easting(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select False_easting<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="projectionparam_false_easting" id="projectionparam_false_easting" onchange="getFalse_northing(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>  
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select False_northing<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="projectionparam_false_northing" id="projectionparam_false_northing" onchange="getParalell_1(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>   

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Paralell_1<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="projectionparam_paralell_1" id="projectionparam_paralell_1" onchange="getParalell_2(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Paralell_2<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="projectionparam_paralell_2" id="projectionparam_paralell_2" onchange="getProjectiontype(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Projection Type<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="projectiontype_id" id="projectiontype_id"    required>
                                            <option value="" selected disabled>Select One</option>

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

    <!-- ADD Hemisphere Model End -->s
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

        function getOrigin_lat(value) {
            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&Zone_name=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getOrigin_lat = response.getProjectionparametersList;
                    $('#projectionparam_origin_lat').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_origin_lat').append(defaultAngleInput);
                    for (var i = 0; i < getOrigin_lat.length; i++) {


                        var newInput1 = '<option id="' + getOrigin_lat[i].origin_lat + '" value="' + getOrigin_lat[i].origin_lat + '" class="docDetails">' + getOrigin_lat[i].origin_lat + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_origin_lat').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

        function getOrigin_lng(value) {
            var zone_name = document.getElementById('zone_name').value;
            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&Origin_lat=" + value + "&Zone_name=" + zone_name,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getOrigin_lat = response.getProjectionparametersList;
                    $('#projectionparam_origin_lng').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_origin_lng').append(defaultAngleInput);
                    for (var i = 0; i < getOrigin_lat.length; i++) {


                        var newInput1 = '<option id="' + getOrigin_lat[i].origin_lng + '" value="' + getOrigin_lat[i].origin_lng + '" class="docDetails">' + getOrigin_lat[i].origin_lng + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_origin_lng').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

        function getScale(value) {
            var zone_name = document.getElementById('zone_name').value;
            var origin_lat = document.getElementById('projectionparam_origin_lat').value;


            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&Origin_lng=" + value + "&Zone_name=" + zone_name + "&Origin_lat=" + origin_lat,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getScale = response.getProjectionparametersList;
                    $('#projectionparam_scale').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_scale').append(defaultAngleInput);
                    for (var i = 0; i < getScale.length; i++) {


                        var newInput1 = '<option id="' + getScale[i].scale + '" value="' + getScale[i].scale + '" class="docDetails">' + getScale[i].scale + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_scale').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }


        function getFalse_easting(value) {
            var zone_name = document.getElementById('zone_name').value;
            var origin_lat = document.getElementById('projectionparam_origin_lat').value;
            var origin_lng = document.getElementById('projectionparam_origin_lng').value;

            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&Scale=" + value + "&Origin_lng=" + origin_lng + "&Zone_name=" + zone_name + "&Origin_lat=" + origin_lat,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getFalse_easting = response.getProjectionparametersList;
                    $('#projectionparam_false_easting').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_false_easting').append(defaultAngleInput);
                    for (var i = 0; i < getFalse_easting.length; i++) {


                        var newInput1 = '<option id="' + getFalse_easting[i].false_easting + '" value="' + getFalse_easting[i].false_easting + '" class="docDetails">' + getFalse_easting[i].false_easting + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_false_easting').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }


        function getFalse_northing(value) {
            var zone_name = document.getElementById('zone_name').value;
            var origin_lat = document.getElementById('projectionparam_origin_lat').value;
            var origin_lng = document.getElementById('projectionparam_origin_lng').value;
            var Scale = document.getElementById('projectionparam_scale').value;

            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&False_easting=" + value + "&Scale=" + Scale + "&Origin_lng=" + origin_lng + "&Zone_name=" + zone_name + "&Origin_lat=" + origin_lat,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getFalse_northing = response.getProjectionparametersList;
                    $('#projectionparam_false_northing').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_false_northing').append(defaultAngleInput);
                    for (var i = 0; i < getFalse_northing.length; i++) {


                        var newInput1 = '<option id="' + getFalse_northing[i].false_northing + '" value="' + getFalse_northing[i].false_northing + '" class="docDetails">' + getFalse_northing[i].false_northing + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_false_northing').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }



        function getParalell_1(value) {
            var zone_name = document.getElementById('zone_name').value;
            var origin_lat = document.getElementById('projectionparam_origin_lat').value;
            var origin_lng = document.getElementById('projectionparam_origin_lng').value;
            var Scale = document.getElementById('projectionparam_scale').value;
            var False_easting = document.getElementById('projectionparam_false_easting').value;

            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&False_northing=" + value + "&False_easting=" + False_easting + "&Scale=" + Scale + "&Origin_lng=" + origin_lng + "&Zone_name=" + zone_name + "&Origin_lat=" + origin_lat,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getParalell_1 = response.getProjectionparametersList;
                    $('#projectionparam_paralell_1').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_paralell_1').append(defaultAngleInput);
                    for (var i = 0; i < getParalell_1.length; i++) {


                        var newInput1 = '<option id="' + getParalell_1[i].paralell_1 + '" value="' + getParalell_1[i].paralell_1 + '" class="docDetails">' + getParalell_1[i].paralell_1 + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_paralell_1').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

        function getParalell_2(value) {
            var zone_name = document.getElementById('zone_name').value;
            var origin_lat = document.getElementById('projectionparam_origin_lat').value;
            var origin_lng = document.getElementById('projectionparam_origin_lng').value;
            var Scale = document.getElementById('projectionparam_scale').value;
            var False_easting = document.getElementById('projectionparam_false_easting').value;
            var False_northing = document.getElementById('projectionparam_false_northing').value;


            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&Paralell_1=" + value + "&False_northing=" + False_northing + "&False_easting=" + False_easting + "&Scale=" + Scale + "&Origin_lng=" + origin_lng + "&Zone_name=" + zone_name + "&Origin_lat=" + origin_lat,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getParalell_2 = response.getProjectionparametersList;
                    $('#projectionparam_paralell_2').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_paralell_2').append(defaultAngleInput);
                    for (var i = 0; i < getParalell_2.length; i++) {


                        var newInput1 = '<option id="' + getParalell_2[i].paralell_2 + '" value="' + getParalell_2[i].paralell_2 + '" class="docDetails">' + getParalell_2[i].paralell_2 + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_paralell_2').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

        function getMisc1(value) {
            var zone_name = document.getElementById('zone_name').value;
            var origin_lat = document.getElementById('projectionparam_origin_lat').value;
            var origin_lng = document.getElementById('projectionparam_origin_lng').value;
            var Scale = document.getElementById('projectionparam_scale').value;
            var False_easting = document.getElementById('projectionparam_false_easting').value;
            var False_northing = document.getElementById('projectionparam_false_northing').value;
            var Paralell_1 = document.getElementById('projectionparam_paralell_1').value;



            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&Paralell_2=" + value + "&Paralell_1=" + Paralell_1 + "&False_northing=" + False_northing + "&False_easting=" + False_easting + "&Scale=" + Scale + "&Origin_lng=" + origin_lng + "&Zone_name=" + zone_name + "&Origin_lat=" + origin_lat,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getMisc1 = response.getProjectionparametersList;
                    $('#projectionparam_misc1').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_misc1').append(defaultAngleInput);
                    for (var i = 0; i < getMisc1.length; i++) {


                        var newInput1 = '<option id="' + getMisc1[i].misc1 + '" value="' + getMisc1[i].misc1 + '" class="docDetails">' + getMisc1[i].misc1 + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_misc1').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

        function getMisc2(value) {
            var zone_name = document.getElementById('zone_name').value;
            var origin_lat = document.getElementById('projectionparam_origin_lat').value;
            var origin_lng = document.getElementById('projectionparam_origin_lng').value;
            var Scale = document.getElementById('projectionparam_scale').value;
            var False_easting = document.getElementById('projectionparam_false_easting').value;
            var False_northing = document.getElementById('projectionparam_false_northing').value;
            var Paralell_1 = document.getElementById('projectionparam_paralell_1').value;
            var Paralell_2 = document.getElementById('projectionparam_paralell_2').value;




            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&Misc1=" + value + "&Paralell_2=" + Paralell_2 + "&Paralell_1=" + Paralell_1 + "&False_northing=" + False_northing + "&False_easting=" + False_easting + "&Scale=" + Scale + "&Origin_lng=" + origin_lng + "&Zone_name=" + zone_name + "&Origin_lat=" + origin_lat,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getMisc2 = response.getProjectionparametersList;
                    $('#projectionparam_misc2').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_misc2').append(defaultAngleInput);
                    for (var i = 0; i < getMisc2.length; i++) {


                        var newInput1 = '<option id="' + getMisc2[i].misc2 + '" value="' + getMisc2[i].misc2 + '" class="docDetails">' + getMisc2[i].misc2 + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_misc2').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }


        function getMisc3(value) {
            var zone_name = document.getElementById('zone_name').value;
            var origin_lat = document.getElementById('projectionparam_origin_lat').value;
            var origin_lng = document.getElementById('projectionparam_origin_lng').value;
            var Scale = document.getElementById('projectionparam_scale').value;
            var False_easting = document.getElementById('projectionparam_false_easting').value;
            var False_northing = document.getElementById('projectionparam_false_northing').value;
            var Paralell_1 = document.getElementById('projectionparam_paralell_1').value;
            var Paralell_2 = document.getElementById('projectionparam_paralell_2').value;
            var Misc1 = document.getElementById('projectionparam_misc1').value;

            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&Misc2=" + value + "&Misc1=" + Misc1 + "&Paralell_2=" + Paralell_2 + "&Paralell_1=" + Paralell_1 + "&False_northing=" + False_northing + "&False_easting=" + False_easting + "&Scale=" + Scale + "&Origin_lng=" + origin_lng + "&Zone_name=" + zone_name + "&Origin_lat=" + origin_lat,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getMisc3 = response.getProjectionparametersList;
                    $('#projectionparam_misc3').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_misc3').append(defaultAngleInput);
                    for (var i = 0; i < getMisc3.length; i++) {


                        var newInput1 = '<option id="' + getMisc3[i].misc3 + '" value="' + getMisc3[i].misc3 + '" class="docDetails">' + getMisc3[i].misc3 + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_misc3').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }


        function getMisc4(value) {
            var zone_name = document.getElementById('zone_name').value;
            var origin_lat = document.getElementById('projectionparam_origin_lat').value;
            var origin_lng = document.getElementById('projectionparam_origin_lng').value;
            var Scale = document.getElementById('projectionparam_scale').value;
            var False_easting = document.getElementById('projectionparam_false_easting').value;
            var False_northing = document.getElementById('projectionparam_false_northing').value;
            var Paralell_1 = document.getElementById('projectionparam_paralell_1').value;
            var Paralell_2 = document.getElementById('projectionparam_paralell_2').value;
            var Misc1 = document.getElementById('projectionparam_misc1').value;
            var Misc2 = document.getElementById('projectionparam_misc2').value;
            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&Misc3=" + value + "&Misc2=" + Misc2 + "&Misc1=" + Misc1 + "&Paralell_2=" + Paralell_2 + "&Paralell_1=" + Paralell_1 + "&False_northing=" + False_northing + "&False_easting=" + False_easting + "&Scale=" + Scale + "&Origin_lng=" + origin_lng + "&Zone_name=" + zone_name + "&Origin_lat=" + origin_lat,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var getMisc4 = response.getProjectionparametersList;
                    $('#projectionparam_misc4').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectionparam_misc4').append(defaultAngleInput);
                    for (var i = 0; i < getMisc4.length; i++) {


                        var newInput1 = '<option id="' + getMisc4[i].misc4 + '" value="' + getMisc4[i].misc4 + '" class="docDetails">' + getMisc4[i].misc4 + '</option>';
                        console.log(newInput1);

                        $('#projectionparam_misc4').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }


        function getProjectiontype(value) {
            var zone_name = document.getElementById('zone_name').value;
            var origin_lat = document.getElementById('projectionparam_origin_lat').value;
            var origin_lng = document.getElementById('projectionparam_origin_lng').value;
            var Scale = document.getElementById('projectionparam_scale').value;
            var False_easting = document.getElementById('projectionparam_false_easting').value;
            var False_northing = document.getElementById('projectionparam_false_northing').value;
            var Paralell_1 = document.getElementById('projectionparam_paralell_1').value;
            var Paralell_2 = document.getElementById('projectionparam_paralell_2').value;



            $.ajax({
                url: "SurveyConfigurationController",
                data: "task=getProjectionparametersList&Paralell_2=" + value + "&Paralell_1=" + Paralell_1 + "&False_northing=" + False_northing + "&False_easting=" + False_easting + "&Scale=" + Scale + "&Origin_lng=" + origin_lng + "&Zone_name=" + zone_name + "&Origin_lat=" + origin_lat,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    var getProjectiontype = response.getProjectionparametersList;
                    $('#projectiontype_id').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#projectiontype_id').append(defaultAngleInput);
                    for (var i = 0; i < getProjectiontype.length; i++) {


                        var newInput1 = '<option id="' + getProjectiontype[i].projectiontype_id + '" value="' + getProjectiontype[i].projectiontype_id + '" class="docDetails">' + getProjectiontype[i].projectionType + '</option>';
                        console.log(newInput1);

                        $('#projectiontype_id').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

    </script>