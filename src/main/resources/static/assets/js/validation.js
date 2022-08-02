function validateForm() {

    let a = document.forms["createProduct"]["producer"].value;
    if (a  === "" || a === null) {
        alert("Укажите производителя");
        return false;
    }

    let b = document.forms["createProduct"]["mnn"].value;
    if (b === "" || b === null) {
        alert("Укажите МНН");
        return false;
    }

    let c = document.forms["createProduct"]["category"].value;
    if (c === "" || c === null) {
        alert("Укажите категорию");
        return false;
    }

    let d = document.forms["createProduct"]["pharmacies"].value;
    if (d === "" || d === null) {
        alert("Укажите аптеку");
        return false;
    }

    let e = document.forms["createProduct"]["drugName"].value;
    if (e === "" || e === null) {
        alert("Укажите название");
        return false;
    }

    let f = document.forms["createProduct"]["prescription"].value;
    if (f  === ""|| f === null) {
        alert("Выберите порядок отпуска");
        return false;
    }

    let g = document.forms["createProduct"]["price"].value;
    if (g  === ""|| g === null) {
        alert("Укажите цену");
        return false;
    }

    let j = document.forms["createProduct"]["countAvailable"].value;
    if (j  === ""|| j === null) {
        alert("Укажите количество");
        return false;
    }

}