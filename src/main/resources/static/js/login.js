document.getElementById('form-container').addEventListener('submit', function(event) {
    event.preventDefault();


    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;


    const credentials = {
        username: username,
        password: password
    };


    fetch('backend/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(response => response.json())
        .then(data => {

            if (data.success) {
                alert('Login successful');

            } else {
                alert('Login failed: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error detected. Please try again.');
        });
});
