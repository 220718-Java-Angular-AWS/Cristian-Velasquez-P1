import View from "./view.js";

class ListType extends View {
    _parentElement = document.querySelector('.aside');

    _generateMarkup() {
        return `
        <div class="aside__list-type">
            <div class="aside__list-type-items ${this._data === 'reimbursement' ? 'aside__list-type-items--active' : ''}">Reimbursements</div>
            <div class="aside__list-type-items ">Users</div>
        </div>
        `
    }
}

export default new ListType();