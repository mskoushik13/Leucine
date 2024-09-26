const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser');
const cors = require('cors');
const path = require('path'); // Import the path module

const app = express();
app.use(cors());
app.use(bodyParser.json());

// Serve static files from the 'public' directory
app.use(express.static(path.join('/templates')));

const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'root',
    database: 'college_directory'
});

db.connect(err => {
    if (err) throw err;
    console.log('MySQL Connected...');
});

// Login API
app.post('/login', (req, res) => {
    const { username, password, role } = req.body;
    const query = 'SELECT * FROM User WHERE username = ? AND password = ? AND role = ?';
    db.query(query, [username, password, role], (err, results) => {
        if (err) return res.status(500).send(err);
        if (results.length > 0) {
            res.json({ success: true, user: results[0] });
        } else {
            res.json({ success: false, message: 'Invalid credentials' });
        }
    });
});

const newLocal = 'templates/index.html';
// Root route to serve the index.html
app.get('/', (req, res) => {
    res.sendFile(path.resolve(newLocal), (err) => {
        if (err) {
            res.status(err.status).end();
        }
    });
});


// Start the server
app.listen(3000, () => {
    console.log('Server running on http://localhost:3000');
});
