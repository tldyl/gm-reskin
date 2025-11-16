package gmreskin.ui;

import basemod.IUIElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.options.DropdownMenu;

import java.util.function.Consumer;

public class ModDropdownBox implements IUIElement {
    private float x;
    private float y;
    private final DropdownMenu dropdownMenu;

    public ModDropdownBox(float xPos, float yPos, String[] options, int defaultIndex, Consumer<Integer> onSelected) {
        this.x = xPos * Settings.scale;
        this.y = yPos * Settings.scale;
        this.dropdownMenu = new DropdownMenu((menu, index, text) -> {
            if (onSelected != null) {
                onSelected.accept(index);
            }
        }, options, FontHelper.tipBodyFont, Settings.CREAM_COLOR.cpy());
        this.dropdownMenu.setSelectedIndex(defaultIndex);
    }

    @Override
    public void render(SpriteBatch sb) {
        dropdownMenu.render(sb, x, y);
    }

    @Override
    public void update() {
        dropdownMenu.update();
    }

    @Override
    public int renderLayer() {
        return 1;
    }

    @Override
    public int updateOrder() {
        return 1;
    }

    @Override
    public void set(float xPos, float yPos) {
        this.x = xPos * Settings.scale;
        this.y = yPos * Settings.scale;
    }

    @Override
    public void setX(float xPos) {
        this.set(xPos, this.y / Settings.scale);
    }

    @Override
    public void setY(float yPos) {
        this.set(this.x / Settings.scale, yPos);
    }

    @Override
    public float getX() {
        return this.x / Settings.scale;
    }

    @Override
    public float getY() {
        return this.y / Settings.scale;
    }
}
