document.addEventListener("DOMContentLoaded", loadJobs);

function loadJobs() {

    const resumeData = JSON.parse(localStorage.getItem("resumeData"));

    if (!resumeData) {

        alert("No resume analysis found.");

        window.location.href = "upload.html";

        return;

    }

    const jobs = resumeData.recommendations || [];

    displayStatistics(jobs);

    displayJobs(jobs);

}

function displayStatistics(jobs) {

    document.getElementById("jobCount").innerHTML =
        jobs.length;

    if (jobs.length === 0) {

        document.getElementById("highestMatch").innerHTML = "0%";

        document.getElementById("averageMatch").innerHTML = "0%";

        return;

    }

    const highest = jobs[0].score;

    document.getElementById("highestMatch").innerHTML =
        highest.toFixed(1) + "%";

    let total = 0;

    jobs.forEach(job => {

        total += job.score;

    });

    const average = total / jobs.length;

    document.getElementById("averageMatch").innerHTML =
        average.toFixed(1) + "%";

}

function displayJobs(jobs) {

    const container =
        document.getElementById("jobsContainer");

    container.innerHTML = "";

    if (jobs.length === 0) {

        container.innerHTML =

        `
        <div class="col-12">

            <div class="alert alert-warning text-center">

                No career recommendations available.

            </div>

        </div>
        `;

        return;

    }

    jobs.forEach((job, index) => {

        let badge = "bg-secondary";

        if (index === 0) {

            badge = "bg-success";

        }
        else if (index === 1) {

            badge = "bg-primary";

        }
        else if (index === 2) {

            badge = "bg-warning text-dark";

        }

        container.innerHTML +=

        `
        <div class="col-lg-4 col-md-6 mb-4">

            <div class="card shadow h-100">

                <div class="card-body">

                    <h4>

                        ${job.role}

                    </h4>

                    <span class="badge ${badge}">

                        ${job.score.toFixed(1)}% Match

                    </span>

                    <div class="progress mt-3 mb-3">

                        <div
                            class="progress-bar bg-success"
                            role="progressbar"
                            style="width:${job.score}%">

                            ${job.score.toFixed(0)}%

                        </div>

                    </div>

                    <p>

                        This role matches your current skills and profile.

                    </p>

                </div>

            </div>

        </div>
        `;

    });

}

function toggleTheme() {

    document.body.classList.toggle("dark-mode");

}