document.getElementById('loginForm').addEventListener('submit', async function (event) {
    event.preventDefault(); // Prevent the default form submission

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;

    try {
        const response = await fetch('http://localhost:3000/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password, role }),
        });

        const result = await response.json();
        if (result.success) {
            // Redirect based on role or display success message
            alert(`Welcome ${result.role}!`);
            // You could redirect here based on role
            // window.location.href = `/${result.role.toLowerCase()}-dashboard.html`;
        } else {
            document.getElementById('error').innerText = result.message;
        }
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('error').innerText = 'An error occurred. Please try again.';
    }
});
