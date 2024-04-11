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

      <div class="msgWrap">
         <div class="alert alert-success alert-dismissible fade show" role="alert">
            <i class="mdi mdi-check-all me-2"></i>
               Added Successfully
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>                
     </div>

      <div class="row">
         <div class="col-12">
            <div class="page-title-box d-sm-flex align-items-center justify-content-between">
               <h4 class="mb-sm-0 font-size-18">Data List</h4>
               <div class="page-title-right">
                  <ol class="breadcrumb m-0">
                     <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                     <li class="breadcrumb-item active">Data List</li>
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
                           <p class="text-muted mb-2">Search your result according to this fields.</p>
                           <div class="row">                
                              <div class="col-md-8">
                                 <form class="custom-validation" action="backend/employeeAdd.php" method="post" enctype="multipart/form-data">
                                    <div class="row">  
                                       <div class="col-md-3">
                                            <div class="mb-3">
                                                <select class="form-control" name="District">
                                                  <option selected disabled>--Select Source--</option>
                                                  <option value="#">Indiamart</option>
                                                  <option value="#">Google </option>
                                                  <option value="#">Website</option>
                                                  <option value="#">Phone</option>
                                                  <option value="#">Email</option>
                                                </select>
                                            </div>
                                       </div>
                                       <div class="col-md-3">
                                            <div class="mb-3">
                                                <select class="form-control" name="District">
                                                  <option selected disabled>--Select Source--</option>
                                                  <option value="#">Indiamart</option>
                                                  <option value="#">Google </option>
                                                  <option value="#">Website</option>
                                                  <option value="#">Phone</option>
                                                  <option value="#">Email</option>
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
                                       <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Add Enquiry</button>
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
                                             <th>Sender  Name</th>
                                             <th>Mobile No.</th>
                                             <th>Product</th>
                                             <th>Location</th>
                                             <th>Assigned</th>
                                             <th>Status</th>
                                          </tr>
                                       </thead>
                                       <tbody>                                          
                                          <tr>
                                             <td>1.</td>
                                             <td>
                                                <p class="mb-0">Mr. Kundan Pandey</p>
                                             </td>
                                             <td class="text-capitalize">
                                                <p class="mb-0">8700328890</p>
                                             </td>
                                             <td class="text-capitalize">
                                                <p class="mb-0">UHF Radio</p>
                                             </td>
                                             <td class="text-capitalize">
                                                <p class="mb-0">Hapur</p>
                                             </td>
                                             <td>Chandan</td>
                                             <td>
                                                <span class="badge badge-pill badge-soft-success font-size-11">Chargeback</span>
                                             </td>
                                          </tr>
                                          <tr>
                                             <td>2.</td>
                                             <td>
                                                <p class="mb-0">Mr. Kundan Pandey</p>
                                             </td>
                                             <td class="text-capitalize">
                                                <p class="mb-0">8700328890</p>
                                             </td>
                                             <td class="text-capitalize">
                                                <p class="mb-0">UHF Radio</p>
                                             </td>
                                             <td class="text-capitalize">
                                                <p class="mb-0">Hapur</p>
                                             </td>
                                             <td>Chandan</td>
                                             <td>
                                                <span class="badge badge-pill badge-soft-danger font-size-11">Chargeback</span>
                                             </td>
                                          </tr>
                                          <tr>
                                             <td>3.</td>
                                             <td>
                                                <p class="mb-0">Mr. Kundan Pandey</p>
                                             </td>
                                             <td class="text-capitalize">
                                                <p class="mb-0">8700328890</p>
                                             </td>
                                             <td class="text-capitalize">
                                                <p class="mb-0">UHF Radio</p>
                                             </td>
                                             <td class="text-capitalize">
                                                <p class="mb-0">Hapur</p>
                                             </td>
                                             <td>Chandan</td>
                                             <td>
                                                <span class="badge badge-pill badge-soft-warning font-size-11">Chargeback</span>
                                             </td>
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
               <!-- end col -->
            </div>
            <div style='clear:both'></div>
         </div>
      </div>
   </div>
   <!-- container-fluid -->
</div>
<!-- End Page-content -->










<!-- Edit Enquiry Model Start -->

<div class="modal fade bs-example-modal-lg-Edit-Enquery" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="myExtraLargeModalLabel">Enquiry Edit</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
             <div>
              <form class="custom-validation" action="backend/employeeAdd.php" method="post" enctype="multipart/form-data">
                  <div class="row">                      
                      <div class="col-md-3">
                          <div class="mb-3">
                              <label for="" class="form-label">Name:<span class="text-danger">*</span> </label>
                              <input type="text" class="form-control" name="Name"
                                  placeholder="Name" required>
                          </div>
                      </div>
                      <!-- <div class="col-md-3">
                          <div class="mb-3">
                              <label class="form-label">District:<span class="text-danger">*</span></label>
                              <select class="form-control" name="District">
                                <option selected disabled>---Select One---</option>
                                <option value="AL">Alabama</option>
                                <option value="WY">Wyoming</option>
                                <option value="AL">Alabama</option>
                                <option value="WY">Wyoming</option>
                              </select>
                          </div>
                      </div> -->
                      <div class="col-md-3">
                          <div class="mb-3">
                              <label for="" class="form-label">Mobile:<span class="text-danger">*</span></label>
                              <input type="text" class="form-control" name=""
                                  placeholder="Mobile" required>
                          </div>
                      </div>
                      <div class="col-md-3">
                          <div class="mb-3">
                              <label for="" class="form-label">Email:<span class="text-danger">*</span></label>
                              <input type="text" class="form-control" name=""
                                  placeholder="Mobile" required>
                          </div>
                      </div>
                      <div class="col-md-3">
                          <div class="mb-3">
                              <label for="" class="form-label">Enquiry Source:<span class="text-danger">*</span></label>
                              <input type="text" class="form-control" name=""
                                  placeholder="Enquiry Source" required>
                          </div>
                      </div>                      
                      <div class="col-md-6">
                          <div class="mb-3">
                              <label for="" class="form-label">Address:<span class="text-danger">*</span></label>
                              <textarea type="text" class="form-control" name=""
                                  placeholder="Address"></textarea>
                          </div>
                      </div>
                      <div class="col-md-6">
                          <div class="mb-3">
                              <label for="" class="form-label">Enquiry Message:<span class="text-danger">*</span></label>
                              <textarea class="form-control" placeholder="Enquiry Message"></textarea>
                          </div>
                      </div>
                      <div class="col-md-3">
                           <div class="mb-3">
                               <label for="" class="form-label">Marketing Vertical:<span class="text-danger">*</span></label>
                               <select name="marketing_vertical_id" id="marketing_vertical_id" class="form-control" required="required">
                                   <option>Select Vertical</option>                                   
                                       <option value="1">GNSS</option>                                   
                                       <option value="2">Agricultural</option>                                   
                                       <option value="3">Manufacturing</option>                                   
                               </select>
                           </div>
                       </div> 

                      <a href="#" class="show_hide mb-2" data-content="toggle-text">Add More... </a> 
                      <div class="contentHideShow">
                        <div>
                           <div class="row">
                              <div class="col-md-3">
                                   <div class="mb-3">
                                       <label class="form-label">Enquiry No</label>
                                       <input type="text" class="form-control" name="mobile_no" required
                                       placeholder="Enquiry No" />
                                   </div>
                              </div>
                              <div class="col-md-3">
                                   <div class="mb-3">
                                       <label class="form-label">Product Name</label>
                                       <input type="text" class="form-control" name=""
                                           placeholder="Product Name" required>
                                   </div>
                              </div>
                              <div class="col-md-3">
                                   <div class="mb-3">
                                       <label class="form-label">Sender Email</label>
                                       <input type="email" class="form-control" name="emergency_mobile_no" required                            
                                       placeholder="Sender Email" />                                              
                                   </div>
                              </div>
                              <div class="col-md-3">
                                   <div class="mb-3">
                                       <label class="form-label">Sender Company Name</label>
                                       <input type="text" class="form-control" name=""
                                           placeholder="Sender Company Name" required>
                                   </div>
                              </div>
                              <div class="col-md-3">
                                 <div class="mb-3">
                                    <label class="form-label">City</label>
                                    <select class="form-control" name="City">
                                      <option selected disabled>---Select One---</option>
                                      <option value="AL">Alabama</option>
                                      <option value="WY">Wyoming</option>
                                      <option value="AL">Alabama</option>
                                      <option value="WY">Wyoming</option>
                                      <option value="WY">Wyoming</option>
                                    </select>
                                 </div>
                              </div>
                              <div class="col-md-3">
                                 <div class="mb-3">
                                    <label class="form-label">State</label>
                                    <select class="form-control" name="State">
                                      <option selected disabled>---Select One---</option>
                                      <option value="AL">Alabama</option>
                                      <option value="WY">Wyoming</option>
                                      <option value="AL">Alabama</option>
                                      <option value="WY">Wyoming</option>
                                    </select>
                                 </div>
                              </div>
                           </div>
                        </div>
                      </div>
                      
                  </div>

                  <div class="modal-footer">
                      <button type="button" class="btn btn-secondary waves-effect" data-bs-dismiss="modal">Close</button>
                      <button class="btn btn-primary" type="submit" name="submitFormBtn">Submit form</button>
                  </div>
              </form>
             </div>
          </div>
      </div><!-- /.modal-content -->
  </div>
</div>

<!-- Edit Enquiry Model End -->





















<!-- ADD Enquiry Model Start -->

<div class="modal fade bs-example-modal-xl-Add-Enquiry" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="myExtraLargeModalLabel">Enquiry Add</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
             <div>
              <form class="custom-validation" action="backend/employeeAdd.php" method="post" enctype="multipart/form-data">
                  <div class="row">                      
                      <div class="col-md-3">
                          <div class="mb-3">
                              <label for="" class="form-label">Name:<span class="text-danger">*</span> </label>
                              <input type="text" class="form-control" name="Name"
                                  placeholder="Name" required>
                          </div>
                      </div>
                      <!-- <div class="col-md-3">
                          <div class="mb-3">
                              <label class="form-label">District:<span class="text-danger">*</span></label>
                              <select class="form-control" name="District">
                                <option selected disabled>---Select One---</option>
                                <option value="AL">Alabama</option>
                                <option value="WY">Wyoming</option>
                                <option value="AL">Alabama</option>
                                <option value="WY">Wyoming</option>
                              </select>
                          </div>
                      </div> -->
                      <div class="col-md-3">
                          <div class="mb-3">
                              <label for="" class="form-label">Mobile:<span class="text-danger">*</span></label>
                              <input type="text" class="form-control" name=""
                                  placeholder="Mobile" required>
                          </div>
                      </div>
                      <div class="col-md-3">
                          <div class="mb-3">
                              <label for="" class="form-label">Email:<span class="text-danger">*</span></label>
                              <input type="text" class="form-control" name=""
                                  placeholder="Mobile" required>
                          </div>
                      </div>
                      <div class="col-md-3">
                          <div class="mb-3">
                              <label for="" class="form-label">Enquiry Source:<span class="text-danger">*</span></label>
                              <input type="text" class="form-control" name=""
                                  placeholder="Enquiry Source" required>
                          </div>
                      </div>                      
                      <div class="col-md-6">
                          <div class="mb-3">
                              <label for="" class="form-label">Address:<span class="text-danger">*</span></label>
                              <textarea type="text" class="form-control" name=""
                                  placeholder="Address"></textarea>
                          </div>
                      </div>
                      <div class="col-md-6">
                          <div class="mb-3">
                              <label for="" class="form-label">Enquiry Message:<span class="text-danger">*</span></label>
                              <textarea class="form-control" placeholder="Enquiry Message"></textarea>
                          </div>
                      </div>

                      <a href="#" class="show_hide mb-2" data-content="toggle-text">Add More... </a> 
                      <div class="contentHideShow">
                        <div>
                           <div class="row">
                              <div class="col-md-3">
                                   <div class="mb-3">
                                       <label class="form-label">Enquiry No</label>
                                       <input type="text" class="form-control" name="mobile_no" required
                                       placeholder="Enquiry No" />
                                   </div>
                              </div>
                              <div class="col-md-3">
                                   <div class="mb-3">
                                       <label class="form-label">Product Name</label>
                                       <input type="text" class="form-control" name=""
                                           placeholder="Product Name" required>
                                   </div>
                              </div>
                              <div class="col-md-3">
                                   <div class="mb-3">
                                       <label class="form-label">Sender Email</label>
                                       <input type="email" class="form-control" name="emergency_mobile_no" required                            
                                       placeholder="Sender Email" />                                              
                                   </div>
                              </div>
                              <div class="col-md-3">
                                   <div class="mb-3">
                                       <label class="form-label">Sender Company Name</label>
                                       <input type="text" class="form-control" name=""
                                           placeholder="Sender Company Name" required>
                                   </div>
                              </div>
                              <div class="col-md-3">
                                 <div class="mb-3">
                                    <label class="form-label">City</label>
                                    <select class="form-control" name="City">
                                      <option selected disabled>---Select One---</option>
                                      <option value="AL">Alabama</option>
                                      <option value="WY">Wyoming</option>
                                      <option value="AL">Alabama</option>
                                      <option value="WY">Wyoming</option>
                                      <option value="WY">Wyoming</option>
                                    </select>
                                 </div>
                              </div>
                              <div class="col-md-3">
                                 <div class="mb-3">
                                    <label class="form-label">State</label>
                                    <select class="form-control" name="State">
                                      <option selected disabled>---Select One---</option>
                                      <option value="AL">Alabama</option>
                                      <option value="WY">Wyoming</option>
                                      <option value="AL">Alabama</option>
                                      <option value="WY">Wyoming</option>
                                    </select>
                                 </div>
                              </div>
                           </div>
                        </div>
                      </div>
                      
                  </div>

                  <div class="modal-footer">
                      <button type="button" class="btn btn-secondary waves-effect" data-bs-dismiss="modal">Close</button>
                      <button class="btn btn-primary" type="submit" name="submitFormBtn">Submit form</button>
                  </div>
              </form>
             </div>
          </div>
      </div><!-- /.modal-content -->
  </div>
</div>

<!-- ADD Enquiry Model End -->






<!-- Modal -->
 <div class="modal fade" id="composemodal" tabindex="-1" role="dialog" aria-labelledby="composemodalTitle" aria-hidden="true">
     <div class="modal-dialog modal-dialog-centered" role="document">
         <div class="modal-content">

            <form method="post" action="#" enctype="multipart/form-data">
               <div class="modal-header">
                    <h5 class="modal-title" id="composemodalTitle">New Message</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body">
                    <div>
                        <div class="mb-3">
                            <input type="email" class="form-control" placeholder="To">
                        </div>

                        <div class="mb-3">
                            <input type="text" class="form-control" placeholder="Subject">
                        </div>
                        <div class="mb-3">
                           <select class="form-control" name="District" multiple="multiple">
                             <option selected disabled>---Select Brochure & Catalog---</option>
                             <option value="#">Navik 200</option>
                             <option value="#">Navik 50</option>
                             <option value="#">Navik 300</option>
                             <option value="#">Radio</option>
                             <option value="#">Ntrip</option>
                           </select>
                        </div>
                        <!-- <div class="mb-3">
                            <input type="file" class="form-control" placeholder="Subject" multiple>
                        </div> -->
                        <div class="mb-3">
                           <textarea id="email-editor" class="form-control" rows="6"  name="area"></textarea>
                        </div>

                    </div>
               </div>
               <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Send <i class="fab fa-telegram-plane ms-1"></i></button>
               </div>
            </form>

         </div>
     </div>
 </div>
 <!-- end modal -->







<!-- Reminder Modal -->
 <div class="modal fade" id="setReminder" tabindex="-1" role="dialog" aria-labelledby="composemodalTitle" aria-hidden="true">
     <div class="modal-dialog modal-dialog-centered" role="document">
         <div class="modal-content">            
               <div class="modal-header">
                    <h5 class="modal-title" id="composemodalTitle">Set Reminder for</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body">
                  <form method="post" action="#">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                           <label>Date</label>
                           <input type="date" class="form-control" placeholder="To">
                        </div>
                        <div class="col-md-6 mb-3">
                           <label>Time</label>
                           <input type="time" class="form-control" placeholder="To">
                        </div>                       
                        <div class="col-md-12 mb-3">
                           <button type="button" class="btn btn-primary w-100">Set Reminder <i class="fab fa-telegram-plane ms-1"></i></button>
                        </div>
                    </div>
                  </form>

                  <!-- <hr style="border:1px solid #999;margin: 0;"> -->
                  <div class="row mt-2">
                     <div class="col-md-12">
                        <p class="font-size-12 mb-0 py-1">Recent Reminders </p>
                     </div>
                  </div>
                  <hr style="border:0.15px solid #ccc;margin: 0;">


                  <div>
                     <div class="mt-2">
                        <p class="mb-0">Follow-Up with Amit Day Again</p>
                        <p class="mb-2"><span style="font-size:10px;color: #19c3ad;">5-Aug-2023</span>  &nbsp&nbsp&nbsp&nbsp <span style="font-size:10px;color: #19c3ad;">11:30 AM</span></p>
                     </div>

                     <hr style="border:0.05px solid #ebe9e9;margin: 0;">

                     <div class="mt-2">
                        <p class="mb-0">Follow-Up with Amit Day Again</p>
                        <p class="mb-2"><span style="font-size:10px;color: #19c3ad;">5-Aug-2023</span>  &nbsp&nbsp&nbsp&nbsp <span style="font-size:10px;color: #19c3ad;">11:30 AM</span></p>
                     </div>
                  </div>
                  


               </div>
               <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>                    
               </div> 
         </div>
     </div>
 </div>
<!-- end modal -->




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



<!--<script>
$(document).ready(function () {
    $(".contentHideShow").hide();
    $(".show_hide").on("click", function () {
        var txt = $(".contentHideShow").is(':visible') ? 'Add More...' : 'Add Less';
        $(".show_hide").text(txt);
        $(this).next('.contentHideShow').slideToggle(200);
    });
});
</script>

<script>
  jQuery(document).ready(function() {
    jQuery('#showCheckoutAll').change(function() {
        if ($(this).prop('checked')) {
          // let myArray = (".checkBoxList").val();
          $(".checkBoxList").attr("checked","checked",);
        }
        else {
          $(".checkBoxList").removeAttr("checked","checked",);
        }
    });
});
</script>

<script>
   $("#assignedPerson").hide();
   function showAssignedPerson(){
      $("#assignedPerson").show();
   }  
   function hideAssignedPerson(){
      $("#assignedPerson").hide();
   }    
</script>-->


<script>
   $('#datatable').DataTable({
    "ordering": false
});
</script>