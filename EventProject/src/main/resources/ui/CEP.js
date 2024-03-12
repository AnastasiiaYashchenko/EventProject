let image;
document.getElementById('but_save_CEP').addEventListener('click', function (eventt) {
    eventt.preventDefault();
    const nameEvent = document.getElementById("name").value;
    const dayEvent = document.getElementById("day_event").value;
    const timeEvent = document.getElementById("time_start").value;
    const quantityPlace = document.getElementById("quantity_place").value;
    const price = document.getElementById("price_event").value;
    const lasting = document.getElementById("lasting").value;
    const address = document.getElementById("address").value;
    const descriptionEvent = document.getElementById("event_description").value;
    const ageLimit = document.getElementById("age_limit").value;

    const data = {
        nameEvent: nameEvent,
        dayEvent: dayEvent,
        timeEvent: timeEvent,
        quantityPlace: quantityPlace,
        price: price,
        lasting: lasting,
        address: address,
        descriptionEvent: descriptionEvent,
        ageLimit: ageLimit,
        image: image
    };

    console.log(JSON.stringify(data))


    if (nameEvent === "" || dayEvent === "" || timeEvent === "" || quantityPlace === "" || price === "" || lasting === "" || address === "" || descriptionEvent === "" || ageLimit === "") {
        alert("Заповніть всі поля")
    } else {
        fetch('http://localhost:8080/Event', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + localStorage.getItem('token')
            },
            body: JSON.stringify(data)
        })
        alert("Подію створено")
    }
});


const fileInput = document.getElementById("imageInput");
fileInput.addEventListener("change", e => {
    const file = fileInput.files[0];
    const reader = new FileReader();
    reader.addEventListener("load", () => {
        image = reader.result;
    });
    reader.readAsDataURL(file);
})

document.getElementById('imageInput').addEventListener('change', function () {
    const preview = document.getElementById('imagePreview');
    const fileInput = this;
    const file = fileInput.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
        };
        reader.readAsDataURL(file);
    } else {
        preview.src = "";
    }

});
console.log(localStorage.getItem('token'));




