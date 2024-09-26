document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;

    // Show a loading message or spinner
    document.getElementById('error-message').innerText = 'Logging in...';

    fetch('http://localhost:3000/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password, role })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('Login successful! Redirecting to dashboard...');
            // Redirect to respective dashboard based on role
            window.location.href = `${data.user.role.toLowerCase()}Dashboard.html`;
        } else {
            document.getElementById('error-message').innerText = data.message;
        }
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('error-message').innerText = 'An error occurred. Please try again.';
    });
});
// script.js

// Function to load user profile based on the role
function loadUserProfile(role) {
    // This can be expanded to fetch user data from the server
    // For demonstration, let's just log the role
    console.log(`Loading profile for: ${role}`);
}

// Call this function on each dashboard load
const userRole = 'Student'; // Replace with dynamic role from login session
loadUserProfile(userRole);
