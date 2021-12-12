package me.fedee.coinsapi;
import org.bukkit.entity.Player;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;

public class PlayerHasCoins extends Element {
    public PlayerHasCoins(UltraCustomizer plugin){
        super(plugin);
    }
    public String getName() {
        return "Player Has Coins";
    }

    public String getRequiredPlugin() {
        return "CoinsAPINB";
    }

    public String getInternalName() {
        return "player-has-coins";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.GOLD_INGOT;
    }

    public String[] getDescription() {
        return new String[] { "Check if the player has the specified amount of Coins" };
    }

    public Argument[] getArguments(ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo),
                new Argument("amount", "Amount", DataType.STRING, elementInfo)};
    }

    public OutcomingVariable[] getOutcomingVariables(ElementInfo elementInfo) {
        return new OutcomingVariable[0];
    }

    public Child[] getConnectors(final ElementInfo elementInfo) {
        return new Child[] { new Child(elementInfo, "yes") {
            public String getName() {
                return "Has the Coins";
            }

            public String[] getDescription() {
                return new String[] { "Will be executed if the player", "has the specified amount of coins" };
            }

            public XMaterial getIcon() {
                return XMaterial.LIME_STAINED_GLASS_PANE;
            }
        }, new Child(elementInfo, "no") {
            public String getName() {
                return "Doesn't have the Coins";
            }

            public String[] getDescription() {
                return new String[] { "Will be executed if the player", "doesn't have the specified amount of coins" };
            }

            public XMaterial getIcon() {
                return XMaterial.RED_STAINED_GLASS_PANE;
            }
        } };
    }

    public void run(ElementInfo info, ScriptInstance instance) {

        Player p = (Player) getArguments(info)[0].getValue(instance);
        String Amount = (String) getArguments(info)[1].getValue(instance);
        int AmountNew = Integer.parseInt(Amount);

        int PlayerCoins = CoinsAPI.getCoins(p.getUniqueId().toString());

        if(PlayerCoins >= AmountNew) {
            getConnectors(info)[0].run(instance);
        } else {
            getConnectors(info)[1].run(instance);
        }

    }
}

