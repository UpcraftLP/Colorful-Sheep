package mod.upcraftlp.colorfulsheep;

import com.google.common.collect.Maps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Map;
import java.util.UUID;

/**
 * @author UpcraftLP
 */
@Mod.EventBusSubscriber(modid = Reference.MODID)
public class SheepSpawnHandler {

    private static final Map<Integer, EnumDyeColor> COLOR_QUEUE = Maps.newConcurrentMap();

    @SubscribeEvent
    public static void onUpdateEntity(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntityLiving() instanceof EntitySheep) {
            EntitySheep sheep = (EntitySheep) event.getEntityLiving();
            int id = sheep.getEntityId();
            if(COLOR_QUEUE.containsKey(id)) {
                sheep.setFleeceColor(COLOR_QUEUE.get(sheep.getEntityId()));
                COLOR_QUEUE.remove(id);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onSpawnSheep(LivingSpawnEvent.CheckSpawn event) {
        if(event.getWorld() instanceof WorldServer && event.getEntityLiving() instanceof EntitySheep) {
            EntitySheep sheep = (EntitySheep) event.getEntityLiving();
            if(!sheep.isChild()) {
                double completeWeight = 0.0;
                for(String color : ModConfig.WEIGHTS.keySet()) {
                    completeWeight += ModConfig.WEIGHTS.get(color);
                }
                double result = sheep.getRNG().nextDouble() * completeWeight;
                double countWeight = 0.0D;
                for(String color : ModConfig.WEIGHTS.keySet()) {
                    countWeight += ModConfig.WEIGHTS.get(color);
                    if(countWeight >= result) {
                        COLOR_QUEUE.put(sheep.getEntityId(), EnumDyeColor.valueOf(color));
                        break;
                    }
                }
            }
        }
    }
}
