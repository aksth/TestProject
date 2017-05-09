<div>

    <h1>Form</h1>

        <ul class="list-unstyled">
            <li>Name: {{admin.admin.name}}</li>
            <li>Username: {{admin.admin.username}}</li>
            <li>Email: {{admin.admin.email}}</li>
            <li ng-show="admin.hasOwnProperty('profilePic')">
                Profile Pic <br/>
                <img src="akash/ProfilePictureUploads/Admins/{{admin.profilePic.filename}}"/>
            </li>
            Roles:
            <ul>
                <li ng-repeat="role in admin.roles">
                    {{role.name}}
                </li>
            </ul>
            <li>Gender: {{admin.admin.gender}}</li>
            <li>Date of Birth: {{admin.admin.dob}}</li>
        </ul>


</div>
