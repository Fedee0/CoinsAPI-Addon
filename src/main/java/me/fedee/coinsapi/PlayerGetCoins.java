package me.fedee.coinsapi;
import org.bukkit.entity.Player;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;

public class PlayerGetCoins extends Element {
    public PlayerGetCoins(UltraCustomizer plugin){
        super(plugin);
    }
    public String getName() {
        return "Player Get Coins";
    }

    public String getRequiredPlugin() {
        return "CoinsAPINB";
    }

    public String getInternalName() {
        return "player-get-coins";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.GOLD_INGOT;
    }

    public String[] getDescription() {
        return new String[] { "Retrieves the amount of Coins a player has" };
    }

    public Argument[] getArguments(ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo) };
    }

    public OutcomingVariable[] getOutcomingVariables(ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("player-coins", "Player Coins", DataType.NUMBER, elementInfo) };
    }

    public Child[] getConnectors(ElementInfo elementInfo) {
        return new Child[] {new DefaultChild(elementInfo, "next") };
    }

    public void run(ElementInfo info, ScriptInstance instance) {

        Player p = (Player) getArguments(info)[0].getValue(instance);

        getOutcomingVariables(info)[0].register(instance, new DataRequester() {
            public Object request() {

                int PlayerCoins = CoinsAPI.getCoins(p.getUniqueId().toString());
                return PlayerCoins;
            }
        });

        getConnectors(info)[0].run(instance);
    }
}
