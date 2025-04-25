document.addEventListener("DOMContentLoaded", function () {
    const checkoutItems = document.getElementById("checkout-items");
    const checkoutTotal = document.getElementById("checkout-total");
    const qrModal = document.getElementById("qr-modal");
    const qrImg = document.getElementById("qr-img");
    const form = document.querySelector("form.checkout-form");
    const confirmBtn = document.getElementById("confirm-paid-btn");

    // Hiển thị giỏ hàng từ localStorage
    if (checkoutItems && checkoutTotal) {
        const cart = JSON.parse(localStorage.getItem("cart")) || [];
        let total = 0;

        cart.forEach(item => {
            const subtotal = item.price * item.quantity;
            total += subtotal;

            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${item.name}</td>
                <td>${item.quantity}</td>
                <td>${subtotal.toLocaleString()} đ</td>
            `;
            checkoutItems.appendChild(row);
        });

        checkoutTotal.textContent = `${total.toLocaleString()} đ`;
    }

    // Ghi nhớ phương thức thanh toán
    const paymentOptions = document.querySelectorAll("input[name='paymentMethod']");
    paymentOptions.forEach(option => {
        option.addEventListener("change", () => {
            localStorage.setItem("paymentMethod", option.value);
        });
    });

    // Tự động chọn lại phương thức đã lưu
    const savedMethod = localStorage.getItem("paymentMethod");
    if (savedMethod) {
        const radio = document.querySelector(`input[name='paymentMethod'][value='${savedMethod}']`);
        if (radio) radio.checked = true;
    }

    // Kiểm tra phương thức trước khi submit
    if (form) {
        form.addEventListener("submit", function (e) {
            const selected = document.querySelector("input[name='paymentMethod']:checked")?.value;

            if (selected === "bank" || selected === "momo") {
                e.preventDefault(); // Ngăn submit
                if (qrModal && qrImg) {
                    qrImg.src = selected === "bank" ? "/images/qr-bank.png" : "/images/qr-momo.png";
                    qrModal.style.display = "block";
                }
            }
        });
    }

    // Xác nhận đã thanh toán -> Submit form
    if (confirmBtn && form) {
        confirmBtn.addEventListener("click", () => {
            qrModal.style.display = "none";
            form.submit();
        });
    }
});
