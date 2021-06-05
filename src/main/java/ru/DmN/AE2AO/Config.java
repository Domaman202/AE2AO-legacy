package ru.DmN.AE2AO;

import codechicken.lib.packet.PacketCustom;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class Config implements Cloneable {
    public Config() { }
    //
    public boolean DisableChannels = false;
    public boolean ControllerLimits = false;
    //
    public int Max_X = 7;
    public int Max_Y = 7;
    public int Max_Z = 7;
    // Storage Cell Fire Damage
    public boolean SCFD = false;
    //
    public boolean ChatInfo = true;
    //
    public Config clone() {
        try { super.clone(); } catch (CloneNotSupportedException ignored) { }

        Config c = new Config();

        c.DisableChannels = DisableChannels;
        c.ControllerLimits = ControllerLimits;
        c.SCFD = SCFD;
        c.Max_X = Max_X;
        c.Max_Y = Max_Y;
        c.Max_Z = Max_Z;
        c.ChatInfo = ChatInfo;

        return c;
    }
    // Networking
    public void ofPacket(PacketCustom packet) {
        DisableChannels = packet.readBoolean();
        ControllerLimits = packet.readBoolean();
        SCFD = packet.readBoolean();
        ChatInfo = packet.readBoolean();
        Max_X = packet.readInt();
        Max_Y = packet.readInt();
        Max_Z = packet.readInt();

        if (ChatInfo) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(
                    "AE2AO config loaded!\nControllerLimits = " + ControllerLimits +
                            "\nDisableChannels = " + DisableChannels +
                            "\nSCFD = " + SCFD +
                            "\nMax_X = " + Max_X +
                            "\nMax_Y = " + Max_Y +
                            "\nMax_Z = " + Max_Z
            ));
        }
    }

    public PacketCustom toPacket(PacketCustom packet) {
        packet.writeBoolean(DisableChannels);
        packet.writeBoolean(ControllerLimits);
        packet.writeBoolean(SCFD);
        packet.writeBoolean(ChatInfo);
        packet.writeInt(Max_X);
        packet.writeInt(Max_Y);
        packet.writeInt(Max_Z);
        return packet;
    }
}