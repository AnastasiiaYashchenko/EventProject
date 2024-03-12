import axios from 'axios';

const data = {
    nameEvent: document.getElementById("name"),
    price: document.getElementById("price_event"),
    lasting: document.getElementById("name"),
    address: document.getElementById("address"),
    descriptionEvent: document.getElementById("event_description"),
    ageLimit: document.getElementById("age_limit"),

};

axios.post('http://localhost:8080/Event', data)
    .then(response => {
        console.log(response.data);
    })
    .catch(error => {
        console.error(error);
    });
/*
<input id="id1" type="text" placeholder="Введіть email">
    <button style="height: 20px" id="but"></button>
    <div id="id2">...</div>
    <input id="id22" type="text" placeholder="2">

        <script>
            const but = document.getElementById("but");
            const id1 = document.getElementById("id1");
            const id22 = document.getElementById("id22");
            but.addEventListener("click", function () {
            const textToSave = id1.value;
            id22.value = textToSave;
            //id22.textContent = textToSave;
        });
        </script>

 */
