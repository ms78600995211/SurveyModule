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
        width: 300px;
        height: 200px;
        overflow: auto;
        border: 1px solid #ccc;
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
                                                        <form class="custom-validation" action="SelectionValueMappingController" method="post">
                                                            <table id="" class="table table-bordered dt-responsive  nowrap w-100">
                                                                <input type="hidden" name="totalcount" id="totalcount" value="">

                                                                <thead>
                                                                    <tr>
                                                                <input type="button" class="delete-row btn btn-danger" value="Delete Column">
                                                                <input  type="submit" value="Submit" class="btn btn-success" name="submitFormBtn">
                                                                </tr>
                                                                <input type="hidden" name="colm" value="" id="colm">

                                                                <br> <br>
                                                                <tr>

                                                                    <th>Form Name</th>
                                                                    <th>Column Name</th>
                                                                    <th>Selection Values</th>
                                                                    <th>Remark</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>                                                    
                                                                    <tr>
                                                                        <td> 
                                                                            <select class="form-control" name="Form0" id="formId" onchange="getColumnName(this.value, this.id)" required>
                                                                                <option value="" selected disabled>Select</option>
                                                                                <c:forEach var="form" items="${requestScope['FormData']}"
                                                                                           varStatus="loopCounter">
                                                                                    <option id ="${form.form_name}" value="${form.form_id}" >${form.form_name}</option>
                                                                                </c:forEach>
                                                                            </select> 
                                                                        </td>

                                                                        <td>
                                                                            <select class="form-control" name="columnType" id="columnType" onchange="AddSelectionValues()" required>
                                                                                <option value="" selected disabled>Select</option>
                                                                                <c:forEach var="column" items="${requestScope['ColumnType']}"
                                                                                           varStatus="loopCounter">
                                                                                    <option id ="${column.type_name}" value="${column.column_type_id}" >${column.type_name}</option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </td> 

                                                                        <td style="width: 100px;">
                                                                            <div class="scrollable" id="selectionDiv">
                                                                                <input  type="button" class="add-selection btn btn-primary" name="task" value="Add" id="selectionbutton" style="display: none">
                                                                                <!--<input  type="checkbox" name="record" id="removecheck" style="display: none">-->
                                                                                <input class='form-control' type="text" name="selectionValues0" id="selection_row" style="display: none" placeholder='selectionValues..' required>
                                                                            </div>
                                                                        </td>

                                                                        <td><input class='form-control' type="text" name="Remark"></td>
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

        function getColumnName(value, id) {
            var count = id.substring(10, 11);
            $.ajax({
                url: "SelectionValueMappingController",
                data: "task=getColumnNameOnlyYesSelection&formTypeId=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
//                debugger;
                    var dataValue = response.data;
//                    alert(dataValue);
                    $('#columnType').empty();
                    var option1;
                    for (var i = 0; i < dataValue.length; i++) {
                        var myBean = dataValue[i];
                        option1 += "<option value='" + dataValue[i].form_mapping_id + "'>" + dataValue[i].display_name + "</option>";

                        // Update your HTML with values from myBean
                    }
                    $('#columnType').html('<option >Select</option>' + option1);

                },
                error: function (e) {
                    alert('Error:12212212112121 ' + e);
                }
            });
        }

        function AddSelectionValues() {
            $('#selection_row').show();
            $('#selectionbutton').show();
            $('#removecheck').show();

        }
//
//        function addselectioninput() {
//            alert("Hii");
//        }
    </script>

    <script type="text/javascript">

        var i = 1;

        $(document).ready(function () {
            $(".add-selection").click(function () {
//                alert("hiiiii")
//                var markup = "<input type='checkbox' name='record" + i + "'>"
//                        + "<input class='form-control' type='text' name='selectionValues" + i + "' placeholder='selectionValues..' required>";
////                        + "<td><input type='button' class='add-selection btn btn-primary' name='task' value='Add' id='selectionbutton'></td>";

                var markup = "<input class='form-control' type='text' name='selectionValues" + i + "' placeholder='selectionValues..' required>";
//                        + "<td><input type='button' class='add-selection btn btn-primary' name='task' value='Add' id='selectionbutton'></td>";

                $("#selectionDiv").append(markup);
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

//        function inspectbox() {
//            if (document.forms.addshow.morelocations.checked)
//                document.forms.addshow.Location1.style.visibility = 'visible';
//            else
//                document.forms.addshow.Location1.style.visibility = 'hidden';
//        }
//        function count() {
//            document.getElementById('colm').value = i;
//            var table_name = document.getElementById("table_name").value;
//            var result = true;
//            if (table_name === null) {
//                result = false;
//                $("#message").html("<td colspan='5' bgcolor='coral'><b>Table Name is required...</b></td>");
//            } else {
//                result = true;
//            }
//            return result;
//        }

    </script>
