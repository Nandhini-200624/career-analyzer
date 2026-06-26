function login() {

    const username =
        document.getElementById("username").value.trim();

    if (!username) {

        alert("Enter Email");

        return;

    }

    localStorage.setItem("username", username);

    fetch(`${CONFIG.BASE_URL}/api/auth/login`, {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            email: username

        })

    })

    .then(res => res.text())

    .then(token => {

        localStorage.setItem("token", token);

        window.location.href = "upload.html";

    })

    .catch(err => {

        console.error(err);

        alert("Login Failed");

    });

}