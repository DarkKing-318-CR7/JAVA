document.addEventListener('DOMContentLoaded', function() {
    const slots = document.querySelectorAll('.slot');
    const bookingDetails = document.querySelector('.booking-details');
    const nameInput = document.querySelector('.booking-details input[type="text"]');
    const phoneInput = document.querySelector('.booking-details input[type="tel"]');
    const bookButton = document.querySelector('.booking-details .cta');

    let selectedSlot = null;

    slots.forEach(slot => {
        slot.addEventListener('click', function() {
            if (this.classList.contains('booked')) {
                return; // Không cho phép chọn sân đã đặt
            }

            slots.forEach(s => s.classList.remove('selected')); // Bỏ chọn tất cả các sân khác
            this.classList.add('selected'); // Chọn sân hiện tại
            selectedSlot = this;

            // Hiển thị thông tin sân đã chọn (ví dụ: tên sân)
            bookingDetails.style.display = 'block';
        });
    });

    bookButton.addEventListener('click', function() {
        if (!selectedSlot) {
            alert('Vui lòng chọn sân!');
            return;
        }

        const name = nameInput.value;
        const phone = phoneInput.value;

        if (!name || !phone) {
            alert('Vui lòng nhập đầy đủ thông tin!');
            return;
        }

        // Xử lý đặt sân (gửi dữ liệu lên server, thanh toán, v.v.)
        alert(`Đặt sân ${selectedSlot.textContent} thành công cho ${name} (${phone})!`);

        // Reset form
        selectedSlot.classList.remove('selected');
        selectedSlot = null;
        nameInput.value = '';
        phoneInput.value = '';
        bookingDetails.style.display = 'none';
    });
});