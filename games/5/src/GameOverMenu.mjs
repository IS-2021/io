class GameOverMenu {
  #score;
  #context;
  #canvas;
  #GameOverMenuIsOnTop = false;

  #img = new Image();
  constructor(canvas, score) {
    this.#canvas = canvas;
    this.#context = canvas.getContext("2d");
    this.#score = score;
    this.#img.src = "./img/karol.jpg";

    this.retryButton = {
      x: 200,
      y: 250,
      width: 200,
      height: 80,
    };

    this.menuButton = {
      x: 200,
      y: 350,
      width: 200,
      height: 80,
    };

    this.#canvas.addEventListener("click", this.handleButtonClick.bind(this));
  }

  draw(score, highScore) {
    this.#GameOverMenuIsOnTop = true;

    this.#score = score;
    this.#context.drawImage(
      this.#img,
      0,
      0,
      this.#canvas.width,
      this.#canvas.height
    );

      if(score > highScore){
        this.#context.fillStyle = "yellow";
      this.#context.font = "70px Arial";
      this.#context.fillText("New personal record!", 200, 100);
      }
    this.#context.fillStyle = "white";
    this.#context.font = "40px Arial";
    this.#context.fillText("Game Over", 200, 200);
    this.#context.fillText("Score: " + this.#score, 200, 150);

    this.#context.fillStyle = "rgb(20,20,20)";
    this.#context.fillRect(
      this.retryButton.x,
      this.retryButton.y,
      this.retryButton.width,
      this.retryButton.height
    );
    this.#context.fillRect(
      this.menuButton.x,
      this.menuButton.y,
      this.menuButton.width,
      this.menuButton.height
    );

    this.#context.fillStyle = "white";
    this.#context.font = "50px Arial";
    this.#context.fillText(
      "Retry",
      this.retryButton.x + 40,
      this.retryButton.y + 55
    );
    this.#context.fillText(
      "Menu",
      this.menuButton.x + 40,
      this.menuButton.y + 55
    );
  }

  handleButtonClick(event) {
    if(this.#GameOverMenuIsOnTop){
    const mouseX = event.clientX - this.#canvas.getBoundingClientRect().left;
    const mouseY = event.clientY - this.#canvas.getBoundingClientRect().top;

    if (
      mouseX >= this.retryButton.x &&
      mouseX <= this.retryButton.x + this.retryButton.width &&
      mouseY >= this.retryButton.y &&
      mouseY <= this.retryButton.y + this.retryButton.height
    ) {
        this.#GameOverMenuIsOnTop = false;
      this.handleRetryButtonClick();
    } else if (
      mouseX >= this.menuButton.x &&
      mouseX <= this.menuButton.x + this.menuButton.width &&
      mouseY >= this.menuButton.y &&
      mouseY <= this.menuButton.y + this.menuButton.height
    ) {
        this.#GameOverMenuIsOnTop = false;
      this.handleMenuButtonClick();
    }}
  }

  handleRetryButtonClick() {
    if (this.onRetryClickedCallback) {
      this.onRetryClickedCallback();
    }
  }

  onRetryClickedCallback(callback) {
    this.onRetryClickedCallback = callback;
  }

  handleMenuButtonClick() {
    if (this.onMenuClickedCallback) {
      this.onMenuClickedCallback();
    }
  }

  onMenuClickedCallback(callback) {
    this.onMenuClickedCallback = callback;
  }
}

export default GameOverMenu;
