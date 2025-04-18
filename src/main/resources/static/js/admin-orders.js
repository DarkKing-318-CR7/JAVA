document.addEventListener("DOMContentLoaded", () => {
    const filter = document.getElementById("status-filter");
    const orders = document.querySelectorAll("#order-list tr");

    filter.addEventListener("change", () => {
        const value = filter.value;
        orders.forEach(row => {
            const statusCell = row.querySelector(".status");
            if (value === "all" || statusCell.classList.contains(value)) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        });
    });
});
