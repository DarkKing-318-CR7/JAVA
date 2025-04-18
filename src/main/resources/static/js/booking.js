document.getElementById("booking-form").addEventListener("submit", function (e) {
    e.preventDefault();

    const court = e.target.court.value;
    const date = e.target.date.value;
    const time = e.target.time.value;
    const duration = e.target.duration.value;

    // Đơn giản là in ra console hoặc alert
    alert(`Bạn đã đặt ${court} vào lúc ${time} ngày ${date} trong ${duration} giờ.`);

    // TODO: gọi API backend để lưu thông tin nếu có
});
