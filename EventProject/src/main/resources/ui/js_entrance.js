document.getElementById('btn_entrance').addEventListener('click', function (entrance) {
    entrance.preventDefault();

    const emailUser = document.getElementById("email_entrance").value
    const passUser = document.getElementById("pass_entrance").value

    const entranceUser = {
        email: emailUser,
        pass: passUser
    };
    console.log(JSON.stringify(entranceUser))

    let str = emailUser + ":" + passUser;
    let convertBase = btoa(str);
    if (emailUser === "" || passUser === "") {
        alert("Введіть всі дані")
    } else {
        fetch('http://localhost:8080/User_entrance', {
            method: 'POST', headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + convertBase
            }, body: JSON.stringify(entranceUser)
        })
            .then(response => {
                if (!response.ok) {
                    if (response.status === 401) {
                        alert("Ви ввели неправильні дані");
                    }
                    if (response.status === 500) {
                        alert("Ви не зареєстровані");
                    }
                }
                return response.json();
            })

            .then(user => {
                window.location.href = `personal_cabinet.html?id=${user.id}`;
            })
    }
    localStorage.setItem('token', convertBase);
    console.log(convertBase);
    console.log(str);

})

