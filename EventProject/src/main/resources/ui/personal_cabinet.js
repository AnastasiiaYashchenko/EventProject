const currentUrl = window.location.href;    const url = new URL(currentUrl);    const id = url.searchParams.get('id');    document.addEventListener('DOMContentLoaded', function () {    if (id) {    const encodedId = encodeURIComponent(id);    console.log(id);    const apiUrl = `http://localhost:8080/User/${encodedId}`;    fetch(apiUrl, {    method: 'GET',    headers: {    'Content-Type': 'application/json',    'Authorization': 'Basic ' + localStorage.getItem('token')}})    .then(response => response.json())    .then(dataUser => {    document.querySelector('div.form_group>input.name_PC').value = dataUser.name;    document.querySelector('div.form_group>input.last_name_PC').value = dataUser.lastName;    document.querySelector('div.form_group>input.email_PC').value = dataUser.email;    document.querySelector('div.form_group>input#dateOfBirth_PC').textContent = dataUser.dateOfBirth;    document.querySelector('div.form_group>input#phoneNumber_PC').textContent = dataUser.phoneNumber;})} else {    console.error('ID не найден в URL.');}});    document.getElementById('but_new_pass').addEventListener('click', function (newpass) {    newpass.preventDefault();    const pass = document.getElementById("edit_pass_input").value    const pass_replace = document.getElementById("edit_replace_pass_input").value    const dateOfBirth = document.getElementById("dateOfBirth_PC").value    const phoneNumber = document.getElementById("phoneNumber_PC").value    if (pass.value === pass_replace.value) {    const pass_put = {    pass: pass,    dateOfBirth:dateOfBirth,    phoneNumber:phoneNumber};    if (id) {    const encodedId = encodeURIComponent(id);    console.log(id);    const apiUrl = `http://localhost:8080/User/${encodedId}`;    fetch(apiUrl, {    method: 'PUT',    headers: {    'Content-Type': 'application/json',    'Authorization': 'Basic ' + localStorage.getItem('token')},    body: JSON.stringify(pass_put)})    alert("Ok");} else {    console.error('ID не найден в URL.');}} else {    alert("Паролі не співпадають");}});