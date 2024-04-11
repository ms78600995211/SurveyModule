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
                    <div class="alert alert-danger alert-dismissible fade show"   id="alert_message" role="alert">
                        <i class="mdi mdi-check-all me-2"></i>
                        ${message}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>  
                </div>
            </c:if>

            <div class="row">
                <div class="col-12">
                    <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                        <h4 class="mb-sm-0 font-size-18">Dynamic Form</h4>
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item"><a href="index.php">Dashboard</a></li>
                                <li class="breadcrumb-item active">Dynamic Form</li>
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
                                        <!--<p class="text-muted mb-2">Search your result according to this fields.</p>-->
                                        <div class="row">                
                                            <div class="col-md-8">
                                                <form class="custom-validation" action="backend/employeeAdd.php" method="post" enctype="multipart/form-data">
                                                    <div class="row">  
                                                        <!--                                                        <div class="col-md-3">
                                                                                                                    <div class="mb-3">
                                                                                                                        <select class="form-control" name="District">
                                                                                                                            <option selected disabled>--Select Source--</option>
                                                                                                                            <option value="#">Status Type1</option>
                                                                                                                            <option value="#">Status Type2 </option>
                                                                                                                            <option value="#">Status Type3</option>
                                                                                                                            <option value="#">Status Type4</option>
                                                                                                                            <option value="#">Status Type5</option>
                                                                                                                        </select>
                                                                                                                    </div>
                                                                                                                </div>-->
                                                        <!--                                                        <div class="col-md-3">
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
                                                                                                                </div>                                         -->
                                                        <!--                                                        <div class="col-md-3">
                                                                                                                    <div class="mb-3">
                                                                                                                        <button class="btn btn-info w-100" type="submit" name=""><i class="bx bx-search font-size-16 align-middle me-2"></i> Search</button>
                                                                                                                    </div>
                                                                                                                </div>-->
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="row">
                                                    <div class="col-md-1"></div>
                                                    <!--                                                    <div class="col-md-3">
                                                                                                            <div class="mb-1">
                                                                                                                <button class="btn btn-danger w-100"><i class="far fa-file-pdf font-size-16 align-middle"></i> PDF</button>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="col-md-3">
                                                                                                            <div class="mb-1">
                                                                                                                <button class="btn btn-success w-100"><i class="far fa-file-excel font-size-16 align-middle"></i> Excel</button>
                                                                                                            </div>
                                                                                                        </div>-->
                                                    <div class="col-md-5 text-end">
                                                        <!--<button type="button" class="btn btn-primary waves-effect waves-light" data-bs-toggle="modal" data-bs-target=".bs-example-modal-xl-Add-Enquiry"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Add Form Mapping</button>-->
                                                        <!--                                                        <a  class="btn btn-primary waves-effect waves-light"  href="SelectionValueMappingController?task=selectionMapping"> <i class="bx bx-plus font-size-16 align-middle me-2"></i> Add Selection Mapping</a>-->

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
                                                    <div id="example3" >

                                                        <form class="custom-validation" action="DynamicFormController" method="post">

                                                            <div class="col-md-3">
                                                                <div class="mb-3">
                                                                    <label class="form-label">Table Name<span class="text-danger">*</span></label>
                                                                    <select class="form-control" name="Form0" id="formId" onchange="getColumnName(this.value)" required>
                                                                        <option value="" selected disabled>Select</option>
                                                                        <c:forEach var="form" items="${requestScope['FormData']}"
                                                                                   varStatus="loopCounter">
                                                                            <option id ="${form.form_name}" value="${form.form_id}" >${form.form_name}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>

                                                            <table id="myTable" class="table table-bordered dt-responsive  nowrap w-100">
                                                                <input type="hidden" name="totalcount" id="totalcount" value="">                                        
                                                                <thead>
                                                                    <tr>
                                                                    </tr>
                                                                <input type="hidden" name="colm" value="" id="colm">

                                                                <br> <br>
                                                                <tr>
                                                                </tr>
                                                                </thead>
                                                                <tbody>                                                    
                                                                    <tr>
                                                                        <!--write values of column-->
                                                                    </tr>
                                                                </tbody>
                                                            </table>

                                                            <div class="container-fluid">                                                                 
                                                                <div class="row" id="contentWrap">

                                                                </div>
                                                            </div>

                                                            <center> <div class="ms"><p style="color: red">${msg}</p></div></center
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

        function getColumnName(value) {
            $.ajax({
                url: "DynamicFormController",
                data: "task=getColumnNameWithValues&formTypeId=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    debugger;
                    var data = response.data;

                    var message = response.message;

                    if ((message !== null || !message.equals("undefined")) && data === undefined) {
                        alert(message);
                        var $secondTrBodyButton = $('#myTable tbody tr');
                        $secondTrBodyButton.empty();
                        var button = "<button type:'submit',  class:'btn normalBtn', id:'addBtn' , onclick='onClickFunction(" + value + ")' >View</button>"
                        $secondTrBodyButton.append(button);

                    } else {
//                        alert("Data Found")
//                         var $secondTrHead = $('#myTable thead tr:eq(1)');
                        var $secondTrBody = $('#contentWrap');
//                        $secondTrHead.empty();
                        $secondTrBody.empty();
                        for (var i = 0; i < data.length; i++) {
                            var myBean = data[i];

                            if (myBean.type_name === "TEXT") {

                                var inputField = "<div class='col-md-4 mb-3'><div class='form-group'><label for='" + myBean.display_name + "'>" + myBean.display_name + ":</label>\n\
                                   <input type='text' class='form-control' placeholder='" + myBean.display_name + "' id='" + myBean.display_name + "' name='" + myBean.display_name + "'        ></div></div>";
                                $secondTrBody.append(inputField);
                            }
                            // ... Previous code ...

                            if (myBean.type_name === "MIC INPUT") {



                                if (myBean.subtype_name === "AUDIO") {

                                    var button = "<div class='col-md-4 mb-14'><button type='button' class='btn normalBtn' id='recordMicButton' >" + myBean.display_name + " </button></div>";
                                    $secondTrBody.append(button);
                                    document.getElementById('recordMicButton').style.background = "rgb(211, 211, 211)";
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

                            }
                            if (myBean.type_name === "CAMERA INPUT") {

                                if (myBean.subtype_name === "VIDEO") {

                                    var button = "<div class='col-md-4 mb-14'><button type='button' class='btn normalBtn' id='recordVideoButton1'>" + myBean.display_name + " </button></div>";
                                    $secondTrBody.append(button);
                                    document.getElementById('recordVideoButton1').style.background = "#D3D3D3"
                                    let isMicRecording = false;
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
                                        startRecordingButton.style.color = "black";



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
                                        removeButton.style.color = 'black';

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
                                        var f = document.getElementsByClassName('video-recording-container col-md-4 mb-3');
                                        if (f.length < 1) {
                                            $secondTrBody.append(videoRecordingContainer);
                                        }



                                    }
                                }


                                if (myBean.type_name === "CAMERA INPUT" && myBean.subtype_name === "PICTURE") {

                                    var button = "<div class='col-md-4 mb-14'><button type='button' class='btn normalBtn' id='recordMicButton1'>" + myBean.display_name + " </button></div>";
                                    $secondTrBody.append(button);

                                    document.getElementById('recordMicButton1').style.background = "#D3D3D3";

                                    let isMicRecording = false;
                                    const recordMicButton1 = document.getElementById('recordMicButton1');

                                    recordMicButton1.addEventListener('click', () => {
                                        if (!isMicRecording) {
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
                                        captureImageButton.style.background = '#90EE90';


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

                                        // Append the buttons and elements to the container
                                        pictureCaptureContainer.appendChild(captureImageButton);
                                        pictureCaptureContainer.appendChild(removeButton);
                                        var a = document.getElementsByClassName('picture-capture-container col-md-4 mb-3');
                                        if (a.length < 1) {
                                            $secondTrBody.append(pictureCaptureContainer);
                                        }

                                        // Add an event listener to start webcam when the "Capture Picture" button is clicked
                                        captureImageButton.addEventListener('click', function () {
                                            startWebcamAndCapture();
                                        });
                                    }

                                }

                            }
                            if (myBean.type_name === "DATE") {
                                // Create the button dynamically
                                var calendarField = "<div class='col-md-4 mb-3'><div class='form-group'><label for='" + myBean.display_name + "'>" + myBean.display_name + ":</label><input type='date' class='form-control datepicker' placeholder='" + myBean.display_name + "' id='calendar'></div></div>";
                                // Initialize the datepicker for calendar input fields
                                $secondTrBody.append(calendarField);

                            }
                            if (myBean.type_name === "LOCATION") {
                                // Create the button dynamically
                                var locationField = "<div class='col-md-4 mb-3'><div class='form-group'><label for='" + myBean.display_name + "'>" + myBean.display_name + ":</label>\n\
                                                      <input type='text' class='form-control locationpicker' placeholder='" + myBean.display_name + "' id='" + myBean.display_name + "' name='" + myBean.display_name + "'></div></div>";
                                $secondTrBody.append(locationField);

                            }

                            if (myBean.type_name === "DOCUMENT") {

                                if (myBean.subtype_name === "IMAGE") {

                                    var fileField = "<div class='col-md-4 mb-3'><div class='form-group'><label for='" + myBean.display_name + "'>" + myBean.display_name + ":</label><input type='file' class='form-control-file' id='" + myBean.display_name + "' name='" + myBean.display_name + "'></div></div>";
                                    $secondTrBody.append(fileField);
                                }


                                if (myBean.subtype_name === "VIDEO") {
                                    var fileField = "<div class='col-md-4 mb-3'><div class='form-group'><label for='" + myBean.display_name + "'>" + myBean.display_name + ":</label><input type='file' class='form-control-file' id='" + myBean.display_name + "' name='" + myBean.display_name + "'></div></div>";
                                    $secondTrBody.append(fileField);
                                }

                                if (myBean.subtype_name === "AUDIO") {
                                    var fileField = "<div class='col-md-4 mb-3'><div class='form-group'><label for='" + myBean.display_name + "'>" + myBean.display_name + ":</label><input type='file' class='form-control-file' id='" + myBean.display_name + "' name='" + myBean.display_name + "'></div></div>";
                                    $secondTrBody.append(fileField);
                                }

                                if (myBean.subtype_name === "PDF") {
                                    var fileField = "<div class='col-md-4 mb-3'><div class='form-group'><label for='" + myBean.display_name + "'>" + myBean.display_name + ":</label><input type='file' class='form-control-file' id='" + myBean.display_name + "' name='" + myBean.display_name + "'></div></div>";
                                    $secondTrBody.append(fileField);
                                }

                                if (myBean.subtype_name === "EXCEL") {
                                    var fileField = "<div class='col-md-4 mb-3'><div class='form-group'><label for='" + myBean.display_name + "'>" + myBean.display_name + ":</label><input type='file' class='form-control-file' id='" + myBean.display_name + "' name='" + myBean.display_name + "'></div></div>";
                                    $secondTrBody.append(fileField);
                                }
                            }

                        }
// Initialize the datepicker for calendar input fields
                        $(".datepicker").datepicker();

                        // Initialize the location picker for location input fields (you'll need to include the appropriate library)
                        $(".locationpicker").locationpicker({
                            location: {latitude: 0, longitude: 0}, // Set default location
                            radius: 0, // Set default search radius
                        });

                    }

                },
                error: function (e) {
                    alert('Error:12212212112121 ' + e);
                }
            });
        }


        function onClickFunction(value) {
            alert("viewFunction");
            $.ajax({
                url: "DynamicFormController",
                data: "task=createDynamicTable&form_id=" + value,
                type: 'POST',
                dataType: 'json',
                success: function (response) {
                    alert("Form created.Please select this to show this");
                    getColumnName(response.data);
                },
                error: function (e) {
                    alert('Error:================= ' + e);
                }
            });
        }

    </script>

    <script type="text/javascript">

        var i = 1;

        $(document).ready(function () {

            // Find and remove selected table rows
            $(".delete-row").click(function () {
                $("table tbody").find('input[name="record"]').each(function () {
                    if ($(this).is(":checked")) {
                        $(this).parents("tr").remove();
                    }
                });
            });
        });



    </script>

    <script type="text/javascript">

//        function openMIC(value) {
//            alert("value=====" + value);
//
//
//        }

    </script>