package gmreskin.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireRawPatch;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.*;
import gmreskin.GMReskin;
import gmreskin.annotations.CustomSkinRenderer;
import gmreskin.annotations.CustomSkinRenders;
import gmreskin.skins.SkinRenderer;
import gmreskin.skins.characters.GeneralCharacterSkinRenderer;
import gmreskin.skins.monsters.*;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class MonsterAddAnnotationPatch {
    @SpirePatch(
            cls = "characters.GodOfAbstract",
            method = "getStartingDeck",
            optional = true
    )
    public static class PatchGodOfAbstract {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(GeneralCharacterSkinRenderer.class, GMReskin.getResourcePath("skins/characters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            cls = "demoMod.scapegoat.characters.ScapegoatCharacter",
            method = "render",
            optional = true
    )
    public static class PatchScapegoat {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(GeneralCharacterSkinRenderer.class, GMReskin.getResourcePath("skins/characters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }

        public static ExprEditor Instrument() {
            return new ExprEditor() {
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals("com.megacrit.cardcrawl.stances.AbstractStance") && m.getMethodName().equals("render")) {
                        m.replace("super.render(sb);if (true) return;");
                    }
                }
            };
        }
    }

    @SpirePatch(
            cls = "demoMod.derfreischutz.characters.DerFreischutzCharacter",
            method = "render",
            optional = true
    )
    public static class PatchDerFreischutz {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(GeneralCharacterSkinRenderer.class, GMReskin.getResourcePath("skins/characters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }

        public static ExprEditor Instrument() {
            return new ExprEditor() {
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals("com.megacrit.cardcrawl.stances.AbstractStance") && m.getMethodName().equals("render")) {
                        m.replace("super.render(sb);if (true) return;");
                    }
                }
            };
        }
    }

    @SpirePatch(
            clz = CorruptHeart.class,
            method = "takeTurn"
    )
    public static class PatchCorruptHeart {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(CorruptHeartSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = SpireShield.class,
            method = "takeTurn"
    )
    public static class PatchSpireShield {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(SpireShieldSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = SpireSpear.class,
            method = "takeTurn"
    )
    public static class PatchSpireSpear {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(SpireSpearSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = AwakenedOne.class,
            method = "takeTurn"
    )
    public static class PatchAwakenedOne {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(AwakenedOneSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = TimeEater.class,
            method = "takeTurn"
    )
    public static class PatchTimeEater {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(TimeEaterSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Deca.class,
            method = "takeTurn"
    )
    public static class PatchDeca {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(DecaSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Donu.class,
            method = "takeTurn"
    )
    public static class PatchDonu {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(DonuSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = SnakeDagger.class,
            method = "takeTurn"
    )
    public static class PatchSnakeDagger {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(SnakeDaggerSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Reptomancer.class,
            method = "takeTurn"
    )
    public static class PatchReptomancer {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(ReptomancerSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Nemesis.class,
            method = "takeTurn"
    )
    public static class PatchNemesis {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(NemesisSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = BanditBear.class,
            method = "takeTurn"
    )
    public static class PatchBanditBear {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(BanditBearSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = BanditPointy.class,
            method = "takeTurn"
    )
    public static class PatchBanditPointy {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(BanditPointySkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = BanditLeader.class,
            method = "takeTurn"
    )
    public static class PatchBanditLeader {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(BanditLeaderSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Champ.class,
            method = "takeTurn"
    )
    public static class PatchChamp {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(ChampSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = TheCollector.class,
            method = "takeTurn"
    )
    public static class PatchTheCollector {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(TheCollectorSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = TorchHead.class,
            method = "takeTurn"
    )
    public static class PatchTorchHead {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(TorchHeadSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = BronzeAutomaton.class,
            method = "takeTurn"
    )
    public static class PatchBronzeAutomaton {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(BronzeAutomatonSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = GremlinLeader.class,
            method = "takeTurn"
    )
    public static class PatchGremlinLeader {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(GremlinLeaderSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = BookOfStabbing.class,
            method = "takeTurn"
    )
    public static class PatchBookOfStabbing {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(BookOfStabbingSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Lagavulin.class,
            method = "takeTurn"
    )
    public static class PatchLagavulin {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(LagavulinSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Sentry.class,
            method = "takeTurn"
    )
    public static class PatchSentry {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(SentrySkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = SlimeBoss.class,
            method = "takeTurn"
    )
    public static class PatchSlimeBoss {

    }@SpireRawPatch
    public static void Raw(CtBehavior ctMethodToPatch) {
        CtClass ctClass = ctMethodToPatch.getDeclaringClass();
        ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
        Annotation skin1 = setCustomSkinRenderer(SlimeBossSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
        addAnnotations(ctMethodToPatch, skin1);
    }

    @SpirePatch(
            clz = TheGuardian.class,
            method = "takeTurn"
    )
    public static class PatchTheGuardian {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(TheGuardianRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Hexaghost.class,
            method = "takeTurn"
    )
    public static class PatchHexaghost {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(HexaghostSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Transient.class,
            method = "takeTurn"
    )
    public static class PatchTransient {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(TransientSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = WrithingMass.class,
            method = "takeTurn"
    )
    public static class PatchWrithingMass {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(WrithingMassSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = SpireGrowth.class,
            method = "takeTurn"
    )
    public static class PatchSpireGrowth {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(SpireGrowthSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Healer.class,
            method = "takeTurn"
    )
    public static class PatchHealer {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(HealerSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Centurion.class,
            method = "takeTurn"
    )
    public static class PatchCenturion {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(CenturionSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Snecko.class,
            method = "takeTurn"
    )
    public static class PatchSnecko {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(SneckoSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = SnakePlant.class,
            method = "takeTurn"
    )
    public static class PatchSnakePlant {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(SnakePlantSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = LouseNormal.class,
            method = "takeTurn"
    )
    public static class PatchLouseNormal {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(LouseNormalSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = LouseDefensive.class,
            method = "takeTurn"
    )
    public static class PatchLouseDefensive {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(LouseDefensiveSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Chosen.class,
            method = "takeTurn"
    )
    public static class PatchChosen {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(ChosenSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = SphericGuardian.class,
            method = "takeTurn"
    )
    public static class PatchSphericGuardian {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(SphericGuardianSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = Byrd.class,
            method = "takeTurn"
    )
    public static class PatchByrd {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(ByrdSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    @SpirePatch(
            clz = FungiBeast.class,
            method = "takeTurn"
    )
    public static class PatchFungiBeast {
        @SpireRawPatch
        public static void Raw(CtBehavior ctMethodToPatch) {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();
            ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
            Annotation skin1 = setCustomSkinRenderer(FungiBeastSkinRenderer.class, GMReskin.getResourcePath("skins/monsters/" + ctClass.getSimpleName() + ".xml"), 0, constPool);
            addAnnotations(ctMethodToPatch, skin1);
        }
    }

    private static Annotation setCustomSkinRenderer(Class<? extends SkinRenderer> skinCls, String anm2Path, int index, ConstPool constPool) {
        Annotation annotation = new Annotation(CustomSkinRenderer.class.getName(), constPool);
        annotation.addMemberValue("skinCls", new ClassMemberValue(skinCls.getName(), constPool));
        annotation.addMemberValue("anm2Path", new StringMemberValue(anm2Path, constPool));
        annotation.addMemberValue("index", new IntegerMemberValue(constPool, index));
        return annotation;
    }

    private static void addAnnotations(CtBehavior ctMethodToPatch, Annotation... annotationsToAdd) {
        CtClass ctClass = ctMethodToPatch.getDeclaringClass();
        ConstPool constPool = ctMethodToPatch.getMethodInfo().getConstPool();
        AnnotationsAttribute attributes = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation skins = new Annotation(CustomSkinRenders.class.getName(), constPool);
        ArrayMemberValue arrayMemberValue = new ArrayMemberValue(constPool);
        AnnotationMemberValue[] annotationMemberValues = new AnnotationMemberValue[annotationsToAdd.length];
        for (int i=0;i<annotationsToAdd.length;i++) {
            annotationMemberValues[i] = new AnnotationMemberValue(annotationsToAdd[i], constPool);
        }
        arrayMemberValue.setValue(annotationMemberValues);
        skins.addMemberValue("value", arrayMemberValue);
        attributes.addAnnotation(skins);
        ctClass.getClassFile().addAttribute(attributes);
    }
}
