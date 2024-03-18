document.addEventListener('DOMContentLoaded', function() {
    const userList = document.getElementById('userList');
    const addUserForm = document.getElementById('addUserForm');

    // Function to fetch and display users
    function fetchUsers() {
        fetch('http://localhost:8080/users')
            .then(response => response.json())
            .then(users => {
                userList.innerHTML = '';
                users.forEach(user => {
                    userList.innerHTML += `<div class="card mb-3">
                                                <div class="card-body">
                                                    <h5 class="card-title">${user.nom}, ${user.age}</h5>
                                                    <button class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button>
                                                </div>
                                            </div>`;
                });
            })
            .catch(error => console.error('Error:', error));
    }

    // Function to add a user
    addUserForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const nom = document.getElementById('nom').value;
        const age = document.getElementById('age').value;
        const user = { nom, age };

        fetch('http://localhost:8080/user', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            fetchUsers(); // Refresh the user list
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    });

    // Function to delete a user
    function deleteUser(id) {
        fetch(`http://localhost:8080/user/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            console.log('User deleted successfully');
            // Refresh the user list or perform other actions as needed
        })
        .catch(error => {
            console.error('There was a problem with the DELETE request:', error.message);
        });
    }
    

    // Initial fetch of users
    fetchUsers();
});
