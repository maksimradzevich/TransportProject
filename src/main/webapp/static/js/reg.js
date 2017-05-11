var module = angular.module("reg", []);

function regController() {
    var vm = this;

    vm.user = {
        email: "",
        password: ""
    };
}

module.controller("regCont", [regController]);