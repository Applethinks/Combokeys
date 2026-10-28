package dev.combokeys;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.Perspective;
import net.minecraft.text.Text;

public class ComboKeysClient implements ClientModInitializer {
    public static boolean leftHeld = false;
    public static boolean rightHeld = false;

    @Override
    public void onInitializeClient() {
        // Mouse combo handling is implemented in MouseMixin.
    }

    public static void handleScrollCombo(double vertical) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client == null || client.player == null || client.world == null || vertical == 0) {
            return;
        }

        // Right mouse button + wheel:
        // Scroll up = open inventory; scroll down = close the current screen.
        if (rightHeld) {
            if (vertical > 0) {
                client.setScreen(new InventoryScreen(client.player));
                notifyPlayer(client, "ComboKeys: Inventory");
            } else {
                if (client.currentScreen != null) {
                    client.setScreen(null);
                    notifyPlayer(client, "ComboKeys: Back / close screen");
                }
            }
            return;
        }

        // Left mouse button + wheel:
        // Scroll up = toggle perspective; scroll down = drop one item.
        if (leftHeld) {
            if (vertical > 0) {
                Perspective next = client.options.getPerspective() == Perspective.FIRST_PERSON
                        ? Perspective.THIRD_PERSON_BACK
                        : Perspective.FIRST_PERSON;
                client.options.setPerspective(next);
                notifyPlayer(client, "ComboKeys: Perspective changed");
            } else if (client.player.getMainHandStack() != null
                    && !client.player.getMainHandStack().isEmpty()) {
                client.player.dropSelectedItem(false);
                notifyPlayer(client, "ComboKeys: Dropped one item");
            }
        }
    }

    private static void notifyPlayer(MinecraftClient client, String message) {
        if (client.player != null) {
            client.player.sendMessage(Text.literal(message), true);
        }
    }
}
