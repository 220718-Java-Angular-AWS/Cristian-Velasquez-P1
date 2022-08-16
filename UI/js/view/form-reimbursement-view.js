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
        console.log(this._data);
        return `
            <form action="" id="content__form-reimbursement" class="content__form form content__form-reimbursement">
                <h2>Create Reimbursement</h2>
                <input class="form__input" type="text" name="title" placeholder="Title">
                <textarea class="form__textarea" name="description" placeholder="Description"></textarea>
                <input type="hidden" name="status_id" value="1">
                <input type="hidden" name="user_id" value="${this._data.id}">
                <input type="hidden" name="form_type" value="reimbursement">
                <button class="btn btn__primary btn__submit btn__submit-send u-mb-2 u-pb-1 u-pt-1">Send</button>
            </form>
        `;
    }
}

export default new FormReimbursementView();