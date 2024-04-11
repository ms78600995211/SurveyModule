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
                        <h4 class="mb-sm-0 font-size-18">Communication Type Mapping</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                                <li class="breadcrumb-item active">Communication Type Mapping </li>
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
                                        <p class="text-muted mb-2">Select Communication Type</p>
                                        <div class="row">                
                                            <div class="col-md-8">
                                                <form method="post" action="CommunicationTypeMappingController">
                                                    <div class="row">  
                                                        <div class="col-md-3">
                                                            <div class="custom-select" class="form-control"     style="width:100%; border:  1px  solid #ccc ;border-radius:5px">
                                                                <select class="form-control" id="dropdown" name="typeOfCommunicationId" size="1" >
                                                                    <option value="" selected disabled>Select One</option>
                                                                    <option value="" selected>All</option>
                                                                    <c:forEach var="option" items="${getTypeOfCommunicationList}">
                                                                        <option value="${option.type_of_communication_id}" ${option.type_of_communication_id eq requestScope.typeOfCommunicationId ? 'selected' : ''}>${option.type_of_communication_name}</option>
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
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Communication Type Mapping </button>
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
                                                                <th>Communication Type Mapping</th>
                                                                <th>Communication Types</th>
                                                                <th>4G Parameters</th>
                                                                <th>Wifi Parameters</th>
                                                                <th>PDA Parameters</th>
                                                                <th>Radio External Parameters</th>
                                                                <th>Radio Internal Parameters</th>
                                                                <th>Default Config</th>
                                                                <th>Remarks</th>
                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="CommunicationTypeMapping" items="${requestScope['getCommunicationTypeMappingList']}"
                                                                       varStatus="loopCounter">
                                                                <tr>
                                                                    <td>${loopCounter.count}</td>
                                                                    <td>
                                                                        <p class="mb-0">${CommunicationTypeMapping.communication_type_mapping_name}</p>

                                                                    </td>
                                                                    <td>
                                                                        <p class="mb-0">${CommunicationTypeMapping.type_of_communication_name}</p>

                                                                    </td>
                                                                    <td>
                                                                        <c:if test="${not empty CommunicationTypeMapping.via4gparams_name}"  >
                                                                            <span>${CommunicationTypeMapping.via4gparams_name}</span>                                                                      
                                                                            <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry1"  onclick="showVia4gparams('${CommunicationTypeMapping.via4gparams_name}')">View</button>
                                                                        </c:if>
                                                                    </td>

                                                                    <td>
                                                                        <c:if test="${not empty CommunicationTypeMapping.wifiparams_name}"  >
                                                                            <span>${CommunicationTypeMapping.wifiparams_name}</span>                                                                      
                                                                            <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry2"  onclick="showWiFiparams('${CommunicationTypeMapping.wifiparams_name}')">View</button>
                                                                        </c:if>
                                                                    </td>

                                                                    <td>
                                                                        <c:if test="${not empty CommunicationTypeMapping.pdaparams_name}"  >
                                                                            <span>${CommunicationTypeMapping.pdaparams_name}</span>                                                                      
                                                                            <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry3"  onclick="showPdaparams('${CommunicationTypeMapping.pdaparams_name}')">View</button>
                                                                        </c:if>
                                                                    </td>

                                                                    <td>
                                                                        <c:if test="${not empty CommunicationTypeMapping.radioexternalparams_name}"  >
                                                                            <span>${CommunicationTypeMapping.radioexternalparams_name}</span>                                                                      
                                                                            <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry4"  onclick="showExternalRadioparams('${CommunicationTypeMapping.radioexternalparams_name}')">View</button>
                                                                        </c:if>
                                                                    </td>


                                                                    <td>
                                                                        <c:if test="${not empty CommunicationTypeMapping.radiointernalparams_name}"  >
                                                                            <span>${CommunicationTypeMapping.radiointernalparams_name}</span>                                                                      
                                                                            <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry5"  onclick="showInternalRadioparams('${CommunicationTypeMapping.radiointernalparams_name}')">View</button>
                                                                        </c:if>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${CommunicationTypeMapping.defaultConfig}</p>
                                                                    </td>

                                                                    <td>
                                                                        <p class="mb-0">${CommunicationTypeMapping.remark}</p>
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
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Communication Type Mapping </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <form class="custom-validation" action="CommunicationTypeMappingController" method="post">
                            <div class="row">  


                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Communication Type<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="type_of_communication_id" id="type_of_communication_id" onchange="getCommunicationList(this.value)"   required>
                                            <option value="" selected disabled>Select One</option>
                                            <c:forEach var="TypeOfCommunication" items="${requestScope['getTypeOfCommunicationList']}"
                                                       varStatus="loopCounter">
                                                <option id ="${TypeOfCommunication.type_of_communication_id}" value="${TypeOfCommunication.type_of_communication_id}" >${TypeOfCommunication.type_of_communication_name}</option>
                                            </c:forEach>                                      
                                        </select>
                                    </div>
                                </div>


                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label" id="label_name" >Communication Type Parameters<span class="text-danger">*</span></label>

                                        <select class="form-control"  name="params_name" id="mydiv"   required>
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

    <!-- ADD Hemisphere Model End -->




    <div class="modal fade bs-example-modal-xl-Add-Enquiry1" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Via 4G Parameters </h5>
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
                                                        <th>IP</th>
                                                        <th>Port No.</th>
                                                        <th>Username</th>
                                                        <th>Password</th>
                                                        <th>Default Config</th>
                                                        <th>Remarks</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody  >
                                                    <tr id="via4GParams">


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
                    <h5 class="modal-title" id="myExtraLargeModalLabel">WiFi Parameters </h5>
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
                                                        <th>IP</th>
                                                        <th>Port No.</th>
                                                        <th>SS Id</th>
                                                        <th>SSId Password</th>
                                                        <th>Username</th>
                                                        <th>Password</th>
                                                        <th>Default Config</th>
                                                        <th>mountPoint</th>
                                                        <th>Remarks</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody  >
                                                    <tr id="wifiParams">


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
                    <h5 class="modal-title" id="myExtraLargeModalLabel">PDA Parameters </h5>
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
                                                        <th>IP</th>
                                                        <th>Port No.</th>
                                                        <th>URL</th>
                                                        <th>Username</th>
                                                        <th>Password</th>
                                                        <th>Default Config</th>                                                    
                                                        <th>Mount Point</th>
                                                        <th>Remarks</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody  >
                                                    <tr id="pdaParams">


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



    <div class="modal fade bs-example-modal-xl-Add-Enquiry4" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Radio External Parameters </h5>
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
                                                        <th>Power</th>
                                                        <th>Protocol</th>
                                                        <th>Frequency</th>
                                                        <th>Default Config</th>                                                    
                                                        <th>Remarks</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody  >
                                                    <tr id="externalRadioparams">


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



    <div class="modal fade bs-example-modal-xl-Add-Enquiry5" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Radio Internal Parameters </h5>
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
                                                        <th>Data Rate</th>
                                                        <th>baudRate</th>
                                                        <th>Power</th>
                                                        <th>Frequency</th>
                                                        <th>Default Config</th>                                                    
                                                        <th>Remarks</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody  >
                                                    <tr id="internalRadioparams">


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




        function getCommunicationList(value) {

            $.ajax({
                url: "CommunicationTypeMappingController",
                data: "task=getCommunicationList&Type_of_communication_id=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    var getData = response.getData;
                    var paramsList_name = response.paramsList_name;
                    $('#mydiv').empty();
                    document.getElementById('label_name').innerText = paramsList_name;
                    var defaultInput = "<option value='' selected disabled>Select One</option>";
                    $('#mydiv').append(defaultInput);
                    for (var i = 0; i < getData.length; i++) {

                        var newInput1 = '<option id="' + getData[i].params_name + '" value="' + getData[i].params_name + "_" + getData[i].params_id + '" class="docDetails">' + getData[i].params_name + '</option>';
                        console.log(newInput1);
                        $('#mydiv').append(newInput1);

                    }

                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }


    </script>


    <script>

        function showVia4gparams(value) {
            $.ajax({
                url: "CommunicationTypeMappingController",
                data: "task=getParameters&Via4gparams_name=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    var via4gparams = response.Via4gparams;
                    $('#via4GParams').empty();
                    for (var i = 0; i < via4gparams.length; i++) {
                        var markup = "<td><p class='mb-0'>" + via4gparams[i].IP + "</p></td><td><p class='mb-0'>" + via4gparams[i].portNo
                                + "</p></td><td><p class='mb-0'>" + via4gparams[i].username +
                                "</p></td><td><p class='mb-0'>" + via4gparams[i].passwd + "</p></td><td><p class='mb-0'>"
                                + via4gparams[i].defaultConfig + "</p></td><td><p class='mb-0'>" + via4gparams[i].remark + "</p></td>";
                        console.log(markup);
                        $('#via4GParams').append(markup);
                    }
                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }




    </script>



    <script>

        function showWiFiparams(value) {
            $.ajax({
                url: "CommunicationTypeMappingController",
                data: "task=getWiFiparams&WiFiparams_name=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    var wifiparams = response.WiFiparams;
                    $('#wifiParams').empty();
                    for (var i = 0; i < wifiparams.length; i++) {
                        var wifiParam = "<td><p class='mb-0'>" + wifiparams[i].IP + "</p></td><td><p class='mb-0'>" + wifiparams[i].portNo
                                + "</p></td><td><p class='mb-0'>" + wifiparams[i].ssid + "</p></td><td><p class='mb-0'>"
                                + wifiparams[i].ssid_password + "</p></td><td><p class='mb-0'>" + wifiparams[i].username
                                + "</p></td><td><p class='mb-0'>" + wifiparams[i].passwd +
                                "</p></td><td><p class='mb-0'>" + wifiparams[i].defaultConfig + "</p></td><td><p class='mb-0'>"
                                + wifiparams[i].mountPoint + "</p></td><td><p class='mb-0'>" + wifiparams[i].remark + "</p></td>";
                        $('#wifiParams').append(wifiParam);
                    }
                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }




        function showPdaparams(value) {
            $.ajax({
                url: "CommunicationTypeMappingController",
                data: "task=getPDAparams&PDAparams_name=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    var pdaParams = response.PDAparams;
                    $('#pdaParams').empty();
                    for (var i = 0; i < pdaParams.length; i++) {
                        var pdaParam = "<td><p class='mb-0'>" + pdaParams[i].IP + "</p></td><td><p class='mb-0'>" + pdaParams[i].portNo
                                + "</p></td><td><p class='mb-0'>" + pdaParams[i].url + "</p></td><td><p class='mb-0'>"
                                + pdaParams[i].username + "</p></td><td><p class='mb-0'>" + pdaParams[i].passwd +
                                "</p></td><td><p class='mb-0'>" + pdaParams[i].defaultConfig + "</p></td><td><p class='mb-0'>"
                                + pdaParams[i].mountPoint + "</p></td><td><p class='mb-0'>" + pdaParams[i].remark + "</p></td>";
                        $('#pdaParams').append(pdaParam);
                    }
                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }



        function showExternalRadioparams(value) {
            $.ajax({
                url: "CommunicationTypeMappingController",
                data: "task=getExternalRadioparams&ExternalRadioparams=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    var externalRadioparams = response.ExternalRadioparams;
                    $('#externalRadioparams').empty();
                    for (var i = 0; i < externalRadioparams.length; i++) {

                        var externalRadioparam = "<td><p class='mb-0'>" + externalRadioparams[i].power + "</p></td><td><p class='mb-0'>" + externalRadioparams[i].protocol
                                + "</p></td><td><p class='mb-0'>" + externalRadioparams[i].frequency +
                                "</p></td><td><p class='mb-0'>" + externalRadioparams[i].defaultConfig
                                + "</p></td><td><p class='mb-0'>" + externalRadioparams[i].remark + "</p></td>";
                        $('#externalRadioparams').append(externalRadioparam);
                    }
                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }


        function showInternalRadioparams(value) {
            $.ajax({
                url: "CommunicationTypeMappingController",
                data: "task=getInternalRadioparams&InternalRadioparams=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {

                    var internalRadioparams = response.InternalRadioparams;

                    $('#internalRadioparams').empty();

                    for (var i = 0; i < internalRadioparams.length; i++) {

                        var internalRadioparam = "<td><p class='mb-0'>" + internalRadioparams[i].datarate
                                + "</p></td><td><p class='mb-0'>" + internalRadioparams[i].baudrate
                                + "</p></td><td><p class='mb-0'>" + internalRadioparams[i].power + "</p></td>\n\
                                <td><p class='mb-0'>" + internalRadioparams[i].frequency +
                                "</p></td><td><p class='mb-0'>" + internalRadioparams[i].defaultConfig
                                + "</p></td><td><p class='mb-0'>" + internalRadioparams[i].remark + "</p></td>";

                        $('#internalRadioparams').append(internalRadioparam);

                    }



                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }


    </script>