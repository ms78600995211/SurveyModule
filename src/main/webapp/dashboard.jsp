<%--
    Document   : index.jsp
    Created on : 3 Jul, 2023, 1:32:09 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>        
<jsp:include page="layout/header.jsp" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-xB+3pAnegdE00rPrl4czPfgc7BDZU6iQUdeWbd+JzebEu7PRt9eFCFUh5TGdjNl0z8phUHTW0cRZg6Z3DqGnT4A==" crossorigin="anonymous" />

<style>
    .btn-cream {
        background-color:#aaa;
        color: black; 
    }
</style>

<!-- ============================================================== -->
<!-- Start right Content here -->
<!-- ============================================================== -->
<div class="main-content">
    <div class="page-content">
        <div class="container-fluid">

            <!-- start page title -->
            <div class="row">
                <div class="col-12">
                    <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                        <h4 class="mb-sm-0 font-size-18">Dashboard</h4>

                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="javascript: void(0);">Dashboards</a></li>
                                <li class="breadcrumb-item active">Dashboard</li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end page title -->

            <div class="row">
                <div class="col-md-4">
                    <div class="card" style="background-color:#545a6d; color: white;">
                        <a href="#" class="text-center">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1 text-center">
                                        <i class="fas fa-user fa-3x text-light"></i>
                                        <br>                                      
                                        <br>
                                        <h4 class="text-light fw-medium">Your Information</h4>
                                        <h4 class="text-light fw-medium">01</h4>
                                    </div>
                                </div>
                            </div>
                           <div class="card-footer text-center">
                                <a href="" class="btn btn-cream">More Info</a>
                            </div>
                        </a>
                    </div>
                </div>



                <div class="col-md-4">
                    <div class="card" style="background-color:#545a6d; color: white;">
                        <a href="#" class="text-center">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1 text-center">
                                        <i class="fas fa-user fa-3x text-light"></i>
                                        <br>                                      
                                        <br>
                                        <h4 class="text-light fw-medium">Total Clients</h4>
                                        <h4 class="text-light fw-medium">07</h4>
                                    </div>
                                </div>
                            </div>
                           <div class="card-footer text-center">
                                <a href="" class="btn btn-cream">More Info</a>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card" style="background-color:#545a6d; color: white;">
                        <a href="#" class="text-center">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1 text-center">
                                        <i class="fas fa-mobile  fa-3x text-light"></i>
                                        <br>                                      
                                        <br>
                                        <h4 class="text-light fw-medium">Total Devices</h4>
                                        <h4 class="text-light fw-medium">11</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer text-center">
                                <a href="" class="btn btn-cream">More Info</a>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card" style="background-color:#545a6d; color: white;">
                        <a href="#" class="text-center">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1 text-center">
                                        <i class="fas fa-project-diagram fa-3x text-light"></i>      
                                        <br>                                      
                                        <br>
                                        <h4 class="text-light fw-medium">Total Projects</h4>
                                        <h4 class="text-light fw-medium">05</h4>
                                    </div>
                                </div>
                            </div>
                           <div class="card-footer text-center">
                                <a href="" class="btn btn-cream">More Info</a>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card" style="background-color:#545a6d; color: white;">
                        <a href="#" class="text-center">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1 text-center">
                                        <i class="fas fa-cogs fa-3x text-light"></i> <!-- Configuration icon added here -->
                                        <br>                                      
                                        <br>
                                        <h4 class="text-light fw-medium">Project Configuration</h4>
                                        <h4 class="text-light fw-medium">05</h4>
                                    </div>
                                </div>
                            </div>
                           <div class="card-footer text-center">
                                <a href="" class="btn btn-cream">More Info</a>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card" style="background-color:#545a6d; color: white;">
                        <a href="#" class="text-center">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1 text-center">
                                        <i class="fas fa-code fa-3x text-light"></i> <!-- Code icon added here -->
                                        <br>                                      
                                        <br>
                                        <h4 class="text-light fw-medium">All CodeList</h4>
                                        <h4 class="text-light fw-medium">135</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer text-center">
                                <a href="" class="btn btn-cream">More Info</a>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card" style="background-color:#545a6d; color: white;">
                        <a href="#" class="text-center">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1 text-center">
                                        <i class="fas fa-project-diagram fa-3x text-light"></i>
                                        <i class="fas fa-code fa-3x text-light mx-2"></i>
                                        <i class="fas fa-link fa-3x text-light"></i>
                                        <br>                                      
                                        <br>
                                        <h4 class="text-light fw-medium">Project CodeList Mapping</h4>
                                        <h4 class="text-light fw-medium">135</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer text-center">
                                <a href="" class="btn btn-cream">More Info</a>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card" style="background-color:#545a6d; color: white;">
                        <a href="#" class="text-center">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1 text-center">
                                        <i class="fas fa-user fa-3x text-light"></i>
                                        <br>                                      
                                        <br>
                                        <h4 class="text-light fw-medium">Total Surveyors</h4>
                                        <h4 class="text-light fw-medium">07</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer text-center">
                                <a href="" class="btn btn-cream">More Info</a>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card" style="background-color:#545a6d; color: white;">
                        <a href="#" class="text-center">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div class="flex-grow-1 text-center">
                                        <i class="fas fa-clipboard-list fa-3x text-light"></i>
                                        <i class="fas fa-chart-bar fa-3x text-light mx-2"></i>                                        
                                        <br>                                      
                                        <br>
                                        <h4 class="text-light fw-medium">Survey Data</h4>
                                        <h4 class="text-light fw-medium">00</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer text-center">
                                <a href="" class="btn btn-cream">More Info</a>
                            </div>
                        </a>
                    </div>
                </div>




            </div>
        </div>
    </div>
</div>
<!-- End Page-content -->

<!-- Transaction Modal -->
<div class="modal fade transaction-detailModal" tabindex="-1" role="dialog" aria-labelledby="transaction-detailModalLabel" aria-hidden="true">
    <!-- Modal content -->
</div>

<jsp:include page="layout/footer.jsp" />
