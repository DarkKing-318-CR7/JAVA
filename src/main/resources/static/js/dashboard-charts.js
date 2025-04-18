// Lưu ý: File này cần dữ liệu từ server (revenueLabels, revenueData, etc.).
// Dữ liệu này cần được truyền vào qua biến JS toàn cục 'pageChartData'
// được khởi tạo trong thẻ <script> inline của file HTML admin/dashboard.html.

document.addEventListener('DOMContentLoaded', () => {
    // Kiểm tra xem Chart.js đã được tải chưa
    if (typeof Chart === 'undefined') {
        console.error('Chart.js is not loaded. Make sure to include it.');
        return;
    }

    // Lấy dữ liệu từ biến toàn cục (nếu tồn tại)
    const chartData = typeof pageChartData !== 'undefined' ? pageChartData : null;

    if (!chartData) {
        console.warn('Chart data (pageChartData) not found. Charts will not be rendered.');
        return;
    }

    // Biểu đồ Doanh thu (Line chart)
    const ctxRevenue = document.getElementById('revenueChart');
    if (ctxRevenue && chartData.revenueLabels && chartData.revenueData) {
        try {
            new Chart(ctxRevenue, {
                type: 'line',
                data: {
                    labels: chartData.revenueLabels,
                    datasets: [{
                        label: 'Doanh thu (VNĐ)',
                        data: chartData.revenueData,
                        borderColor: 'rgb(75, 192, 192)',
                        backgroundColor: 'rgba(75, 192, 192, 0.1)', // Add a light fill
                        tension: 0.1,
                        fill: true // Fill area below line
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            ticks: {
                                callback: function(value) {
                                    // Check if value is a number before formatting
                                    if (typeof value === 'number') {
                                        return new Intl.NumberFormat('vi-VN').format(value);
                                    }
                                    return value;
                                }
                            }
                        }
                    },
                    responsive: true,
                    maintainAspectRatio: false
                }
            });
        } catch (e) {
            console.error("Error rendering revenue chart:", e);
        }
    } else {
        console.warn("Revenue chart canvas or data not found.");
    }


    // Biểu đồ Loại sân (Pie chart)
    const ctxCourtType = document.getElementById('courtTypeChart');
    if (ctxCourtType && chartData.courtTypeLabels && chartData.courtTypeData) {
        try {
            new Chart(ctxCourtType, {
                type: 'pie',
                data: {
                    labels: chartData.courtTypeLabels,
                    datasets: [{
                        label: 'Số lượt đặt',
                        data: chartData.courtTypeData,
                        backgroundColor: [ // Use a color palette
                            'rgba(255, 99, 132, 0.8)',
                            'rgba(54, 162, 235, 0.8)',
                            'rgba(255, 206, 86, 0.8)',
                            'rgba(75, 192, 192, 0.8)',
                            'rgba(153, 102, 255, 0.8)',
                            'rgba(255, 159, 64, 0.8)'
                        ],
                        borderColor: [ // Add borders for better separation
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1, // Optional border width
                        hoverOffset: 4
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: { // Optional: Configure legend, tooltips
                        legend: {
                            position: 'top', // Or 'bottom', 'left', 'right'
                        },
                        tooltip: {
                            callbacks: { // Format tooltip
                                label: function(context) {
                                    let label = context.label || '';
                                    if (label) {
                                        label += ': ';
                                    }
                                    if (context.parsed !== null) {
                                        // You might want to calculate percentage here if data is raw count
                                        label += context.formattedValue;
                                    }
                                    return label;
                                }
                            }
                        }
                    }
                }
            });
        } catch(e) {
            console.error("Error rendering court type chart:", e);
        }
    } else {
        console.warn("Court type chart canvas or data not found.");
    }

});