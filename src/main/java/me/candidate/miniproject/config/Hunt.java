package me.candidate.miniproject.config;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class Hunt {
    private String egg_id;

    private int x;
    private int y;
    private int z;

    private String claimMessage;
    private String claimRewardCommand;


    public Hunt() {}


    // Getters
    public String GetEggID()
    {
        return egg_id;
    }

    public int GetX()
    {
        return x;
    }
    public int GetY()
    {
        return y;
    }
    public int GetZ()
    {
        return z;
    }

    public String GetClaimMessage()
    {
        return claimMessage;
    }
    public String GetClaimRewardCommand()
    {

        return claimRewardCommand;
    }

}
