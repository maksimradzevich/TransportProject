var module = angular.module("reg", []);

function regController() {
    var vm = this;

    vm.user = {
        email: "",
        password: "",
        passwordRetype: ""
    };
}

module.controller("regCont", [regController]);

function loginController() {

}
module.controller("login", [loginController]);