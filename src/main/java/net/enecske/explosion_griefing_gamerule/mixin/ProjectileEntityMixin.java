package net.enecske.explosion_griefing_gamerule.mixin;

import net.enecske.explosion_griefing_gamerule.ExplosionGriefingGamerule;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ProjectileEntity.class)
public class ProjectileEntityMixin {
    @Redirect(method = "canModifyAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean redirectMobGriefing(GameRules instance, GameRules.Key<GameRules.BooleanRule> rule) {
        if (rule == GameRules.DO_MOB_GRIEFING)
            return instance.getBoolean(ExplosionGriefingGamerule.EXPLOSION_GRIEFING);
        return instance.getBoolean(rule);
    }
}
