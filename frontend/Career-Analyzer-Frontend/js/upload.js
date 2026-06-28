function uploadResume() {

    const file = document.getElementById("resumeFile").files[0];

    if (!file) {

        alert("Please select a resume.");

        return;

    }

    const uploadButton =
        document.getElementById("uploadBtn");

    uploadButton.disabled = true;

    uploadButton.innerHTML =
        "Analyzing...";

    document.getElementById(
        "loaderOverlay"
    ).style.display = "flex";

    const token =
        localStorage.getItem("token");

    const formData =
        new FormData();

    formData.append("file", file);

    fetch(

        `${CONFIG.BASE_URL}/api/resume/upload/1`,

        {

            method: "POST",

            headers: {

                "Authorization":
                "Bearer " + token

            },

            body: formData

        }

    )

    .then(async response => {

        const text =
            await response.text();

        /*
            Backend returns

            MANUAL_SKILL_ENTRY_REQUIRED

            if resume has no skills.
        */

        if (

            text.trim() ===
            "MANUAL_SKILL_ENTRY_REQUIRED"

        ) {

          

            window.location.href =
                "manual-skills.html";

            return null;

        }

        if (!response.ok) {

            throw new Error(text);

        }

        return JSON.parse(text);

    })

    .then(data => {

        if (!data) return;

        /*
            Save complete response.

            Dashboard,
            Skills,
            Jobs,
            Roadmap

            all use this.
        */

        localStorage.setItem(

            "resumeData",

            JSON.stringify(data)

        );

        window.location.href =
            "dashboard.html";

    })

    .catch(error => {

        console.error(error);

        alert(error.message);

    })

    .finally(() => {

        document.getElementById(
            "loaderOverlay"
        ).style.display = "none";

        uploadButton.disabled = false;

        uploadButton.innerHTML =
            "Analyze Resume";

    });

}
function logout() {

    localStorage.clear();

    window.location.href =
        "index.html";

}

function toggleTheme() {

    document.body.classList.toggle("dark-mode");

}