package workshopOpg3;

import javafx.scene.control.TextField;

import java.io.InputStream;

public class TextFieldInputStream extends InputStream {

    private String data;
    private int pos = 0;

    public TextFieldInputStream(TextField textField) {
        this.data = textField.getText() + '\n'; // Add new line for termination
    }

    @Override
    public int read() {
        if (pos < data.length()) {
            return data.charAt(pos++);
        } else {
            return -1; // End of stream
        }
    }
}
