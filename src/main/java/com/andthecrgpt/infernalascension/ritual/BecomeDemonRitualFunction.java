package com.andthecrgpt.infernalascension.ritual;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class BecomeDemonRitualFunction {
    public boolean isValid(ServerWorld world, BlockPos pos, Inventory inventory) {
        PlayerEntity player = world.getClosestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 8.0, false);
        if (player != null && player.getScoreboardTags().contains("is_demon")) {
            player.sendMessage(Text.literal("Sei già un demone."), true);
            return false;
        }
        return true;
    }

    public void start(ServerWorld world, BlockPos pos) {
        PlayerEntity player = world.getClosestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 8.0, false);
        if (player != null) {
            player.addScoreboardTag("is_demon");
            world.spawnParticles(ParticleTypes.FLAME, player.getX(), player.getY() + 1, player.getZ(), 50, 0.5, 1, 0.5, 0.01);
            player.sendMessage(Text.literal("Il patto è stato sigillato. Sei diventato un demone."), false);
        }
    }
}