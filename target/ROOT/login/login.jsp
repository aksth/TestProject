<div class="col-lg-3">
    <h1>Login</h1>
    <form name="login">
        <label>username<input type="text" name="username" ng-model="loginCtrl.loginData.username"/></label><br/>
        <label>password<input type="password" name="password" ng-model="loginCtrl.loginData.password"/></label><br/>
        <button ng-click="loginCtrl.login()">Login</button>
    </form>
    <div uib-alert class="alert-danger" close="loginCtrl.errorMsg = ''" ng-show="loginCtrl.errorMsg != ''">{{loginCtrl.errorMsg}}</div>
</div>
