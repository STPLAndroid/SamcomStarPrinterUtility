package com.samcom.starprinterutility.printerUtils.localizerreceipt;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import com.samcom.starprinterutility.printerUtils.PrinterSettingConstant;
import com.starmicronics.starioextension.ICommandBuilder;
import com.starmicronics.starioextension.ICommandBuilder.AlignmentPosition;
import com.starmicronics.starioextension.ICommandBuilder.BarcodeSymbology;
import com.starmicronics.starioextension.ICommandBuilder.BarcodeWidth;
import com.starmicronics.starioextension.ICommandBuilder.CodePageType;
import com.starmicronics.starioextension.ICommandBuilder.InternationalType;
import com.starmicronics.starioextension.StarIoExt.CharacterCode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class EnglishReceiptsImpl extends ILocalizeReceipts {

    private final ArrayList<String> data_param = new ArrayList<>();
    private LinearLayout llSample;
    private boolean is_Gift_recipt;

    public EnglishReceiptsImpl() {
        mLanguageCode = "En";
        mCharacterCode = CharacterCode.Standard;
    }

    @Override
    public void append2inchTextReceiptData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = StandardCharsets.UTF_8;

            builder.appendCodePage(CodePageType.UTF8);
        } else {
            encoding = StandardCharsets.US_ASCII;

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendAlignment(AlignmentPosition.Center);

        builder.append((
                "Star Clothing Boutique\n" +
                        "123 Star Road\n" +
                        "City, State 12345\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Left);

        builder.append((
                "Date:MM/DD/YYYY    Time:HH:MM PM\n" +
                        "--------------------------------\n" +
                        "\n").getBytes(encoding));


        builder.append(("Total     ").getBytes(encoding));

        builder.appendMultiple(("   $156.95\n").getBytes(encoding), 2, 2);

        builder.append((
                "--------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "\n").getBytes(encoding));

        builder.appendInvert(("Refunds and Exchanges\n").getBytes(encoding));

        builder.append(("Within ").getBytes(encoding));

        builder.appendUnderLine(("30 days").getBytes(encoding));

        builder.append((" with receipt\n").getBytes(encoding));

        builder.append((
                "And tags attached\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Center);

//        builder.appendBarcode(("{BStar.").getBytes(Charset.forName("US-ASCII")), BarcodeSymbology.Code128, BarcodeWidth.Mode2, 40, true);
    }

    @Override
    public void append3inchTextReceiptData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;
        if (utf8) {
            encoding = StandardCharsets.UTF_8;
            builder.appendCodePage(CodePageType.UTF8);
        } else {
            encoding = StandardCharsets.US_ASCII;
            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendAlignment(AlignmentPosition.Center);

        builder.append((
                "Date:MM/DD/YYYY                    Time:HH:MM PM\n" +
                        "------------------------------------------------\n" +
                        "\n").getBytes(encoding));

        builder.appendEmphasis(("SALE\n").getBytes(encoding));

        builder.append((
                "SKU               Description              Total\n" +
                        "300678566         PLAIN T-SHIRT            10.99\n" +
                        "300692003         BLACK DENIM              29.99\n" +
                        "300651148         BLUE DENIM               29.99\n" +
                        "300642980         STRIPED DRESS            49.99\n" +
                        "300638471         BLACK BOOTS              35.99\n" +
                        "\n" +
                        "Subtotal                                  156.95\n" +
                        "Tax                                         0.00\n" +
                        "------------------------------------------------\n").getBytes(encoding));

        builder.append(("Total                       ").getBytes(encoding));

        builder.appendMultiple(("   $156.95\n").getBytes(encoding), 2, 2);

        builder.append((
                "------------------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "\n").getBytes(encoding));

        builder.appendInvert(("Refunds and Exchanges\n").getBytes(encoding));

        builder.append(("Within ").getBytes(encoding));

        builder.appendUnderLine(("30 days").getBytes(encoding));

        builder.append((" with receipt\n").getBytes(encoding));

        builder.append((
                "And tags attached\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Center);

        builder.appendBarcode(("{BStar.").getBytes(StandardCharsets.US_ASCII), BarcodeSymbology.Code128, BarcodeWidth.Mode2, 40, true);
    }







    @Override
    public void append4inchTextReceiptData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = StandardCharsets.UTF_8;

            builder.appendCodePage(CodePageType.UTF8);
        } else {
            encoding = StandardCharsets.US_ASCII;

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendAlignment(AlignmentPosition.Center);


        builder.appendAlignment(AlignmentPosition.Left);

        builder.append((
                "Date:MM/DD/YYYY                                         Time:HH:MM PM\n" +
                        "---------------------------------------------------------------------\n" +
                        "\n").getBytes(encoding));

        builder.appendEmphasis(("SALE\n").getBytes(encoding));

        builder.append((
                "SKU                        Description                          Total\n" +
                        "300678566                  PLAIN T-SHIRT                        10.99\n" +
                        "300692003                  BLACK DENIM                          29.99\n" +
                        "300651148                  BLUE DENIM                           29.99\n" +
                        "300642980                  STRIPED DRESS                        49.99\n" +
                        "300638471                  BLACK BOOTS                          35.99\n" +
                        "\n" +
                        "Subtotal                                                       156.95\n" +
                        "Tax                                                              0.00\n" +
                        "---------------------------------------------------------------------\n").getBytes(encoding));

        builder.append(("Total                                            ").getBytes(encoding));

        builder.appendMultiple(("   $156.95\n").getBytes(encoding), 2, 2);

        builder.append((
                "---------------------------------------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "\n").getBytes(encoding));

        builder.appendInvert(("Refunds and Exchanges\n").getBytes(encoding));

        builder.append(("Within ").getBytes(encoding));

        builder.appendUnderLine(("30 days").getBytes(encoding));

        builder.append((" with receipt\n").getBytes(encoding));

        builder.append((
                "And tags attached\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Center);

        builder.appendBarcode(("{BStar.").getBytes(StandardCharsets.US_ASCII), BarcodeSymbology.Code128, BarcodeWidth.Mode2, 40, true);
    }

    @Override
    public Bitmap create2inchRasterReceiptImage() {
        String textToPrint =
                "   Star ";

        int textSize = 22;
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);

        return createBitmapFromText(textToPrint, textSize, PrinterSettingConstant.PAPER_SIZE_TWO_INCH, typeface);
    }

    @Override
    public Bitmap create3inchRasterReceiptImage() {
        //linCreate();
        String textToPrint =
                "TEST COPY\n\n\n" +
                        "Powered by Shopfast.com\n" +
                        "Copyright © 2018 ShopFast.\n" +
                        "a product of OMNISOFT Inc. \n" +
                        "All rights reserved.\n";


        int textSize = 18;
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);
        return createBitmapFromText(textToPrint, textSize, PrinterSettingConstant.PAPER_SIZE_FOUR_INCH, typeface);
    }

    @Override
    public Bitmap create4inchRasterReceiptImage() {
        String textToPrint =
                "                   Star Clothing Boutique\n" +
                        "                        123 Star Road\n" +
                        "                      City, State 12345\n" +
                        "\n" +
                        "Date:MM/DD/YYYY                             Time:HH:MM PM\n" +
                        "---------------------------------------------------------\n" +
                        "SALE\n" +
                        "SKU                     Description                 Total\n" +
                        "300678566               PLAIN T-SHIRT               10.99\n" +
                        "300692003               BLACK DENIM                 29.99\n" +
                        "300651148               BLUE DENIM                  29.99\n" +
                        "300642980               STRIPED DRESS               49.99\n" +
                        "300638471               BLACK BOOTS                 35.99\n" +
                        "\n" +
                        "Subtotal                                           156.95\n" +
                        "Tax                                                  0.00\n" +
                        "---------------------------------------------------------\n" +
                        "Total                                             $156.95\n" +
                        "---------------------------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "Refunds and Exchanges\n" +
                        "Within 30 days with receipt\n" +
                        "And tags attached\n";

        int textSize = 23;
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);

        return createBitmapFromText(textToPrint, textSize, PrinterSettingConstant.PAPER_SIZE_FOUR_INCH, typeface);
    }

    @Override
    public Bitmap createCouponImage(Resources resources) {
        return BitmapFactory.decodeResource(resources, androidx.constraintlayout.widget.R.drawable.abc_ic_star_black_48dp);
    }

    @Override
    public Bitmap createEscPos3inchRasterReceiptImage() {
        String textToPrint =
                "\n" +
                        "      Star Clothing Boutique\n" +
                        "           123 Star Road\n" +
                        "         City, State 12345\n" +
                        "\n" +
                        "Date:MM/DD/YYYY       Time:HH:MM PM\n" +
                        "-----------------------------------\n" +
                        "SALE\n" +
                        "SKU          Description      Total\n" +
                        "300678566    PLAIN T-SHIRT    10.99\n" +
                        "300692003    BLACK DENIM      29.99\n" +
                        "300651148    BLUE DENIM       29.99\n" +
                        "300642980    STRIPED DRESS    49.99\n" +
                        "30063847     BLACK BOOTS      35.99\n" +
                        "\n" +
                        "Subtotal                     156.95\n" +
                        "Tax                            0.00\n" +
                        "-----------------------------------\n" +
                        "Total                       $156.95\n" +
                        "-----------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "Refunds and Exchanges\n" +
                        "Within 30 days with receipt\n" +
                        "And tags attached\n";

        int textSize = 24;
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);

        return createBitmapFromText(textToPrint, textSize, PrinterSettingConstant.PAPER_SIZE_ESCPOS_THREE_INCH, typeface);
    }

    @Override
    public void appendEscPos3inchTextReceiptData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = StandardCharsets.UTF_8;

            builder.appendCodePage(CodePageType.UTF8);
        } else {
            encoding = StandardCharsets.US_ASCII;

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendAlignment(AlignmentPosition.Center);

        builder.append((
                "\n" +
                        "Star Clothing Boutique\n" +
                        "123 Star Road\n" +
                        "City, State 12345\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Left);

        builder.append((
                "Date:MM/DD/YYYY              Time:HH:MM PM\n" +
                        "------------------------------------------\n" +
                        "\n").getBytes(encoding));

        builder.appendEmphasis(("SALE \n").getBytes(encoding));

        builder.append((
                "SKU            Description           Total\n" +
                        "300678566      PLAIN T-SHIRT         10.99\n" +
                        "300692003      BLACK DENIM           29.99\n" +
                        "300651148      BLUE DENIM            29.99\n" +
                        "300642980      STRIPED DRESS         49.99\n" +
                        "300638471      BLACK BOOTS           35.99\n" +
                        "\n" +
                        "Subtotal                            156.95\n" +
                        "Tax                                   0.00\n" +
                        "------------------------------------------\n").getBytes(encoding));

        builder.append(("Total                 ").getBytes(encoding));

        builder.appendMultiple(("   $156.95\n").getBytes(encoding), 2, 2);

        builder.append((
                "------------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "\n").getBytes(encoding));

        builder.appendInvert(("Refunds and Exchanges\n").getBytes(encoding));

        builder.append(("Within ").getBytes(encoding));

        builder.appendUnderLine(("30 days").getBytes(encoding));

        builder.append((" with receipt\n").getBytes(encoding));

        builder.append((
                "And tags attached\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Center);

        builder.appendBarcode(("{BStar.").getBytes(StandardCharsets.US_ASCII), BarcodeSymbology.Code128, BarcodeWidth.Mode2, 40, true);
    }

    @Override
    public void appendDotImpact3inchTextReceiptData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = StandardCharsets.UTF_8;

            builder.appendCodePage(CodePageType.UTF8);
        } else {
            encoding = StandardCharsets.US_ASCII;

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendAlignment(AlignmentPosition.Center);

        builder.append((
                "Star Clothing Boutique\n" +
                        "123 Star Road\n" +
                        "City, State 12345\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Left);

        builder.append((
                "Date:MM/DD/YYYY              Time:HH:MM PM\n" +
                        "------------------------------------------\n" +
                        "\n").getBytes(encoding));

        builder.appendEmphasis(("SALE \n").getBytes(encoding));

        builder.append((
                "SKU             Description          Total\n" +
                        "300678566       PLAIN T-SHIRT        10.99\n" +
                        "300692003       BLACK DENIM          29.99\n" +
                        "300651148       BLUE DENIM           29.99\n" +
                        "300642980       STRIPED DRESS        49.99\n" +
                        "300638471       BLACK BOOTS          35.99\n" +
                        "\n" +
                        "Subtotal                            156.95\n" +
                        "Tax                                   0.00\n" +
                        "------------------------------------------\n" +
                        "Total                              $156.95\n" +
                        "------------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "\n").getBytes(encoding));

        builder.appendInvert(("Refunds and Exchanges\n").getBytes(encoding));

        builder.append(("Within ").getBytes(encoding));

        builder.appendUnderLine(("30 days").getBytes(encoding));

        builder.append((" with receipt\n").getBytes(encoding));
    }

    @Override
    public void appendTextLabelData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = StandardCharsets.UTF_8;

            builder.appendCodePage(CodePageType.UTF8);
        } else {
            encoding = StandardCharsets.US_ASCII;

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendUnitFeed(20 * 2);

        builder.appendMultipleHeight(2);

        builder.append(("Star Micronics America, Inc.").getBytes(encoding));

        builder.appendUnitFeed(64);

        builder.append(("65 Clyde Road Suite G").getBytes(encoding));

        builder.appendUnitFeed(64);

        builder.append(("Somerset, NJ 08873-3485 U.S.A").getBytes(encoding));

        builder.appendUnitFeed(64);

        builder.appendMultipleHeight(1);
    }

    @Override
    public String createPasteTextLabelString() {
        return "Star Micronics America, Inc.\n" +
                "65 Clyde Road Suite G\n" +
                "Somerset, NJ 08873-3485 U.S.A";
    }

    @Override
    public void appendPasteTextLabelData(ICommandBuilder builder, String pasteText, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = StandardCharsets.UTF_8;

            builder.appendCodePage(CodePageType.UTF8);
        } else {
            encoding = StandardCharsets.US_ASCII;

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.append(pasteText.getBytes(encoding));
    }

    public class MyTask extends AsyncTask<Void, Void, Bitmap> {
        View v;
        GetBitmap mBitmapListen;

        public MyTask(View vie) {
            v = vie;
            this.mBitmapListen = mBitmapListen;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            v.setDrawingCacheEnabled(true);
            v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            v.buildDrawingCache(true);
            Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
            v.setDrawingCacheEnabled(false);
            return b;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void setOnBitmapListener(GetBitmap listener) {
            this.mBitmapListen = listener;
        }
    }
}
