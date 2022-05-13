package vehicle.views;

import vehicle.Sketch;
import vehicle.views.components.Button;
import vehicle.views.components.Component;
import vehicle.views.components.Container;
import vehicle.views.components.Text;

public class ConfirmationView extends View {
    private final View previousView;

    public ConfirmationView(Sketch app, View previousView, String message, Runnable onConfirm, Runnable onCancel) {
        super(app, new Component[]{new Container.Builder(app).setDirection(Container.Direction.VERTICAL).withComponents(
                new Text.Builder(app).setText(message).build(),
                new Button.Builder(app).setText("Confirm").setOnClick(onConfirm).build(),
                new Button.Builder(app).setText("Cancel").setOnClick(onCancel).build()
        ).build()});
        this.previousView = previousView;
    }

    public void draw() {
        previousView.draw();

        super.draw();
    }
}
