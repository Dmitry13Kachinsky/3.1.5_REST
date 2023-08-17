const userUrl = 'http://localhost:8080/user';


function getPage() {
    fetch(userUrl).then(response => response.json()).then(user =>
        getInformation(user))
}

function getInformation(user) {

    document.getElementById('basicTable').innerHTML = `<tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.name}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.roles.map(r => r.name.replace('ROLE_', '')).join(' ')}</td>
        </tr>`;

}

getPage();