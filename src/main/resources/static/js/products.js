let filtered = productCards;

// Lọc theo category
if (category !== "all") {
    filtered = filtered.filter(card => card.dataset.category === category);
}

// Sắp xếp theo giá
if (sort === "price-asc") {
    filtered.sort((a, b) => parseInt(a.dataset.price) - parseInt(b.dataset.price));
} else if (sort === "price-desc") {
    filtered.sort((a, b) => parseInt(b.dataset.price) - parseInt(a.dataset.price));
}

// Cập nhật giao diện
productGrid.innerHTML = "";
filtered.forEach(card => productGrid.appendChild(card));
