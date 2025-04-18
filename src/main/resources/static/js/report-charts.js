// Lưu ý: File này cần dữ liệu từ server (reportChartData).
// Dữ liệu này cần được truyền vào qua biến JS toàn cục 'pageReportChartData'
// được khởi tạo trong thẻ <script> inline của file HTML admin/reports.html.

document.addEventListener('DOMContentLoaded', () => {
    if (typeof Chart === 'undefined') {
        console.error('Chart.js is not loaded.');
        return;
    }

    const reportChartData = typeof pageReportChartData !== 'undefined' ? pageReportChartData : null;

    if (!reportChartData || !reportChartData.type) {
        console.warn('Report chart data (pageReportChartData) or report type not found. No chart will be rendered.');
        return;
    }

    // --- Render Chart Based on Report Type ---

    // Example: Court Usage Bar Chart
    if (reportChartData.type === 'court_usage') {
        const courtUsageCtx = document.getElementById('courtUsageChart');
        if (courtUsageCtx && reportChartData.labels && reportChartData.data) {
            try {
                new Chart(courtUsageCtx, {
                    type: 'bar',
                    data: {
                        labels: reportChartData.labels, // Tên sân
                        datasets: [{
                            label: 'Số lượt đặt',
                            data: reportChartData.data, // Số lượt
                            backgroundColor: 'rgba(54, 162, 235, 0.6)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: { x: { beginAtZero: true } }, // Start count from 0
                        indexAxis: 'y', // Horizontal bar chart makes names easier to read
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: { display: false } // Hide legend for single dataset bar chart
                        }
                    }
                });
            } catch(e) {
                console.error("Error rendering court usage chart:", e);
            }
        } else {
            console.warn("Court usage chart canvas or data not found/incomplete.");
        }
    }

    // Example: Revenue Trend Line Chart (if needed on reports page)
    else if (reportChartData.type === 'revenue_trend') {
        const revenueTrendCtx = document.getElementById('revenueTrendChart'); // Assume canvas ID
        if (revenueTrendCtx && reportChartData.labels && reportChartData.data) {
            try {
                new Chart(revenueTrendCtx, {
                    type: 'line',
                    data: {
                        labels: reportChartData.labels, // Dates/Months
                        datasets: [{
                            label: 'Tổng Doanh Thu (VNĐ)',
                            data: reportChartData.data, // Revenue amounts
                            borderColor: 'rgb(75, 192, 192)',
                            tension: 0.1,
                            fill: false
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true,
                                ticks: {
                                    callback: function(value) {
                                        if(typeof value === 'number') {
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
            } catch(e) {
                console.error("Error rendering revenue trend chart:", e);
            }
        } else {
            console.warn("Revenue trend chart canvas or data not found/incomplete.");
        }
    }

    // Add more 'else if' blocks for other report chart types (e.g., product sales pie chart)

});