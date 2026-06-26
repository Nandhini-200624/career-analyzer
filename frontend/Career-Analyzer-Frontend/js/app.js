document.addEventListener("DOMContentLoaded", initApp);

function initApp() {

    applyTheme();

    initializeUser();

    initializeNavbar();

}
function applyTheme() {

    const theme = Storage.getText("theme");

    if (theme === "dark") {

        document.body.classList.add("dark");

    }

}
function toggleTheme() {

    document.body.classList.toggle("dark");

    Storage.saveText(

        "theme",

        document.body.classList.contains("dark")
            ? "dark"
            : "light"

    );

}
function initializeUser() {

    Utils.setText(

        "welcomeUser",

        Storage.getText("username") || "User"

    );

}
function login() {

    const email = Utils.byId("username").value.trim();

    if (!email) {

        Notify.error("Enter Email");

        return;

    }

    API.login(email)

        .then(token => {

            Storage.saveText("username", email);

            Storage.saveText("token", token);

            window.location.href = "upload.html";

        })

        .catch(() => {

            Notify.error("Login Failed");

        });

}
function uploadResume() {

    const file = Utils.byId("resumeFile").files[0];

    if (!file) {

        Notify.warning("Select Resume");

        return;

    }

    Utils.showLoader();

    API.uploadResume(

        file,

        Storage.getText("token")

    )

    .then(async response => {

        const text = await response.text();

        Utils.hideLoader();

        if (text === "MANUAL_SKILL_ENTRY_REQUIRED") {

            window.location.href = "manual-skills.html";

            return;

        }

        Storage.save(

            "resumeData",

            JSON.parse(text)

        );

        window.location.href = "dashboard.html";

    })

    .catch(() => {

        Utils.hideLoader();

        Notify.error("Upload Failed");

    });

}
function initializeNavbar() {

    const logoutBtn = Utils.byId("logoutBtn");

    if (logoutBtn) {

        logoutBtn.addEventListener(

            "click",

            logout

        );

    }

    const themeBtn = Utils.byId("themeBtn");

    if (themeBtn) {

        themeBtn.addEventListener(

            "click",

            toggleTheme

        );

    }

}