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
                        <h4 class="mb-sm-0 font-size-18">Miscellaneous Configuration</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                                <li class="breadcrumb-item active">Miscellaneous Configuration</li>
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

                                        <!--                                        <p class="text-muted mb-2">Select </p>-->
                                        <div class="row">                
                                            <div class="col-md-8">


                                                <!--                                                <form method="post" action="MiscellaneousConfigurationController">
                                                                                                    <div class="row">  
                                                                                                        <div class="col-md-3">
                                                                                                            <div class="custom-select" class="form-control"     style="width:100%; border:  1px  solid #ccc ;border-radius:5px">
                                                                                                                <select class="form-control" id="dropdown" name="codeName" size="1" >
                                                                                                                    <option value="" selected disabled>Select One</option>
                                                                                                                    <option value="" selected>All</option>
                                                <c:forEach var="option" items="${allCode_name}">
                                                    <option value="${option}" ${option eq requestScope.codeName ? 'selected' : ''}>${option}</option>
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
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Miscellaneous Configuration</button>
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

                                                                <th>Miscellaneous Config Name</th>
                                                                <th>Position Type</th>
                                                                <th>Min no_of_satellite </th>
                                                                <th>age_of_correction</th>
                                                                <th>pdop_tolerance</th>
                                                                <th>horizontal_percision</th>
                                                                <th>vertical_percision</th>
                                                                <th>check_validity_of_point_taken</th>
                                                                <th>observation_time_per_point</th>
                                                                <th>show_point_name</th>
                                                                <th>show_code_name</th>
                                                                <th>stake_horizontal_percision</th>
                                                                <th>stake_vertical_percision</th>
                                                                <th>stake_target_distance</th>
                                                                <th>inner_circle_radius</th>
                                                                <th>outer_circle_radius</th>
                                                                <th>auto_connect_last_connected_reciever</th>
                                                                <th>automatically_send_last_working_profile</th> 
                                                                <th>enable_compass</th>
                                                                <th>enable_e_bubble</th>
                                                                <th>enable_lrf</th>
                                                                <th>defaultConfig</th>   
                                                                <th>miscConfig_time</th>   
                                                                <th>Remarks</th>
                                                                <th></th>								
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="MiscellaneousConfiguration" items="${requestScope['getMiscellaneousConfigList']}"
                                                                       varStatus="loopCounter">
                                                                <tr>
                                                                    <td>${loopCounter.count}</td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.miscConfig_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.position_type}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.min_no_of_satellite}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.age_of_correction}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.pdop_tolerance}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.horizontal_percision}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.vertical_percision}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.check_validity_of_point_taken}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.observation_time_per_point}</p>
                                                                    </td>


                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.show_point_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.show_code_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.stake_horizontal_percision}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.stake_vertical_percision}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.stake_target_distance}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.inner_circle_radius}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.outer_circle_radius}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.auto_connect_last_connected_reciever}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.automatically_send_last_working_profile}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.enable_compass}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.enable_e_bubble}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.enable_lrf}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.defaultConfig}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.miscConfig_time}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${MiscellaneousConfiguration.remark}</p>
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
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Miscellaneous Configuration</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <form class="custom-validation" action="MiscellaneousConfigurationController" method="post">
                            <div class="row">  

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Misc Config Name<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="miscConfig_name"
                                               placeholder="Misc Config Name" required>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Position Type<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="position_type"
                                               placeholder="Position Type" >
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Min No of Satellite<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="min_no_of_satellite"
                                               placeholder="Min No of Satellite" >
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Age of correction<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="age_of_correction"
                                               placeholder="Age of correction" >
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">pdop_tolerance<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="pdop_tolerance"
                                               placeholder="pdop_tolerance" >
                                    </div>
                                </div>



                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Horizontal Percision<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="horizontal_percision"
                                               placeholder="Horizontal Percision" >
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Vertical Percision<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="vertical_percision"
                                               placeholder="Vertical Percision" >
                                    </div>
                                </div>
                                <!--                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label for="" class="form-label">Check Validity of point taken<span class="text-danger"></span> </label>
                                                                        <input type="text" class="form-control" name="check_validity_of_point_taken"
                                                                               placeholder="Check Validity of point taken" >
                                                                    </div>
                                                                </div>
                                
                                                                
                                -->

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Check Validity of point taken<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="check_validity_of_point_taken" id="check_validity_of_point_taken"  >
                                            <option value="" selected disabled>Select One</option>
                                            <option value="T" selected>True</option>
                                            <option value="F" >False</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Observation_time_per_point<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="observation_time_per_point"
                                               placeholder="Observation_time_per_point" >
                                    </div>
                                </div>



                                <!--                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label for="" class="form-label">Show_point_name<span class="text-danger"></span> </label>
                                                                        <input type="text" class="form-control" name="show_point_name"
                                                                               placeholder="Show_point_name" >
                                                                    </div>
                                                                </div>-->


                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Show_point_name<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="show_point_name" id="show_point_name"  >
                                            <option value="" selected disabled>Select One</option>
                                            <option value="T">True</option>
                                            <option value="F" selected>False</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Show_code_name<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="show_code_name" id="show_code_name"  >
                                            <option value="" selected disabled>Select One</option>
                                            <option value="T">True</option>
                                            <option value="F"  selected>False</option>
                                        </select>
                                    </div>
                                </div>

                                <!--
                                                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label for="" class="form-label">Show_code_name<span class="text-danger"></span> </label>
                                                                        <input type="text" class="form-control" name="show_code_name"
                                                                               placeholder="Show_code_name" >
                                                                    </div>
                                                                </div>-->

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Stake_horizontal_percision<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="stake_horizontal_percision"
                                               placeholder="Stake_horizontal_percision" >
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Stake_vertical_percision<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="stake_vertical_percision"
                                               placeholder="Stake_vertical_percision" >
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Stake_target_distance<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="stake_target_distance"
                                               placeholder="Stake_target_distance" >
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Inner_circle_radius<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="inner_circle_radius"
                                               placeholder="Inner_circle_radius" >
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Outer_circle_radius<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="outer_circle_radius"
                                               placeholder="Outer_circle_radius" >
                                    </div>
                                </div>



                                <!--                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label for="" class="form-label">Connect_last_connected_reciever<span class="text-danger">*</span> </label>
                                                                        <input type="text" class="form-control" name="auto_connect_last_connected_reciever"
                                                                               placeholder="Auto_connect_last_connected_reciever" >
                                                                    </div>
                                                                </div>
                                
                                -->


                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Connect_last_connected_reciever<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="auto_connect_last_connected_reciever" id="auto_connect_last_connected_reciever"  >
                                            <option value="" selected disabled>Select One</option>
                                            <option value="T">True</option>
                                            <option value="F" selected>False</option>
                                        </select>
                                    </div>
                                </div>



                                <!--                                
                                                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label for="" class="form-label" style="text-align: center">Enable_compass<span class="text-danger"></span> </label>
                                                                        <input type="text" class="form-control" name="enable_compass"
                                                                               placeholder="Enable_compass" >
                                                                    </div>
                                                                </div>
                                -->
                                <!--                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label for="" class="form-label">Enable_e_bubble<span class="text-danger"></span> </label>
                                                                        <input type="text" class="form-control" name="enable_e_bubble"
                                                                               placeholder="Enable_e_bubble" >
                                                                    </div>
                                                                </div>-->

                                <!--                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label for="" class="form-label">Send_last_working_profile<span class="text-danger"></span> </label>
                                                                        <input type="text" class="form-control" name="automatically_send_last_working_profile"
                                                                               placeholder="Automatically_send_last_working_profile" >
                                                                    </div>
                                                                </div>-->



                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Send_last_working_profile<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="automatically_send_last_working_profile" id="automatically_send_last_working_profile"  >
                                            <option value="" selected disabled>Select One</option>
                                            <option value="T">True</option>
                                            <option value="F" selected>False</option>
                                        </select>
                                    </div>
                                </div>


                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Enable_compass<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="enable_compass" id="enable_compass"  >
                                            <option value="" selected disabled>Select One</option>
                                            <option value="T">True</option>
                                            <option value="F" selected>False</option>
                                        </select>
                                    </div>
                                </div>





                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Enable_e_bubble<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="enable_e_bubble" id="enable_e_bubble"  >
                                            <option value="" selected disabled>Select One</option>
                                            <option value="T">True</option>
                                            <option value="F" selected>False</option>
                                        </select>
                                    </div>
                                </div>




                                <!--                                
                                                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label for="" class="form-label">Enable_lrf<span class="text-danger">*</span> </label>
                                                                        <input type="text" class="form-control" name="enable_lrf"
                                                                               placeholder="Enable_lrf" >
                                                                    </div>
                                                                </div>
                                -->

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Enable_lrf<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="enable_lrf" id="enable_lrf"  >
                                            <option value="" selected disabled>Select One</option>
                                            <option value="T">True</option>
                                            <option value="F" selected>False</option>
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

    <!-- ADD Hemisphere Model End -->
    <jsp:include page="layout/footer.jsp" />

    <script src="assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
    <script src="assets/js/pages/datatables.init.js"></script>
    <!--    <script src="assets/js/myScript.js"></script>-->
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>


    <script>


                                                                            $(document).ready(function () {
                                                                                $('.js-example-basic-single').select2();
                                                                            });

                                                                            setTimeout(function () {
                                                                                var alertElement = document.querySelector('.alert');
                                                                                alertElement.classList.add('fade');
                                                                                setTimeout(function () {
                                                                                    alertElement.remove();
                                                                                }, 1000);
                                                                            }, 2000);

    </script>