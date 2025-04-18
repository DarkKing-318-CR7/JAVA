// js/main.js
document.addEventListener("DOMContentLoaded", function () {
    fetch("/html/layout/header.html")
        .then(res => res.text())
        .then(data => document.getElementById("header").innerHTML = data);

    fetch("/html/layout/left-menu.html")
        .then(res => res.text())
        .then(data => document.getElementById("left-menu").innerHTML = data);

    fetch("/html/layout/footer.html")
        .then(res => res.text())
        .then(data => document.getElementById("footer").innerHTML = data);
});
