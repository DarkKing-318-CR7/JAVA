document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.btn-delete').forEach(function(button) {
        button.addEventListener('click', function() {
            const bookingId = this.getAttribute('data-id');
            if (confirm('Bạn có chắc chắn muốn xóa đặt sân này?')) {
                fetch(`/admin/bookings/${bookingId}`, {
                    method: 'DELETE'
                })
                    .then(response => {
                        if (response.ok) {
                            alert('Xóa thành công!');
                            location.reload();
                        } else {
                            alert('Xóa thất bại!');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('Đã xảy ra lỗi server!');
                    });
            }
        });
    });
});
