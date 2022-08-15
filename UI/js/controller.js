import * as model from './model.js';
import NavView from './view/nav-view.js';
import LoginView from "./view/login-view.js";
import NewReimbursementView from "./view/new-reimbursement-view.js";
import {loadReimbursement, state} from "./model.js";
import ListView from "./view/list-view.js";

const controlGuessPage = function(goTo, err = false) {
    const data = {
        goTo: goTo,
        err: err
    }
    NavView.render(goTo);
    LoginView.render(data);

}

const controlLogin = async function (data) {
    await model.loadUser(data['email']);

    console.log(model.state.user)
    if (model.state.user.password === data['password']) {
        controlHome();
    }else {
        controlGuessPage(data['form_type'], true)
    }
}

const controlHome = async function () {
    NewReimbursementView.render(state.user);
    await loadReimbursement(state.user);
    console.log(state.reimbursementList);
    ListView.render(state.reimbursementList);

}

export function init() {
    NavView.addHandleClick(controlGuessPage);
    LoginView.addHandleClick(controlLogin)
}