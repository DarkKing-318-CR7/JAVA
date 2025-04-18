document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById("search-user");
    const users = document.querySelectorAll("#user-list tr");

    searchInput.addEventListener("keyup", () => {
        const keyword = searchInput.value.toLowerCase();
        users.forEach(row => {
            const text = row.textContent.toLowerCase();
            row.style.display = text.includes(keyword) ? "" : "none";
        });
    });
});
