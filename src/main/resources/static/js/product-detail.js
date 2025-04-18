document.addEventListener("DOMContentLoaded", function () {
    const addToCartBtn = document.getElementById("add-to-cart-btn");
    const quantityInput = document.getElementById("quantity");

    addToCartBtn.addEventListener("click", () => {
        const quantity = parseInt(quantityInput.value);
        alert(`Đã thêm ${quantity} sản phẩm vào giỏ hàng!`);
        // Tại đây bạn có thể thêm logic để lưu vào localStorage hoặc gửi request lên server
    });
});
