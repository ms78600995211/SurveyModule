<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>SURVEY MODULE</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="assets/images/product/playLogo.png">
        <!-- <link href="assets/libs/select2/css/select2.min.css" rel="stylesheet" type="text/css" /> -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <link href="assets/libs/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet" type="text/css" />
        <link href="assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/libs/datatables.net-buttons-bs4/css/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <link href="assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />
        <link href="assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="assets/css/myStyle.css">
    </head>


    <body data-sidebar="dark" data-layout-mode="light">
        <!--<body data-layout="horizontal" data-topbar="dark"> -->
        <div id="layout-wrapper">            
            <header id="page-topbar">
                <div class="navbar-header">
                    <div class="d-flex">
                        <div class="navbar-brand-box">
                            <a href="DashboardController" class="logo logo-dark">
                                <span class="logo-sm">
                                    <img src="assets/images/product/full_logo_black.png" alt="" height="22">
                                </span>
                                <span class="logo-lg">
                                    <img src="assets/images/product/full_logo_black.png" alt="" height="17">
                                </span>
                            </a>
                            <a href="DashboardController" class="logo logo-light">
                                <span class="logo-sm">
                                    <img src="assets/images/product/full_logo_white.png" alt="" height="28">
                                </span>
                                <span class="logo-lg">
                                    <img src="assets/images/product/full_logo_white.png" alt="" height="35">
                                </span>
                            </a>
                        </div>
                        <button type="button" class="btn btn-sm px-3 font-size-16 header-item waves-effect" id="vertical-menu-btn"><i class="fa fa-fw fa-bars"></i>
                        </button>
                        <form class="app-search d-none d-lg-block">
                            <div class="position-relative">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="bx bx-search-alt"></span>
                            </div>
                        </form>
                    </div>
                    <div class="d-flex">
                        <div class="dropdown d-inline-block d-lg-none ms-2">
                            <button type="button" class="btn header-item noti-icon waves-effect" id="page-header-search-dropdown" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="mdi mdi-magnify"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-end p-0" aria-labelledby="page-header-search-dropdown">
                                <form class="p-3">
                                    <div class="form-group m-0">
                                        <div class="input-group">
                                            <input type="text" class="form-control" placeholder="Search ..." aria-label="Recipient's username">
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="submit"><i class="mdi mdi-magnify"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="dropdown d-none d-lg-inline-block ms-1">
                            <button type="button" class="btn header-item noti-icon waves-effect" data-bs-toggle="fullscreen">
                                <i class="bx bx-fullscreen"></i>
                            </button>
                        </div>
                        <div class="dropdown d-inline-block">
                            <button type="button" class="btn header-item noti-icon waves-effect" id="page-header-notifications-dropdown" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="bx bx-bell bx-tada"></i>
                                <span class="badge bg-danger rounded-pill">3</span>
                            </button>
                            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-end p-0" aria-labelledby="page-header-notifications-dropdown">
                                <div class="p-3">
                                    <div class="row align-items-center">
                                        <div class="col">
                                            <h6 class="m-0" key="t-notifications"> Notifications </h6>
                                        </div>
                                        <div class="col-auto">
                                            <a href="#!" class="small" key="t-view-all"> View All</a>
                                        </div>
                                    </div>
                                </div>
                                <div data-simplebar="init" style="max-height: 230px;"><div class="simplebar-wrapper" style="margin: 0px;"><div class="simplebar-height-auto-observer-wrapper"><div class="simplebar-height-auto-observer"></div></div><div class="simplebar-mask"><div class="simplebar-offset" style="right: 0px; bottom: 0px;"><div class="simplebar-content-wrapper" style="height: auto; overflow: hidden;"><div class="simplebar-content" style="padding: 0px;">
                                                        <a href="javascript: void(0);" class="text-reset notification-item">
                                                            <div class="d-flex">
                                                                <div class="avatar-xs me-3">
                                                                    <span class="avatar-title bg-primary rounded-circle font-size-16">
                                                                        <i class="bx bx-cart"></i>
                                                                    </span>
                                                                </div>
                                                                <div class="flex-grow-1">
                                                                    <h6 class="mb-1" key="t-your-order">Your order is placed</h6>
                                                                    <div class="font-size-12 text-muted">
                                                                        <p class="mb-1" key="t-grammer">If several languages coalesce the grammar</p>
                                                                        <p class="mb-0"><i class="mdi mdi-clock-outline"></i> <span key="t-min-ago">3 min ago</span></p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </a>
                                                        <a href="javascript: void(0);" class="text-reset notification-item">
                                                            <div class="d-flex">
                                                                <img src="assets/images/users/avatar-3.jpg" class="me-3 rounded-circle avatar-xs" alt="user-pic">
                                                                <div class="flex-grow-1">
                                                                    <h6 class="mb-1">James Lemire</h6>
                                                                    <div class="font-size-12 text-muted">
                                                                        <p class="mb-1" key="t-simplified">It will seem like simplified English.</p>
                                                                        <p class="mb-0"><i class="mdi mdi-clock-outline"></i> <span key="t-hours-ago">1 hours ago</span></p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </a>
                                                        <a href="javascript: void(0);" class="text-reset notification-item">
                                                            <div class="d-flex">
                                                                <div class="avatar-xs me-3">
                                                                    <span class="avatar-title bg-success rounded-circle font-size-16">
                                                                        <i class="bx bx-badge-check"></i>
                                                                    </span>
                                                                </div>
                                                                <div class="flex-grow-1">
                                                                    <h6 class="mb-1" key="t-shipped">Your item is shipped</h6>
                                                                    <div class="font-size-12 text-muted">
                                                                        <p class="mb-1" key="t-grammer">If several languages coalesce the grammar</p>
                                                                        <p class="mb-0"><i class="mdi mdi-clock-outline"></i> <span key="t-min-ago">3 min ago</span></p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </a>

                                                        <a href="javascript: void(0);" class="text-reset notification-item">
                                                            <div class="d-flex">
                                                                <img src="assets/images/users/avatar-4.jpg" class="me-3 rounded-circle avatar-xs" alt="user-pic">
                                                                <div class="flex-grow-1">
                                                                    <h6 class="mb-1">Salena Layfield</h6>
                                                                    <div class="font-size-12 text-muted">
                                                                        <p class="mb-1" key="t-occidental">As a skeptical Cambridge friend of mine occidental.</p>
                                                                        <p class="mb-0"><i class="mdi mdi-clock-outline"></i> <span key="t-hours-ago">1 hours ago</span></p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </div></div></div></div><div class="simplebar-placeholder" style="width: 0px; height: 0px;"></div></div><div class="simplebar-track simplebar-horizontal" style="visibility: hidden;"><div class="simplebar-scrollbar" style="transform: translate3d(0px, 0px, 0px); display: none;"></div></div><div class="simplebar-track simplebar-vertical" style="visibility: hidden;"><div class="simplebar-scrollbar" style="transform: translate3d(0px, 0px, 0px); display: none;"></div></div></div>
                                <div class="p-2 border-top d-grid">
                                    <a class="btn btn-sm btn-link font-size-14 text-center" href="javascript:void(0)">
                                        <i class="mdi mdi-arrow-right-circle me-1"></i> <span key="t-view-more">View More..</span> 
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="dropdown d-inline-block">
                            <button type="button" class="btn header-item waves-effect" id="page-header-user-dropdown" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img class="rounded-circle header-profile-user" src="assets/images/no_image.jpg" alt="Header Avatar">
                                <span class="d-none d-xl-inline-block ms-1" key="t-henry">${name}</span>
                                <i class="mdi mdi-chevron-down d-none d-xl-inline-block"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-end">
                                <!-- item-->
                                <a class="dropdown-item" href="#"><i class="bx bx-user font-size-16 align-middle me-1"></i> <span key="t-profile">Profile</span></a>
                                <a class="dropdown-item" href="#"><i class="bx bx-wallet font-size-16 align-middle me-1"></i> <span key="t-my-wallet">My Wallet</span></a>
                                <a class="dropdown-item d-block" href="#"><span class="badge bg-success float-end">11</span><i class="bx bx-wrench font-size-16 align-middle me-1"></i> <span key="t-settings">Settings</span></a>
                                <a class="dropdown-item" href="#"><i class="bx bx-lock-open font-size-16 align-middle me-1"></i> <span key="t-lock-screen">Lock screen</span></a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item text-danger" href="LoginController?task=logout"><i class="bx bx-power-off font-size-16 align-middle me-1 text-danger"></i> <span key="t-logout">Logout</span></a>
                            </div>
                        </div>

                        <!-- <div class="dropdown d-inline-block">
                            <button type="button" class="btn header-item noti-icon right-bar-toggle waves-effect">
                                <i class="bx bx-cog bx-spin"></i>
                            </button>
                        </div> -->

                    </div>
                </div>
            </header>



            <!-- ========== Left Sidebar Start ========== -->
            <div class="vertical-menu">
                <div data-simplebar class="h-100">
                    <div id="sidebar-menu">
                        <ul class="metismenu list-unstyled" id="side-menu">
                            <li class="menu-title" key="t-menu">Menu</li>

                            <li>
                                <a href="DashboardController">
                                    <i class="fa-solid fa-gauge-high"></i>
                                    <span key="t-dashboards">Dashboards</span>
                                </a>
                            </li>
                            <li>
                                <a href="AboutController">
                                    <i class="fa-regular fa-address-book"></i>
                                    <span key="t-dashboards">About</span>
                                </a>
                            </li> 
                            <li>
                                <a target="_blank" href="http://120.138.10.146:8080/OrganisationModule/DashboardController?project_name=SurveyModule&key_person_id=${key_person_id}">
                                    <i class="fa-regular fa-address-book"></i>
                                    <span key="t-dashboards">Organisation key person</span>
                                </a>
                            </li> 

                            <li>
                                <a href="javascript: void(0);" class="has-arrow waves-effect">
                                    <i class="bx bx-task"></i>
                                    <span key="t-tasks">Basic Data Entry</span>
                                </a> 
                                <ul class="sub-menu" aria-expanded="false">
                                    <!--                                    <li><a href="StatusTypeController" key="t-task-list">Status Type</a></li>-->
                                    <li><a href="ProjectStatusController" key="t-kanban-board">Project Status</a></li>

                                    <li><a href="MaskAngleController" key="t-kanban-board">Mask Angle</a></li>
                                    <li><a href="ManualBaseController" key="t-kanban-board">Manual Base Params</a></li>
                                    <li><a href="HemisphereController" key="t-kanban-board">Hemisphere</a></li>
                                    <li><a href="AngleUnitController" key="t-kanban-board">Angle Unit</a></li>
                                    <li><a href="ProjectionTypeController" key="t-kanban-board">Projection Type</a></li>
                                    <li><a href="ProjectionParametersController" key="t-kanban-board">Projection Parameter</a></li>
                                    <li><a href="ContinentsController" key="t-kanban-board">Continents</a></li>
                                    <li><a href="CountriesController" key="t-kanban-board">Countries</a></li>
                                    <li><a href="DatumTypeController" key="t-kanban-board">Datum Type</a></li>
                                    <li><a href="DatumnController" key="t-kanban-board">Datum</a></li>
                                    <li><a href="ConstellationController" key="t-kanban-board">Constellation</a></li>
                                    <li><a href="ElevationTypeController" key="t-kanban-board">Elevation Type</a></li>
                                    <li><a href="ZoneDataController" key="t-kanban-board">Zone Data</a></li>
                                    <li><a href="DistanceUnitController" key="t-kanban-board">Distance Unit</a></li>
                                    <li><a href="StaticParamsController" key="t-kanban-board">Static Params</a></li>   
                                    <li><a href="PpkParamsController" key="t-kanban-board">Ppk Params</a></li>
                                    <li><a href="DynamicModeDataController" key="t-kanban-board">Dynamic Mode Data</a></li>
                                    <li><a href="NtripBaseRegistrationController" key="t-kanban-board">Base Registration</a></li>
                                    <li><a href="NtripRoverRegistrationController" key="t-kanban-board">Rover Registration</a></li>  
                                    <li><a href="SiteCalibrationController" key="t-kanban-board">Site Calibration</a></li> 
                                    <li><a href="RadioExternalParamsController" key="t-kanban-board">Radio External Params</a></li> 
                                    <li><a href="RadioInternalParamsController" key="t-kanban-board">Radio Internal Params</a></li>  
                                    <li><a href="WifiParamsController" key="t-kanban-board">Wifi Parameters</a></li>  
                                    <li><a href="PDAParamsController" key="t-kanban-board">PDA Parameters</a></li>  
                                    <li><a href="Via4GParamsController" key="t-kanban-board">Via4G Parameters</a></li>  


                                </ul>
                            </li>



                            <li>
                                <a href="javascript: void(0);" class="has-arrow waves-effect">
                                    <i class="bx bx-task"></i>
                                    <span key="t-tasks">Project</span>
                                </a>
                                <ul class="sub-menu" aria-expanded="false">
                                    <li><a href="ClientController" key="t-task-list">Client</a></li>
                                    <li><a href="CodeListIconMappingController" key="t-task-list">Codelist Icon Map</a></li>
                                    <li><a href="ProjectCodeListMappingController" key="t-task-list">Project CodeList Map</a></li>
                                    <li><a href="ClientBaseRoverRegistrationMappingController" key="t-task-list">Client Base Rover Registration Map</a></li>
                                    <li><a href="ProjectClientDeviceMappingController" key="t-task-list">Project Client Device Map</a></li>   
                                    <li><a href="ProjectDetailsController" key="t-task-list">Project Details</a></li>   
                                    <li><a href="ProjectFolderController" key="t-kanban-board">Project Folder</a></li>
                                    <li><a href="ProjectFileController" key="t-kanban-board">Project File</a></li>


                                    <!--<li><a href="tasks-create.html" key="t-create-task">Create Task</a></li>--> 
                                </ul>
                            </li>

                            <li>
                                <a href="javascript: void(0);" class="has-arrow waves-effect">
                                    <i class="bx bx-task"></i>
                                    <span key="t-tasks">Form Data</span>
                                </a>
                                <ul class="sub-menu" aria-expanded="false">
                                    <li><a href="FormController" key="t-task-list">Form</a></li>
                                    <!--<li><a href="SelectionController" key="t-task-list">Selection</a></li>-->
                                    <li><a href="ColumnTypeController" key="t-task-list">Column Type</a></li>
                                    <li><a href="ColumnSubTypeController" key="t-task-list">Column SubType</a></li>
                                    <li><a href="FileTypeController" key="t-task-list">File Type</a></li>
                                    <li><a href="ShowDataController" key="t-task-list">Show Data</a></li>
                                    <li><a href="FormMappingController" key="t-task-list">Form Mapping</a></li>
                                    <li><a href="SelectionValueMappingController" key="t-task-list">Selection Value Mapping</a></li>
                                    <li><a href="DriveController" key="t-task-list">Drive</a></li>

                                </ul>
                            </li>
                            <li>
                                <a href="DynamicFormController">
                                    <i class="fa-regular fa-address-book"></i>
                                    <span key="t-dashboards">Dynamic Form</span>
                                </a>
                            </li> 
                            <li>
                                <a href="javascript: void(0);" class="has-arrow waves-effect">
                                    <i class="bx bx-task"></i>
                                    <span key="t-tasks">Survey Data</span>
                                </a>
                                <ul class="sub-menu" aria-expanded="false">
                                    <li><a href="SurveyerProjectMappingController" key="t-task-list">SurveyorProjectClientDevice Mapping</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="javascript: void(0);" class="has-arrow waves-effect">
                                    <i class="bx bx-task"></i>
                                    <span key="t-tasks">Configuration</span>
                                </a>
                                <ul class="sub-menu" aria-expanded="false">

                                    <li><a href="SurveyConfigurationController" key="t-task-list">Survey Configuration</a></li>
                                    <li><a href="DynamicConfigMappingController" key="t-task-list">Dynamic Config Mapping</a></li>  
                                    <li><a href="MiscellaneousConfigurationController" key="t-task-list">Miscellaneous Configuration</a></li>  
                                    <li><a href="SatelliteConfigurationController" key="t-task-list">Satellite Configuration</a></li> 
                                    <li><a href="SatelliteConfigurationConstellationMappingController" key="t-task-list">Satellite Configuration Constellation Mapping</a></li>  
                                    <li><a href="TypeOfCommunicationController" key="t-kanban-board">Type Of Communication</a></li>  
                                    <li><a href="CommunicationTypeMappingController" key="t-task-list">Communication Type Mapping</a></li>  
                                    <li><a href="DeviceConfighierarchyController" key="t-task-list">Device Confighierarchy</a></li>  
                                    <li><a href="DeviceConfigurationController" key="t-task-list">Device Configuration</a></li>  
                                    <li><a href="ProjectConfigurationMappingController" key="t-task-list">Project Configuration Mapping</a></li>  
                                    <li><a href="ProjectConfigurationMainMappingController" key="t-task-list">Project Configuration Main Mapping</a></li>  
                                </ul>

                            </li>

                        </ul>
                    </div>
                    <!-- Sidebar -->
                </div>
            </div>
            <!-- Left Sidebar End -->





