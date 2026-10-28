package dev.combokeys.mixin;

import dev.combokeys.ComboKeysClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public abstract class MouseMixin {
    @Shadow private long window;

    @Inject(method = "onMouseButton", at = @At("HEAD"))
    private void combokeys$trackButtons(long window, int button, int action, int mods, CallbackInfo ci) {
        // GLFW: 0 = left button, 1 = right button; 1 = press, 0 = release.
        boolean pressed = action == 1;
        if (button == 0) {
            ComboKeysClient.leftHeld = pressed;
        } else if (button == 1) {
            ComboKeysClient.rightHeld = pressed;
        }
    }

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void combokeys$handleScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (vertical != 0 && (ComboKeysClient.leftHeld || ComboKeysClient.rightHeld)) {
            ComboKeysClient.handleScrollCombo(vertical);
            ci.cancel();
        }
    }
}
