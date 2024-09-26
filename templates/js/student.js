document.addEventListener('DOMContentLoaded', function () {
    fetchStudentProfile();
    fetchFacultyAdvisors();

    document.getElementById('search').addEventListener('input', function () {
        const query = this.value;
        searchStudents(query);
    });
});

function fetchStudentProfile() {
    fetch('http://localhost:3000/api/student/profile')
        .then(response => response.json())
        .then(data => {
            document.getElementById('profile').innerHTML = `
                <h2>Profile</h2>
                <p>Name: ${data.name}</p>
                <p>Email: ${data.email}</p>
                <p>Phone: ${data.phone}</p>
                <img src="${data.photo}" alt="Profile Photo">
            `;
        });
}

function searchStudents(query) {
    fetch(`http://localhost:3000/api/students/search?query=${query}`)
        .then(response => response.json())
        .then(data => {
            const results = data.map(student => `<p>${student.name} - ${student.department}</p>`).join('');
            document.getElementById('searchResults').innerHTML = results;
        });
}

function fetchFacultyAdvisors() {
    fetch('http://localhost:3000/api/faculty/advisors')
        .then(response => response.json())
        .then(data => {
            const advisors = data.map(advisor => `
                <p>${advisor.name} - 
                <a href="mailto:${advisor.email}">${advisor.email}</a> / 
                ${advisor.phone}</p>
            `).join('');
            document.getElementById('facultyAdvisors').innerHTML = advisors;
        });
}
