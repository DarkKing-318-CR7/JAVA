document.addEventListener("DOMContentLoaded", () => {
    const historyData = [
        { id: 1, court: "Sân 1 - Trung tâm", date: "2025-04-20", time: "10:00", duration: 2, status: "Đã xác nhận" },
        { id: 2, court: "Sân 2 - Quận 7", date: "2025-04-15", time: "14:00", duration: 1, status: "Hoàn thành" },
        { id: 3, court: "Sân 3 - Bình Thạnh", date: "2025-04-10", time: "18:00", duration: 2, status: "Đã hủy" }
    ];

    const tbody = document.getElementById("booking-history-body");
    historyData.forEach((booking, index) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
      <td>${index + 1}</td>
      <td>${booking.court}</td>
      <td>${booking.date}</td>
      <td>${booking.time}</td>
      <td>${booking.duration} giờ</td>
      <td>${booking.status}</td>
    `;
        tbody.appendChild(tr);
    });
});
