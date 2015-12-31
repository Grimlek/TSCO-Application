package control.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import view.MainFrame;

public final class CloseAction
    extends AbstractAction
{
    public CloseAction(String name)
    {
        super(name);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        MainFrame.getMainFrameInstance().getFrame().dispose();
        System.exit(0);
    }
}
