
export default class View {
    _data;
    _parentElement;

    render(data, render = true) {
        this._data = data;

        if(!render) return this._generateMarkup();

        this._clear();
        this._parentElement.insertAdjacentHTML('beforeend', this._generateMarkup());
    }

    _clear() {
        this._parentElement.innerHTML = '';
    }
}