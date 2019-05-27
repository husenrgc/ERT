package muhammadhusen.com.ert.api;

import java.util.Date;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RegisterAPI {


    @FormUrlEncoded
    @POST("register.php")
    Call<Value> registerUser(

            @Field("nama") String nama,
            @Field("ttl") String ttl,
            @Field("no_hp") String no_hp,
            @Field("alamat") String alamat,
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("useruploaDb.php")
    Call<Value> uploadimgdb(

            @Field("id_user") String id_user,
            @Field("caption") String caption,
            @Field("gambar") String gambar);

    @FormUrlEncoded
    @POST("direktori.php")
    Call<Value> dir(

            @Field("name") String name);

    @GET("read_produk.php")
    Call<Value> read();
    @GET("readAllimg.php")
    Call<Value> readimg();

    @FormUrlEncoded
    @POST("login.php")
    Call<Value> loginUser(@Field("username") String nm_user,
                          @Field("password") String pass);

    @GET("readberita.php")
    Call<Value> loadberita();


    @Multipart
    @POST("uploadimage.php")
    Call<Value> uploadimg(@Part MultipartBody.Part image,
                          @Part MultipartBody.Part  url);

    @FormUrlEncoded
    @POST("readimageprofile.php")
    Call<Value> readimageby(

            @Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("tulisberita.php")
    Call<Value> tulisnews(

            @Field("username") String username,
            @Field("nama_user") String nama_user,
            @Field("isiberita") String isiberita,
            @Field("judul") String judul,
            @Field("sumber")String sumber,
            @Field("tanggal")String tanggal);
}

