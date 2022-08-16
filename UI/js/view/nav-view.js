import View from "./view.js";

class NavView extends View {
    _parentElement = document.querySelector('.header__nav');

    addHandleClick(handle) {
        this._parentElement.addEventListener('click', function (e) {
            e.preventDefault();

            const btn = e.target.closest('.btn__nav');

            if (!btn) return;

            const goTo = btn.dataset.page;

            handle(goTo);

        });
    }

    _generateMarkup() {
        return `
            <!--<button class="btn btn__secondary btn__nav ${this._data === 'login' ? 'btn--active' : '' }" data-page="login">Log In</button>
            <button class="btn btn__secondary btn__nav ${this._data === 'singup' ? 'btn--active' : '' }" data-page="singup">Sing Up</button>-->
        `;
    }

}

export default new NavView();