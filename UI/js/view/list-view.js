import View from "./view.js";
import ListType from "./list-type.js";

class ListView extends View {
    _parentElement = document.querySelector('.aside');

    addHandleClick(handler) {
        this._parentElement.addEventListener('click', function (e) {
            const btn = e.target.closest('.list__item-reimbursement');

            if (!btn) return;
            const id = btn.dataset.reimbursementId;

            handler(id);
        })
    }

    addHandleSelect(handler) {
        this._parentElement.addEventListener("click", function (e) {
            e.preventDefault();
            const btn = e.target.closest('.option');

            if (!btn) return;

            const statusId = btn.dataset.value;
            handler(statusId);
        })
    }

    addHandleListType(handler) {
        this._parentElement.addEventListener("click", function (e) {
            e.preventDefault();
            const btn = e.target.closest('.aside__list-type-items');

            if (!btn) return;

            const listType = btn.dataset.value;

            handler(listType);
        })
    }

    _generateMarkup() {
        return `
            ${this._data.user.userTypeID === 1 ? ListType.render('reimbursement', false) : ''}
            ${this._data.user.userTypeID === 1 ? this._filter() : ''}
            <div class="aside__list">
                <ul class="list">
                    ${this._data.reimbursementList.map(item => this._itemMarkup(item)).join('')}
                </ul>
            </div>
        `;
    }

    _filter() {
        return `
        <div class="aside__filter">
            <label>Filter by:</label>
            <div class="u-ml-2 aside__filter-content">
                <span data-value="0" class="option ${this._data.filter == 0 ? 'option--active' : ''}">All</span>
                    ${this._data.reimbursementStatusList.map(item => `<span data-value="${item.statusId}" class="option ${this._data.filter == item.statusId ? 'option--active' : ''} ">${item.statusTitle}</span>`).join('')}
                
           </div>
        </div>
        `
    }

    _itemMarkup(item) {
        return`
        <li class="list__item list__item-reimbursement" data-reimbursement-id="${item.id}">
            <span class="list__title">${item.title}</span>
            <span class="list__subtitle ${item.statusTitle.toLowerCase()}">${item.statusTitle}</span>
        </li>
        `
    }


}

export default new ListView();