import View from "./view.js";

class UserView extends View{
    _parentElement = document.querySelector('.content__section');


    addHandleClick(handler) {
        const that = this;
        this._parentElement.addEventListener("click", function (e) {
            e.preventDefault();

            const btn = e.target.closest('.btn__edit-user');

            if (!btn) return;

            let data = {
                id: that._data.currentUser.id,
                firstName: that._data.currentUser.firstName,
                lastName: that._data.currentUser.lastName,
                email: that._data.currentUser.email,
                password: that._data.currentUser.password,
                userTypeID: btn.dataset.convertTo,
            };

            handler(data);
        })
    }

    _generateMarkup() {
        return `
            <div class="content__reimbursement">
                <h2>Reimbursement</h2>
                <table>
                    <tbody>
                        <tr>
                            <td><strong>Full Name:</strong></td>
                            <td>${this._data.currentUser.firstName} ${this._data.currentUser.lastName}</td>
                        </tr>
                        <tr>
                            <td><strong>Email:</strong></td>
                            <td>${this._data.currentUser.email}</td>
                        </tr>
                        <tr>
                            <td><strong>User Type:</strong></td>
                            <td>${this._data.currentUser.userType}</td>
                        </tr>
                    </tbody>
                </table>
                <div>
                    ${this._data.user.userTypeID === 1? this._generateBtnEdit() : ''}
                </div>
            </div>
        `;
    }

    _generateBtnEdit() {
        if (this._data.currentUser.userTypeID === 2){
            return `
                <button class="btn btn__edit-user" 
                        data-action="edit" 
                        data-convert-to="1">Convert in admin user</button> 
            `;
        }else {
            return `
                <button class="btn btn__edit-user" d
                        ata-action="edit" 
                        data-convert-to="2">Convert in employee user</button> 
            `;
        }
    }
}

export default new UserView();