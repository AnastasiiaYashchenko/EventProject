document.getElementById('btn_registr').addEventListener('click', function (registr) {
    registr.preventDefault();
    const nameUser = document.getElementById("nameUser").value
    const lastNameUser = document.getElementById("lastNameUser").value
    const emailUser = document.getElementById("emailUser").value
    const pass = document.getElementById("pass").value


    const dataUser = {
        name: nameUser, lastName: lastNameUser, email: emailUser, pass: pass
    }
    console.log(JSON.stringify(dataUser))


    if (nameUser === "" || lastNameUser === "" || emailUser === "" || pass === "") {
        alert("Введіть всі дані")
    } else {
        validateEmail();
    }

    function validateEmail() {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (emailRegex.test(emailUser)) {
            fetch('http://localhost:8080/User', {
                method: 'POST', headers: {
                    'Content-Type': 'application/json'
                }, body: JSON.stringify(dataUser)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Помилка при обробці запиту');
                    }
                    return response.json();
                })
            alert("Ви зареєстровані");
        } else {
            alert("Неправильно введена електронна адреса");
        }
    }
})