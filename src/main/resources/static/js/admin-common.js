/**
 * Hiển thị hộp thoại xác nhận trước khi submit form.
 * @param {string} formId ID của form cần submit.
 * @param {string} message Nội dung thông báo xác nhận.
 * @returns {boolean} Trả về false để ngăn chặn hành động mặc định nếu người dùng hủy.
 */
function confirmAndSubmit(formId, message) {
    if (confirm(message)) {
        const form = document.getElementById(formId);
        if (form) {
            form.submit();
        } else {
            console.error('Form with ID ' + formId + ' not found.');
        }
    }
    return false; // Ngăn chặn hành động mặc định (nếu dùng trong onclick của thẻ a)
}

/**
 * Các hàm xác nhận cụ thể gọi hàm chung confirmAndSubmit.
 */
function confirmDelete(courtId, courtName) {
    const message = `Bạn có chắc chắn muốn xóa sân "${courtName}" (ID: ${courtId}) không? Hành động này không thể hoàn tác.`;
    confirmAndSubmit(`deleteForm-${courtId}`, message);
}

function confirmCancellation(bookingId) {
    const message = 'Bạn có chắc chắn muốn hủy yêu cầu đặt sân này không? Lưu ý các chính sách hủy sân (nếu có).';
    // Giả sử form hủy có ID là cancelForm-bookingId
    confirmAndSubmit(`cancelForm-${bookingId}`, message);
}

function confirmDeleteProduct(productId, productName) {
    const message = `Bạn có chắc chắn muốn xóa sản phẩm "${productName}" (ID: ${productId}) không? Hành động này sẽ xóa vĩnh viễn sản phẩm.`;
    confirmAndSubmit(`deleteProductForm-${productId}`, message);
}

function confirmToggleStatus(userId, currentStatus) {
    const action = currentStatus ? 'khóa' : 'mở khóa';
    const message = `Bạn có chắc muốn ${action} tài khoản này (ID: ${userId})?`;
    // Giả sử form toggle có ID là toggleStatusForm-userId
    confirmAndSubmit(`toggleStatusForm-${userId}`, message);
}


/**
 * Giữ lại các tham số filter trên URL khi click vào link phân trang.
 * @param {string} paginationSelector Selector CSS cho container chứa các link phân trang (ví dụ: '.pagination').
 * @param {string[]} paramsToKeep Mảng các tên tham số cần giữ lại (ví dụ: ['keyword', 'status']).
 */
function preserveFilterParams(paginationSelector = '.pagination .page-link', paramsToKeep = []) {
    document.addEventListener("DOMContentLoaded", function() {
        const paginationLinks = document.querySelectorAll(paginationSelector);
        if (!paginationLinks.length) return; // Thoát nếu không tìm thấy link phân trang

        const urlParams = new URLSearchParams(window.location.search);

        paginationLinks.forEach(link => {
            // Kiểm tra xem thẻ link có href không và không phải là link disabled
            if (link.hasAttribute('href') && !link.closest('.page-item.disabled')) {
                try {
                    const linkUrl = new URL(link.href);
                    paramsToKeep.forEach(param => {
                        if (urlParams.has(param) && urlParams.get(param)) {
                            linkUrl.searchParams.set(param, urlParams.get(param));
                        } else {
                            // Xóa param khỏi link phân trang nếu nó không có trên URL hiện tại
                            // (để tránh giữ lại filter cũ khi người dùng đã reset)
                            linkUrl.searchParams.delete(param);
                        }
                    });
                    // Cập nhật href của link
                    link.href = linkUrl.toString();
                } catch (e) {
                    console.error("Error processing pagination link:", link.href, e);
                    // Bỏ qua link bị lỗi (ví dụ href="javascript:...")
                }
            }
        });
    });
}

// Tự động gọi hàm preserveFilterParams cho các trang admin phổ biến
// Bạn có thể cần điều chỉnh danh sách paramsToKeep cho từng trang cụ thể
// Hoặc gọi hàm này trong script của từng trang nếu cần tùy chỉnh params
// preserveFilterParams('.pagination .page-link', ['keyword', 'status', 'role', 'category', 'stockStatus', 'paymentStatus', 'agentId', 'courtId', 'fromDate', 'toDate', 'size']);