document.addEventListener("DOMContentLoaded", loadSkills);

function loadSkills() {

    const resumeData = JSON.parse(localStorage.getItem("resumeData"));

    if (!resumeData) {

        alert("No resume analysis found.");

        window.location.href = "upload.html";

        return;

    }

    displayDetectedSkills(resumeData.categorizedSkills);

    displayMissingSkills(resumeData.missingSkills);

}

function displayDetectedSkills(categorizedSkills) {

    const container =
        document.getElementById("currentSkills");

    container.innerHTML = "";

    let totalSkills = 0;

    for (const category in categorizedSkills) {

        const skills = categorizedSkills[category];

        totalSkills += skills.length;

        let html =

        `
        <div class="mb-4">

            <h5 class="text-primary">

                ${category}

            </h5>

        `;

        skills.forEach(skill => {

            html +=

            `
            <span class="badge bg-success me-2 mb-2 p-2">

                ${skill}

            </span>
            `;

        });

        html += "</div>";

        container.innerHTML += html;

    }

    document.getElementById("skillCount").innerHTML =
        totalSkills;

}

function displayMissingSkills(missingSkills) {

    const list =
        document.getElementById("missingSkills");

    list.innerHTML = "";

    if (missingSkills.length === 0) {

        list.innerHTML =

        `
        <li class="list-group-item text-success">

            No missing skills 🎉

        </li>
        `;

    }

    else {

        missingSkills.forEach(skill => {

            list.innerHTML +=

            `
            <li class="list-group-item">

                ${skill}

            </li>
            `;

        });

    }

    document.getElementById("missingCount").innerHTML =
        missingSkills.length;

}
function logout() {

    localStorage.clear();

    window.location.href =
        "index.html";

}

function toggleTheme() {

    document.body.classList.toggle("dark-mode");

}