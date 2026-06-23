const BASE_URL = "https://career-analyzer-5.onrender.com";

/* =========================
   LOGIN
========================= */
function login() {
    const username = document.getElementById("username").value;

    localStorage.setItem("username", username);

    fetch(`${BASE_URL}/auth/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: username
        })
    })
    .then(response => response.text())
    .then(token => {
        localStorage.setItem("token", token);
        window.location.href = "upload.html";
    })
    .catch(error => {
        console.error("Login Error:", error);
    });
}


/* =========================
   RESUME UPLOAD
========================= */
function uploadResume() {
    const file = document.getElementById("resumeFile").files[0];

    if (!file) {
        alert("Please select a file");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    const token = localStorage.getItem("token");

    fetch(`${BASE_URL}/resume/upload/1`, {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token
        },
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        localStorage.setItem("resumeData", JSON.stringify(data));
        window.location.href = "dashboard.html";
    })
    .catch(err => console.error("Upload Error:", err));
}


/* =========================
   DARK MODE TOGGLE
========================= */
function toggleTheme() {
    document.body.classList.toggle("dark");

    const isDark = document.body.classList.contains("dark");
    localStorage.setItem("theme", isDark ? "dark" : "light");
}


/* =========================
   INIT DASHBOARD
========================= */
window.onload = function () {

    /* ---------- APPLY THEME ---------- */
    const savedTheme = localStorage.getItem("theme");

    if (savedTheme === "dark") {
        document.body.classList.add("dark");
    } else {
        document.body.classList.remove("dark");
    }

    /* ---------- USER NAME ---------- */
    const welcomeEl = document.getElementById("welcomeUser");

    if (welcomeEl) {
        welcomeEl.innerText = localStorage.getItem("username") || "User";
    }

    /* =========================
       DASHBOARD PAGE LOGIC
    ========================= */
    if (window.location.pathname.includes("dashboard.html")) {

        fetch(`${BASE_URL}/dashboard/1`)
            .then(res => res.json())
            .then(data => {

                if (!data) return;

                /* Skills */
                const skills = data.currentSkills || "";
                
                document.getElementById("skills").innerText = skills;

                document.getElementById("skillCount").innerText =
                    skills ? skills.split(",").length : 0;
                    const skillDiv =
    document.getElementById("skillCategories");

if(skillDiv){

    skillDiv.innerHTML =
        skills
            .split(",")
            .filter(skill => skill.trim() !== "")
            .map(skill => `
                <span class="badge bg-primary m-1 p-2">
                    ${skill.trim()}
                </span>
            `)
            .join("");
}

                /* Domain & Goal */
                document.getElementById("domain").innerText =
                    data.interestedDomain || "-";
                    

                document.getElementById("goal").innerText =
                    data.careerGoal || "-";
            })
            .catch(err => console.error("Dashboard Error:", err));


        /* ---------- RESUME DATA ---------- */
        const data = JSON.parse(localStorage.getItem("resumeData"));

        if (data && data.recommendations) {

            /* BEST JOB */
            const best = data.recommendations[0];
            const jobLabels =
    data.recommendations.map(
        j => j.role
    );

const jobScores =
    data.recommendations.map(
        j => j.score
    );

new Chart(
    document.getElementById(
        "jobChart"
    ),
    {
        type: "bar",
        data: {
            labels: jobLabels,
            datasets: [{
                label: "Match %",
                data: jobScores
            }]
        }
    }
);
const currentSkills =
    data.extractedText
        ? (data.extractedText.match(/Skills:/)
            ? data.extractedText
                  .split("Skills:")[1]
                  .replace("[","")
                  .replace("]","")
                  .split(",").length
            : 0)
        : 0;

const missingSkills =
    (data.missingSkills || []).length;

const strength =
    currentSkills + missingSkills === 0
        ? 0
        : Math.round(
            (currentSkills /
            (currentSkills + missingSkills))
            * 100
        );

new Chart(
    document.getElementById(
        "strengthChart"
    ),
    {
        type: "doughnut",
        data: {
            labels: [
                "Strength",
                "Gap"
            ],
            datasets: [{
                data: [
                    strength,
                    100 - strength
                ]
            }]
        },
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text:
                        "Resume Strength: "
                        + strength + "%"
                }
            }
        }
    }
);
const gapSkills = data.missingSkills || [];

new Chart(
    document.getElementById("skillGapChart"),
    {
        type: "bar",
        data: {
            labels: gapSkills,
            datasets: [{
                label: "Missing Skills",
                data: gapSkills.map(() => 1)
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: false
                }
            }
        }
    }
);

            if (best) {
                document.getElementById("bestJob").innerText = best.role;
                document.getElementById("bestScore").innerText =
                    best.score.toFixed(1) + "% Match";
            }
            

            /* JOB LIST */
            const jobsEl = document.getElementById("jobs");
            jobsEl.innerHTML = "";

            document.getElementById("jobCount").innerText =
                data.recommendations.length;

            data.recommendations.forEach(job => {

                const li = document.createElement("li");

                let stars = "⭐⭐⭐⭐⭐";
                if (job.score < 80) stars = "⭐⭐⭐⭐";
                if (job.score < 60) stars = "⭐⭐⭐";

                li.innerHTML = `
                    <b>${job.role}</b><br>
                    ${stars}<br>
                    <span>${job.score.toFixed(1)}% match</span>
                `;

                jobsEl.appendChild(li);
            });
            
const gapEl =
    document.getElementById(
        "missingSkills"
    );
    if (gapEl) {

    gapEl.innerHTML = "";

    const missing =
        data.missingSkills || [];

    if (missing.length === 0) {

        gapEl.innerHTML =
            "<li>✅ No major skill gaps detected</li>";

    } else {

        missing.forEach(skill => {

            const li =
                document.createElement("li");

            li.innerHTML =
                "❌ " + skill;

            gapEl.appendChild(li);
        });
    }
}

            /* ROADMAP */
            const roadmapEl = document.getElementById("roadmap");
            roadmapEl.innerHTML = "";

            document.getElementById("roadmapCount").innerText =
                data.roadmapSteps?.length || 0;

            (data.roadmapSteps || []).forEach((step, index) => {

                const li = document.createElement("li");
                li.innerHTML = `✅ Step ${index + 1}: ${step}`;

                roadmapEl.appendChild(li);
            });
        }
    }
};


/* =========================
   LOGOUT
========================= */
function logout() {
    localStorage.clear();
    window.location.href = "index.html";
}
function downloadReport() {

    window.open(
        `${BASE_URL}/report/1`
    );
}