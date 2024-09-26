document.addEventListener('DOMContentLoaded', function () {
    fetchRecords();

    document.getElementById('manageRecordsForm').addEventListener('submit', function (event) {
        event.preventDefault();
        manageRecords();
    });
});

function fetchRecords() {
    fetch('http://localhost:3000/api/admin/records')
        .then(response => response.json())
        .then(data => {
            const records = data.map(record => `<p>${record.username} - ${record.role}</p>`).join('');
            document.getElementById('recordsList').innerHTML = records;
        });
}

function manageRecords() {
    const username = document.getElementById('username').value;
    const role = document.getElementById('role').value;

    fetch('http://localhost:3000/api/admin/manage', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, role }),
    })
    .then(response => response.json())
    .then(data => {
        alert(data.message);
        fetchRecords(); // Refresh records
    });
}
