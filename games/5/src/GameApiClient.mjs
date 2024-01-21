import {updateRanking, loadGameData, saveGameData} from "https://unpkg.com/greengame-api-client@latest";

class GameApiClient {


     async saveScore(score) {

        const rankingData = {
            "gameID": 5,
            "score": score,
        }

        const gameData = {
            "gameId": 5,
            "gameData": {
                "userScore" : score,
            }
        }

         await updateRanking(rankingData);
        await  saveGameData(gameData);
    }
}

export default GameApiClient;
