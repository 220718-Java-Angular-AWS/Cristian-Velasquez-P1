
export const state = {
    user:{},
    reimbursement:{},
    userList: {},
    reimbursementList: {},

}

const apiUrl = `http://localhost:8080/Cristian-Velasquez-P1/`;

const createUser = function (data) {
    const user = data;
    return {
        id: user.id,
        email: user.email,
        firstName: user.firstName,
        lastName: user.lastName,
        password: user.password,
        userType: user.userType,
        userTypeID: user.userTypeID
    }
}

export const loadUser = async function (email) {
    try {
        const resp = await fetch(`${apiUrl}users?email=${email}`);
        const data = await resp.json();

        state.user = createUser(data);
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

