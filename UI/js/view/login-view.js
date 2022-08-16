import View from "./view.js";

class LoginView extends View {
    _parentElement = document.querySelector('.content__section');

    addHandleClick(handle) {
        this._parentElement.addEventListener('click', function (e) {
            e.preventDefault();

            const btn = e.target.closest('.btn__submit-log');
            if (!btn) return;

            const form = e.target.closest('.content__form-login');

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
        <div class="login-form">
            ${this._data.goTo === 'login' ? this._formLoginMarkup() : this._formSingupMarkup() }
        </div>
        `;
    }

    _formLoginMarkup() {
        return `
        <form action="" class="content__form form content__form-login" id="content__form-login">
            <input class="form__input form__input-email" type="text" name="email" placeholder="Email">
            <input class="form__input form__input-password" type="password" name="password" placeholder="Password">
            <input class="form__input-form_type" type="hidden" name="form_type" value="login">
            <button class="btn btn__primary btn__submit btn__submit-log u-mb-2 u-pb-1 u-pt-1">Sing in</button>
        </form>
        ${this._data.err === true ? '<div class="form__err">The email and password you entered don\'t match.</div>' : ''}
        `;
    }

    _formSingupMarkup() {
        return`
            <form action="" class="content__form form content__form-login">
                <input class="form__input" type="text" name="first_name" placeholder="First Name">
                <input class="form__input" type="text" name="last_name" placeholder="Last Name">
                <input class="form__input" type="text" name="email" placeholder="Email">
                <input class="form__input" type="password" name="password" placeholder="Password">
                <input type="hidden" name="form_type" value="singup">
                <button class="btn btn__primary btn__submit u-mb-2 u-pb-1 u-pt-1">Sing in</button>
            </form>
            ${this._data.err === true ? '<div class="form__err">All fields are required</div>' : ''}
        `;
    }

    _getDataForm() {

    }
}

export default new LoginView();