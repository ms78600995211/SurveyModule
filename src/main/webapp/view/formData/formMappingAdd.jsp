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

<style type="text/css">

    .scrollable{
        overflow: auto;
        height: 100px;
        border: 1px solid;
        font-size: 13px;
    }

</style>
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
                        <h4 class="mb-sm-0 font-size-18">Add Form</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                                <li class="breadcrumb-item active">Add Form</li>
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
                                                    <div id="example3"  align="center" >
                                                        <form class="custom-validation" action="FormMappingController" method="post">
                                                            <table id="" class="table table-bordered dt-responsive  nowrap w-100">
                                                                <input type="hidden" name="totalcount" id="totalcount" value="0">

                                                                <thead>
                                                                    <tr>
                                                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Form Name<span class="text-danger">*</span></label>
                                                                        <select class="form-control" name="Form0" id="formId" required>
                                                                            <option value="" selected disabled>Select</option>
                                                                            <c:forEach var="form" items="${requestScope['FormData']}"
                                                                                       varStatus="loopCounter">
                                                                                <option id ="${form.form_name}" value="${form.form_id}" >${form.form_name}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <input  type="button" class="add-row btn btn-primary" name="task" value="Add Column">
                                                                <input type="button" class="delete-row btn btn-danger" value="Delete Column">
                                                                <input  type="submit" value="Submit" class="btn btn-success" name="submitFormBtn">
                                                                </tr>
                                                                <input type="hidden" name="colm" value="" id="colm">

                                                                <br> <br>
                                                                <tr>
                                                                    <th>Check</th>
                                                                    <th>column name</th>
                                                                    <th>Display name</th>
                                                                    <th>Column Type</th>
                                                                    <th>Column Sub Type</th>
                                                                    <th>Selection</th>
                                                                    <!--<th>Data Type</th>-->
                                                                    <th>Data Length</th>
                                                                    <!--<th>Remark</th>-->
                                                                </tr>
                                                                </thead>
                                                                <tbody>                                                    
                                                                    <tr>
                                                                        <td><input  type="checkbox" name="record"></td>
                                                                        <td><input class='form-control' type="text" name="columnName0" placeholder="columnName.." onchange="checkColumnName(this.value)" required></td>
                                                                        <td><input class='form-control' type="text" name="DisplayName0" placeholder="DisplayName.." required></td>
                                                                        <td><select class="form-control" name="columnType0" id="columnType0" onchange="getColumnSubType(this.value, this.id)"required>
                                                                                <option value="" selected disabled>Select</option>
                                                                                <c:forEach var="column" items="${requestScope['ColumnType']}"
                                                                                           varStatus="loopCounter">
                                                                                    <option id ="${column.type_name}" value="${column.column_type_id}" >${column.type_name}</option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </td>

                                                                        <td><select class="form-control" name="columnSubType0" id="columnSubType0" required>
                                                                                <option value="" selected disabled>Select</option>
                                                                                <c:forEach var="columnSub" items="${requestScope['columnSubType']}"
                                                                                           varStatus="loopCounter">
                                                                                    <option id ="${columnSub.subtype_name}" value="${columnSub.column_subtype_id}" >${columnSub.subtype_name}</option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </td>
                                                                        <td><select class="form-control" name="selection0" id="selection">
                                                                                <option value="" selected disabled>Select</option>
                                                                                <option value="Yes">Yes</option>
                                                                                <option value="No">No</option>
                                                                            </select>
                                                                        </td>
                                                                        <!--<td><input class='form-control' type="text" name="dataType0" placeholder="datatype.." required></td>-->
                                                                        <td><input class='form-control' type="text" name="dataLength0" placeholder="datalength.." required></td>
                                                                        <!--<td><input class='form-control' type="text" name="Remark0"></td>-->
                                                                    </tr>
                                                                </tbody>

                                                            </table
                                                            <center> <div class="ms"><p style="color: red">${msg}</p></div></center>
                                                        </form>
                                                    </div>

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











    <!-- ADD Enquiry Model Start -->

    <div class="modal fade bs-example-modal-xl-Add-Enquiry" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Project Status Add</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <form class="custom-validation" action="FormMappingController" method="post">
                            <div class="row">  
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Form<span class="text-danger">*</span></label>
                                        <select class="form-control" name="statusType" id="statusType" required>
                                            <option value="" selected disabled>Select</option>
                                            <c:forEach var="StatusType" items="${requestScope['StatusTypeData']}"
                                                       varStatus="loopCounter">
                                                <option id ="${StatusType.status_type}" value="${StatusType.status_type_id}" >${StatusType.status_type}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row"> 
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Column Name<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="projectStatus"
                                               placeholder="Name" required>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Display Name<span class="text-danger">*</span> </label>
                                        <input type="text" class="form-control" name="projectStatus"
                                               placeholder="Name" required>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Column Type<span class="text-danger">*</span></label>
                                        <select class="form-control" name="statusType" id="statusType" required>
                                            <option value="" selected disabled>Select</option>
                                            <c:forEach var="StatusType" items="${requestScope['StatusTypeData']}"
                                                       varStatus="loopCounter">
                                                <option id ="${StatusType.status_type}" value="${StatusType.status_type_id}" >${StatusType.status_type}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Column Sub Type<span class="text-danger">*</span></label>
                                        <select class="form-control" name="statusType" id="statusType" required>
                                            <option value="" selected disabled>Select</option>
                                            <c:forEach var="StatusType" items="${requestScope['StatusTypeData']}"
                                                       varStatus="loopCounter">
                                                <option id ="${StatusType.status_type}" value="${StatusType.status_type_id}" >${StatusType.status_type}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Selection<span class="text-danger">*</span></label>
                                        <select class="form-control" name="selection" id="selection" onchange="AddSelectionValues(this.value)" required>
                                            <option value="" selected disabled>Select</option>
                                            <option value="Yes" >Yes</option>
                                            <option value="No" >No</option>
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

                            <!--                            <div class="row" style="display: none" id="selection_row"> 
                                                            <div class="col-md-3">
                                                                <div class="mb-3">
                                                                    <label for="" class="form-label">Selection<span class="text-danger">*</span> </label>
                                                                    <button type="button" class="btn btn-primary" onclick="addselectioninput()">+</button>
                                                                    <button class="btn btn-danger" type="button" >-</button>
                                                                    <input type="text" class="form-control" name="selection"
                                                                           placeholder="selection Value.." required>
                                                                </div>
                                                            </div>
                                                        </div>-->

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

    <!-- ADD Enquiry Model End -->







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



    <script>
                                            $('#datatable').DataTable({
                                                "ordering": false
                                            });
    </script>

    <script>
        function getColumnSubType(value, id) {
            var count = id.substring(10, 11);
            $.ajax({
                url: "FormMappingController",
                data: "task=getColumnSubType&columnType=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
//                debugger;
                    var dataValue = response.data;
                    $('#columnSubType' + count).empty();
                    var option1;
                    for (var i = 0; i < dataValue.length; i++) {
                        var myBean = dataValue[i];
                        option1 += "<option value='" + dataValue[i].column_subtype_id + "'>" + dataValue[i].subtype_name + "</option>";

                        // Update your HTML with values from myBean
                    }
                    $('#columnSubType' + count).html('<option >Select</option>' + option1);

                },
                error: function (e) {
                    alert('Error:12212212112121 ' + e);
                }
            });
        }

//        function AddSelectionValues(value) {
////            alert(value);
//            if (value == 'Yes') {
//                $('#selection_row').show();
//            } else {
//                $('#selection_row').hide();
//            }
//        }
//
//        function addselectioninput() {
//            alert("Hii");
//        }
    </script>

    <script type="text/javascript">

        var i = 1;

        $(document).ready(function () {
            $(".add-row").click(function () {
                var markup = "<tr><td><input type='checkbox' name='record'></td>"

                        + "<td><input class='form-control' type='text' name='columnName" + i + "' placeholder='columnName..' onchange='checkColumnName(this.value)' required></td>"
                        + "<td><input class='form-control' type='text' name='DisplayName" + i + "' placeholder='DisplayName..' required></td>"
                        + "<td><select class='form-control columnTypeClass' name='columnType" + i + "' id='columnType" + i + "'  onchange='getColumnSubType(this.value, this.id)' required><option value='' selected disabled>Select</option></select></td>"
                        + "<td><select class='form-control' name='columnSubType" + i + "' id='columnSubType" + i + "' required><option value='' selected disabled>Select</option></select></td>"
                        + "<td><select class='form-control' name='selection" + i + "' id='selection' required><option value='' selected disabled>Select</option> <option value='Yes'>Yes</option><option value='No'>No</option></select></td>"
//                        + "<td><input class='form-control' type='text' name='dataType" + i + "' placeholder='datatype..' required></td>"
                        + "<td><input class='form-control' type='text' name='dataLength" + i + "' placeholder='datalength..' required></td>";
//                        + "<td><input class='form-control' type='text' name='Remark" + i + "'></td>";

                $("table tbody").append(markup);


                $.ajax({
                    url: "FormMappingController",
                    data: "task=getColumnType",
                    type: 'POST',
                    dataType: 'json',
                    success: function (response) {
                        var dataValue = response.data1;
                        var option1;
                        for (var j = 0; j < dataValue.length; j++) {
                            option1 += "<option value='" + dataValue[j].column_type_id + "'>" + dataValue[j].type_name + "</option>";

                        }
                        $('#columnType' + (i - 1)).html('<option >Select</option>' + option1);

                    },
                    error: function (e) {
                        alert('Error:12212212112121 ' + e);
                    }
                });
                $('#totalcount').val(i);
                i++;
            });


            // Find and remove selected table rows
            $(".delete-row").click(function () {
                $("table tbody").find('input[name="record"]').each(function () {
                    if ($(this).is(":checked")) {
                        $(this).parents("tr").remove();
                    }
                });
            });
        });

        function inspectbox() {
            if (document.forms.addshow.morelocations.checked)
                document.forms.addshow.Location1.style.visibility = 'visible';
            else
                document.forms.addshow.Location1.style.visibility = 'hidden';
        }
        function count() {
            document.getElementById('colm').value = i;
            var table_name = document.getElementById("table_name").value;
            var result = true;
            if (table_name === null) {
                result = false;
                $("#message").html("<td colspan='5' bgcolor='coral'><b>Table Name is required...</b></td>");
            } else {
                result = true;
            }
            return result;
        }

        function checkColumnName(inputValue) {
            var formId = $("#formId").val();
//            alert(formId);
            if (formId !== null) {
                $.ajax({
                    url: "FormMappingController",
                    data: "task=checkColumnName&columnValue=" + inputValue + "&formId=" + formId,
                    type: 'POST',
                    dataType: 'json',
                    success: function (response) {
                        if (response.data2 === "column already exists") {
                            alert(response.data2);
                        }

                    },
                    error: function (e) {
                        alert('Error:12212212112121 ' + e);
                    }
                });
            } else {
                alert("Please Select form!");
            }
        }

    </script>


