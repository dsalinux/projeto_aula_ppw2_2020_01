

function expandirMenu(event) {
    var element = event.srcElement;
    element.classList.toggle("submenu-aberto");
    event.preventDefault();
}

function abrirMenu(event) {
    event.preventDefault();
    if(isMobile()){
        document.querySelector('body').classList.toggle('menu-mobile-open');
    } else {
        document.querySelector('body').classList.toggle('menu-open');
    }
}
function isMobile() {
    return window.innerWidth <= 991;
}

document.querySelectorAll('.menu-dropdown').forEach(item => item.addEventListener('click', event => expandirMenu(event)));

document.querySelector('header .menu-toggle').addEventListener('click', event => abrirMenu(event));
document.querySelector('.overlay-menu').addEventListener('click', event => abrirMenu(event));