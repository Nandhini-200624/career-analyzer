document.addEventListener("DOMContentLoaded", loadRoadmap);

function loadRoadmap() {

    const resumeData =
        JSON.parse(localStorage.getItem("resumeData"));

    if (!resumeData) {

        alert("No resume analysis found.");

        window.location.href = "upload.html";

        return;

    }

    const roadmap =
        resumeData.roadmapSteps || [];

    document.getElementById("roadmapCount").innerHTML =
        roadmap.length;

    displayRoadmap(roadmap);

}

function displayRoadmap(roadmap) {

    const container =
        document.getElementById("roadmapContainer");

    container.innerHTML = "";

    if (roadmap.length === 0) {

        container.innerHTML =

        `
        <div class="alert alert-warning text-center">

            No roadmap available.

        </div>
        `;

        return;

    }

    roadmap.forEach((step, index) => {

        container.innerHTML +=

        `
        <div class="card shadow-sm mb-3">

            <div class="card-body d-flex align-items-center">

                <div class="me-4">

                    <span
                    class="badge bg-primary rounded-circle p-3">

                        ${index + 1}

                    </span>

                </div>

                <div>

                    <h5>

                        Step ${index + 1}

                    </h5>

                    <p class="mb-0">

                        ${step}

                    </p>

                </div>

            </div>

        </div>
        `;

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