document.addEventListener("DOMContentLoaded", loadSkillGap);

function loadSkillGap() {

    const resumeData =
        JSON.parse(localStorage.getItem("resumeData"));

    if (!resumeData) {

        alert("No analysis found.");

        window.location.href = "upload.html";

        return;

    }

    const missing =
        resumeData.missingSkills || [];

    const categories =
        resumeData.categorizedSkills || {};

    let detected = 0;

    Object.values(categories).forEach(list => {

        detected += list.length;

    });

    document.getElementById("missingSkillCount").innerHTML =
        missing.length;

    createProgressBar(detected, missing.length);

    createChart(detected, missing.length);

    showPrioritySkills(missing);

    showMissingSkills(missing);

}
function createProgressBar(detected, missing) {

    const total = detected + missing;

    let readiness = 0;

    if(total > 0){

        readiness =
            Math.round((detected / total) * 100);

    }

    const bar =
        document.getElementById("readinessBar");

    bar.style.width =
        readiness + "%";

    bar.innerHTML =
        readiness + "%";

}
function createChart(detected, missing){

    const ctx =
        document.getElementById("gapChart");

    new Chart(ctx,{

        type:"doughnut",

        data:{

            labels:[
                "Detected",
                "Missing"
            ],

            datasets:[{

                data:[
                    detected,
                    missing
                ],

                backgroundColor:[
                    "#198754",
                    "#dc3545"
                ]

            }]

        },

        options:{

            plugins:{

                legend:{

                    position:"bottom"

                }

            }

        }

    });

}
function showPrioritySkills(missing){

    const div =
        document.getElementById("prioritySkills");

    div.innerHTML="";

    if(missing.length===0){

        div.innerHTML=

        "<span class='badge bg-success p-3'>Excellent! No Missing Skills</span>";

        return;

    }

    missing.slice(0,5).forEach(skill=>{

        div.innerHTML+=

        `
        <span class="badge bg-warning text-dark p-3 m-2">

            ${skill}

        </span>
        `;

    });

}
function showMissingSkills(missing){

    const container =
        document.getElementById("missingSkillsContainer");

    container.innerHTML="";

    if(missing.length===0){

        container.innerHTML=

        `
        <div class="col-12">

            <div class="alert alert-success text-center">

                🎉 No Missing Skills

            </div>

        </div>
        `;

        return;

    }

    missing.forEach(skill=>{

        container.innerHTML+=

        `
        <div class="col-md-4 mb-3">

            <div class="card shadow">

                <div class="card-body text-center">

                    <h5>

                        ${skill}

                    </h5>

                    <small class="text-danger">

                        Recommended to Learn

                    </small>

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