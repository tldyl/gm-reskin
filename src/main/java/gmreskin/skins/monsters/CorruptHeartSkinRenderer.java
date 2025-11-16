package gmreskin.skins.monsters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import gmreskin.skins.SkinRenderer;

public class CorruptHeartSkinRenderer extends SkinRenderer {
    public CorruptHeartSkinRenderer(String skinPath) {
        super(skinPath);
        if (this.isSkinLoaded()) {
            this.animation.scale = 2.3F;
            this.animation.addTriggerEvent("0", a -> {
                CardCrawlGame.sound.playAV("HEART_SIMPLE", MathUtils.random(-0.05F, 0.05F), 0.75F);
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
            });
            this.animation.getCurAnimation().getLayerAnimation("2").setBlendSrcFunction(770);
            this.animation.getCurAnimation().getLayerAnimation("2").setBlendDstFunction(1);
        }
    }

    @Override
    public void update() {
        this.animation.xPosition = this.owner.hb.cX + this.owner.animX;
        this.animation.yPosition = this.owner.hb.y + this.owner.animY;
        this.animation.flipX = this.owner.flipHorizontal;
        this.animation.flipY = this.owner.flipVertical;
        this.animation.alpha = this.owner.tint.color.a * 255.0F;
        this.animation.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        this.animation.render(sb);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
