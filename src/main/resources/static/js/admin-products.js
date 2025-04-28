document.addEventListener("DOMContentLoaded", function () {
    const btnCreate = document.getElementById("btn-create");
    const modal = document.getElementById("productModal");
    const closeModalBtn = document.getElementById("closeModalBtn");
    const productForm = document.getElementById("productForm");

    function filterByCategory() {
        const selectedCategory = document.getElementById('categoryFilter').value;
        const rows = document.querySelectorAll("#productTableBody tr");

        rows.forEach(row => {
            const categoryCell = row.querySelector("td:nth-child(5)"); // cột phân loại
            if (selectedCategory === "" || (categoryCell && categoryCell.textContent.trim() === selectedCategory)) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        });
    }

    filterByCategory();

    document.getElementById('categoryFilter').addEventListener("change", filterByCategory);

    btnCreate.addEventListener("click", () => {
        document.getElementById("modalTitle").innerText = "Thêm sản phẩm mới";
        productForm.reset();
        document.getElementById("productId").value = "";
        modal.style.display = "block";
    });

    closeModalBtn.addEventListener("click", () => {
        modal.style.display = "none";
    });

    // Submit form (thêm/sửa)
    productForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const id = document.getElementById("productId").value;
        const method = id ? "PUT" : "POST";
        const url = id ? `/api/products/${id}` : "/api/products";

        fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: document.getElementById("name").value,
                description: document.getElementById("description").value,
                price: document.getElementById("price").value,
                category: document.getElementById("category").value,
                imageUrl: document.getElementById("imageUrl").value
            })
        })
            .then(response => {
                if (response.ok) {
                    alert("Thành công!");
                    location.reload();
                } else {
                    alert("Có lỗi xảy ra!");
                }
            });
    });

    // Edit sản phẩm
    document.querySelectorAll(".btn-edit").forEach(btn => {
        btn.addEventListener("click", function () {
            const id = this.dataset.id;
            fetch(`/api/products/${id}`)
                .then(res => res.json())
                .then(product => {
                    document.getElementById("modalTitle").innerText = "Sửa sản phẩm";
                    document.getElementById("productId").value = product.id;
                    document.getElementById("name").value = product.name;
                    document.getElementById("description").value = product.description;
                    document.getElementById("price").value = product.price;
                    document.getElementById("category").value = product.category;
                    document.getElementById("imageUrl").value = product.imageUrl;
                    modal.style.display = "block";
                });
        });
    });

    // Xóa sản phẩm
    document.querySelectorAll(".btn-delete").forEach(btn => {
        btn.addEventListener("click", function () {
            if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này không?")) {
                const id = this.dataset.id;
                fetch(`/api/products/${id}`, {
                    method: "DELETE"
                })
                    .then(response => {
                        if (response.ok) {
                            alert("Đã xóa thành công!");
                            location.reload();
                        } else {
                            alert("Lỗi khi xóa sản phẩm!");
                        }
                    });
            }
        });
    });
});
