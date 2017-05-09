<div class="col-md-9" >
    <div class="row">
        <p>Welcome {{formCtrl.currentUserName}}</p>
        <p>You can :
            <span ng-repeat="roleId in formCtrl.currentUserRoles">
                {{formCtrl.roleListMapping[roleId]}},
            </span>

        </p>
        <button ng-click="formCtrl.logout();">Logout</button>
    </div>

    <div ng-hide="formCtrl.currentUserRoles.indexOf(1) == -1">
        <h1>Form</h1>
        <form name="adminForm">

            <label>Name<input type="text" ng-model="formCtrl.formData.name" name="name" required/></label><br/>
            <label>Username<input type="text" ng-model="formCtrl.formData.username" name="username" required/></label><br/>
            <label>Password<input type="password" ng-model="formCtrl.formData.password" name="password" required/></label><br/>
            <label>Email<input type="email" ng-model="formCtrl.formData.email" name="email" required/></label><br/>
            <label>Roles</label><br/>

            <label ng-repeat="role in formCtrl.roleList">
                <input type="checkbox" ng-model="formCtrl.roles[role.id]" name="roles" value="{{role.id}}"/>{{role.name}}
            </label>

            <br/>
            <label><input type="radio" name="gender" ng-model="formCtrl.formData.gender"  value="M" required />Male</label>
            <label><input type="radio" name="gender" ng-model="formCtrl.formData.gender"  value="F" required />Female</label><br/>
            <label>Date<input type="text" name="dob" ng-model="formCtrl.formData.dob" my-datepicker required/></label><br/>

            <img ng-src="{{formCtrl.profilePic}}" ng-show="formCtrl.profilePic != ''"/><a ng-show="formCtrl.profilePic != ''" href="#" ng-click="formCtrl.profilePic = ''">Remove</a><br/>
            <button ng-click="formCtrl.openProfilePicModal()" formnovalidate>Upload Picture</button>

            <br/>
            <button ng-click="formCtrl.submit()" ng-disabled="adminForm.$invalid">Submit</button><br/>
            <div uib-alert class="alert-success" close="formCtrl.successMsg = ''" ng-show="formCtrl.successMsg != ''">{{formCtrl.successMsg}}</div>
            <div uib-alert class="alert-danger" close="formCtrl.errorMsg = ''" ng-show="formCtrl.errorMsg != ''">{{formCtrl.errorMsg}}</div>
        </form>
    </div>

    <div>
        <table class="table table-bordred">
            <tbody>
            <tr>
                <td></td>
                <td>name</td>
                <td>gender</td>
                <td>dob</td>
                <td>username</td>
                <td>email</td>
                <td>roles</td>
                <td ng-hide="formCtrl.currentUserRoles.indexOf(2) == -1">edit</td>
                <td ng-hide="formCtrl.currentUserRoles.indexOf(2) == -1">delete</td>
            </tr>
            <tr ng-repeat="item in formCtrl.adminList" ng-click="formCtrl.detailAdmin(item)">
                <td><img ng-show="item.hasOwnProperty('profilePic')" width="50px" src="akash/ProfilePictureUploads/Admins/{{item.profilePic.filename}}"/></td>
                <td>{{item.admin.name}}</td>
                <td>{{item.admin.gender}}</td>
                <td>{{item.admin.dob}}</td>
                <td>{{item.admin.username}}</td>
                <td>{{item.admin.email}}</td>
                <td><span ng-repeat="role in item.roles">{{role.name}}, </span></td>
                <td ng-hide="formCtrl.currentUserRoles.indexOf(2) == -1"><button ng-click="formCtrl.editAdmin(item); $event.stopPropagation();">Edit</button></td>
                <td ng-hide="formCtrl.currentUserRoles.indexOf(2) == -1"><button ng-click="formCtrl.deleteAdmin(item.admin.id); $event.stopPropagation();">Delete</button></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>