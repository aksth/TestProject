<div>

    <h1>Upload Pic</h1>

    <div>Select an image file: <input type="file" id="fileInput" upload my-image="myImage"/></div>

    <div class="col-md-6">
        <div class="cropArea">
            <img-crop image="myImage" result-image="myCroppedImage"></img-crop>
        </div>
    </div>

    <div class="col-md-6">
        <div>Cropped Image:</div>
        <div><img ng-src="{{myCroppedImage}}" /></div>
    </div>

    <button ng-click="ok();">Submit</button>

</div>