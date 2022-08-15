import View from "./view.js";

class NewReimbursementView extends View {
    _parentElement = document.querySelector('.content__section');

    _generateMarkup() {
        return `
            <form action="" class="content__form form content__form-reimbursement">
                <h2>Create Reimbursement</h2>
                <input class="form__input" type="text" name="title" placeholder="Title">
                <textarea class="form__textarea" name="description" placeholder="Description"></textarea>
                <input type="hidden" name="form_type" value="reimbursement">
                <button class="btn btn__primary btn__submit u-mb-2 u-pb-1 u-pt-1">Send</button>
            </form>
        `;
    }
}

export default new NewReimbursementView();