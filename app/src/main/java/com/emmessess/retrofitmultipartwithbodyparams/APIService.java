package com.emmessess.retrofitmultipartwithbodyparams;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Sadiq on 05/25/18.
 */

public interface APIService {
    @Multipart
    @POST("upload_assignments.php")
    Call<AssignmentUpload> uploadMultipleFiles(
            @Part("user_id") RequestBody user_id,
            @Part("session_id") RequestBody session_id,
            @Part("depart_id") RequestBody depart_id,
            @Part("semester") RequestBody semester,
            @Part("start_date") RequestBody start_date,
            @Part("end_date") RequestBody end_date,
            @Part("description") RequestBody description,
            @Part("title") RequestBody title,
            @Part MultipartBody.Part files);

}
