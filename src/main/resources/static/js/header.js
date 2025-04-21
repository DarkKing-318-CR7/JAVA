document.getElementById("header").innerHTML = `
  <header class="header">
    <div class="logo">🏓 Pickleball Booking</div>
    <nav class="nav">
     <li><a th:href="@{/}">Trang chủ</a></li>
            <li><a th:href="@{/bookings}">Đặt sân</a></li>
            <li><a th:href="@{/shop}">Cửa hàng</a></li>
            <li><a th:href="@{/support}">Hỗ trợ</a></li>
            <li><a th:href="@{/profile}">Tài khoản</a></li>
            <li><a th:href="@{/cart}">Giỏ hàng</a></li>
    </nav>
  </header>
`;
