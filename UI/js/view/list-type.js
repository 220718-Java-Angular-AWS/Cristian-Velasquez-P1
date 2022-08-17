import View from "./view.js";

class ListType extends View {
    _parentElement = document.querySelector('.aside');

    _generateMarkup() {
        return `
        <div class="aside__list-type">
            <div class="aside__list-type-items ${this._data === 'reimbursement' ? 'aside__list-type-items--active' : ''}" data-value="reimbursement">Reimbursements</div>
            <div class="aside__list-type-items ${this._data === 'user' ? 'aside__list-type-items--active' : ''} " data-value="user">Users</div>
        </div>
        `
    }
}

export default new ListType();