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
                        <h4 class="mb-sm-0 font-size-18">Project Details</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                                <li class="breadcrumb-item active">Project Details</li>
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
                                        <p class="text-muted mb-2">Select Operator</p>
                                        <div class="row">                
                                            <div class="col-md-8">
                                                <form method="post" action="ProjectDetailsController">
                                                    <div class="row">  
                                                        <div class="col-md-3">
                                                            <div class="custom-select" class="form-control"     style="width:100%; border:  1px  solid #ccc ;border-radius:5px">
                                                                <select  class="form-control"  id="dropdown" name="operatorName">
                                                                    <option value="" selected disabled>Select Operator</option>
                                                                    <option value="" selected >All</option>
                                                                    <c:forEach var="option" items="${getAllOperator}">
                                                                        <option value="${option.operator}" ${option.operator eq requestScope.operatorName ? 'selected' : ''}>${option.operator}</option>
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
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Project Details</button>
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
                                                    <table id="datatable" class="table table-bordered dt-responsive  nowrap w-100">
                                                        <thead>
                                                            <tr>
                                                                <th>Sr.</th>
                                                                <th>Project Name</th>
                                                                <th>Operator</th>
                                                                <th>Comment</th>
                                                                <th>Scale</th>
                                                                <th>Angle</th>
                                                                <th>Tx</th>
                                                                <th>Ty</th>
                                                                <th>Fixed_Easting</th>
                                                                <th>Fixed_Northing</th>
                                                                <th>SigmaZ</th>                                                               
                                                                <th>Project Config Mapping Name</th>
                                                                <th>Remarks</th>

                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="project" items="${requestScope['project_details']}"
                                                                       varStatus="loopCounter">
                                                                <tr>
                                                                    <td>${loopCounter.count}</td>
                                                                    <td>
                                                                        <p class="mb-0">${project.project_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${project.operator}</p>
                                                                    </td>
                                                                    
                                                                     <td>
                                                                        <p class="mb-0">${project.comment}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${project.scale}</p>
                                                                    </td>   

                                                                    <td>
                                                                        <p class="mb-0">${project.angle}</p>
                                                                    </td>   

                                                                    <td>
                                                                        <p class="mb-0">${project.tx}</p>
                                                                    </td>    
                                                                    <td>
                                                                        <p class="mb-0">${project.ty}</p>
                                                                    </td>  
                                                                    <td>
                                                                        <p class="mb-0">${project.fixed_Easting}</p>
                                                                    </td>  
                                                                    <td>
                                                                        <p class="mb-0">${project.fixed_Northing}</p>
                                                                    </td> 
                                                                      <td>
                                                                        <p class="mb-0">${project.sigmaZ}</p>
                                                                    </td> 
                                                                    <td>
                                                                        <p class="mb-0">${project.project_configuration_mapping_name}</p>
                                                                    </td> 
                                                                    
                                                                    <td>
                                                                        <p class="mb-0">${project.remark}</p>
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
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Project Details</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <form class="custom-validation" action="ProjectDetailsController" method="post" >
                            <div class="row">   
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Project Name<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="project_name"
                                               placeholder="Project Name"  required>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Operator<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="operator"
                                               placeholder="Operator"  required>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Comment<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="comment"
                                               placeholder="Comment" >
                                    </div>
                                </div>
                                <!--                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Select Folder<span class="text-danger">*</span></label>
                                
                                                                        <select class="form-control"  name="folder_id" id="folder_id" required>
                                                                            <option value="" selected disabled>---Select One---</option>
                                <c:forEach var="folder" items="${requestScope['folder_details']}"
                                           varStatus="loopCounter">
                                    <option id ="${folder.folder_id}" value="${folder.folder_id}" >${folder.folder_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>-->
                                <!--                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Project Status<span class="text-danger">*</span></label>
                                
                                                                        <select class="form-control"  name="project_status_id" id="project_status_id" required>
                                                                            <option value="" selected disabled>---Select One---</option>
                                <c:forEach var="status" items="${requestScope['status_details']}"
                                           varStatus="loopCounter">
                                    <option id ="${status.project_status_id}" value="${status.project_status_id}" >${status.status}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>-->
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Scale<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="scale" id="scale"    onchange="getAngleList(this.value)"  required>
                                            <option value="" selected disabled>---Select One---</option>
                                            <c:forEach var="Scale" items="${requestScope['scale_list']}"
                                                       varStatus="loopCounter">
                                                <option id ="${Scale.scale}" value="${Scale.scale}" >${Scale.scale}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Angle<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="angle" id="angle_siteCal_id" onchange="getTxList(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Tx<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="Tx" id="Tx_siteCal_id" onchange="getTyList(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Ty<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="Ty" id="Ty_siteCal_id" onchange="getFixed_EastingList(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Fixed_Easting<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="Fixed_Easting" id="Fixed_Easting_siteCal_id" onchange="getFixed_NorthingList(this.value)" required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Fixed_Northing<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="Fixed_Northing" id="Fixed_Northing_siteCal_id" onchange="getSigmaZList(this.value)"  required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select sigmaZ<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="sigmaZ" id="sigmaZ_siteCal_id"   required>
                                            <option value="" selected disabled>Select One</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Project Configuration Mapping<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="project_configuration_mapping_id" id="project_configuration_mapping_id" required>
                                            <option value="" selected disabled>---Select One---</option>
                                            <c:forEach var="ProjectConfigurationMapping" items="${requestScope['getProjectConfigurationMappingList']}"
                                                       varStatus="loopCounter">
                                                <option  value="${ProjectConfigurationMapping.project_configuration_mapping_id}" >${ProjectConfigurationMapping.project_configuration_mapping_name}</option>
                                            </c:forEach>
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

    <jsp:include page="layout/footer.jsp" />

    <script src="assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
    <script src="assets/libs/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="assets/libs/datatables.net-buttons-bs4/js/buttons.bootstrap4.min.js"></script>

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

        function getAngleList(value) {
            $.ajax({
                url: "ProjectDetailsController",
                data: "task=getSiteCalList&scale=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var angle_details = response.getSiteCalList;
                    $('#angle_siteCal_id').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#angle_siteCal_id').append(defaultAngleInput);
                    for (var i = 0; i < angle_details.length; i++) {


                        var newInput1 = '<option id="' + angle_details[i].angle + '" value="' + angle_details[i].angle + '" class="docDetails">' + angle_details[i].angle + '</option>';
                        console.log(newInput1);

                        $('#angle_siteCal_id').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }



        function getTxList(value1) {



            var scale = document.getElementById('scale').value;
            $.ajax({
                url: "ProjectDetailsController",
                data: "task=getSiteCalList&angle=" + value1 + "&scale=" + scale,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var Tx_details = response.getSiteCalList;
                    $('#Tx_siteCal_id').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#Tx_siteCal_id').append(defaultAngleInput);
                    for (var i = 0; i < Tx_details.length; i++) {


                        var newInput1 = '<option id="' + Tx_details[i].tx + '" value="' + Tx_details[i].tx + '" class="docDetails">' + Tx_details[i].tx + '</option>';
                        console.log(newInput1);

                        $('#Tx_siteCal_id').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

        function getTyList(value) {
            var scale = document.getElementById('scale').value;
            var angle = document.getElementById('angle_siteCal_id').value;


            $.ajax({
                url: "ProjectDetailsController",
                data: "task=getSiteCalList&Tx=" + value + "&scale=" + scale + "&angle=" + angle,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var Ty_details = response.getSiteCalList;
                    $('#Ty_siteCal_id').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#Ty_siteCal_id').append(defaultAngleInput);
                    for (var i = 0; i < Ty_details.length; i++) {


                        var newInput1 = '<option id="' + Ty_details[i].ty + '" value="' + Ty_details[i].ty + '" class="docDetails">' + Ty_details[i].ty + '</option>';
                        console.log(newInput1);

                        $('#Ty_siteCal_id').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }


        function getFixed_EastingList(value) {
            var scale = document.getElementById('scale').value;
            var angle = document.getElementById('angle_siteCal_id').value;
            var Tx = document.getElementById('Tx_siteCal_id').value;
            $.ajax({
                url: "ProjectDetailsController",
                data: "task=getSiteCalList&Ty=" + value + "&scale=" + scale + "&angle=" + angle + "&Tx=" + Tx,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var Fixed_Easting_details = response.getSiteCalList;
                    $('#Fixed_Easting_siteCal_id').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#Fixed_Easting_siteCal_id').append(defaultAngleInput);
                    for (var i = 0; i < Fixed_Easting_details.length; i++) {


                        var newInput1 = '<option id="' + Fixed_Easting_details[i].fixed_Easting + '" value="' + Fixed_Easting_details[i].fixed_Easting + '" class="docDetails">' + Fixed_Easting_details[i].fixed_Easting + '</option>';
                        console.log(newInput1);

                        $('#Fixed_Easting_siteCal_id').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }



        function getFixed_NorthingList(value) {
            var scale = document.getElementById('scale').value;
            var angle = document.getElementById('angle_siteCal_id').value;
            var Tx = document.getElementById('Tx_siteCal_id').value;
            var Ty = document.getElementById('Ty_siteCal_id').value;
            $.ajax({
                url: "ProjectDetailsController",
                data: "task=getSiteCalList&Fixed_Easting=" + value + "&Ty=" + Ty + "&scale=" + scale + "&angle=" + angle + "&Tx=" + Tx,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var Fixed_Northing_details = response.getSiteCalList;

                    $('#Fixed_Northing_siteCal_id').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#Fixed_Northing_siteCal_id').append(defaultAngleInput);
                    for (var i = 0; i < Fixed_Northing_details.length; i++) {


                        var newInput1 = '<option id="' + Fixed_Northing_details[i].fixed_Northing + '" value="' + Fixed_Northing_details[i].fixed_Northing + '" class="docDetails">' + Fixed_Northing_details[i].fixed_Northing + '</option>';
                        console.log(newInput1);

                        $('#Fixed_Northing_siteCal_id').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }



        function getSigmaZList(value) {
            var scale = document.getElementById('scale').value;
            var angle = document.getElementById('angle_siteCal_id').value;
            var Tx = document.getElementById('Tx_siteCal_id').value;
            var Ty = document.getElementById('Ty_siteCal_id').value;
            var Fixed_Easting = document.getElementById('Fixed_Easting_siteCal_id').value;
            $.ajax({
                url: "ProjectDetailsController",
                data: "task=getSiteCalList&Fixed_Northing=" + value + "&Fixed_Easting=" + Fixed_Easting + "&Ty=" + Ty + "&scale=" + scale + "&angle=" + angle + "&Tx=" + Tx,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var SigmaZ_details = response.getSiteCalList;
                    $('#sigmaZ_siteCal_id').empty();
                    var defaultAngleInput = "<option value='' selected disabled>Select One</option>";
                    $('#sigmaZ_siteCal_id').append(defaultAngleInput);
                    for (var i = 0; i < SigmaZ_details.length; i++) {


                        var newInput1 = '<option id="' + SigmaZ_details[i].sigmaZ + '" value="' + SigmaZ_details[i].sigmaZ + '" class="docDetails">' + SigmaZ_details[i].sigmaZ + '</option>';
                        console.log(newInput1);

                        $('#sigmaZ_siteCal_id').append(newInput1);
                    }


                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

    </script>