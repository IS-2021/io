import Game from "./Game.mjs";
import Menu from "./Menu.mjs";


const menu = new Menu();
menu.getCanvas().addEventListener("click", (event) => {
  menu.handleMouseClick(event);
});
