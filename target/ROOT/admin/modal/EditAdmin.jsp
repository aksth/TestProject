<div style="overflow: auto">

    <h1>Form</h1>
    <div class="col-md-6">
        <form name="adminForm">
            <label>Name<input type="text" ng-model="admin.admin.name" name="name" required/></label><br/>
            <label>Username<input type="text" ng-model="admin.admin.username" name="username" required/></label><br/>
            <%--<label>Password<input type="password" ng-model="admin.admin.password" name="password" required/></label><br/>--%>
            <label>Email<input type="text" ng-model="admin.admin.email" name="email" required/></label><br/>
            <label>Roles</label><br/>

            <label ng-repeat="role in roleList">
                <input type="checkbox" ng-model="roles[role.id]" name="roles" value="{{role.id}}"/>{{role.name}}
            </label>

            <br/>
            <div ng-show="admin.hasOwnProperty('profilePic')">
                <p>Profile Pic:</p>

                <img ng-hide="removeProPic || profilePic != ''" src="akash/ProfilePictureUploads/Admins/{{admin.profilePic.filename}}"/><br/>
                <img ng-show="profilePic != ''" src="{{profilePic}}"/>

                <button ng-hide="removeProPic" ng-click="removeProPic = true; profilePic=''">Remove</button>
                <button ng-click="uploadingNew = !uploadingNew">Upload New</button>
            </div>

            <div ng-hide="admin.hasOwnProperty('profilePic')">

                <p>Profile Pic</p>

                <img ng-show="profilePic != ''" src="{{profilePic}}"/>
                <button ng-hide="removeProPic || profilePic == ''" ng-click="removeProPic = true; profilePic=''">Remove</button>
                <button ng-click="uploadingNew = !uploadingNew">Upload New</button>
            </div>

            <br/>

            <label><input type="radio" name="gender" ng-model="admin.admin.gender"  value="M" required>Male</label>
            <label><input type="radio" name="gender" ng-model="admin.admin.gender"  value="F" required>Female</label><br/>
            <label>Date<input type="text" name="dob" ng-model="admin.admin.dob" my-datepicker required/></label><br/>

            <button ng-click="ok()" ng-disabled="adminForm.$invalid">Submit</button><br/>
            <span ng-show="successMsg != ''">{{formCtrl.successMsg}}</span>
            <span ng-show="errorMsg != ''">{{formCtrl.errorMsg}}</span>
        </form>

    </div>

    <div class="col-md-6" ng-show="uploadingNew">
        <h1>Upload New</h1>

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

        <button ng-click="profilePic = myCroppedImage; uploadingNew = false; removeProPic = false">Submit</button>
        <button ng-click="uploadingNew = false">Cancel</button>

    </div>

</div>

