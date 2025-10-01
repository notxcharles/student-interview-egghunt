package me.candidate.miniproject.scaffold.config.example;

import me.candidate.miniproject.scaffold.other.FancyMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

/**
 * This file will be outputted in /plugins/student-miniproject/example-config.yml
 */
@ConfigSerializable
public class ExampleConfig {

    private String someData = "something_we_need";
    private FancyMessage someMessage = new FancyMessage("Hello world!");

    public FancyMessage someMessage() {
        return someMessage;
    }
}
