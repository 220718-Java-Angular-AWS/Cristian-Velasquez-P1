import * as model from './model.js';
import NavView from './view/nav-view.js';
import LoginView from "./view/login-view.js";
import FormReimbursementView from "./view/form-reimbursement-view.js";
import ListView from "./view/list-view.js";
import ReimbursementView from "./view/reimbursement-view.js";

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

    if (model.state.user.password === data['password']) {
        await controlHome();
    }else {
        controlGuessPage(data['form_type'], true)
    }
}

const controlHome = async function (id) {
    FormReimbursementView.render(model.state.user);
    await model.loadReimbursement(model.state.user);
    await model.loadReimbursementStatus();
    ListView.render(model.state);
}

const controlReimbursement = async function (id) {
    await model.loadReimbursementById(id);

    ReimbursementView.render(model.state.reimbursement);
}

const controlCreateReimbursement = async function (data) {
    await model.createReimbursement(data);
}

export function init() {
    NavView.addHandleClick(controlGuessPage);
    LoginView.addHandleClick(controlLogin);
    ListView.addHandleClick(controlReimbursement);
    FormReimbursementView.addHandleClick(controlCreateReimbursement);
}