function updateCourtInfo() {
    const select = document.getElementById('court');
    const selectedOption = select.options[select.selectedIndex];

    if (selectedOption && selectedOption.value !== "") {
        const imageUrl = selectedOption.getAttribute('data-image');
        const location = selectedOption.getAttribute('data-location');
        const description = selectedOption.getAttribute('data-description');
        const price = selectedOption.getAttribute('data-price');

        // Update thông tin sân
        document.getElementById('court-image').src = imageUrl;
        document.getElementById('court-location').innerText = location;
        document.getElementById('court-description').innerText = description;
        document.getElementById('court-price').innerText = Number(price).toLocaleString('vi-VN');

        // Hiện khối thông tin
        document.getElementById('court-info').style.display = 'block';
    } else {
        // Nếu chọn "-- Chọn sân --" thì ẩn khối
        document.getElementById('court-info').style.display = 'none';
    }
}
