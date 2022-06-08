package com.samcom.starprinterutility.printerUtils;
import com.starmicronics.starioextension.ICommandBuilder;
import com.starmicronics.starioextension.StarIoExt;
public class CashDrawerFunctions {

    public static byte[] createData(StarIoExt.Emulation emulation, ICommandBuilder.PeripheralChannel channel) {
        ICommandBuilder builder = StarIoExt.createCommandBuilder(emulation);

        builder.beginDocument();

        builder.appendPeripheral(channel);

        builder.endDocument();

        return builder.getCommands();
    }
}
