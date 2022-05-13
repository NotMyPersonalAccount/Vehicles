package vehicle.views;

import vehicle.Sketch;
import vehicle.views.components.*;

import static vehicle.Sketch.*;
import static vehicle.utils.Constants.BASE_TEXT_SIZE;

public class EndView extends View {
    public EndView(Sketch app, int score) {
        super(app, new Component[]{new Container.Builder(app).setDirection(Container.Direction.VERTICAL).withComponents(
                new Text.Builder(app).setText("You Died!").setTextSize(BASE_TEXT_SIZE * 3).build(),
                new Text.Builder(app).setText("Score: " + score).build(),
                new Button.Builder(app).setText("Play Again").setOnClick(() -> app.setView(new GameView(app))).build()
        ).build()});
    }
}
