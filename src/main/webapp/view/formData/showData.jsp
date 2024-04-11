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
                        <h4 class="mb-sm-0 font-size-18">Show Data</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                                <li class="breadcrumb-item active">Show Data</li>
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
                                        <p class="text-muted mb-2">Form Name</p        >
                                        <div class="row">                
                                            <div class="col-md-8">
                                                <form method="post" action="ShowDataController">
                                                    <div class="row">  
                                                        <div class="col-md-3">
                                                            <div class="mb-3">
                                                                <select  class="form-control"  id="dropdown" name="form_id">
                                                                    <c:forEach var="option" items="${allforms}">
                                                                        <option value="${option.form_id}" ${option.form_id eq requestScope.form_id ? 'selected' : ''}>${option.form_name}</option>
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
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Add Data</button>
                                                    </div>      
                                                </div>                        
                                            </div>
                                        </div>                            
                                    </div>
                                </div>




                                <div class="card-body pt-0">
                                    <div class="mt-1">

                                        <div class="row mt-0">
                                            <div class="col-12">
                                                <div class="table-responsive">
                                                    <table id="datatable" class="table align-middle table-nowrap table-hover mb-0">

                                                        <thead>
                                                            <tr>
                                                                <th>Sr.</th>
                                                                    <c:forEach var="key" items="${data.keySet()}">
                                                                    <th>  <p class="mb-0">${key}</p></th>
                                                                    </c:forEach>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="i" begin="0" end="${maxValue}"   varStatus="loopCounter">
                                                                <tr>
                                                                    <td>${loopCounter.count}</td>
                                                                    <c:forEach var="key" items="${data.keySet()}">
                                                                        <td>
                                                                            <c:set var="fileExtension" value="${data[key][i].substring(data[key][i].lastIndexOf('.') + 1)}" />  
                                                                            <c:choose>
                                                                                <c:when test="${fileExtension == 'pdf' || fileExtension == 'mp4' || fileExtension == 'jpg' || fileExtension == 'png' || fileExtension == 'jpeg' || fileExtension == 'mp3' || fileExtension == 'xlsx' || fileExtension == 'docx' || fileExtension == 'xls'}">
                                                                                    <span>${data[key][i]}</span>
                                                                                    <button type="button" class="btn btn-info w-15" onclick="downloadFile('${data[key][i]}')">View</button>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    ${data[key][i]}
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </td>                                                               
                                                                    </c:forEach>
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
                    <h5 class="modal-title" id="myExtraLargeModalLabel">Add Data</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <form class="custom-validation" action="ShowDataController" method="post"  enctype="multipart/form-data">
                            <div class="row">  
                                <div class="col-md-3">
                                    <div class="mb-3">
                                        <label class="form-label">Form Type<span class="text-danger">*</span></label>

                                        <select class="form-control"   id="form_id"  name="form_id"  onchange="getColumnName(this.value, this.id)"  >
                                            <option value="" selected disabled>---Select One---</option>
                                            <c:forEach var="form" items="${requestScope['allforms']}"
                                                       varStatus="loopCounter">
                                                <option id ="${form.form_id}" value="${form.form_id}" >${form.form_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>



                                <div class="col-md-3">
                                    <div class="mb-3" id ="mydiv">
                                        <!-- comment <c:forEach var="fieldName" items="${ColumnName}">
                                           <label for="${fieldName.column_name}">${fieldName.column_name}:</label>
                                           <input type="text" class="form-control" id="${fieldName.column_name}" name="${fieldName.column_name}"><br>
                                        </c:forEach> -->
                                    </div>
                                </div>
                                <!--                                <div class="col-md-3">
                                                                    <div class="mb-3">
                                                                        <label for="" class="form-label">Remark<span class="text-danger"></span> </label>
                                                                        <input type="text" class="form-control" name="remark"
                                                                               placeholder="Remark" >
                            </div>
                                                                </div>-->
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



    <script>
                                            $('#datatable').DataTable({
                                                "ordering": false
                                            });
    </script>      



    <script>


        function getColumnName(value, id) {
            $.ajax({
                url: "ShowDataController",
                data: "task=getformMappingIds&form_id=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    //                debugger;
                    var dataValue = response.data;
                    $('#mydiv').empty();

                    for (var i = 0; i < dataValue.length; i++) {
                        var myBean = dataValue[i];

                        if (myBean.column_type === "TEXT") {
                            var markup = "<label>" + myBean.column_name + "</label>" + "<input type='text' class='form-control' name='" + myBean.column_name + "'><br>";
                            $('#mydiv').append(markup);

                        }
                        if (myBean.column_type === "DOCUMENT") {

                            if (myBean.column_subType === "IMAGE") {
                                var markup = "<label>" + myBean.column_name + "</label>" + "<input type='file' class='form-control' id='" + myBean.column_name + "' name='" + myBean.column_name + "'><br>";
                                console.log(markup);
                                $('#mydiv').append(markup);
                            }


                            if (myBean.column_subType === "VIDEO") {
                                var markup = "<label>" + myBean.column_name + "</label>" + "<input type='file' class='form-control' id='" + myBean.column_name + "' name='" + myBean.column_name + "'><br>";
                                $('#mydiv').append(markup);
                            }

                            if (myBean.column_subType === "AUDIO") {
                                var markup = "<label>" + myBean.column_name + "</label>" + "<input type='file' class='form-control' id='" + myBean.column_name + "' name='" + myBean.column_name + "'><br>";
                                $('#mydiv').append(markup);
                            }

                            if (myBean.column_subType === "PDF") {
                                var markup = "<label>" + myBean.column_name + "</label>" + "<input type='file' class='form-control' id='" + myBean.column_name + "' name='" + myBean.column_name + "'> <br>";
                                $('#mydiv').append(markup);
                            }
                            if (myBean.column_subType === "EXCEL") {
                                var markup = "<label>" + myBean.column_name + "</label>" + "<input type='file' class='form-control' id='" + myBean.column_name + "' name='" + myBean.column_name + "'> <br>";
                                $('#mydiv').append(markup);
                            }

                        }
                        if (myBean.column_type === "CAMERA INPUT" && myBean.column_subType === "PICTURE") {
                            var button = "<button type='button' class='btn normalBtn' id='capturePicButton'>" + myBean.column_name + " </button> <br> <br>";
                            $('#mydiv').append(button);
                            document.getElementById('capturePicButton').style.background = "#D3D3D3";
                            let isPicCapturing = false;
                            const capturePicButton = document.getElementById('capturePicButton');

                            capturePicButton.addEventListener('click', () => {
                                if (!isPicCapturing) {
                                    openCamera();

                                }
                            });

                            function openCamera() {

                                // Create a container for the webcam picture capture and remove button
                                var pictureCaptureContainer = document.createElement('div');
                                pictureCaptureContainer.className = 'picture-capture-container col-md-4 mb-3';

                                // Initialize variables
                                let webcamStream;
                                let pictureBlob;

                                // Create an image capture button
                                var captureImageButton = document.createElement('button');
                                captureImageButton.type = 'button';
                                captureImageButton.className = 'btn normalBtn';
                                captureImageButton.textContent = 'Capture Picture';
                                captureImageButton.style.background = "#90EE90";
                                // Create an image element to display the captured picture
                                var capturedImage = document.createElement('img');
                                capturedImage.id = 'capturedImage';
                                // Function to start webcam and capture picture
                                function startWebcamAndCapture() {
                                    navigator.mediaDevices
                                            .getUserMedia({video: true})
                                            .then((stream) => {
                                                webcamStream = stream;

                                                // Create a video element for the webcam feed
                                                var videoElement = document.createElement('video');
                                                videoElement.id = 'webcamVideo';
                                                videoElement.autoplay = true;
                                                pictureCaptureContainer.appendChild(videoElement);

                                                // Attach the webcam stream to the video element
                                                videoElement.srcObject = stream;

                                                // Create a "Capture" button
                                                var captureButton = document.createElement('button');
                                                captureButton.type = 'button';
                                                captureButton.className = 'btn normalBtn';
                                                captureButton.textContent = 'Capture';
                                                captureButton.onclick = function () {
                                                    capturePicture();
                                                };
                                                pictureCaptureContainer.appendChild(captureButton);
                                            })
                                            .catch((error) => {
                                                console.error('Error accessing the webcam:', error);
                                            });
                                }

                                // Function to capture a picture from the webcam
                                function capturePicture() {
                                    var videoElement = document.getElementById('webcamVideo');
                                    var canvas = document.createElement('canvas');
                                    canvas.width = videoElement.videoWidth;
                                    canvas.height = videoElement.videoHeight;
                                    var context = canvas.getContext('2d');
                                    context.drawImage(videoElement, 0, 0, canvas.width, canvas.height);
                                    canvas.toBlob(function (blob) {
                                        pictureBlob = blob;
                                        displayCapturedImage();
                                    }, 'image/jpeg');
                                }

                                // Function to display the captured picture
                                function displayCapturedImage() {
                                    capturedImage.src = URL.createObjectURL(pictureBlob);
                                    pictureCaptureContainer.appendChild(capturedImage);
                                }

                                // Create a "Remove" button for the picture capture container
                                var removeButton = document.createElement('button');
                                removeButton.type = 'button';
                                removeButton.className = 'btn btn-danger';
                                removeButton.textContent = 'Remove';
                                removeButton.onclick = function () {
                                    // Stop the webcam stream and remove the container
                                    if (webcamStream) {
                                        webcamStream.getTracks().forEach(function (track) {
                                            track.stop();
                                        });
                                    }
                                    pictureCaptureContainer.remove();
                                };

                                pictureCaptureContainer.appendChild(captureImageButton);
                                pictureCaptureContainer.appendChild(removeButton);
                                var a = document.getElementsByClassName('picture-capture-container col-md-4 mb-3');
                                if (a.length < 1) {
                                    $('#mydiv').append(pictureCaptureContainer);
                                }



                                captureImageButton.addEventListener('click', function () {
                                    startWebcamAndCapture();
                                });
                            }
                        }
                        if (myBean.column_type === "CAMERA INPUT" && myBean.column_subType === "VIDEO") {
                            var button = "<button type='button' class='btn normalBtn' id='recordVideoButton1'>" + myBean.column_name + " </button> <br><br>";
                            $('#mydiv').append(button);
                            document.getElementById('recordVideoButton1').style.background = "#D3D3D3";

                            // Initialize recording variable
                            let isMicRecording = false;

                            // Reference to the newly created elements
                            const recordVideoButton1 = document.getElementById('recordVideoButton1');

                            recordVideoButton1.addEventListener('click', () => {
                                if (!isMicRecording) {
                                    videoCAM();

                                }
                            });



                            function videoCAM() {
                                var videoRecordingContainer = document.createElement('div');
                                videoRecordingContainer.className = 'video-recording-container col-md-4 mb-3';

                                let mediaRecorder;
                                let recordedChunks = [];

                                var recordedVideo = document.createElement('video');
                                recordedVideo.id = 'recordedVideo';
                                recordedVideo.controls = true;

                                var startRecordingButton = document.createElement('button');
                                startRecordingButton.type = 'button';
                                startRecordingButton.className = 'btn normalBtn';
                                startRecordingButton.textContent = 'Start Recording';
                                startRecordingButton.style.background = "#90EE90";


                                var stopRecordingButton = document.createElement('button');
                                stopRecordingButton.type = 'button';
                                stopRecordingButton.className = 'btn normalBtn';
                                stopRecordingButton.textContent = 'Stop Recording';
                                stopRecordingButton.disabled = true;

                                var downloadButton = document.createElement('a');
                                downloadButton.href = '#';
                                downloadButton.download = 'recorded-video.webm';
                                downloadButton.className = 'btn normalBtn';
                                downloadButton.textContent = 'Download';
                                downloadButton.style.display = 'none';

                                function startVideoRecording() {
                                    navigator.mediaDevices
                                            .getUserMedia({video: true})
                                            .then((stream) => {
                                                mediaRecorder = new MediaRecorder(stream);
                                                recordedChunks = [];

                                                // Create a video element for the recording feed
                                                var videoElement = document.createElement('video');
                                                videoElement.id = 'recordingVideo';
                                                videoElement.autoplay = true;
                                                videoRecordingContainer.appendChild(videoElement);

                                                // Attach the stream to the video element
                                                videoElement.srcObject = stream;

                                                // Handle data available event for recording chunks
                                                mediaRecorder.ondataavailable = function (event) {
                                                    if (event.data.size > 0) {
                                                        recordedChunks.push(event.data);
                                                    }
                                                };

                                                // Start recording
                                                mediaRecorder.start();

                                                // Update button states
                                                startRecordingButton.disabled = true;
                                                stopRecordingButton.disabled = false;
                                            })
                                            .catch((error) => {
                                                console.error('Error accessing the webcam:', error);
                                            });
                                }

                                function stopVideoRecording() {
                                    if (mediaRecorder && mediaRecorder.state !== 'inactive') {
                                        mediaRecorder.stop();

                                        // Update button states
                                        startRecordingButton.disabled = false;
                                        stopRecordingButton.disabled = true;

                                        // Create a Blob from recorded chunks
                                        var recordedBlob = new Blob(recordedChunks, {type: 'video/webm'});

                                        // Set the recorded video source
                                        recordedVideo.src = URL.createObjectURL(recordedBlob);
                                        videoRecordingContainer.appendChild(recordedVideo);

                                        // Show the download button
                                        downloadButton.style.display = 'block';

                                        // Reset the recorder
                                        mediaRecorder = null;
                                    }
                                }

                                startRecordingButton.addEventListener('click', startVideoRecording);
                                stopRecordingButton.addEventListener('click', stopVideoRecording);

                                var removeButton = document.createElement('button');
                                removeButton.type = 'button';
                                removeButton.className = 'btn btn-danger';
                                removeButton.textContent = 'Remove';
                                removeButton.onclick = function () {
                                    // Stop the video recording and remove the container
                                    if (mediaRecorder && mediaRecorder.state !== 'inactive') {
                                        stopVideoRecording();
                                    }
                                    videoRecordingContainer.remove();
                                };

                                videoRecordingContainer.appendChild(startRecordingButton);
                                videoRecordingContainer.appendChild(stopRecordingButton);
                                videoRecordingContainer.appendChild(downloadButton);
                                videoRecordingContainer.appendChild(removeButton);

                                var a = document.getElementsByClassName('video-recording-container col-md-4 mb-3');
                                if (a.length < 1) {
                                    $('#mydiv').append(videoRecordingContainer);
                                }



                            }

                        }

                        if (myBean.column_type === "MIC INPUT" && myBean.column_subType === "AUDIO") {

                            var button = "<button type='button' class='btn normalBtn' id='recordMicButton'>" + myBean.column_name + " </button> <br><br>";
                            $('#mydiv').append(button);
                            document.getElementById('recordMicButton').style.background = "#D3D3D3";

                            // Initialize recording variables
                            let micMediaRecorder;
                            let micAudioChunks = [];
                            let isMicRecording = false;

                            // Reference to the newly created elements
                            const recordMicButton = document.getElementById('recordMicButton');

                            recordMicButton.addEventListener('click', () => {
                                if (!isMicRecording) {

                                    startMicRecording();
                                    alert("recording on....");

                                } else {
                                    stopMicRecording();
                                    alert("recording off....");

                                }
                                isMicRecording = !isMicRecording;
                            });

                            // Function to start microphone recording
                            function startMicRecording() {
                                debugger;
                                navigator.mediaDevices
                                        .getUserMedia({audio: true})
                                        .then((stream) => {
                                            micMediaRecorder = new MediaRecorder(stream);
                                            micMediaRecorder.ondataavailable = (e) => {
                                                if (e.data.size > 0) {
                                                    micAudioChunks.push(e.data);
                                                }
                                            };
                                            micMediaRecorder.onstop = () => {
                                                const micAudioBlob = new Blob(micAudioChunks, {type: 'audio/wav'});
                                                const micAudioUrl = URL.createObjectURL(micAudioBlob);

                                                // Create a container for the audio slider and remove button
                                                var audioSliderContainer = document.createElement('div');
                                                audioSliderContainer.className = 'audio-slider-container col-md-4 mb-3';

                                                // Create an audio player
                                                var micAudioPlayer = document.createElement('audio');
                                                micAudioPlayer.controls = true;
                                                micAudioPlayer.src = micAudioUrl;

                                                // Create a remove button
                                                var removeButton = document.createElement('button');
                                                removeButton.type = 'button';
                                                removeButton.className = 'btn btn-danger';
                                                removeButton.textContent = 'Remove';
                                                removeButton.onclick = function () {
                                                    audioSliderContainer.remove(); // Remove the entire slider container
                                                };

                                                audioSliderContainer.appendChild(micAudioPlayer);
                                                audioSliderContainer.appendChild(removeButton);
                                                $secondTrBody.append(audioSliderContainer);
                                            };
                                            micMediaRecorder.start();
                                        })
                                        .catch((error) => {
                                            console.error('Error accessing the microphone:', error);
                                        });
                            }


                            // Function to stop microphone recording
                            function stopMicRecording() {
                                if (micMediaRecorder && micMediaRecorder.state === 'recording') {
                                    micMediaRecorder.stop();
                                }
                            }

                        }


                        if (myBean.column_type === "DATE") {

                            var button = "<input type='date' class='form-control datepicker' placeholder='" + myBean.column_name + "' name='" + myBean.column_name + "' id='calendar'> <br><br>";
                            $('#mydiv').append(button);

                        }
                        if (myBean.column_type === "LOCATION") {

                            var button = "<input type='text' class='form-control locationpicker' placeholder='" + myBean.column_name + "' id='" + myBean.column_name + "' name='" + myBean.column_name + "'> <br><br>";
                            $('#mydiv').append(button);
                        }


                    }
                    // Initialize the datepicker for calendar input fields
                    $(".datepicker").datepicker();

                    // Initialize the location picker for location input fields (you'll need to include the appropriate library)
                    $(".locationpicker").locationpicker({
                        location: {latitude: 0, longitude: 0}, // Set default location
                        radius: 0, // Set default search radius
                    });

                },
                error: function (e)



                {
                    alert('Error:12212212112121 ' + e);
                }
            });
        }
    </script>

    <script>

        function downloadFile(fileName) {
            window.location.href = 'ShowDataController?fileName=' + fileName;
        }



//        function downloadFile(fileName) {
//            $.ajax({
//                url: "ShowDataController",
//                data: "task=viewfile&fileName=" + fileName,
//                type: 'POST',
//                dataType: 'json',
//                success: function (response) {
//
//
//                    alert(response.data);
//
//
//
//                },
//                error: function (e)
//
//
//
//                {
//                    alert('Error:12212212112121 ' + e);
//                }
//            });
//        }
    </script>