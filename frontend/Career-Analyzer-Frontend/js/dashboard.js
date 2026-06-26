document.addEventListener("DOMContentLoaded", loadDashboard);

function loadDashboard() {

    const resumeData =
        JSON.parse(localStorage.getItem("resumeData"));

    if (!resumeData) {

        alert("No analysis data found.");

        window.location.href = "upload.html";

        return;

    }

    loadUser();

    loadStatistics(resumeData);

    loadBestCareer(resumeData);

    loadProfile(resumeData);

    loadStrengthChart(resumeData);

}

function loadUser() {

    const email =
        localStorage.getItem("email");

    document.getElementById("welcomeUser").innerHTML =
        email || "User";

}

function loadStatistics(data) {

    let skillCount = 0;

    Object.values(data.categorizedSkills).forEach(list => {

        skillCount += list.length;

    });

    document.getElementById("skillCount").innerHTML =
        skillCount;

    document.getElementById("jobCount").innerHTML =
        data.recommendations.length;

    document.getElementById("roadmapCount").innerHTML =
        data.roadmapSteps.length;

}

function loadBestCareer(data) {

    if (data.recommendations.length === 0)
        return;

    const best =
        data.recommendations[0];

    document.getElementById("bestJob").innerHTML =
        best.role;

    document.getElementById("bestScore").innerHTML =
        best.score.toFixed(1) + "% Match";

    const bar =
        document.getElementById("matchBar");

    bar.style.width =
        best.score + "%";

    bar.innerHTML =
        best.score.toFixed(0) + "%";

}

function loadProfile(data) {

    document.getElementById("domain").innerHTML =
        data.domain;

    if (data.recommendations.length > 0) {

        document.getElementById("goal").innerHTML =
            data.recommendations[0].role;

    }
    else {

        document.getElementById("goal").innerHTML =
            "-";

    }

}

function loadStrengthChart(data) {

    let skillCount = 0;

    Object.values(data.categorizedSkills).forEach(list => {

        skillCount += list.length;

    });

    const missing =
        data.missingSkills.length;

    const ctx =
        document.getElementById("strengthChart");

    new Chart(ctx, {

        type: "doughnut",

        data: {

            labels: [

                "Available Skills",

                "Missing Skills"

            ],

            datasets: [

                {

                    data: [

                        skillCount,

                        missing

                    ],

                    backgroundColor: [

                        "#28a745",

                        "#dc3545"

                    ]

                }

            ]

        },

        options: {

            responsive: true,

            plugins: {

                legend: {

                    position: "bottom"

                }

            }

        }

    });

}

function logout() {

    localStorage.clear();

    window.location.href =
        "index.html";

}

function downloadReport() {

    window.location.href =
        "reports.html";

}

function toggleTheme() {

    document.body.classList.toggle("dark-mode");

}