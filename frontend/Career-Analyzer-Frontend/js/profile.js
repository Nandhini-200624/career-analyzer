document.addEventListener("DOMContentLoaded", loadProfile);

// ==============================
// Load Profile
// ==============================

function loadProfile() {

    const resumeData =
        JSON.parse(localStorage.getItem("resumeData"));

    if (!resumeData) {

        alert("No profile data found.");

        window.location.href = "upload.html";

        return;
    }

    const username =
        localStorage.getItem("username") || "User";

    document.getElementById("profileName").innerHTML =
        username;

    document.getElementById("profileEmail").value =
        username;

    document.getElementById("profileDomain").value =
        resumeData.domain || "Not Available";

    if (resumeData.recommendations &&
        resumeData.recommendations.length > 0) {

        document.getElementById("profileGoal").value =
            resumeData.recommendations[0].role;

    }
    else {

        document.getElementById("profileGoal").value =
            "Not Available";

    }

    let totalSkills = 0;

    const categories =
        resumeData.categorizedSkills || {};

    Object.values(categories).forEach(list => {

        totalSkills += list.length;

    });

    document.getElementById("profileSkillCount").value =
        totalSkills;

    renderSkills(categories);

    restoreTheme();

}

// ==============================
// Display Skills
// ==============================

function renderSkills(categories) {

    const container =
        document.getElementById("profileSkills");

    container.innerHTML = "";

    if (Object.keys(categories).length === 0) {

        container.innerHTML =
            "<p>No skills available.</p>";

        return;

    }

    for (const category in categories) {

        const title =
            document.createElement("h5");

        title.className = "mt-3";

        title.innerHTML =
            category;

        container.appendChild(title);

        categories[category].forEach(skill => {

            const badge =
                document.createElement("span");

            badge.className =
                "badge bg-primary me-2 mb-2 p-2";

            badge.innerHTML =
                skill;

            container.appendChild(badge);

        });

    }

}

// ==============================
// Theme Toggle
// ==============================

function toggleTheme() {

    document.body.classList.toggle("dark-mode");

    if (document.body.classList.contains("dark-mode")) {

        localStorage.setItem("theme", "dark");

    }
    else {

        localStorage.setItem("theme", "light");

    }

}

// ==============================
// Restore Theme
// ==============================

function restoreTheme() {

    const theme =
        localStorage.getItem("theme");

    if (theme === "dark") {

        document.body.classList.add("dark-mode");

    }

}