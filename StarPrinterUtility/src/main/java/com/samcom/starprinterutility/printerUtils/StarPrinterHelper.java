package com.samcom.starprinterutility.printerUtils;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.samcom.starprinterutility.interfaces.PrintInterface;
import com.samcom.starprinterutility.model.PrinterSettings;
import com.samcom.starprinterutility.printerUtils.functions.PrinterFunctions;
import com.samcom.starprinterutility.printerUtils.localizerreceipt.ILocalizeReceipts;
import com.starmicronics.starioextension.ICommandBuilder;
import com.starmicronics.starioextension.StarIoExt;
import com.starmicronics.starioextension.StarIoExtManager;

/**
 * Created by priyank on 10/25/2018.
 */

public class StarPrinterHelper {
    public static void print(final Context context, int selectedIndex, int mLanguage, Bitmap mBitmap, final PrintInterface printInterface) {
        byte[] commands;
        PrinterSettingManager settingManager = new PrinterSettingManager(context);
        PrinterSettings settings = settingManager.getPrinterSettings();
        StarIoExt.Emulation emulation = ModelCapability.getEmulation(settings.getModelIndex());
        int paperSize = settings.getPaperSize();
        ILocalizeReceipts localizeReceipts = ILocalizeReceipts.createLocalizeReceipts(mLanguage, paperSize);

        switch (selectedIndex) {
            default:
            case 1:
                commands = PrinterFunctions.createTextReceiptData(emulation, localizeReceipts, false);
                break;
            case 2:
                commands = PrinterFunctions.createTextReceiptData(emulation, localizeReceipts, true);
                break;
            case 3:
                commands = PrinterFunctions.createRasterReceiptData(emulation, localizeReceipts, context.getResources());
                break;
            case 4:
                commands = PrinterFunctions.createScaleRasterReceiptData(emulation, localizeReceipts, context.getResources(), paperSize, true);
                break;
            case 5:
                commands = PrinterFunctions.createScaleRasterReceiptData(emulation, localizeReceipts, context.getResources(), paperSize, false);
                break;
            case 6:
                commands = PrinterFunctions.createCouponData(emulation, localizeReceipts, context.getResources(), paperSize, ICommandBuilder.BitmapConverterRotation.Normal);
                break;
            case 7:
                commands = PrinterFunctions.createCouponData(emulation, localizeReceipts, context.getResources(), paperSize, ICommandBuilder.BitmapConverterRotation.Right90);
                break;
            case 8:
                if (mBitmap != null) {
                    commands = PrinterFunctions.createRasterData(emulation, mBitmap, paperSize, true);
                } else {
                    commands = new byte[0];
                }
                break;
        }

        Communication.sendCommands(context, commands, settings.getPortName(), settings.getPortSettings(), 10000, context, new Communication.SendCallback() {
            @Override
            public void onStatus(boolean result, Communication.Result communicateResult) {
                String msg;
                boolean status;
                switch (communicateResult) {
                    case Success:
                        msg = "Success!";
                        status = true;
                        break;
                    case ErrorOpenPort:
                        msg = "Fail to openPort";
                        status = false;
                        break;
                    case ErrorBeginCheckedBlock:
                        msg = "Printer is offline (beginCheckedBlock)";
                        status = false;
                        break;
                    case ErrorEndCheckedBlock:
                        msg = "Printer is offline (endCheckedBlock)";
                        status = false;
                        break;
                    case ErrorReadPort:
                        msg = "Read port error (readPort)";
                        status = false;
                        break;
                    case ErrorWritePort:
                        status = false;
                        msg = "Write port error (writePort)";
                        break;
                    default:
                        msg = "Unknown error";
                        status = false;
                        break;
                }
                if (printInterface != null)
                    printInterface.callback(status,msg);
//                Utils.ShowToastMessage(getActivity(), context,msg,Toast.LENGTH_SHORT).show();
            }
        });     // 10000mS!!!

    }


    public static void openCashDrawer(final Context context) {

        byte[] data;

        PrinterSettingManager settingManager = new PrinterSettingManager(context);
        PrinterSettings       settings       = settingManager.getPrinterSettings();
        StarIoExtManager mStarIoExtManager = new StarIoExtManager(StarIoExtManager.Type.Standard, settings.getPortName(), settings.getPortSettings(), 10000, context);     // 10000mS!!!
        mStarIoExtManager.setCashDrawerOpenActiveHigh(settings.getCashDrawerOpenActiveHigh());
        StarIoExt.Emulation emulation = ModelCapability.getEmulation(settings.getModelIndex());
        data = CashDrawerFunctions.createData(emulation, ICommandBuilder.PeripheralChannel.No1);


        Communication.sendCommands(context, data, settings.getPortName(), settings.getPortSettings(), 10000, context, new Communication.SendCallback() {
            @Override
            public void onStatus(boolean result, Communication.Result communicateResult) {
                String msg;
                switch (communicateResult) {
                    case Success:
                        msg = "Success!";
                        break;
                    case ErrorOpenPort:
                        msg = "Fail to openPort";
                        break;
                    case ErrorBeginCheckedBlock:
                        msg = "Printer is offline (beginCheckedBlock)";
                        break;
                    case ErrorEndCheckedBlock:
                        msg = "Printer is offline (endCheckedBlock)";
                        break;
                    case ErrorReadPort:
                        msg = "Read port error (readPort)";
                        break;
                    case ErrorWritePort:
                        msg = "Write port error (writePort)";
                        break;
                    default:
                        msg = "Unknown error";
                        break;
                }
                Log.e(StarPrinterHelper.class.getSimpleName(), "open drawer callbacl : " + msg);
//                if (printInterface != null)
//                    printInterface.callback(msg);
//                Utils.ShowToastMessage(getActivity(), context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
