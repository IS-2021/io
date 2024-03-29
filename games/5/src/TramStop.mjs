import Tram from "./Tram.mjs";
class TramStop {
  #passengersWaiting;
  #track;
  #x = 1200;
  #y;
  #width;
  #height = 50;
  #speed;
  #context;
  #img = new Image();
   
  constructor(passengersWaiting, context, track, speed, width) {
    this.#passengersWaiting = passengersWaiting;
    this.#track = track;
    this.#speed = speed;
    this.#width = width;
    this.#context = context;
    this.#y = this.#track * 100 + 60;
    this.#img.src = "./img/tramstop.png";
  }
  checkCloseness(tram) {
    if (
      tram.getTrack() === this.#track &&
      tram.getX() < this.#x + this.#width &&
      tram.getX() + tram.getWidth() > this.#x
    ) {
      return true;
    }
    return false;
  }
  draw() {
    
    this.#context.fillStyle = "rgba(100, 200, 250, 0.8)";
    this.#context.fillRect(this.#x, this.#y, this.#width, this.#height);
    this.#context.drawImage(this.#img, this.#x + 75, this.#y -40 , 50, 50);

  }

  pickPassengers() {
    const a = this.#passengersWaiting;
    this.#passengersWaiting = 0;
    return a;
  }
  getTrack() {
    return this.#track;
  }
  
  getX() {
    return this.#x;
  }
  getWidth() {
    return this.#width;
  }
  update() {
    this.#x -= this.#speed;
  }
}
export default TramStop;
