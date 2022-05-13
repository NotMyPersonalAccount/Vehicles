package vehicle.views;

import vehicle.Sketch;
import vehicle.views.components.Button;
import vehicle.views.components.Component;
import vehicle.views.components.Container;
import vehicle.views.components.Text;

import static vehicle.utils.Constants.BASE_TEXT_SIZE;

public class StartView extends View {
    public StartView(Sketch app) {
        super(app, new Component[]{new Container.Builder(app).setDirection(Container.Direction.VERTICAL).withComponents(
                new Text.Builder(app).setText("vehicle").setTextSize(BASE_TEXT_SIZE * 4).build(),
                new Button.Builder(app).setText("Start").setOnClick(() -> app.setView(new GameView(app))).build()
        ).build()});
    }
}
