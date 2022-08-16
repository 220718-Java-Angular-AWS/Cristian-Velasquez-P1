import View from "./view.js";

class ReimbursementView extends View{
    _parentElement = document.querySelector('.content__section');

    _generateMarkup() {
        console.log(this._data);
        return `
            <div class="content__reimbursement">
                <h2>${this._data.title}</h2>
                <p>${this._data.description}</p>
                <div>
                    ${this._data.statusId === 1 ? this._generateBtnStatus() : this._generateStatus() }
                </div>
            </div>
        `;
    }

    _generateBtnStatus() {
        return `
            <button class="btn btn__delete" data-action"delete" data-id="1">Delete</button>
            <button class="btn btn__approve" data-action"delete" data-id="1">Approve</button>
            <button class="btn btn__reject" data-action"delete" data-id="1">Reject</button>
        `
    }

    _generateStatus() {
        if (this._data.statusId === 2) {
            return `<p class="approved">This reimbursement has been approved</p>`;
        }else if(this._data.statusId === 3) {
            return `<p class="reject">This reimbursement has been Reject`;
        }
    }
}

export default new ReimbursementView();