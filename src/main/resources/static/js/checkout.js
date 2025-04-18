document.addEventListener('DOMContentLoaded', () => {
    const paymentMethodRadios = document.querySelectorAll('input[name="paymentMethod"]');
    const bankInfoDiv = document.getElementById('bankInfo');

    function toggleBankInfo() {
        if (!bankInfoDiv) return;
        const selectedMethod = document.querySelector('input[name="paymentMethod"]:checked');
        if (selectedMethod && selectedMethod.value === 'BANK_TRANSFER') {
            bankInfoDiv.style.display = 'block';
        } else {
            bankInfoDiv.style.display = 'none';
        }
    }

    if (paymentMethodRadios.length > 0) {
        paymentMethodRadios.forEach((radio) => {
            radio.addEventListener("change", toggleBankInfo);
        });

        // Initial check when page loads
        toggleBankInfo();
    }
});