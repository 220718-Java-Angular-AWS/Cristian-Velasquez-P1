import * as model from './model.js';
import NavView from './view/nav-view.js';
import LoginView from "./view/login-view.js";
import FormReimbursementView from "./view/form-reimbursement-view.js";
import ListView from "./view/list-view.js";
import ReimbursementView from "./view/reimbursement-view.js";
import {setPage} from "./model.js";

const controlLogin = async function (data) {
    model.setPage(data['form_type']);
    if (model.state.page === 'login') {
        await model.loadUser(data['email']);

        if (model.state.user.password === data['password']) {
            setPage('create');
            await createReimbursementPage();
        }else {
            guessPage(model.state.page, true)
        }
    } else {
        await model.createUser(data);
        if (model.state.user.password === data['password']) {
            setPage('create');
            await createReimbursementPage();
        }else {
            guessPage(model.state.page, true);
        }
    }



}

const guessPage = async function(goTo, err = false) {
    const data = {
        goTo: goTo,
        err: err
    }
    NavView.render(goTo);
    if (goTo === 'login' || goTo === 'singup' ) {
        LoginView.render(data);
    }else {
        setPage('create');
        FormReimbursementView.render(model.state);
        await model.loadAllReimbursement(model.state.user);
        await model.loadReimbursementStatus();
        ListView.render(model.state);
    }


}

const createReimbursementPage = async function () {
    NavView.render(model.state.page);
    FormReimbursementView.render(model.state);
    await model.loadAllReimbursement(model.state.user);
    await model.loadReimbursementStatus();
    ListView.render(model.state);
}

const controlReimbursement = async function (id) {
    await model.loadReimbursementById(id);

    ReimbursementView.render(model.state);
}

const controlCreateReimbursement = async function (data) {
    console.log(data);
    if (data['action'] === 'update'){
        await model.updateReimbursement(data, false);
        await model.loadReimbursementById(data['reimbursement_id']);

    }else {
        await model.createReimbursement(data);

    }
    ReimbursementView.render(model.state);
    await model.loadAllReimbursement(model.state.user);
    await model.loadReimbursementStatus();
    ListView.render(model.state);



}

const controlReimbursementAction = async function (data) {

    if(data['action'] === 'delete') {
        await model.deleteReimbursementById(data['reimbursementId']);
        setPage('create');
        await createReimbursementPage();
    }else if(data['action'] === 'approve') {
        let newData = model.state.reimbursement;
        newData.statusId = 2;
        await model.updateReimbursement(newData);

        ReimbursementView.render(model.state);
        await model.loadAllReimbursement(model.state.user);
        await model.loadReimbursementStatus();
        ListView.render(model.state);
    }else if(data['action'] === 'reject') {
        let newData = model.state.reimbursement;
        newData.statusId = 3;
        await model.updateReimbursement(newData);

        ReimbursementView.render(model.state);
        await model.loadAllReimbursement(model.state.user);
        await model.loadReimbursementStatus();
        ListView.render(model.state);
    }else if(data['action'] === 'edit') {
        model.setPage('edit');
        FormReimbursementView.render(model.state);
    }

}

const controlSelectStatus = async function(id){
    model.setFilter(id);
    if (id == 0) {
        await model.loadAllReimbursement(model.state.user);
    } else {
        await model.loadAllReimbursementByStatusId(id);
    }

    ListView.render(model.state);
}

export function init() {
    NavView.addHandleClick(guessPage);
    LoginView.addHandleClick(controlLogin);
    ListView.addHandleClick(controlReimbursement);
    ListView.addHandleSelect(controlSelectStatus);
    FormReimbursementView.addHandleClick(controlCreateReimbursement);
    ReimbursementView.addHandleClick(controlReimbursementAction);
}