import View from "./view.js";
import ListType from "./list-type.js";

class ListUsersView extends View {
    _parentElement = document.querySelector('.aside');

    addHandleClick(handler) {
        this._parentElement.addEventListener('click', function (e) {
            const btn = e.target.closest('.list__item-user');

            if (!btn) return;
            const id = btn.dataset.userId;

            handler(id);
        })
    }

    _generateMarkup() {
        return `
            ${this._data.user.userTypeID === 1 ? ListType.render('user', false) : ''}
            <div class="aside__list">
                <ul class="list">
                    ${this._data.userList.map(item => this._itemMarkup(item)).join('')}
                </ul>
            </div>
        `;
    }

    _itemMarkup(item) {
        return`
        <li class="list__item list__item-user" data-user-id="${item.id}">
            <span class="list__title">${item.firstName} ${item.lastName}</span>
            <span class="list__subtitle">${item.userType}</span>
        </li>
        `
    }
}

export default new ListUsersView();