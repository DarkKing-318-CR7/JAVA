document.addEventListener("DOMContentLoaded", function () {
    const orders = [
        {
            id: "DH001",
            date: "2025-04-15",
            items: "Vợt Pickleball, Bóng, Snack",
            total: "450.000₫",
            status: "Đã giao"
        },
        {
            id: "DH002",
            date: "2025-04-12",
            items: "Nước thể thao, Vớ thể thao",
            total: "200.000₫",
            status: "Đang xử lý"
        }
    ];

    const tbody = document.getElementById("order-history-body");
    orders.forEach(order => {
        const row = `
      <tr>
        <td>${order.id}</td>
        <td>${order.date}</td>
        <td>${order.items}</td>
        <td>${order.total}</td>
        <td>${order.status}</td>
      </tr>
    `;
        tbody.innerHTML += row;
    });
});
