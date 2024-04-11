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
                        <h4 class="mb-sm-0 font-size-18">Device Configuration</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                                <li class="breadcrumb-item active">Device Configuration</li>
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
                                        <p class="text-muted mb-2">Select Dynamic_configuration_mapping</p>
                                        <div class="row">                
                                            <div class="col-md-8">
                                                <form method="post" action="DeviceConfigurationController">
                                                    <div class="row">
                                                        <div class="col-md-3">
                                                            <div class="mb-3">
                                                                <select  class="form-control  js-example-basic-single" id="dropdown" name="dynamic_configuration_mappingId">
                                                                    <option value="" selected>All</option>
                                                                    <c:forEach var="option" items="${getAllDynamicConfig}">
                                                                        <option value="${option.dynamic_configuration_mapping_id}" ${option.dynamic_configuration_mapping_id eq requestScope.dynamic_configuration_mappingId ? 'selected' : ''}>${option.dynamic_config_name}</option>
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
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Device Configuration</button>
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
                                                                <th>Device Configuration Name</th>
                                                                <th>Dynamic Configuration Mapping</th>
                                                                <th>Communication Type Mapping</th>
                                                                <th>Mask Angle Byte Value</th>
                                                                <th>Mask Angle Display Value</th>
                                                                <th>Device Work Mode Name</th>
                                                                <th>Device Work Mode Value</th>
                                                                <th>Default Config</th>
                                                                <th>Config_time</th>
                                                                <th>Remarks</th>

                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="DeviceConfiguration" items="${requestScope['getDeviceConfigurationList']}"
                                                                       varStatus="loopCounter">
                                                                <tr>
                                                                    <td>${loopCounter.count}</td>
                                                                    <td>
                                                                        <p class="mb-0">${DeviceConfiguration.device_configuration_name}</p>
                                                                    </td>
                                                                     <td>
                                                                        <p class="mb-0">${DeviceConfiguration.dynamic_config_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DeviceConfiguration.communication_type_mapping_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DeviceConfiguration.mask_angle_byteValue}</p>
                                                                    </td>
                                                                    
                                                                    <td>
                                                                        <p class="mb-0">${DeviceConfiguration.mask_angle_displayValue}</p>
                                                                    </td>
                                                                    
                                                                    <td>
                                                                        <p class="mb-0">${DeviceConfiguration.device_work_mode_name}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DeviceConfiguration.device_work_mode_value}</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${DeviceConfiguration.defaultConfig}</p>
                                                                    </td>
                                                                 
                                                                    <td>
                                                                        <p class="mb-0">${DeviceConfiguration.config_time}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${DeviceConfiguration.remark}</p>
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
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Device Configuration</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <form class="custom-validation" action="DeviceConfigurationController" method="post">
                            <div class="row">  

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Dynamic Configuration<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="dynamic_configuration_mapping_id" id="dynamic_configuration_mapping_id" required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="DynamicConfigName" items="${requestScope['getAllDynamicConfigName']}"
                                                       varStatus="loopCounter">
                                                <option  value="${DynamicConfigName.dynamic_configuration_mapping_id}" >${DynamicConfigName.dynamic_config_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Select Communication Type Mapping<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="communication_type_mapping_id" id="communication_type_mapping_id"    required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="Communication_type_mapping" items="${requestScope['getCommunication_type_mappingList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${Communication_type_mapping.communication_type_mapping_id}" value="${Communication_type_mapping.communication_type_mapping_id}" >${Communication_type_mapping.communication_type_mapping_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Mask Angle Byte Value<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="mask_angle_byteValue"
                                               placeholder="Mask Angle Byte Value" required>
                                    </div>
                                </div>
                                
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Mask Angle Display Value<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="mask_angle_displayValue"
                                               placeholder="Mask Angle Display Value" required>
                                    </div>
                                </div>
                                
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Device Work Mode Name<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="device_work_mode_name"
                                               placeholder="Device Work Mode Name" required>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Device Work Mode Value<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="device_work_mode_value"
                                               placeholder="Device Work Mode Value" required>
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