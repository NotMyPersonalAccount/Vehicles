package vehicle.views;

import vehicle.Sketch;
import vehicle.views.components.Button;
import vehicle.views.components.Component;
import vehicle.views.components.Container;

public class PauseView extends View {
    protected final GameView pausedView;

    public PauseView(Sketch app, GameView pausedView) {
        super(app, new Component[]{new Container.Builder(app).setDirection(Container.Direction.VERTICAL).withComponents(
                new Button.Builder(app).setText("Resume").setOnClick(() -> app.setView(pausedView)).build(),
                new Button.Builder(app).setText("Restart").setOnClick(() -> app.setView(new ConfirmationView(app, pausedView, "Are you sure you want to restart?", () -> app.setView(new GameView(app)), () -> app.setView(new PauseView(app, pausedView))))).build()
        ).build()});
        this.pausedView = pausedView;
    }

    public void draw() {
        pausedView.draw();
        super.draw();
    }
}
