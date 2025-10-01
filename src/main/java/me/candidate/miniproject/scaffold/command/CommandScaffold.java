package me.candidate.miniproject.scaffold.command;

import me.candidate.miniproject.MiniProjectPlugin;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.SenderMapper;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.LegacyPaperCommandManager;
import org.incendo.cloud.setting.ManagerSetting;
import org.slf4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class CommandScaffold {

    final LegacyPaperCommandManager<CommandSender> commandManager;
    final AnnotationParser<CommandSender> annotationParser;

    public CommandScaffold(MiniProjectPlugin plugin) {

        Logger logger = plugin.getSLF4JLogger();

        this.commandManager = new LegacyPaperCommandManager<>(
                plugin,
                ExecutionCoordinator.asyncCoordinator(),
                SenderMapper.identity()
        );

        commandManager.settings().set(ManagerSetting.ALLOW_UNSAFE_REGISTRATION, true);
        commandManager.settings().set(ManagerSetting.OVERRIDE_EXISTING_COMMANDS, true);

        annotationParser = new AnnotationParser<>(
                commandManager,
                CommandSender.class
        );

        logger.info("CommandScaffold initialized");

    }

    public void registerAnnotatedCommand(AnnotationCommand command) {
        annotationParser.parse(command);
    }

    public void registerAnnotatedCommand(Class<AnnotationCommand> command) {
        try {
            annotationParser.parse(command.getConstructor().newInstance());
        } catch (NoSuchMethodException |
                 InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

}
