
export const state = {
    user:{},
    reimbursement:{},
    userList: {},
    reimbursementList: {},
    reimbursementStatusList: {},
}

const apiUrl = `http://localhost:8080/Cristian-Velasquez-P1/`;


export const loadUser = async function (email) {
    try {
        const resp = await fetch(`${apiUrl}users?email=${email}`);
        const data = await resp.json();

        state.user = data;
    }catch (err) {
        console.log(err)
    }
}

export const loadReimbursement = async function (user) {
    try {
        const url = user.userTypeID === 1 ? `${apiUrl}reimbursements` : `${apiUrl}reimbursements?user-id`;
        const resp = await fetch(url);
        const data = await resp.json();

        state.reimbursementList = data;
    }catch (err) {
        console.log(err)
    }
}

export const loadReimbursementById = async function(id) {
    try {
        const url = `http://localhost:8080/Cristian-Velasquez-P1/reimbursements?id=${id}`;
        const resp = await fetch(url);
        const data = await resp.json();
        state.reimbursement = data;
    }catch (err) {
        console.log(err)
    }
}

export const loadReimbursementStatus = async function () {
    try {
        const url = `http://localhost:8080/Cristian-Velasquez-P1/reimbursements-status`;
        const resp = await fetch(url);
        const data = await resp.json();
        state.reimbursementStatusList = data;
    }catch (err) {
        console.log(err)
    }
}

export const createReimbursement = async function (data) {
    console.log(data)
}

