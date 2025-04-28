document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("support-form");
    const responseDiv = document.getElementById("support-response");

    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const formData = new FormData(form);
        console.log("Sending support request:", Object.fromEntries(formData.entries()));

        responseDiv.textContent = "✅ Yêu cầu của bạn đã được gửi. Chúng tôi sẽ phản hồi sớm nhất có thể.";
        form.reset();
    });
});
