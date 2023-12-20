package com.example.fantomeapp;

import android.os.Build;
import android.provider.Settings;
import android.content.ContentResolver;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyInfo;
import android.security.keystore.KeyProperties;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

//cette classe contient toutes les fonctions necessaires aux tests de l'analyse -> va être utilisé dans ResAna.java
public class analyse {

    private static final String KEYSTORE_PROVIDER = "AndroidKeyStore";
    private static final String KEY_NAME = "your_key_name"; // Specify a key name

    public analyse() {
    }

    //version de l'OS Android
    public static String currentVersion(){
        double release=Double.parseDouble(Build.VERSION.RELEASE.replaceAll("(\\d+[.]\\d+)(.*)","$1"));
        String codeName="Unsupported";//below Jelly Bean
        if(release >= 4.1 && release < 4.4) codeName = "Jelly Bean";
        else if(release < 5)   codeName="Kit Kat";
        else if(release < 6)   codeName="Lollipop";
        else if(release < 7)   codeName="Marshmallow";
        else if(release < 8)   codeName="Nougat";
        else if(release < 9)   codeName="Oreo";
        else if(release < 10)  codeName="Pie";
        else if(release >= 10) codeName="Android "+((int)release);//since API 29 no more candy code names
        return codeName+" v"+release+", API Level: "+Build.VERSION.SDK_INT;
    }

    // version du kernel liunx
    // source : https://stackoverflow.com/questions/5234574/how-to-get-the-version-of-the-linux-kernel-using-android
    public static String readKernelVersion() {
        try {
            Process p = Runtime.getRuntime().exec("uname -a");
            InputStream is = null;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is),
                    1000);
            String line = br.readLine();
            br.close();
            return line;
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

    //est ce que le téléphone est chiffré ?
    public static String isPhoneEncrypted() {
        try {
            Process p = Runtime.getRuntime().exec("getprop ro.crypto.state");
            InputStream is = null;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is),
                    1000);
            String line = br.readLine();
            br.close();
            return line;
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

    //est ce que le téléphone est en mode développeur ?
     public static boolean isDevMode(ContentResolver contentResolver) {
        if (Settings.Global.getInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0) {
            return true;
        } else {
            return false;
        }
    }

    //est ce que adb est activé ?
    public static boolean isADB(ContentResolver contentResolver) {
        if (Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) != 0) {
            return true;
        } else {
            return false;
        }
    }

    //est ce que AVB (Android Verified Boot) est activé ?
    public static String isAVB() {
        try {
            Process p = Runtime.getRuntime().exec("getprop ro.boot.veritymode");
            InputStream is = null;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is),
                    1000);
            String line = br.readLine();
            br.close();
            return line;
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

    //Les fonctions suivantes visent a viser l'isolation de l'application.
    //L'interet est de ne pas donner de permissions particuliere, et vérifier l'accès aux fichiers.
    //Dans le fonctionnement d'Android : 1 UID + 1 application, donc il ne devrait pas y avoir de problème normalement
    public static String isolation1() {
        try {
            Process p = Runtime.getRuntime().exec("pwd");
            InputStream is = null;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is),
                    1000);
            String line = br.readLine();
            br.close();
            return line;
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

    public static String isolation2() {
        try {
            Process p = Runtime.getRuntime().exec("dumpsys package com.example.fantomeapp");
            InputStream is = null;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is),
                    1000);
            String line = br.readLine();
            br.close();
            return line;
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

    public static String isolation3() {
        try {
            Process p = Runtime.getRuntime().exec("ls");
            InputStream is = null;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is),
                    1000);
            String line = br.readLine();
            br.close();
            return line;
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

    public static String isolation_final(String isolation1, String isolation2, String isolation3) {

        // Définir le modèle de l'expression régulière
        String regex = "uid=(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        // Créer un objet Matcher avec la chaîne d'entrée
        Matcher matcher = pattern.matcher(isolation2);

        String uid;
        // Vérifier si l'expression régulière correspond à la chaîne d'entrée
        if (matcher.find()) {
            // Extraire le groupe correspondant à l'UID
            uid = matcher.group(1);
        } else {
            uid = "ERROR : UID NOT FOUND";
        }

        if ((isolation1.equals("/")) && (isolation3.contains("denied"))) {
            return "Isolation conforme [UID >= 10003 (" + uid + ")]";
        }
        else {
            return "PROBLEME D'ISOLATION -> uid : " + uid;
        }

    }



    public static String createKeyForTimeout() {
        try {
            KeyStore ks = KeyStore.getInstance(KEYSTORE_PROVIDER);
            ks.load(null, null); // null InputStream and null password for loading the default keystore

            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE_PROVIDER);

            KeyGenParameterSpec.Builder keyGenParameterSpecBuilder = new KeyGenParameterSpec.Builder(
                    KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .setRandomizedEncryptionRequired(false); // Adjust as needed

            boolean boxbox = true;
            // Utilisation de la réflexion pour vérifier la présence de la méthode setIsStrongBoxBacked
            if (Build.VERSION.SDK_INT >= 28) {
                try {
                    Class<?> keyGenParameterSpecClass = Class.forName("android.security.keystore.KeyGenParameterSpec");
                    keyGenParameterSpecClass.getMethod("setIsStrongBoxBacked", boolean.class);

                    // Si la méthode est présente, alors on peut l'utiliser
                    keyGenParameterSpecBuilder.setIsStrongBoxBacked(true);
                } catch (Exception e) {
                    // Handle the case where setIsStrongBoxBacked is not available
                    e.printStackTrace();
                    return "Pas de Strongbox";
                }
            }
            else{
                boxbox = false;
            }

            keyGenerator.init(keyGenParameterSpecBuilder.build());
            keyGenerator.generateKey();
            if(boxbox){
                return"StrongBox présente";
            }
            else{
                return "Pas de Strongbox";
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(analyse.class.getSimpleName(), "Exception creating key", e);
            return "Exception creating key: " + e.getMessage();
        }
    }


    public static String[] isInsideSecureHardware() {
        String[] values = new String[2];
        try {
            values[0] = createKeyForTimeout();
        } catch (Exception e) {
            Log.e(analyse.class.getSimpleName(), "Exception creating key", e);
            values[1] = "Exception creating key: " + e.getMessage();
            return values;
        }

        try {
            KeyStore ks = KeyStore.getInstance(KEYSTORE_PROVIDER);
            ks.load(null, null);

            SecretKey key = (SecretKey) ks.getKey(KEY_NAME, null);
            KeyInfo info = (KeyInfo) SecretKeyFactory.getInstance(key.getAlgorithm(), KEYSTORE_PROVIDER)
                    .getKeySpec(key, KeyInfo.class);

            if (info.isInsideSecureHardware()) {
                values[1] = "Sécurité hardware";
                return values;
            } else {
                values[1] = "Sécurité software";
                return values;
            }
        } catch (Exception e) {
            values[1]="Error getting key info: " + e.getMessage();
            return values;
        }
    }
    
    public static String LastSecurityPatch() {
        try {
            Process p = Runtime.getRuntime().exec("getprop ro.build.version.security_patch");
            InputStream is = null;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is),
                    1000);
            String line = br.readLine();
            br.close();
            return line;
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

}

