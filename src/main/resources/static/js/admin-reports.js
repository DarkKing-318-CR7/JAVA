document.addEventListener("DOMContentLoaded", () => {
    const generateBtn = document.getElementById("generate-report");

    generateBtn.addEventListener("click", () => {
        const from = document.getElementById("from-date").value;
        const to = document.getElementById("to-date").value;

        if (!from || !to) {
            alert("Vui lòng chọn khoảng thời gian!");
            return;
        }

        // Demo data — có thể thay bằng dữ liệu thực từ API
        document.getElementById("total-orders").textContent = "128";
        document.getElementById("total-revenue").textContent = "12.500.000₫";
        document.getElementById("new-users").textContent = "23";

        const ctx = document.getElementById("orderChart").getContext("2d");
        new Chart(ctx, {
            type: "line",
            data: {
                labels: ["01", "02", "03", "04", "05", "06", "07"],
                datasets: [{
                    label: "Đơn hàng",
                    data: [12, 15, 10, 18, 25, 22, 30],
                    borderColor: "#007bff",
                    backgroundColor: "rgba(0,123,255,0.2)",
                    fill: true,
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    });
});
