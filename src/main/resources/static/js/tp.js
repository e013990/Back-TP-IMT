/**
 * 

 */
function insertions() {
	const par1 = document.querySelector("#p1");
	par1.insertAdjacentHTML("afterend", "<p>inséré après le 1er paragraphe</p>");
	const paraC1 = Array.from(document.querySelectorAll(".c1"));
	for (const par of paraC1) {
		par.insertAdjacentHTML("afterbegin", "(inséré au début) ");
		par.insertAdjacentHTML("beforeend", " (inséré à la fin)");
	}
}

function parClicked(event) {
	event.target.innerHTML = "paragraphe cliqué";
}
function buttonClicked(event) {
	console.log("button clicked");
}
const para = Array.from(document.querySelectorAll(".c1"));
for (const par of para) {
	par.addEventListener("click", parClicked);
}
const btnClear = document.querySelector("#idClear");
btnClear.addEventListener("click", buttonClicked);
