package gmreskin;

import basemod.*;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PostUpdateSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.Defect;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.characters.TheSilent;
import com.megacrit.cardcrawl.characters.Watcher;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.*;
import gmreskin.patches.AbstractMonsterPatch;
import gmreskin.skins.SkinRenderer;
import gmreskin.ui.ModDropdownBox;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpireInitializer
public class GMReskin implements PostInitializeSubscriber,
                                 EditStringsSubscriber,
                                 PostUpdateSubscriber {
    private static final SpireConfig skinIndexInfo;
    private boolean enableDefaultSkin = true;
    private static final List<AbstractGameAction> actionList = new ArrayList<>();

    static {
        SpireConfig skinIndexInfo1;
        try {
            skinIndexInfo1 = new SpireConfig("GMReskin", "settings");
        } catch (IOException e) {
            skinIndexInfo1 = null;
            e.printStackTrace();
        }
        skinIndexInfo = skinIndexInfo1;
    }

    public static void initialize() {
        new GMReskin();
    }

    public GMReskin() {
        BaseMod.subscribe(this);
    }

    public static String makeID(String name) {
        return "GMReskin:" + name;
    }

    public static String getResourcePath(String resource) {
        return "GMImages/" + resource;
    }

    public static String getLanguageString() {
        String language;
        switch (Settings.language) {
            case ZHS:
                language = "zhs";
                break;
            default:
                language = "eng";
        }
        return language;
    }

    public static void setSkinIndex(Class<? extends AbstractCreature> cls, int index) {
        if (index < 0) index = 0;
        skinIndexInfo.setInt(cls.getName(), index);
    }

    public static int getSkinIndex(Class<? extends AbstractCreature> cls) {
        if (skinIndexInfo.has(cls.getName())) {
            return skinIndexInfo.getInt(cls.getName());
        }
        return 0;
    }

    public static void refreshSkin(AbstractCreature creature) {
        SkinRenderer oldSkinRenderer = AbstractMonsterPatch.AddFieldPatch.skinRenderer.get(creature);
        AbstractMonsterPatch.AddFieldPatch.skinRenderer.set(creature, SkinRenderer.getSkinRenderer(creature.getClass(), getSkinIndex(creature.getClass())));
        SkinRenderer skinRenderer = AbstractMonsterPatch.AddFieldPatch.skinRenderer.get(creature);
        if (skinRenderer != null && skinRenderer.isSkinLoaded()) {
            skinRenderer.setOwner(creature);
            if (creature instanceof AbstractMonster) {
                List<Disposable> disposables = ReflectionHacks.getPrivate(creature, AbstractMonster.class, "disposables");
                List<Disposable> toRemove = new ArrayList<>();
                for (Disposable disposable : disposables) {
                    if (disposable instanceof SkinRenderer && disposable != skinRenderer) {
                        toRemove.add(disposable);
                        disposable.dispose();
                    }
                }
                disposables.removeAll(toRemove);
                disposables.add(skinRenderer);
            } else {
                oldSkinRenderer.dispose();
            }
        }
    }

    public static void disableSkin(Class<? extends AbstractCreature> cls) {
        skinIndexInfo.setInt(cls.getName(), -1);
    }

    public static void saveSkinIndexInfo() {
        try {
            skinIndexInfo.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void disableAllOriginalSkins() {
        disableSkin(Ironclad.class);
        disableSkin(TheSilent.class);
        disableSkin(Defect.class);
        disableSkin(Watcher.class);
        disableCharacterSkinIndexFromOtherMod("Scapegoat", "demoMod.scapegoat.characters.ScapegoatCharacter");
        disableCharacterSkinIndexFromOtherMod("funnies", "characters.GodOfAbstract");
        disableCharacterSkinIndexFromOtherMod("DerFreischutz", "demoMod.derfreischutz.characters.DerFreischutzCharacter");
        disableCharacterSkinIndexFromOtherMod("IceBreaker", "demoMod.icebreaker.characters.IceBreakerCharacter");
        disableCharacterSkinIndexFromOtherMod("BlueTheAstray", "rs.wolf.theastray.characters.BlueTheAstray");
        disableSkin(AcidSlime_L.class);
        disableSkin(AcidSlime_M.class);
        disableSkin(AcidSlime_S.class);
        disableSkin(Byrd.class);
        disableSkin(Centurion.class);
        disableSkin(Chosen.class);
        disableSkin(Cultist.class);
        disableSkin(Darkling.class);
        disableSkin(Exploder.class);
        disableSkin(FungiBeast.class);
        disableSkin(GremlinFat.class);
        disableSkin(GremlinThief.class);
        disableSkin(GremlinTsundere.class);
        disableSkin(GremlinWarrior.class);
        disableSkin(GremlinWizard.class);
        disableSkin(Healer.class);
        disableSkin(JawWorm.class);
        disableSkin(Looter.class);
        disableSkin(LouseDefensive.class);
        disableSkin(LouseNormal.class);
        disableSkin(Maw.class);
        disableSkin(Mugger.class);
        disableSkin(OrbWalker.class);
        disableSkin(Repulsor.class);
        disableSkin(ShelledParasite.class);
        disableSkin(SlaverBlue.class);
        disableSkin(SlaverRed.class);
        disableSkin(SnakePlant.class);
        disableSkin(Snecko.class);
        disableSkin(SphericGuardian.class);
        disableSkin(Spiker.class);
        disableSkin(SpikeSlime_L.class);
        disableSkin(SpikeSlime_M.class);
        disableSkin(SpikeSlime_S.class);
        disableSkin(SpireGrowth.class);
        disableSkin(Transient.class);
        disableSkin(WrithingMass.class);
        disableSkin(Hexaghost.class);
        disableSkin(TheGuardian.class);
        disableSkin(SlimeBoss.class);
        disableSkin(Sentry.class);
        disableSkin(GremlinNob.class);
        disableSkin(Lagavulin.class);
        disableSkin(Taskmaster.class);
        disableSkin(BookOfStabbing.class);
        disableSkin(GremlinLeader.class);
        disableSkin(BronzeAutomaton.class);
        disableSkin(BronzeOrb.class);
        disableSkin(TheCollector.class);
        disableSkin(TorchHead.class);
        disableSkin(Champ.class);
        disableSkin(BanditLeader.class);
        disableSkin(BanditPointy.class);
        disableSkin(BanditBear.class);
        disableSkin(Nemesis.class);
        disableSkin(Reptomancer.class);
        disableSkin(SnakeDagger.class);
        disableSkin(Donu.class);
        disableSkin(Deca.class);
        disableSkin(TimeEater.class);
        disableSkin(AwakenedOne.class);
        disableSkin(SpireSpear.class);
        disableSkin(SpireShield.class);
        disableSkin(CorruptHeart.class);
    }

    @Override
    public void receivePostInitialize() {
        try {
            skinIndexInfo.load();
            if (skinIndexInfo.has("enableDefaultSkin")) {
                enableDefaultSkin = skinIndexInfo.getBool("enableDefaultSkin");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("ModPanel"));
        ModPanel settingsPanel = new ModPanel();

        ModLabeledToggleButton enableDefaultSkinOption = new ModLabeledToggleButton(uiStrings.TEXT[0], 350.0F, 700.0F, Color.WHITE, FontHelper.buttonLabelFont, enableDefaultSkin, settingsPanel, (me) -> {},
                (me) -> {
                    enableDefaultSkin = me.enabled;
                    if (!enableDefaultSkin) {
                        disableAllOriginalSkins();
                    } else {
                        int skinThemeSelect = skinIndexInfo.has("skinThemeSelect") ? skinIndexInfo.getInt("skinThemeSelect") : 0;
                        setSkinIndex(Ironclad.class, skinThemeSelect);
                        setSkinIndex(TheSilent.class, skinThemeSelect);
                        setSkinIndex(Defect.class, skinThemeSelect);
                        setSkinIndex(Watcher.class, skinThemeSelect);
                        setCharacterSkinIndexFromOtherMod("Scapegoat", "demoMod.scapegoat.characters.ScapegoatCharacter", 0);
                        setCharacterSkinIndexFromOtherMod("funnies", "characters.GodOfAbstract", 0);
                        setCharacterSkinIndexFromOtherMod("DerFreischutz", "demoMod.derfreischutz.characters.DerFreischutzCharacter", 0);
                        setCharacterSkinIndexFromOtherMod("IceBreaker", "demoMod.icebreaker.characters.IceBreakerCharacter", 0);
                        setCharacterSkinIndexFromOtherMod("BlueTheAstray", "rs.wolf.theastray.characters.BlueTheAstray", 0);
                        setSkinIndex(AcidSlime_L.class, 0);
                        setSkinIndex(AcidSlime_M.class, 0);
                        setSkinIndex(AcidSlime_S.class, 0);
                        setSkinIndex(Byrd.class, 0);
                        setSkinIndex(Centurion.class, 0);
                        setSkinIndex(Chosen.class, 0);
                        setSkinIndex(Cultist.class, 0);
                        setSkinIndex(Darkling.class, 0);
                        setSkinIndex(Exploder.class, 0);
                        setSkinIndex(FungiBeast.class, 0);
                        setSkinIndex(GremlinFat.class, 0);
                        setSkinIndex(GremlinThief.class, 0);
                        setSkinIndex(GremlinTsundere.class, 0);
                        setSkinIndex(GremlinWarrior.class, 0);
                        setSkinIndex(GremlinWizard.class, 0);
                        setSkinIndex(Healer.class, 0);
                        setSkinIndex(JawWorm.class, 0);
                        setSkinIndex(Looter.class, 0);
                        setSkinIndex(LouseDefensive.class, 0);
                        setSkinIndex(LouseNormal.class, 0);
                        setSkinIndex(Maw.class, 0);
                        setSkinIndex(Mugger.class, 0);
                        setSkinIndex(OrbWalker.class, 0);
                        setSkinIndex(Repulsor.class, 0);
                        setSkinIndex(ShelledParasite.class, 0);
                        setSkinIndex(SlaverBlue.class, 0);
                        setSkinIndex(SlaverRed.class, 0);
                        setSkinIndex(SnakePlant.class, 0);
                        setSkinIndex(Snecko.class, 0);
                        setSkinIndex(SphericGuardian.class, 0);
                        setSkinIndex(Spiker.class, 0);
                        setSkinIndex(SpikeSlime_L.class, 0);
                        setSkinIndex(SpikeSlime_M.class, 0);
                        setSkinIndex(SpikeSlime_S.class, 0);
                        setSkinIndex(SpireGrowth.class, 0);
                        setSkinIndex(Transient.class, 0);
                        setSkinIndex(WrithingMass.class, 0);
                        setSkinIndex(Hexaghost.class, 0);
                        setSkinIndex(TheGuardian.class, 0);
                        setSkinIndex(SlimeBoss.class, 0);
                        setSkinIndex(Sentry.class, 0);
                        setSkinIndex(GremlinNob.class, 0);
                        setSkinIndex(Lagavulin.class, 0);
                        setSkinIndex(Taskmaster.class, 0);
                        setSkinIndex(BookOfStabbing.class, 0);
                        setSkinIndex(GremlinLeader.class, 0);
                        setSkinIndex(BronzeAutomaton.class, 0);
                        setSkinIndex(BronzeOrb.class, 0);
                        setSkinIndex(TheCollector.class, 0);
                        setSkinIndex(TorchHead.class, 0);
                        setSkinIndex(Champ.class, 0);
                        setSkinIndex(BanditLeader.class, 0);
                        setSkinIndex(BanditPointy.class, 0);
                        setSkinIndex(BanditBear.class, 0);
                        setSkinIndex(Nemesis.class, 0);
                        setSkinIndex(Reptomancer.class, 0);
                        setSkinIndex(SnakeDagger.class, 0);
                        setSkinIndex(Donu.class, 0);
                        setSkinIndex(Deca.class, 0);
                        setSkinIndex(TimeEater.class, 0);
                        setSkinIndex(AwakenedOne.class, 0);
                        setSkinIndex(SpireSpear.class, 0);
                        setSkinIndex(SpireShield.class, 0);
                        setSkinIndex(CorruptHeart.class, 0);
                    }
                    skinIndexInfo.setBool("enableDefaultSkin", enableDefaultSkin);
                    saveSkinIndexInfo();
                }
        );
        ModLabel skinThemeSelectLabel = new ModLabel(uiStrings.TEXT[1], 350.0F, 650.0F, settingsPanel, me -> {});
        UIStrings skinThemeSelect = CardCrawlGame.languagePack.getUIString(makeID("SkinThemeSelect"));
        ModDropdownBox modDropdownBox = new ModDropdownBox(800.0F, 672.0F, skinThemeSelect.TEXT, skinIndexInfo.has("skinThemeSelect") ? skinIndexInfo.getInt("skinThemeSelect") : 0, index -> {
            setSkinIndex(Ironclad.class, index);
            setSkinIndex(TheSilent.class, index);
            setSkinIndex(Defect.class, index);
            setSkinIndex(Watcher.class, index);
            skinIndexInfo.setInt("skinThemeSelect", index);
            saveSkinIndexInfo();
        });
        settingsPanel.addUIElement(enableDefaultSkinOption);
        settingsPanel.addUIElement(skinThemeSelectLabel);
        settingsPanel.addUIElement(modDropdownBox);

        skip:
        for (ModInfo modInfo : Loader.MODINFOS) {
            for (String modId : modInfo.Dependencies) {
                if ("gm-reskin".equals(modId)) {
                    enableDefaultSkin = false;
                    disableAllOriginalSkins();
                    break skip;
                }
            }
        }

        BaseMod.registerModBadge(ImageMaster.loadImage(getResourcePath("ui/badge.png")), "GM Reskin", "Everyone", "TODO", settingsPanel);
    }

    private static void disableCharacterSkinIndexFromOtherMod(String modId, String charClassPath) {
        if (Loader.isModLoaded(modId)) {
            try {
                disableSkin((Class<? extends AbstractCreature>) Class.forName(charClassPath));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setCharacterSkinIndexFromOtherMod(String modId, String charClassPath, int index) {
        if (Loader.isModLoaded(modId)) {
            try {
                setSkinIndex((Class<? extends AbstractCreature>) Class.forName(charClassPath), index);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void receiveEditStrings() {
        String language = getLanguageString();
        String uiStrings = Gdx.files.internal("localization/" + language + "/GMReskin-UIStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(UIStrings.class, uiStrings);
    }

    @Override
    public void receivePostUpdate() {
        if (!actionList.isEmpty()) {
            actionList.get(0).update();
            if (actionList.get(0).isDone) {
                actionList.remove(0);
            }
        }
    }

    public static void addToBot(AbstractGameAction action) {
        actionList.add(action);
    }

    public static void addToTop(AbstractGameAction action) {
        actionList.add(0, action);
    }
}
