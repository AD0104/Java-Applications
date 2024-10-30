document.querySelectorAll("input.form-control").forEach((item) => {
    item.addEventListener("click", function () {
        item.classList.remove("is-invalid");
    })
    item.addEventListener("focus", function () {
        item.classList.remove("is-invalid");
    })
});

function say_hello() { console.log("Hello"); }

function show_error(error) {
    let container = document.createElement("div");
    container.classList.add("alert", "alert-danger", "alert-dismissible");

    let text = document.createElement("p");
    text.innerText = error;

    let button = document.createElement("button");
    button.classList.add("close")
    button.innerText = "X";

    $(button).click((element) => {
        $(element.currentTarget).attr("data-dismiss", "alert");
    });

    container.appendChild(text);
    container.appendChild(button);

    document.getElementById("errors").appendChild(container)
}