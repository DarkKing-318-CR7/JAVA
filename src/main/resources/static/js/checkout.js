document.addEventListener("DOMContentLoaded", function () {
    const checkoutItems = document.getElementById("checkout-items");
    const checkoutTotal = document.getElementById("checkout-total");

    const cart = JSON.parse(localStorage.getItem("cart")) || [];
    let total = 0;

    cart.forEach(item => {
        const subtotal = item.product.price * item.quantity;
        total += subtotal;

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${item.product.name}</td>
            <td>${item.quantity}</td>
            <td>${subtotal.toLocaleString()} đ</td>
        `;
        checkoutItems.appendChild(row);
    });

    checkoutTotal.textContent = `${total.toLocaleString()} đ`;
});
