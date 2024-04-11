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
                        <h4 class="mb-sm-0 font-size-18">Manual Base Params</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="">Dashboard</a></li>
                                <li class="breadcrumb-item active">Manual Base Params</li>
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
                                        <p class="text-muted mb-2">Select Latitude</p>
                                        <div class="row">                
                                            <div class="col-md-8">
                                                <form method="post" action="ManualBaseController">
                                                    <div class="row">  
                                                        <div class="col-md-3">
                                                            <div class="mb-3">
                                                                <div class="custom-select" class="form-control"     style="width:100%; border:  1px  solid #ccc ;border-radius:5px">
                                                                    <select class="form-control" id="dropdown" name="latitude" size="1" >
                                                                        <option value="" selected disabled>Select One</option>
                                                                        <option value="" selected>All</option>
                                                                        <c:forEach var="option" items="${manualBaseList}">
                                                                            <option id="opt" value="${option.latitude}" ${option.latitude eq requestScope.lat ? 'selected' : ''}>${option.latitude}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
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
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Add Manual Base</button>
                                                    </div>      
                                                </div>                        
                                            </div>
                                        </div>                            
                                    </div>
                                </div>




                                <div class="card-body pt-0">
                                    <div class="mt-1">
                                        <!-- <div class="d-flex flex-wrap">
                                           <h5 class="font-size-16 me-3">Recent Leave</h5>
                                           <div class="ms-auto">
                                              <a href="javascript: void(0);" class="fw-medium text-reset">View All</a>
                                           </div>
                                        </div> -->
                                        <div class="table-responsive mt-0">
                                            <div class="row">
                                                <div class="col-12">
                                                    <table id="datatable" class="table table-bordered dt-responsive  nowrap w-100">
                                                        <thead>
                                                            <tr>
                                                                <th>Sr.</th>
                                                                <th>Manual Base Params Name</th>
                                                                <th>Latitude</th>
                                                                <th>Longitude</th>
                                                                <th>Altitude</th>
                                                                <th>Default Config</th>
                                                                <th>Remark</th>
                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody> 
                                                            <c:forEach var="manualBase" items="${requestScope['ManualBaseAllData']}"
                                                                       varStatus="loopCounter">
                                                                <tr>
                                                                    <td>${loopCounter.count}</td>
                                                                    <td>${manualBase.manual_base_params_name}</td>
                                                                    <td>
                                                                        <p class="mb-0">${manualBase.latitude}</p>
                                                                    </td>
                                                                    <td>${manualBase.longitude}</td>
                                                                    <td>${manualBase.altitude}</td>
                                                                    <td>${manualBase.defaultConfig}</td>
                                                                    <td>${manualBase.remark}</td>
                                                                    <td>
                                                                        <!--<a href="FileUploadController?task=getSingleDocumentDetails&documents_id=${documentData.documents_id}" data-bs-toggle="modal" data-bs-target=".bs-example-modal-lg-Add-Files" ><span class="badge badge-pill badge-soft-success font-size-14"><i class="fas fa-edit"></i> </span></a>-->
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



    <!-- ADD Manual Base Model Start -->

    <div class="modal fade bs-example-modal-xl-Add-Enquiry" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Manual Base Params</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <form class="custom-validation" action="ManualBaseController" method="post">
                            <div class="row">                      
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Manual Base Param name<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="manual_base_params_name"
                                               placeholder="manual_base_params_name" required>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Latitude<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="latitude"
                                               placeholder="latitude" required>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Longitude<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="longitude"
                                               placeholder="longitude" required>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Altitude<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="altitude"
                                               placeholder="altitude" required>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Default Config*<span class="text-danger">*</span></label>
                                        <select class="form-control"  name="defaultConfig" id="defaultConfig" required>
                                            <option value="N" selected>No</option>
                                            <option value="Y" selected>Yes</option>                                        
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Remark<span class="text-danger"></span> </label>
                                        <input type="text" class="form-control" name="remark"
                                               placeholder="remark">
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

    <!-- ADD Manual Base Model End -->

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