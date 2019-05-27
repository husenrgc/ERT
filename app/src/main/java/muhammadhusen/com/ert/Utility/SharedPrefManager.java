package muhammadhusen.com.ert.Utility;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String ERT = "ERT";

    public static final String SP_NAMA = "spNama";


    public static final String SP_SUDAH_LOGIN = "spSudahLogin";
    public static final String SP_TTL = "spTTL";
    public static final String SP_ALAMAT = "spALAMAT";
    public static final String SP_NO_HP = "SPNOHP";
    public static final String SP_LEVEL = "SPLEVEL";
    public static final String SP_FOTO_PROFIL = "spFOTO_PROFIL";
    public static final String SP_USERNAME="spUSERNAME";
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(ERT, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public  String getSpTtl(){return sp.getString(SP_TTL,"");}
    public  String getSpLevel(){return sp.getString(SP_LEVEL,"");}
    public String getSpAlamat(){return sp.getString(SP_ALAMAT,"");}
    public  String getSpNoHp(){return sp.getString(SP_NO_HP,"");}
    public  String getSpFotoProfil(){return  sp.getString(SP_FOTO_PROFIL,"");}
public  String getSpUsername(){return  sp.getString(SP_USERNAME,"");}
    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }




    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
