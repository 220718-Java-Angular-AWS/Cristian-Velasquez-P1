
export const state = {
    user:{},
    reimbursement:{},
    userList: {},
    reimbursementList: {},
    reimbursementStatusList: {},
    page: '',
    filter: 0
}

const apiUrl = `http://localhost:8080/Cristian-Velasquez-P1/`;

export const setPage = function (page) {
    state.page = page;
}

export const setFilter = function (id) {
    state.filter = id;
}

const convertArrayToReimbursementObject = function (arr) {
    return {
        title: arr['title'],
        description: arr['description'],
        statusId: arr['status_id'],
        userId: arr['user_id']
    }
}

const convertArrayToUserObject = function (arr) {
    return {
        firstName: arr['first_name'],
        lastName: arr['last_name'],
        email: arr['email'],
        password: arr['password'],
        userTypeID: 2
    }
}


//////////////////////////////////////// Users ///////////////////////////////////////////

export const createUser = async function (data) {
    const dataObj = convertArrayToUserObject(data);
    const url = `${apiUrl}users`;
    const resp = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dataObj)
    });
    const newData = await resp.json();
    state.user = newData;
}



export const loadUser = async function (email) {
    try {
        const resp = await fetch(`${apiUrl}users?email=${email}`);
        const data = await resp.json();

        state.user = data;
    }catch (err) {
        console.log(err)
    }
}

//////////////////////////////////////// Reimbursement ///////////////////////////////////////////

export const createReimbursement = async function (data) {
    const dataObj = convertArrayToReimbursementObject(data);
    console.log()
    const url = `${apiUrl}reimbursements`;
    const resp = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dataObj)
    });
    const newData = await resp.json();
    state.reimbursement = newData;
}

export const loadReimbursementById = async function(id) {
    try {
        const url = `${apiUrl}reimbursements?id=${id}`;
        const resp = await fetch(url);
        const data = await resp.json();
        state.reimbursement = data;
    }catch (err) {
        console.log(err)
    }
}

export const loadAllReimbursement = async function (user) {
    try {
        const url = user.userTypeID === 1 ? `${apiUrl}reimbursements` : `${apiUrl}reimbursements?user-id=${user.id}`;
        const resp = await fetch(url);
        const data = await resp.json();

        state.reimbursementList = data;
    }catch (err) {
        console.log(err)
    }
}

export const loadAllReimbursementByStatusId = async function (statusId) {
    try {
        const url = `${apiUrl}reimbursements?status-id=${statusId}`;
        const resp = await fetch(url);
        const data = await resp.json();

        state.reimbursementList = data;
    }catch (err) {
        console.log(err)
    }
}

export const deleteReimbursementById = async function(id) {
    try {
        const url = `${apiUrl}reimbursements?id=${id}`;
        await fetch(url , {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        });

        state.reimbursement = {};
    }catch (err) {
        console.log(err)
    }
}

export const updateReimbursement = async function (data, isObject = true) {
    const dataObj = isObject ? data : convertArrayToReimbursementObject(data);
    if(!isObject) {
        dataObj.id = data['reimbursement_id'];
    }
    const url = `${apiUrl}reimbursements`;
    const resp = await fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dataObj)
    });
}

//////////////////////////////////////// Reimbursement Status ///////////////////////////////////////////

export const loadReimbursementStatus = async function () {
    try {
        const url = `${apiUrl}reimbursements-status`;
        const resp = await fetch(url);
        const data = await resp.json();
        state.reimbursementStatusList = data;
    }catch (err) {
        console.log(err)
    }
}


