document.getElementById("login-form").addEventListener("submit", function(e) {
    e.preventDefault();
    const username = e.target.username.value;
    const password = e.target.password.value;
    alert(`Đăng nhập với tài khoản: ${username}`);
    // Thêm xử lý gọi API thật ở đây nếu cần
});
