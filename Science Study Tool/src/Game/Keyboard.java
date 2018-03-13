package Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Keyboard{

    public int keyCode;
    public boolean isPressed;

    public Keyboard(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                switch (e.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        keyCode = e.getKeyCode();
                        isPressed = true;
                        break;

                    case KeyEvent.KEY_RELEASED:
                        keyCode = 0;
                        isPressed = false;
                        break;
                }
                return false;
            }
        });
    }
}
