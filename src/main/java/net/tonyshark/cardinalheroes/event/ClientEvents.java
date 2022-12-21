package net.tonyshark.cardinalheroes.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tonyshark.cardinalheroes.CardinalHeroes;
import net.tonyshark.cardinalheroes.client.ThirstHudOverlay;
import net.tonyshark.cardinalheroes.networking.ModMessages;
import net.tonyshark.cardinalheroes.networking.packet.DrinkWaterC2SPacket;
import net.tonyshark.cardinalheroes.networking.packet.ExampleC2SPacket;
import net.tonyshark.cardinalheroes.util.KeyBinding;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = CardinalHeroes.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.DRINKING_KEY.consumeClick()) {
                ModMessages.sendToServer(new DrinkWaterC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = CardinalHeroes.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DRINKING_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("thirst", ThirstHudOverlay.HUD_THIRST);
        }
    }
}
