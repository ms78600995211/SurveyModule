<%-- 
    Document   : dataAdd.jsp
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
                     <div class="card-body pt-4">
                        <div class="mt-1">
                           <!-- <div class="d-flex flex-wrap">
                              <h5 class="font-size-16 me-3">Recent Leave</h5>
                              <div class="ms-auto">
                                 <a href="javascript: void(0);" class="fw-medium text-reset">View All</a>
                              </div>
                           </div> -->
                           <div>
                              <form class="custom-validation" action="backend/employeeAdd-temp.php" method="post" enctype="multipart/form-data">
                                  <div class="row">
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Profile Picture</label>
                                              <input type="file" class="form-control" name="profile_img" required>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label for="" class="form-label">Employee ID</label>
                                              <input type="text" class="form-control" name="emp_id"
                                                  placeholder="Employee ID" required>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Title</label>
                                              <select class="form-select" name="title" required>
                                                  <option selected disabled>Choose...</option>
                                                  <option value="Mr.">Mr</option>
                                                  <option value="Mrs.">Mrs</option>
                                                  <option value="Miss.">Miss</option>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Full Name</label>
                                              <input type="text" class="form-control" name="full_name"
                                                  placeholder="Full Name" required>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Mobile No</label>
                                              <input type="text" class="form-control" name="mobile_no" required
                                              data-parsley-length="[10,10]" data-parsley-type="digits"
                                              placeholder="Mobile No" />
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Emergency Mobile No</label>
                                              <input type="text" class="form-control" name="emergency_mobile_no" required
                                              data-parsley-length="[10,10]" data-parsley-type="digits"
                                              placeholder="Emergency Mobile No" />                                              
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Email</label>
                                              <input type="email" class="form-control" name="email"
                                                  placeholder="Email" required>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Password</label>
                                              <input type="text" class="form-control" name="password"
                                                  placeholder="Password" required data-parsley-min="6">
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Employee Type</label>
                                              <select class="form-select" name="employee_type" required>
                                                  <option selected disabled value="">Choose...</option>
                                                  <option>One</option>
                                                  <option>Two</option>
                                                  <option>Three</option>
                                                  <option>Four</option>
                                              </select>  
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Blood Group</label>
                                              <input type="text" class="form-control" name="blood_group"
                                                  placeholder="Blood Group" required>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Address</label>
                                              <input type="text" class="form-control" name="address"
                                                  placeholder="Address" required>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Designation</label>
                                              <select class="form-select" name="designation" required>
                                                  <option selected disabled value="">Choose...</option>
                                                  <option>One</option>
                                                  <option>Two</option>
                                                  <option>Three</option>
                                                  <option>Four</option>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">DOJ</label>
                                              <input type="date" class="form-control" name="doj"
                                                  placeholder="DOJ" required>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">DOB</label>
                                              <input type="date" class="form-control" name="dob"
                                                  placeholder="DOB" required>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Marital Status</label>
                                              <select class="form-select" name="marital_status" required>
                                                  <option selected disabled>Choose...</option>
                                                  <option value="married">Married</option>
                                                  <option value="unmarried">Unmarried</option>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Gender</label>
                                              <select class="form-select" name="gender" required>
                                                  <option selected disabled>Choose...</option>
                                                  <option value="male">Male</option>
                                                  <option value="female">Female</option>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Religion</label>
                                              <select class="form-select" name="religion" required>
                                                  <option selected disabled>Choose...</option>
                                                  <option>One</option>
                                                  <option>Two</option>
                                                  <option>Three</option>
                                                  <option>Four</option>                          
                                              </select>
                                          </div>
                                      </div>                    
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Is Active</label>
                                              <select class="form-select" name="is_active" required>
                                                  <option selected disabled>Choose...</option>
                                                  <option value="active">Active</option>
                                                  <option value="deactive">Deactive</option>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="col-md-3">
                                          <div class="mb-3">
                                              <label class="form-label">Work Type</label>
                                              <select class="form-select" name="work_type" required>
                                                  <option selected disabled>Choose...</option>
                                                  <option>One</option>
                                                  <option>Two</option>
                                                  <option>Three</option>
                                                  <option>Four</option>
                                              </select>
                                          </div>
                                      </div>
                                  </div>

                                  <!-- <div class="modal-footer"> -->                                     
                                      <button class="btn btn-primary" type="submit" name="submitFormBtn">Submit form</button>
                                  <!-- </div> -->
                              </form>
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
