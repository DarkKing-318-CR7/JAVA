// Lưu ý: File này cần dữ liệu từ server (availableCourts, selectedCourtId, API endpoints).
// Dữ liệu này cần được truyền vào qua data-* attributes hoặc biến JS toàn cục
// được khởi tạo trong thẻ <script> inline của file HTML booking/create.html.

document.addEventListener('DOMContentLoaded', () => {
    const courtSelect = document.getElementById('court');
    const dateInput = document.getElementById('bookingDate');
    const timeSlotsContainer = document.getElementById('timeSlotsContainer');
    const courtInfoPanel = document.getElementById('courtInfoPanel');
    const submitButton = document.getElementById('submitBookingBtn');
    const selectedStartTimeInput = document.getElementById('selectedStartTime');

    // Panels tóm tắt
    const summaryCourt = document.getElementById('summaryCourt');
    const summaryDate = document.getElementById('summaryDate');
    const summaryTime = document.getElementById('summaryTime');
    const summaryTotal = document.getElementById('summaryTotal');

    let selectedCourtData = null; // Lưu thông tin sân đã chọn
    let selectedTimeSlot = null; // Lưu giờ đã chọn

    // Lấy dữ liệu được truyền từ HTML (ví dụ)
    // Cần đảm bảo biến 'pageBookingData' được định nghĩa trong thẻ <script> inline của HTML
    const availableCourts = typeof pageBookingData !== 'undefined' ? pageBookingData.availableCourts : [];
    const apiEndpoints = typeof pageBookingData !== 'undefined' ? pageBookingData.apiEndpoints : { courtInfo: '/api/courts/', availableSlots: '/api/bookings/available-slots' }; // Default endpoints

    function updateBookingSummary() {
        summaryCourt.textContent = selectedCourtData ? selectedCourtData.name : '-';
        summaryDate.textContent = dateInput.value || '-';
        summaryTime.textContent = selectedTimeSlot ? `${selectedTimeSlot} - ${addHour(selectedTimeSlot)}` : '-';

        if (selectedCourtData && selectedTimeSlot) {
            summaryTotal.textContent = formatCurrency(selectedCourtData.pricePerHour);
            submitButton.disabled = false;
        } else {
            summaryTotal.textContent = '0 VNĐ';
            submitButton.disabled = true;
        }
        selectedStartTimeInput.value = selectedTimeSlot || '';
    }

    function addHour(time) {
        if (!time) return '';
        try {
            const [hour, minute] = time.split(':').map(Number);
            const nextHour = (hour + 1) % 24;
            return `${String(nextHour).padStart(2, '0')}:${String(minute).padStart(2, '0')}`;
        } catch (e) {
            console.error("Error adding hour to time:", time, e);
            return '';
        }
    }

    function formatCurrency(amount) {
        if (amount === null || typeof amount === 'undefined') return '0 VNĐ';
        try {
            return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount).replace(/\s?₫/g, ' VNĐ'); // Chuẩn hóa VNĐ
        } catch(e) {
            console.error("Error formatting currency:", amount, e);
            return amount + ' VNĐ'; // Fallback
        }
    }

    async function fetchCourtInfo(courtId) {
        if (!courtId) {
            courtInfoPanel.innerHTML = '<p class="text-muted fst-italic">Vui lòng chọn một sân từ danh sách.</p>';
            selectedCourtData = null;
            updateBookingSummary();
            return;
        }

        courtInfoPanel.innerHTML = '<div class="spinner-border spinner-border-sm" role="status"><span class="visually-hidden">Loading...</span></div>';

        try {
            // Ưu tiên lấy từ dữ liệu đã có sẵn (availableCourts) nếu có
            const existingCourt = availableCourts.find(c => c.id == courtId);
            if (existingCourt) {
                selectedCourtData = existingCourt;
            } else {
                // Nếu không có sẵn, gọi API (cần endpoint đúng)
                console.warn("Fetching court info from API - implement actual API call if needed");
                // const response = await fetch(`${apiEndpoints.courtInfo}${courtId}`);
                // if (!response.ok) throw new Error('Court not found or API error');
                // selectedCourtData = await response.json();

                // *** Giả lập nếu không có API ***
                selectedCourtData = { id: courtId, name: `Sân ${courtId}`, location: "Khu A", pricePerHour: 100000, imageUrl: null, type: "Trong nhà" };
                // *** Kết thúc Giả lập ***
            }

            if (!selectedCourtData) throw new Error('Court data is null');

            courtInfoPanel.innerHTML = `
                <h5>${selectedCourtData.name || 'N/A'}</h5>
                <p class="mb-1"><strong>Địa điểm:</strong> ${selectedCourtData.location || 'N/A'}</p>
                <p class="mb-1"><strong>Loại:</strong> ${selectedCourtData.type || 'N/A'}</p>
                <p class="mb-1"><strong>Giá:</strong> ${formatCurrency(selectedCourtData.pricePerHour)} / giờ</p>
                <img src="${selectedCourtData.imageUrl || 'https://via.placeholder.com/300x200?text=Court'}" class="img-fluid rounded mt-2" alt="${selectedCourtData.name || 'Court'}">
            `;
            updateBookingSummary();
        } catch (error) {
            console.error("Error fetching court info:", error);
            courtInfoPanel.innerHTML = '<p class="text-danger">Lỗi khi tải thông tin sân.</p>';
            selectedCourtData = null;
            updateBookingSummary();
        }
    }

    async function fetchAvailableTimeSlots() {
        const courtId = courtSelect.value;
        const selectedDate = dateInput.value;
        selectedTimeSlot = null; // Reset giờ đã chọn
        selectedStartTimeInput.value = ''; // Reset input ẩn

        if (!courtId || !selectedDate) {
            timeSlotsContainer.innerHTML = '<p class="text-muted fst-italic">Vui lòng chọn sân và ngày để xem các khung giờ trống.</p>';
            updateBookingSummary();
            return;
        }

        timeSlotsContainer.innerHTML = '<div class="spinner-border spinner-border-sm" role="status"><span class="visually-hidden">Loading...</span></div> Đang tải lịch trống...';

        try {
            // *** THAY THẾ BẰNG API THẬT SỰ ***
            const response = await fetch(`${apiEndpoints.availableSlots}?courtId=${courtId}&date=${selectedDate}`);
            if (!response.ok) {
                const errorData = await response.json().catch(() => ({ message: 'Failed to fetch slots' }));
                throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
            }
            const availableSlots = await response.json(); // Ví dụ: ['08:00', '09:00', '14:00']

            // **** BỎ PHẦN GIẢ LẬP DƯỚI ĐÂY KHI CÓ API THẬT ****
            // await new Promise(resolve => setTimeout(resolve, 500));
            // const allSlots = ['06:00','07:00','08:00','09:00','10:00','11:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00'];
            // const availableSlots = allSlots.filter(() => Math.random() > 0.4);
            // **** KẾT THÚC GIẢ LẬP ****


            timeSlotsContainer.innerHTML = ''; // Xóa loading/nội dung cũ
            if (!Array.isArray(availableSlots) || availableSlots.length === 0) {
                timeSlotsContainer.innerHTML = '<p class="text-warning">Không có khung giờ nào trống cho ngày này.</p>';
            } else {
                availableSlots.forEach((slot, index) => {
                    const div = document.createElement('div');
                    div.className = 'form-check form-check-inline mb-2';
                    const inputId = `slot-${index}`;
                    div.innerHTML = `
                        <input class="form-check-input time-slot-radio" type="radio" id="${inputId}" value="${slot}" name="timeSlotRadio">
                        <label class="form-check-label" for="${inputId}">${slot} - ${addHour(slot)}</label>
                    `;
                    timeSlotsContainer.appendChild(div);
                });

                // Add event listener cho các radio button mới tạo
                document.querySelectorAll('.time-slot-radio').forEach(radio => {
                    radio.addEventListener('change', function() {
                        if (this.checked) {
                            selectedTimeSlot = this.value;
                            updateBookingSummary();
                        }
                    });
                });
            }
        } catch (error) {
            console.error("Error fetching time slots:", error);
            timeSlotsContainer.innerHTML = `<p class="text-danger">Lỗi khi tải khung giờ trống: ${error.message || 'Vui lòng thử lại.'}</p>`;
        }
        updateBookingSummary(); // Cập nhật lại summary sau khi fetch
    }

    // --- Event Listeners ---
    if (courtSelect) {
        courtSelect.addEventListener('change', function() {
            fetchCourtInfo(this.value);
            fetchAvailableTimeSlots();
        });
    }

    if (dateInput) {
        dateInput.addEventListener('change', fetchAvailableTimeSlots);
        // Set min date to today
        const today = new Date().toISOString().split('T')[0];
        dateInput.setAttribute('min', today);
    }

    // --- Initial Load ---
    // Load thông tin sân nếu đã được chọn sẵn
    if (courtSelect && courtSelect.value) {
        fetchCourtInfo(courtSelect.value);
    }
    // Load giờ trống nếu ngày và sân đã có giá trị
    if (dateInput && dateInput.value && courtSelect && courtSelect.value) {
        fetchAvailableTimeSlots();
    } else {
        updateBookingSummary(); // Chạy lần đầu để disable nút submit nếu chưa có đủ thông tin
    }

});