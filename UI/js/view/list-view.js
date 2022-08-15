import View from "./view.js";
import ListType from "./list-type.js";

class ListView extends View {
    _parentElement = document.querySelector('.aside');

    _generateMarkup() {
        return `
            ${ListType.render('reimbursement', false)}
            ${this._filter()}  
            <div class="aside__list">
                <ul class="list">
                    ${this._data.map(item => this._itemMarkup(item))}
                </ul>
            </div>
        `;
    }

    _filter() {
        return `
        <div class="aside__filter">
            <label>Filter by:</label>
            <div class="u-ml-2 aside__filter-content">
                <select class="form__select">
                    <option value>Choose an option</option>
                    <option value="1">Pending</option>
                    <option value="2">Approved</option>
                    <option value="3">Rejected</option>
                </select>
           </div>
        </div>
        `
    }

    _itemMarkup(item) {
        return`
        <li class="list__item" data-reimbursement-id="${item.id}">
            <span class="list__title">${item.title}</span>
            <span class="list__subtitle">${item.statusTitle}</span>
        </li>
        `
    }


}

export default new ListView();