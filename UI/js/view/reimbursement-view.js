import View from "./view.js";

class ReimbursementView extends View{
    _parentElement = document.querySelector('.content__section');

    addHandleClick(handler) {
        this._parentElement.addEventListener("click", function (e) {
            e.preventDefault();

            const btn = e.target.closest('.btn');

            if (!btn) return;

            let data = [];
            data['action'] = btn.dataset.action;
            data['reimbursementId'] = btn.dataset.id;

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
                            <td><strong>Title:</strong></td>
                            <td>${this._data.reimbursement.title}</td>
                        </tr>
                        <tr>
                            <td><strong>Description:</strong></td>
                            <td>${this._data.reimbursement.description}</td>
                        </tr>
                        <tr>
                            <td><strong>Status:</strong></td>
                            <td>${this._generateStatusName(this._data.reimbursement.statusId)}</td>
                        </tr>
                    </tbody>
                </table>
                <div>
                    ${this._data.user.userTypeID === 1? this._generateBtnStatus() : this._generateBtnEdit()}
                </div>
            </div>
        `;
    }

    _generateBtnStatus() {
        if (this._data.reimbursement.statusId === 1){
            return `
            <button class="btn btn__delete" data-action="delete" data-id="${this._data.reimbursement.id}">Delete</button>
            <button class="btn btn__approve" data-action="approve" data-id="${this._data.reimbursement.id}">Approve</button>
            <button class="btn btn__reject" data-action="reject" data-id="${this._data.reimbursement.id}">Reject</button>
        `
        }else if (this._data.reimbursement.statusId === 2) {
            return `<p class="approved">This reimbursement has been approved</p>`;
        }else if(this._data.reimbursement.statusId === 3) {
            return `<p class="reject">This reimbursement has been Reject`;
        }

    }

    _generateBtnEdit() {
        if (this._data.reimbursement.statusId === 1){
            return `
                <button class="btn btn__delete" data-action="delete" data-id="${this._data.reimbursement.id}">Delete</button> 
                <button class="btn btn__edit" data-action="edit" data-id="${this._data.reimbursement.id}">Edit</button>
            `;
        }else if (this._data.reimbursement.statusId === 2) {
            return `<p class="approved">This reimbursement has been approved</p>`;
        }else if(this._data.reimbursement.statusId === 3) {
            return `<p class="reject">This reimbursement has been Reject`;
        }
    }

    _generateStatusName(id) {
        let statusName = '';
        this._data.reimbursementStatusList.forEach( s => {
            if (s.statusId === id) {
                statusName = s.statusTitle;
            }
        })
        return statusName;
    }
}

export default new ReimbursementView();