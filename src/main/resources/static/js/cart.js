// js/cart.js
document.addEventListener("DOMContentLoaded", function () {
    const cartItems = JSON.parse(localStorage.getItem("cart") || "[]");
    const cartTableBody = document.getElementById("cart-items");
    const cartTotal = document.getElementById("cart-total");

    function renderCart() {
        cartTableBody.innerHTML = "";
        let total = 0;

        cartItems.forEach((item, index) => {
            const itemTotal = item.price * item.quantity;
            total += itemTotal;

            const row = document.createElement("tr");
            row.innerHTML = `
        <td>${item.name}</td>
        <td>${item.price.toLocaleString()}đ</td>
        <td>
          <input type="number" min="1" value="${item.quantity}" data-index="${index}" class="qty-input" />
        </td>
        <td>${itemTotal.toLocaleString()}đ</td>
        <td><button class="delete-btn" data-index="${index}">🗑️</button></td>
      `;
            cartTableBody.appendChild(row);
        });

        cartTotal.textContent = total.toLocaleString() + "đ";
    }

    // Sự kiện cập nhật số lượng
    cartTableBody.addEventListener("input", function (e) {
        if (e.target.classList.contains("qty-input")) {
            const index = e.target.dataset.index;
            cartItems[index].quantity = parseInt(e.target.value);
            localStorage.setItem("cart", JSON.stringify(cartItems));
            renderCart();
        }
    });

    // Sự kiện xóa sản phẩm
    cartTableBody.addEventListener("click", function (e) {
        if (e.target.classList.contains("delete-btn")) {
            const index = e.target.dataset.index;
            cartItems.splice(index, 1);
            localStorage.setItem("cart", JSON.stringify(cartItems));
            renderCart();
        }
    });

    renderCart();
});
