package controller;

import com.example.conference.commands.ICommand;
import com.example.conference.controller.CommandResolver;
import org.junit.Assert;
import org.junit.Test;

public class CommandResolverTest {
    @Test
    public void checkCommand() {
        String action = "login";
        ICommand com = CommandResolver.getInstance().getCommand(action);
        Assert.assertTrue(com!=null);
        action = "unknown";
        com = CommandResolver.getInstance().getCommand(action);
        Assert.assertTrue(com==null);
    }
}
