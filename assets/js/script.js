function addToCart() {
    var currentButton = this;
    currentButton.disabled = true;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "addtocart", true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var result = xhr.responseText;
            if (result === 'true') {
                currentButton.innerHTML = "In Cart";
                currentButton.classList.remove("btn-outline-success");
                currentButton.classList.add("btn-outline-warning");
                currentButton.classList.remove("addToCart");
                currentButton.classList.add("inCart");
                currentButton.removeEventListener("click", addToCart, true);
                currentButton.addEventListener("click", removeFromCart, true);
                currentButton.disabled = false;
            }
        }
    };
    xhr.send("id=" + currentButton.id);
}

function removeFromCart() {
    var currentButton = this;
    currentButton.disabled = true;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "removefromcart", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var result = xhr.responseText;
            if (result === 'true') {
                currentButton.innerHTML = "Add to Cart";
                currentButton.classList.remove("btn-outline-warning");
                currentButton.classList.add("btn-outline-success");
                currentButton.classList.remove("inCart");
                currentButton.classList.add("addToCart");
                currentButton.removeEventListener("click", removeFromCart, true);
                currentButton.addEventListener("click", addToCart, true);
                currentButton.disabled = false;
            }
        }
    };
    xhr.send("id=" + currentButton.id);
}

var buttons = document.querySelectorAll(".addToCart");
for (var i = 0; i < buttons.length; i++) {
    buttons.item(i).addEventListener("click", addToCart, true);
}

var buttons = document.querySelectorAll(".inCart");
for (var i = 0; i < buttons.length; i++) {
    buttons.item(i).addEventListener("click", removeFromCart, true);
}