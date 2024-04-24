package Object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


public class CustomTextField {

    public String text = "";
    public boolean isSelected = false;
    public boolean isEditing = false;
    public void draw(Graphics2D g2, int x, int y, int width, int height) {
        // Draw border
    	if(isSelected==false) {
    		g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(6));
            g2.drawRect(x, y, width, height);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(6));
            g2.drawRect(x+5, y+5, width, height);
    	}else {
    		g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(6));
            g2.drawRect(x, y, width, height);
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(6));
            g2.drawRect(x+5, y+5, width, height);
    	}
        

        // Draw text
        g2.setColor(Color.black);
        g2.drawString(text, x + 8, y + 46);
        g2.setColor(Color.white);
        g2.drawString(text, x + 12, y + 50);

        // Draw cursor if selected and editing
        if (isSelected && isEditing) {
            int textWidth = g2.getFontMetrics().stringWidth(text);
            g2.drawLine(x + textWidth + 5, y + 5, x + textWidth + 5, y + 53);
        }
    }
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void startEditing() {
        isEditing = true;
    }

    public void stopEditing() {
        isEditing = false;
    }

    public void processKeyEvent(KeyEvent e) {
        if (isEditing) {
            char keyChar = e.getKeyChar();
            int maxLength = 14;
            if (keyChar == KeyEvent.VK_BACK_SPACE && text.length() > 0) {
                text = text.substring(0, text.length() - 1);
            } else if ((Character.isLetterOrDigit(keyChar) || keyChar == '.')&& text.length() < maxLength) {
                text += keyChar;
                System.out.println(text);
            }
        }
    }
}