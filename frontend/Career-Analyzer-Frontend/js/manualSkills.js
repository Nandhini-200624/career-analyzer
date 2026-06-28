function submitManualSkills() {

    const skills = document
        .getElementById("manualSkills")
        .value
        .trim();

    if (skills === "") {

        alert("Please enter your skills.");

        return;

    }

    const token = localStorage.getItem("token");

    fetch(

        `${CONFIG.BASE_URL}/api/manual/skills/1`,

        {

            method: "POST",

            headers: {

                "Content-Type": "application/json",

                "Authorization":
                    "Bearer " + token

            },

            body: JSON.stringify({

                skills: skills

            })

        }

    )

    .then(response => {

        if (!response.ok) {

            throw new Error(

                "Failed to analyze skills."

            );

        }

        return response.json();

    })

    .then(data => {

        /*
            Save backend response.

            Dashboard,
            Skills,
            Jobs,
            Roadmap,
            Skill Gap

            all use this object.
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

    });

}



function clearSkills() {

    document
        .getElementById("manualSkills")
        .value = "";

}
function logout() {

    localStorage.clear();

    window.location.href =
        "index.html";

}

function toggleTheme() {

    document.body.classList.toggle("dark-mode");

}