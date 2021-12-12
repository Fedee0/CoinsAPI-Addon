package me.fedee.coinsapi;
import org.bukkit.entity.Player;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;

public class PlayerAddCoins extends Element {
    public PlayerAddCoins(UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "Player Add Coins";
    }

    public String getRequiredPlugin() {
        return "CoinsAPINB";
    }

    public String getInternalName() {
        return "player-add-coins";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.GOLD_INGOT;
    }

    public String[] getDescription() {
        return new String[] { "Allows you to add Coins to a specified player" };
    }

    public Argument[] getArguments(ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo),
                         new Argument("amount", "Amount", DataType.STRING, elementInfo)};
    }

    public OutcomingVariable[] getOutcomingVariables(ElementInfo elementInfo) {
        return new OutcomingVariable[0];
    }

    public Child[] getConnectors(ElementInfo elementInfo) {
        return new Child[] {new DefaultChild(elementInfo, "next") };
    }

    public void run(ElementInfo info, ScriptInstance instance) {

        Player p = (Player) getArguments(info)[0].getValue(instance);
        String Amount = (String) getArguments(info)[1].getValue(instance);
        int AmountNew = Integer.parseInt(Amount);

        CoinsAPI.addCoins(p.getUniqueId().toString(), AmountNew);

        getConnectors(info)[0].run(instance);
    }
}
