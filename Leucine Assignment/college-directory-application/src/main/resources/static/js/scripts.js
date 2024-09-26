// frontend/js/scripts.js

// Example of handling the login form submission
document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;

    // Simulate API call (Replace with actual fetch to the backend)
    if (username === 'admin' && password === 'admin' && role === 'ADMINISTRATOR') {
        window.location.href = 'admin_dashboard.html';
    } else if (role === 'STUDENT') {
        window.location.href = 'student_dashboard.html';
    } else if (role === 'FACULTY_MEMBER') {
        window.location.href = 'faculty_dashboard.html';
    } else {
        document.getElementById('errorMessage').textContent = 'Invalid login credentials!';
    }
});
