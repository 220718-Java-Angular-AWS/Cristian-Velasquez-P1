import View from "./view.js";

class FormReimbursementView extends View {
    _parentElement = document.querySelector('.content__section');

    addHandleClick(handle) {
        this._parentElement.addEventListener('click', function (e) {
            e.preventDefault();

            const btn = e.target.closest('.btn__submit-send');

            if (!btn) return;

            const form = e.target.closest('.content__form-reimbursement');

            const formData = new FormData(form);
            let data = [];
            for(const [key, value] of formData) {
                data[key] = value;
            }
            handle(data);
        })
    }

    _generateMarkup() {

        return `
            <form action="" id="content__form-reimbursement" class="content__form form content__form-reimbursement">
                <h2>${this._data.page === 'edit' ? 'Update' : 'Create' } Reimbursement</h2>
                <input class="form__input" type="text" name="title" placeholder="Title" ${this._data.page === 'edit' ? 'value="' + this._data.reimbursement.title + '"': ''}>
                <textarea class="form__textarea" name="description" placeholder="Description">${this._data.page === 'edit' ? this._data.reimbursement.description : ''}</textarea>
                <input type="hidden" name="status_id" value="1">
                <input type="hidden" name="user_id" value="${this._data.user.id}">
                <input type="hidden" name="form_type" value="reimbursement">
                <input type="hidden" name="action" value="${this._data.page === 'edit' ? 'update' : 'create'}">
                ${this._data.page === 'edit' ? '<input type="hidden" name="reimbursement_id" value="' + this._data.reimbursement.id + '">' : '' }
                <button class="btn btn__primary btn__submit btn__submit-send u-mb-2 u-pb-1 u-pt-1">Send</button>
            </form>
        `;
    }
}

export default new FormReimbursementView();