import { updateRanking } from "greengame-api-client";

function createButton(id, clickCallback) {
    const button = document.createElement("button");
    button.classList.add("btn", "btn-primary", "m-1");
    button.id = `game-complete-${id}`;
    button.innerText = `Game ${id}`;
    button.addEventListener("click", clickCallback);
    
    return button;
}

document.addEventListener("DOMContentLoaded", async () => {
    const buttonCol = document.querySelector("#game-complete-buttons");
    const minScoreInput = document.querySelector("#min-points");

    const ids = Array.from({length: 7}, (_, i) => i + 1);
    ids.forEach(id => {
        async function completeGame() {
            await updateRanking({
                gameID: id,
                score: minScoreInput.value,
            });
        }

        const button = createButton(id, completeGame);
        buttonCol.appendChild(button);
    });
});
