package com.jd.appstore.web.util.cp;

import android.content.res.AXmlResourceParser;
import android.util.TypedValue;
import com.jd.appstore.domain.json.ApkMessJSON;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;


/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-13
 * Time: 下午2:56
 * To change this template use File | Settings | File Templates.
 */
public class AnalysisApk {

    public static ApkMessJSON unZip(String apkUrl) throws IOException {
        //[0]:版本号;[1]包名
//        String[] st = new String[3];
        ApkMessJSON apkMessJSON = new ApkMessJSON();
        byte b[] = new byte[1024];
        int length;
        ZipFile zipFile = null;
        File apk = null;
        try {
            apk = new File(apkUrl);
            zipFile = new ZipFile(apk);
            Enumeration enumeration = zipFile.getEntries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements()) {
                zipEntry = (ZipEntry) enumeration.nextElement();
                if (zipEntry.isDirectory()) {

                } else {
                    if ("AndroidManifest.xml".equals(zipEntry.getName())) {
                        try {
                            AXmlResourceParser parser = new AXmlResourceParser();
                            parser.open(zipFile.getInputStream(zipEntry));
                            apkMessJSON.setSize(apk.length() + "");
                            while (true) {
                                int type = parser.next();
                                if (type == XmlPullParser.END_DOCUMENT) {
                                    break;
                                }
                                switch (type) {
                                    case XmlPullParser.START_TAG: {
                                        for (int i = 0; i != parser.getAttributeCount(); ++i) {
                                            if ("versionName".equals(parser.getAttributeName(i))) {
                                                apkMessJSON.setVersion(getAttributeValue(parser, i));
                                            } else if ("package".equals(parser.getAttributeName(i))) {
                                                apkMessJSON.setPackageName(getAttributeValue(parser, i));
                                            } else if ("minSdkVersion".equals(parser.getAttributeName(i))) {
                                                if (getAttributeValue(parser, i) == null) {
                                                    apkMessJSON.setMinSdkVersion("8");
                                                } else {
                                                    apkMessJSON.setMinSdkVersion(getAttributeValue(parser, i));
                                                }
                                                apkMessJSON.setMinSdkVersion(getAttributeValue(parser, i));
                                            } else if ("versionCode".equals(parser.getAttributeName(i))) {
                                                if (getAttributeValue(parser, i) == null) {
                                                    apkMessJSON.setVersionCode("1");
                                                } else {
                                                    apkMessJSON.setVersionCode(getAttributeValue(parser, i));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            apk = null;
            zipFile.close();
        }
        return apkMessJSON;
    }

    private static String getAttributeValue(AXmlResourceParser parser, int index) {
        int type = parser.getAttributeValueType(index);
        int data = parser.getAttributeValueData(index);
        if (type == TypedValue.TYPE_STRING) {
            return parser.getAttributeValue(index);
        }
        if (type == TypedValue.TYPE_ATTRIBUTE) {
            return String.format("?%sX", getPackage(data), data);
        }
        if (type == TypedValue.TYPE_REFERENCE) {
            return String.format("@%sX", getPackage(data), data);
        }
        if (type == TypedValue.TYPE_FLOAT) {
            return String.valueOf(Float.intBitsToFloat(data));
        }
        if (type == TypedValue.TYPE_INT_HEX) {
            return String.format("0xX", data);
        }
        if (type == TypedValue.TYPE_INT_BOOLEAN) {
            return data != 0 ? "true" : "false";
        }
        if (type == TypedValue.TYPE_DIMENSION) {
            return Float.toString(complexToFloat(data)) +
                    DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type == TypedValue.TYPE_FRACTION) {
            return Float.toString(complexToFloat(data)) +
                    FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type >= TypedValue.TYPE_FIRST_COLOR_INT && type <= TypedValue.TYPE_LAST_COLOR_INT) {
            return String.format("#X", data);
        }
        if (type >= TypedValue.TYPE_FIRST_INT && type <= TypedValue.TYPE_LAST_INT) {
            return String.valueOf(data);
        }
        return String.format("<0x%X, type 0xX>", data, type);
    }

    private static String getPackage(int id) {
        if (id >>> 24 == 1) {
            return "android:";
        }
        return "";
    }

    public static float complexToFloat(int complex) {
        return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];
    }

    private static final float RADIX_MULTS[] = {
            0.00390625F, 3.051758E-005F, 1.192093E-007F, 4.656613E-010F
    };
    private static final String DIMENSION_UNITS[] = {
            "px", "dip", "sp", "pt", "in", "mm", "", ""
    };
    private static final String FRACTION_UNITS[] = {
            "%", "%p", "", "", "", "", "", ""
    };
}
