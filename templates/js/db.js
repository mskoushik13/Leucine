const mysql = require('mysql');
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root', // Change to your MySQL username
    password: 'admin', // Change to your MySQL password
    database: 'msk'
});

db.connect(err => {
    if (err) throw err;
    console.log('Connected to database.');
});

module.exports = db;
