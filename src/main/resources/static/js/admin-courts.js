document.addEventListener('DOMContentLoaded', function() {
    const openModalBtn = document.getElementById('openModalBtn');
    const closeModalBtn = document.getElementById('closeModalBtn');
    const courtModal = document.getElementById('courtModal');
    const courtForm = document.getElementById('courtForm');

    openModalBtn.addEventListener('click', () => {
        courtForm.reset();
        courtModal.style.display = 'block';
    });

    closeModalBtn.addEventListener('click', () => {
        courtModal.style.display = 'none';
    });

    window.addEventListener('click', (e) => {
        if (e.target === courtModal) {
            courtModal.style.display = 'none';
        }
    });

    courtForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = {
            id: document.getElementById('courtId').value || null,
            name: document.getElementById('name').value,
            location: document.getElementById('location').value,
            type: document.getElementById('type').value,
            pricePerHour: document.getElementById('pricePerHour').value,
            description: document.getElementById('description').value,
            imageUrl: document.getElementById('imageUrl').value,
            active: document.getElementById('active').checked
        };

        fetch('/admin/courts', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Thêm sân thành công!');
                    courtModal.style.display = 'none';
                    location.reload();
                } else {
                    alert('Thêm sân thất bại!');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Lỗi server khi thêm sân!');
            });
    });

    // BẮT SỰ KIỆN click nút XÓA
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', function() {
            const courtId = this.getAttribute('data-id');
            if (confirm('Bạn có chắc muốn xóa sân này?')) {
                fetch(`/admin/courts/${courtId}`, {
                    method: 'DELETE'
                })
                    .then(response => {
                        if (response.ok) {
                            alert('Xóa sân thành công!');
                            location.reload();
                        } else {
                            alert('Xóa sân thất bại!');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('Lỗi server khi xóa sân!');
                    });
            }
        });
    });

    // BẮT SỰ KIỆN click nút SỬA
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', function() {
            const courtId = this.getAttribute('data-id');
            // Gọi API lấy chi tiết sân
            fetch(`/admin/courts/${courtId}`)
                .then(response => response.json())
                .then(court => {
                    document.getElementById('courtId').value = court.id;
                    document.getElementById('name').value = court.name;
                    document.getElementById('location').value = court.location;
                    document.getElementById('type').value = court.type;
                    document.getElementById('pricePerHour').value = court.pricePerHour;
                    document.getElementById('description').value = court.description;
                    document.getElementById('active').checked = court.active;
                    courtModal.style.display = 'block';
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Lỗi khi tải thông tin sân!');
                });
        });
    });

});
