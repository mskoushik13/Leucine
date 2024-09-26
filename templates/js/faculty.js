document.addEventListener('DOMContentLoaded', function () {
    fetchClassList();

    document.getElementById('updateProfileForm').addEventListener('submit', function (event) {
        event.preventDefault();
        updateProfile();
    });
});

function fetchClassList() {
    fetch('http://localhost:3000/api/faculty/class-list')
        .then(response => response.json())
        .then(data => {
            const students = data.map(student => `<p>${student.name} - ${student.email}</p>`).join('');
            document.getElementById('classList').innerHTML = `<h2>Class List</h2>${students}`;
        });
}

function updateProfile() {
    const officeHours = document.getElementById('officeHours').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;

    fetch('http://localhost:3000/api/faculty/update', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ officeHours, email, phone }),
    })
    .then(response => response.json())
    .then(data => {
        alert(data.message);
    });
}
