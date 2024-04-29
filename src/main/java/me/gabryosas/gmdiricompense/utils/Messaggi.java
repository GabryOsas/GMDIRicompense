package me.gabryosas.gmdiricompense.utils;

import me.gabryosas.gmdiricompense.GMDIRicompense;

public enum Messaggi {
    RICOMPENSA_RISCATTATA("Messaggi.Ricompensa-Riscattata"),
    RICOMPENSA_NON_TROVATA("Messaggi.Ricompensa-Non-Trovata"),
    RICOMPENSA_PERMESSI("Messaggi.Ricompensa-Permessi"),
    RICOMPENSA_RELOAD("Messaggi.Ricompensa-Reload");

    private final String configKey;

    Messaggi(String configKey) {
        this.configKey = configKey;
    }

    public String getMessage() {
        return Color.translateHexColorCodes(GMDIRicompense.getInstance().getConfig().getString(configKey));
    }
}