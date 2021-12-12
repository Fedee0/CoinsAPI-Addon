package me.fedee.coinsapi;
import org.bukkit.entity.Player;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import org.bukkit.entity.Player;

public class PlayerSetCoins extends Element {
    public PlayerSetCoins(UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "Player Set Coins";
    }

    public String getRequiredPlugin() {
        return "CoinsAPINB";
    }

    public String getInternalName() {
        return "player-set-coins";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.GOLD_INGOT;
    }

    public String[] getDescription() {
        return new String[] { "Allows you to set a player's balance" };
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

        CoinsAPI.setCoins(p.getUniqueId().toString(), AmountNew);

        getConnectors(info)[0].run(instance);

    }
}
