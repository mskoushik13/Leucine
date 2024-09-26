const express = require('express');
const bodyParser = require('body-parser');
const db = require('./db');

const app = express();
app.use(bodyParser.json());

// User Login
app.post('/api/login', (req, res) => {
    const { username, password, role } = req.body;
    const query = `SELECT * FROM User WHERE username = ? AND password = ? AND role = ?`;
    
    db.query(query, [username, password, role], (err, results) => {
        if (err) return res.status(500).send('Error on the server.');
        
        if (results.length > 0) {
            return res.json({ success: true });
        } else {
            return res.json({ success: false, message: 'Invalid credentials.' });
        }
    });
});

// Fetch Student Profile
app.get('/api/student/profile', (req, res) => {
    // Assume user is logged in, replace with real user ID
    const userId = 1; 
    const query = `SELECT * FROM User WHERE id = ?`;
    
    db.query(query, [userId], (err, results) => {
        if (err) return res.status(500).send('Error on the server.');
        res.json(results[0]);
    });
});

// Search Students
app.get('/api/students/search', (req, res) => {
    const query = req.query.query;
    const sql = `SELECT name, department FROM User WHERE name LIKE ?`;
    
    db.query(sql, [`%${query}%`], (err, results) => {
        if (err) return res.status(500).send('Error on the server.');
        res.json(results);
    });
});

// Fetch Faculty Advisors
app.get('/api/faculty/advisors', (req, res) => {
    // Assume user is logged in, replace with real student ID
    const userId = 1;
    const query = `SELECT * FROM FacultyProfile WHERE user_id IN (SELECT faculty_id FROM StudentProfile WHERE user_id = ?)`;
    
    db.query(query, [userId], (err, results) => {
        if (err) return res.status(500).send('Error on the server.');
        res.json(results);
    });
});

// Fetch Faculty Class List
app.get('/api/faculty/class-list', (req, res) => {
    // Assume user is logged in, replace with real faculty ID
    const facultyId = 1; 
    const query = `SELECT * FROM StudentProfile WHERE department_id = (SELECT department_id FROM FacultyProfile WHERE user_id = ?)`;
    
    db.query(query, [facultyId], (err, results) => {
        if (err) return res.status(500).send('Error on the server.');
        res.json(results);
    });
});

// Update Faculty Profile
app.post('/api/faculty/update', (req, res) => {
    const { officeHours, email, phone } = req.body;
    // Assume user is logged in, replace with real faculty ID
    const facultyId = 1;

    const query = `UPDATE FacultyProfile SET office_hours = ?, email = ?, phone = ? WHERE user_id = ?`;
    db.query(query, [officeHours, email, phone, facultyId], (err) => {
        if (err) return res.status(500).send('Error on the server.');
        res.json({ message: 'Profile updated successfully.' });
    });
});

// Manage Records for Admin
app.get('/api/admin/records', (req, res) => {
    const query = `SELECT username, role FROM User`;
    
    db.query(query, (err, results) => {
        if (err) return res.status(500).send('Error on the server.');
        res.json(results);
    });
});

app.post('/api/admin/manage', (req, res) => {
    const { username, role } = req.body;
    const query = `INSERT INTO User (username, role) VALUES (?, ?) ON DUPLICATE KEY UPDATE role = ?`;
    
    db.query(query, [username, role, role], (err) => {
        if (err) return res.status(500).send('Error on the server.');
        res.json({ message: 'Record added/updated successfully.' });
    });
});

// Start Server
app.listen(3000, () => {
    console.log('Server is running on http://localhost:3000');
});
