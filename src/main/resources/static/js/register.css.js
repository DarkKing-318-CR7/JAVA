document.getElementById("register-form").addEventListener("submit", function(e) {
    e.preventDefault();
    const username = e.target.username.value;
    const email = e.target.email.value;
    const password = e.target.password.value;
    const confirmPassword = e.target.confirmPassword.value;

    if (password !== confirmPassword) {
        alert("Mật khẩu không khớp!");
        return;
    }

    alert(`Đăng ký tài khoản thành công cho: ${username} (${email})`);
    // Gọi API thật ở đây nếu cần
});
